关于 Zipkin 的服务端，在使用 Spring Boot 2.x 版本后，官方就不推荐自行定制编译了，反而是直接提供了编译好的 jar 包来给我们使用
运行命令: java -jar zipkin-server-2.12.9-exec.jar
访问路径: http://localhost:9411
具体参考 github:https://github.com/openzipkin/zipkin    ps:版本问题试了很久 才在github上找到原因
zipkin的存储方式:
内存（默认）
MySQL（数据量大时，查询较为缓慢，不建议使用）
Elasticsearch (ps:建议采用这个 可集群搭建)  下载地址:https://www.elastic.co/cn/downloads/past-releases/
Cassandra（Twitter官方使用Cassandra作为Zipkin Server的存储，但国内大规模用Cassandra的公司较少，Cassandra相关文档也不多）

采用Elasticsearch 存储时  (Elasticsearch 版本最好为5.x  6以上的会报错) 启动jar的命令
java  -jar zipkin-server-2.12.9-exec.jar   --STORAGE_TYPE=elasticsearch --ES_HOSTS=localhost:9200
java -DSTORAGE_TYPE=elasticsearch -DES_HOSTS=http://localhost:9200 -jar zipkin-server-2.12.9-exec.jar
