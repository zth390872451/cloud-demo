#spring-cloud-example-demo


----------
**知识的搬运工**
>


---------------
1、[为什么要使用Spring Cloud ？](http://blog.csdn.net/neosmith/article/details/52204113)

---------------
2、[微服务架构的基础框架选择：Spring Cloud还是Dubbo？](http://blog.didispace.com/microservice-framework)

---------------
3、Spring Cloud分布式开发五大神兽
>- 传送门：https://segmentfault.com/a/1190000005029218
-	服务发现——Netflix Eureka
-	客户端端负载均衡——Netflix Ribbon
-	断路器——Netflix Hystrix
-	服务网关——Netflix Zuul
-	分布式配置——Spring Cloud Config

---------------
####4、组件概览
>
- 	　Spring Cloud Config：配置管理开发工具包，可以让你把配置放到远程服务器，目前支持本地存储、Git以及Subversion。
- 	　Spring Cloud Bus：事件、消息总线，用于在集群（例如，配置变化事件）中传播状态变化，可与Spring Cloud Config联合实现热部署。
- 	　Spring Cloud Netflix：针对多种Netflix组件提供的开发工具包，其中包括Eureka、Hystrix、Zuul、Archaius等。
- 	　Netflix Eureka：云端负载均衡，一个基于 REST 的服务，用于定位服务，以实现云端的负载均衡和中间层服务器的故障转移。
- 	　Netflix Hystrix：容错管理工具，旨在通过控制服务和第三方库的节点,从而对延迟和故障提供更强大的容错能力。
- 	　Netflix Zuul：边缘服务工具，是提供动态路由，监控，弹性，安全等的边缘服务。网关层，用来设置路由，负载均衡，日志。

---------------
####Spring Cloud实践系列
>学习链接
-	　[官网](http://projects.spring.io/spring-cloud/spring-cloud.html)
-	　[Spring Cloud实战 ](https://segmentfault.com/t/springcloud/blogs)
-	　lzhou~[《7天学会spring cloud系列》](http://www.cnblogs.com/skyblog)
<br>      [git项目地址](http://git.oschina.net/zhou666/spring-cloud-7simple)
-	　liaokailin~[《Spring Cloud 实战系列9篇文章》](http://blog.csdn.net/liaokailin/article/category/6212338)
<br>      [git项目地址](https://github.com/liaokailin/springcloud)
-	　程序猿DD~[《Spring Cloud系列》](http://blog.didispace.com/categories/Spring-Cloud/)
<br>        [git项目地址](http://git.oschina.net/didispace)
-	　[不错的Demo地址](https://git.oschina.net/darkranger/spring-cloud-books.git)

---------------
####组件技术介绍
>- 服务注册与发现(云端中间层负载均衡工具 Eureka)
     Eureka是Netflix的服务发现服务器和客户端。Eureka是提供服务注册，为每个注册服务提供知晓其他服务状态的服务。
　内部基础结构:
	　~服务注册中心S、
	　~若干个服务消费方节点C1、C2、C3、。。。
	　~若干个服务提供方节点P1、P2、P3、。。。
    注意：这里的服务消费方和服务提供方都只是相对而言。比如，个人身份角色随着场景更换而改变，如你面对老板是员工，面对妻子自己是丈夫，面对孩子是父亲同一个道理。
术语解释
•	　Eureka Server：提供服务注册和发现。
•	　Service Provider：服务提供方，将自身服务注册到Eureka，从而使服务消费方能够找到。
•	　Service Consumer：服务消费方，从Eureka获取注册服务列表，从而能够消费服务
     服务注册中心，可注册多个(高可用，注册三个，三角形稳定结构)。可根据业务量的大小，将服务部署为集群。

---------
> - ####服务发现(云端中间层负载均衡工具 Eureka)——Netflix Eureka
>[简介](http://blog.csdn.net/liaokailin/article/details/51314001) 
[Dive into Eureka](http://nobodyiam.com/2016/06/25/dive-into-eureka/)
[为什么使用服务发现](http://www.tuicool.com/articles/A7VFra7/)
     Eureka作用：服务注册中心，感知并发现服务，及时剔除宕机(失效)的服务。

> - ####服务注册(云中间层服务 - 区域感知负载均衡器 Ribbon)
>     Ribbon 作用：向注册中心(如Eureka)注册服务；自身缓存从注册中心得到的服务列表。客户端应用负载均衡，区域感知服务节点能力比Eureka自身作为客户端强。
[简介](http://blog.csdn.net/defonds/article/details/32729155)
[Ribbon 和 Eureka 的集成 ](http://blog.csdn.net/defonds/article/details/38016301)
     比较：Ribbon和Eureka都可以应用在客户端应用中，但是更方便的是Feign组件。

-----------
> - ####客户端——Feign实践
>Feign作用：一个声明式的Web Service客户端，它使得编写Web Serivce客户端变得更加简单。
[Feign的简单应用](http://blog.didispace.com/springcloud2/)
[Spring Cloud Feign作为HTTP客户端调用远程HTTP服务](http://blog.csdn.net/neosmith/article/details/52449921)
[Feign日志输出应用](http://www.cnblogs.com/yish/p/6004027.html)


-----------
> - ####断路器——Netflix Hystrix
>[Spring Cloud构建微服务架构——断路器](http://blog.didispace.com/springcloud3/)

-----------
> - ####服务网关——Netflix Zuul
>[Spring Cloud构建微服务架构——服务网关](http://blog.didispace.com/springcloud5)
[使用Springboot和Hystrix构建API Gateway](http://blog.csdn.net/MrTitan/article/details/51565074)

--------------

> - ####分布式配置Spring Cloud Config
>通用组成
1、配置服务器 S、客户端 B、C，服务注册中心 A
2、S、B、C均作为服务，注册自身到A中。
    基础结构
        至少包含两个部分：配置服务器 config-server 和  客户端config-client，配置客户端根据自身规则向配置服务器拉取指定规则的配置文件信息，用于自身应用的初始化。
        比较而言，通用组成是在spring cloud全家桶中的结构形式，而基础结构可用于搭建简单的配置config服务应用。
    Spring Cloud Config 优势
1、	传统应用：某些配置信息发生更改，不得不重启应用。
    Spring Cloud Config应用：可通过Spring Cloud Bus组件的发布订阅者的模式，通知应用自身局部刷新（用@ RefreshScope修饰的环境变量）。
2、	传统应用：配置信息的管理混乱，对于所有的不管是自身需要的还是不需要的都添加到本应用中(文件杂多)，然后需要在应用代码或者部署脚本中进行环境调整，耦合性高且臃肿。
    Spring Cloud Config应用：建立了一个统一的管理分布式系统的配置中心config-server，来管理各个应用的配置文件，而各个应用自身可通过设置自身的规则，在应用启动的时候，按照规则从配置中心获取配置文件信息。
    配置思路：
A、配置服务器 Config-server
- 将自身构建成为服务，并向注册中心注册。
- 设置了配置信息的存放仓库：
	配置地址可以为：1、文件服务器如本地 2、svn 3、git
-----------
B、配置客户端 Config-client
- 将自身构建成为服务，并向注册中心注册。
- 设置了配置服务器地址，自定义拉取文件的规则。
- 可添加@RefreshScope注解修饰配置类。
	1. 使用：配置服务器S、客户端应用B、C
	2. 当远程仓库的配置信息发生变化时，可以从浏览器主动发送请求 /refresh 到当前应用A，则配置服务器将主动向配置仓库如git、svn、文件系统等发送感知请求，若有配置信息改变，立即通知有@RefreshScope修饰的类所在的应用。
	3. Spring Cloud Config 通常与Spring Cloud Bus(使用RabbitMq或者Kafka)实现动态配置的更新，那么测试的时候，也就需要安装RabbitMq以及写相关的配置。
	4. 刷新策略：可分两种策略：客户端pull和服务端的push。
		Pull策略：指的是从某个客户端应用B请求到服务配置应用S，然后触发其他0个或者多个客户端如C主动pull配置文件的动作。
		Push策略：指的是从服务配置应用S定向向某B客户端push属性更改过的配置信息的动作。(可定义指向规则，push符合某种规则的应用服务)

------------------

 

####pom.xm文件依赖说明
>- spring-cloud-starter-parent
具备spring-boot-starter-parent同样功能并附加Spring Cloud的依赖
- spring-cloud-starter-config
默认的配置服务依赖，快速自动引入服务的方式，端口8888
- spring-cloud-config-server／client
用户自定义配置服务的服务端／客户端依赖
- spring-cloud-starter-eureka-server
服务发现的Eureka Server依赖
- spring-cloud-starter-eureka
服务发现的Eureka客户端依赖
- spring-cloud-starter-hystrix／zuul／feign／ribbon
断路器（Hystrix），智能路有（Zuul），客户端负载均衡（Ribbon）的依赖

-----------
####大牛笔记
>[地址](https://git.oschina.net/itmuch/spring-cloud-book)

![IMG_0110.JPG](http://upload-images.jianshu.io/upload_images/1910783-ac6f020523473db4.JPG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)