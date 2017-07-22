package esnerda.keboola.ex.leonardo.result.impl;

import java.util.ArrayList;
import java.util.List;

import esnerda.keboola.components.result.IResultWriter;
import esnerda.keboola.components.result.ResultFileMetadata;
import esnerda.keboola.components.result.impl.DefaultBeanResultWriter;
import esnerda.keboola.ex.leonardo.api.entity.Encodings;
import esnerda.keboola.ex.leonardo.api.entity.ImageItem;
import esnerda.keboola.ex.leonardo.result.wrapper.EncodingWrapper;
import esnerda.keboola.ex.leonardo.result.wrapper.ImageItemWrapper;
import esnerda.keboola.ex.leonardo.result.wrapper.ImageMetadataWrapper;

/**
 * @author David Esner
 */
public class ImageItemWriter implements IResultWriter<ImageItem>{
	
	
	private IResultWriter<EncodingWrapper> encodingWriter;
	private IResultWriter<ImageItemWrapper> imagesgWriter;
	private IResultWriter<ImageMetadataWrapper> imageMetadataWriter;

	
	@Override
	public List<ResultFileMetadata> closeAndRetrieveMetadata() throws Exception{
		List<ResultFileMetadata> results = new ArrayList<>();
		//collect all results
		results.addAll(imagesgWriter.closeAndRetrieveMetadata());
		results.addAll(encodingWriter.closeAndRetrieveMetadata());
		results.addAll(imageMetadataWriter.closeAndRetrieveMetadata());
		return results;
	}

	@Override
	public void initWriter(String path, Class<ImageItem> clazz) throws Exception {
		//init encoding writer
		encodingWriter = new DefaultBeanResultWriter<EncodingWrapper>("imageEncodings.csv", new String[]{"imageId", "encoding"});
		encodingWriter.initWriter(path, EncodingWrapper.class);
		//init imgs writer
		imagesgWriter = new DefaultBeanResultWriter<ImageItemWrapper>("images.csv",  new String[]{"id"});
		imagesgWriter.initWriter(path, ImageItemWrapper.class);				
		//init imgs writer
		imageMetadataWriter = new DefaultBeanResultWriter<ImageMetadataWrapper>("imageMetadata.csv",  new String[]{"imageId", "languageCode"});
		imageMetadataWriter.initWriter(path, ImageMetadataWrapper.class);				
	}

	@Override
	public void writeResult(ImageItem obj) throws Exception {
		if (obj == null) {
			return;
		}
		int imgId = obj.getId();
		imagesgWriter.writeResult(new ImageItemWrapper(obj));
		Encodings encs = obj.getEncodings();
		if (encs != null) {
			encodingWriter.writeAllResults(EncodingWrapper.Builder.build(imgId, encs.getItems()));
		}
		imageMetadataWriter.writeAllResults(ImageMetadataWrapper.Builder.build(obj.getId(), obj.getMetadata()));

	}

	@Override
	public void writeAllResults(List<ImageItem> objs) throws Exception {
		if (objs == null) {
			return;
		}
		for (ImageItem o : objs) {
			writeResult(o);
		}
	}

	@Override
	public List<ResultFileMetadata> writeAndRetrieveResuts(List<ImageItem> objs) throws Exception {
		writeAllResults(objs);
		return closeAndRetrieveMetadata();
	}

	


}
