# redisconnect-splunk-hec-source-demo
Sample client (source) to send log messages (Java logging using logback) to Splunk HTTP Event Collector (HEC)

## Download

Download the [latest release](https://github.com/redis-field-engineering/redisconnect-splunk-hec-source-demo/releases) and un-tar (e.g. tar -xvf redisconnect-splunk-hec-source-1.0-SNAPSHOT.tar.gz) the redisconnect-splunk-hec-source-1.0-SNAPSHOT.tar.gz archive.

## Usage

```bash
java -jar \
-Dlogback.configurationFile=config/logback.xml \
-Dsplunk.url=<HEC_ENDPOINT> \
-Dsplunk.token=<HEC_TOKEN> \
-Dsplunk.index=<HEC_INDEX_NAME> \
-Dsplunk.type=raw \
-Diter=1 \
redisconnect-splunk-hec-source-1.0-SNAPSHOT.jar
```