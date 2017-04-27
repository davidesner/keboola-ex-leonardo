FROM maven:3-jdk-8
MAINTAINER David Esner <esnerda@gmail.com>

ENV APP_VERSION 1.1.0
 WORKDIR /home
RUN git clone https://github.com/davidesner/keboola-ex-leonardo.git ./  
RUN mvn -q install

ENTRYPOINT java -Xmx512m -Xms512m -jar target/keboola.ex.leonardo-1.0.1-jar-with-dependencies.jar /data  