Spring-Boot-Admin 存在的bug：
启动成功之后报错：
java.lang.IllegalStateException: Can't set AsyncWebRequest with concurrent handling in progress

解决：
https://github.com/codecentric/spring-boot-admin/issues/236
Either you downgrade to tomcat 8.0.33 or you wait for spring 4.2.8 containing the fix.
<parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>1.3.7.RELEASE</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
    用的是 4.2.7的Spring 版本