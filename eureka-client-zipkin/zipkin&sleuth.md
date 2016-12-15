客户端：
spring.zipkin.base-url=http://127.0.0.1:9411/
#开启当前应用接受zipkin服务器的监听
#(127.0.0.1:9411是默认值，该属性值可以不用添加，只需要加入pom的依赖)
#(spring-cloud-sleuth-zipkin)和(spring-cloud-starter-sleuth)就ok了
    所以，在父级pom中加入了该依赖所有的子模块将自动加载该依赖

服务器端：
@EnableZipkinServer//作为ZipkinServer启动
加入依赖：
1、(zipkin-server)
2、(zipkin-autoconfigure-ui)
