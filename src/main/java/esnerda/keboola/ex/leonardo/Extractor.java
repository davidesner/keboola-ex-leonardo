package esnerda.keboola.ex.leonardo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import esnerda.keboola.components.KBCException;
import esnerda.keboola.components.configuration.handler.ConfigHandlerBuilder;
import esnerda.keboola.components.configuration.handler.KBCConfigurationEnvHandler;
import esnerda.keboola.components.configuration.tableconfig.ManifestFile;
import esnerda.keboola.components.logging.DefaultLogger;
import esnerda.keboola.components.logging.KBCLogger;
import esnerda.keboola.components.result.IResultWriter;
import esnerda.keboola.components.result.ResultFileMetadata;
import esnerda.keboola.components.result.impl.DefaultBeanResultWriter;
import esnerda.keboola.ex.leonardo.api.LeonardoWs;
import esnerda.keboola.ex.leonardo.api.entity.ImageItem;
import esnerda.keboola.ex.leonardo.api.filters.ErrorResponseFilter.RatelimitExceededException;
import esnerda.keboola.ex.leonardo.config.LeonardoConfigParameters;
import esnerda.keboola.ex.leonardo.config.LeonardoLastState;
import esnerda.keboola.ex.leonardo.result.impl.ImageItemWriter;
import esnerda.keboola.ex.leonardo.result.wrapper.PropertyEntityWrapper;

/**
 * Hello world!
 *
 */
public class Extractor {
	private static final String KEY_ENCODINGS = "encodings";
	private static final long TIMEOUT = 9900000L; //3 hrs

	private static KBCConfigurationEnvHandler handler;
	private static LeonardoConfigParameters config;
	private static LeonardoWs leoWs;
	private static KBCLogger log;

	private static long startTime;
	

	/* writers */
	private static IResultWriter<PropertyEntityWrapper> propResultWriter;
	private static IResultWriter<ImageItem> propImagesWriter;

	public static void main(String[] args) {
		startTimer();

		log = new DefaultLogger(Extractor.class);

		File properiesFile = initEnv(args);
		log.info("Initializing environment...");
		initWriters();
		Optional<LeonardoLastState> lastState = null;
		try {
			lastState = Optional.ofNullable((LeonardoLastState) handler.getStateFile());
		} catch (KBCException e) {
			handleException(e);
		}
		List<String> propertyIds = parseInputFile(properiesFile);
		List<String> idsToProcess = removeLastTimeUpdated(propertyIds,
				lastState.map(LeonardoLastState::getProcessedIds).orElse(Collections.EMPTY_LIST));
		if (idsToProcess.isEmpty()) {
			log.info("All properties from the last run processed. Starting from begining...");
			idsToProcess = propertyIds;
		}

		log.info("Setting up the client...");		
		Set<String> processedIds = new HashSet<>();
		log.info("Retrieving data...");	
		for (String propId : idsToProcess) {
			if (isTimedOut()) {
				log.warning("Extraction timed out, remaing results will be collected on the next run...", null);
				break;
			}
			try {
				if (config.getGetEntInfo()) {
					propResultWriter.writeResult(new PropertyEntityWrapper((leoWs.getProperty(propId))));					
				}
				propImagesWriter.writeAllResults(leoWs.getPropertyImages(propId, null, KEY_ENCODINGS));

				processedIds.add(propId);				
			} catch (RatelimitExceededException e) {
				log.warning("Extraction timed out, remaing results will be collected on the next run...", null);
				break;
			} catch (Exception e) {
				log.warning("Failed to retrieve property info. Poperty id: " + propId + " Cause: " + e.getMessage(), e);
			} 
		}

		// collect results
		List<ResultFileMetadata> results = collectResults();

		// remove proccesed ids
		idsToProcess.removeAll(processedIds);
		LeonardoLastState currState = new LeonardoLastState();
		if (!processedIds.isEmpty()) {
			currState.setProcessedIds(new ArrayList<>(processedIds));
		}
		finalize(results, currState);

	}

	private static final List<ResultFileMetadata> collectResults() {
		List<ResultFileMetadata> results = new ArrayList<>();
		try {
			results.addAll(propResultWriter.closeAndRetrieveMetadata());
			results.addAll(propImagesWriter.closeAndRetrieveMetadata());
		} catch (Exception e) {
			handleException(new KBCException("Failed to write results.", 2, e));
		}
		return results;

	}

	private static List<String> removeLastTimeUpdated(List<String> inputIds, List<String> processedIds) {
		List<String> result = inputIds.stream().filter(t -> !processedIds.contains(t)).collect(Collectors.toList());
		return result;
	}

	private static List<String> parseInputFile(File input) {
		List<String> propertyIds = new ArrayList<>();

		try (BufferedReader reader = new BufferedReader(new FileReader(input));
				CSVReader csvreader = new CSVReader(reader, CSVWriter.DEFAULT_SEPARATOR,
						CSVWriter.DEFAULT_QUOTE_CHARACTER);) {
			String[] line = csvreader.readNext();// skip the header line
			if (line.length > 1) {
				handleException(new KBCException("", "Input file has unexpected number of columns! Must be exactly one",
						null, 1));
			}
			while ((line = csvreader.readNext()) != null) {
				propertyIds.add(line[0]);
			}
		} catch (Exception e) {
			handleException(new KBCException(null, "Failed to procedd input file! " + e.getMessage(), null, 1));
		}
		return propertyIds;
	}

	private static File initEnv(String[] args) {
		handler = initHandler(args, log);
		config = (LeonardoConfigParameters) handler.getParameters();
		try {
			leoWs = new LeonardoWs(config.getApikey(), config.getEndpointUrl(), log.getLogger());
			return handler.getInputTables().get(0).getCsvTable();
		} catch (Exception e) {
			handleException(new KBCException("Failed to init web service!", e.getMessage(), e, 1));
		}
		return null;
	}

	private static KBCConfigurationEnvHandler initHandler(String[] args, KBCLogger log) {
		KBCConfigurationEnvHandler handler = null;
		try {
			handler = ConfigHandlerBuilder.create(LeonardoConfigParameters.class).hasInputTables(true)
					.setStateFileType(LeonardoLastState.class).build();
			// process the configuration
			handler.processConfigFile(args);
		} catch (KBCException ex) {
			log.log(ex);
			System.exit(1);
		}
		return handler;
	}

	private static void initWriters() {
		try {
			propResultWriter = new DefaultBeanResultWriter<>("property.csv", new String[] { "propertyId" });
			propResultWriter.initWriter(handler.getOutputTablesPath(), PropertyEntityWrapper.class);

			propImagesWriter = new ImageItemWriter();
			propImagesWriter.initWriter(handler.getOutputTablesPath(), null);

		} catch (Exception e) {
			handleException(new KBCException("Failed to init writer!", e.getMessage(), e, 1));
		}
	}

	private static void handleException(KBCException ex) {
		log.log(ex);
		if (ex.getSeverity() > 0) {
			System.exit(ex.getSeverity()-1);
		}
	}

	private static void saveResults(List<ResultFileMetadata> results) throws KBCException {
		for (ResultFileMetadata res : results) {
			handler.writeManifestFile(generateManifestFile(res));
		}
	}

	private static void finalize(List<ResultFileMetadata> results, LeonardoLastState thisState) {
		try {
			saveResults(results);

			handler.writeStateFile(thisState);
		} catch (KBCException e) {
			handleException(e);
		}
	}

	private static ManifestFile generateManifestFile(ResultFileMetadata result) throws KBCException {
		return ManifestFile.Builder.buildDefaultFromResult(result, null, config.getIncremental()).build();
	}

	/* -- time counter methods -- */
	private static void startTimer() {
		startTime = System.currentTimeMillis();
	}

	private static boolean isTimedOut() {
		long elapsed = System.currentTimeMillis() - startTime;
		return elapsed >= TIMEOUT;
	}
}
