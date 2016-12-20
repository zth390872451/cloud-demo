*_项目搭建_*：参考链接：http://blog.csdn.net/liaokailin/article/details/51470051


eureka-client-node:1003
端口为1003的本应用：
    作用
    1、使用WebService编程式的Feign接口作为客户端（UserFeignClient extends UserApi）
        调用名为 feign-server的服务
    2、普通的eureka-client-node服务节点(集群节点中的一个)