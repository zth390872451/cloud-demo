 Spring-Cloud—Book书籍：https://git.oschina.net/itmuch/spring-cloud-book/commit/11c1e31c36fbacc3bf56de7262acccd8d2165d7b
Spring Cloud为开发者提供了在分布式系统(如配置管理、服务发现、断路器、智能路由、微代理、控制总线、一次性Token、全局锁、决策竞选、分布式会话和集群状态)操作的开发工具。使用SpringCloud开发者可以快速实现上述这些模式。

 服务注册中心(Eureka的服务器端):
    应用程序名称：eureka-server
     应用名称：eureka-server
    端口：1000

 服务客户端：
     应用程序名称：eureka-client-node-one、eureka-client-node-two、eureka-client-node-three
     服务名称：eureka-client-node (这三个应用提供同一种服务所以服务名称相同)
     eureka-client-node-one：Eureka客户端
     eureka-client-node-two：Ribbon客户端
     eureka-client-node-three：Feign客户端
     端口：1001  1002 1003


配置服务器(配置服务器的服务器端)：
    应用程序名称：config-server
    端口：8888

公共网关层(路由设置):
    应用程序名称：api-gateway
    端口:3000

配置中心可以注册到注册中心上；而注册中心也可以从配置中心读取配置信息








