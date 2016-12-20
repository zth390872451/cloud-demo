*_项目搭建_*：参考链接： http://blog.didispace.com/springcloud4/ (简单版) 
                     http://blog.didispace.com/springcloud4-2/ (加强版——消息总线)
    配置服务器与消息总线结合基础：搭建RabbitMq环境——[ Centos 系统RabbitMQ学习之安装](http://blog.csdn.net/zhu_tianwei/article/details/40832185)
    如果不需要消息总线：只需要将属性注释以及去除依赖就ok了。

Spring Cloud Config：


配置中心服务器：
    1、存储配置文件存储的地址(git仓库或者svn仓库或者本地文件系统)和匹配规则(git：如存储路径)
    2、定义客户端获取文件的规则
    2、配置中心服务器的默认端口为8888，可自行更改
    config-server

配置客户端节点：
    1、指定获取配置文件(如指定：spring.cloud.config.profile=dev)
        具体的pull的文件规则 参考:http://blog.didispace.com/springcloud4/
    eureka-client-one
    eureka-client-two
    eureka-client-three
    2、注意：当config-server端口更改为其他端口时，客户端启动时会先向本地8888端口试图pull文件，然后再到指定端口pull一次

整合思路：
    1、启动eureka服务注册中心(eureka-server)
    1、将配置服务器和配置客户端均注册为服务实例(向eureka-server服务注册中心注册)
    2、启动配置服务器(config-server)
    3、启动客户端( eureka-client-node-${number} )

整体把握：eureka-client-node-xxx(三个节点)
        1、eureka-client (相对于eureka-sever的客户端应用)
        2、config-client (相对于config-sever的客户端应用)
       config-server: 
        1、 eureka-client (相对于eureka-sever的客户端应用)
        2、 config-server (相对于eureka-client的服务器)


        

