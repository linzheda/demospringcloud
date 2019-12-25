--加密解密 要在jdk下 替换 jce的两个jar

--加密 curl localhost:8767/encrypt -d 要加密的内容
--解密 curl localhost:8767/decrypt -d 要解密的内容


bus刷新 http://localhost:8767/actuator/bus-refresh   post请求
bus 事件跟踪 http://localhost:8767/actuator/httptrace  get请求