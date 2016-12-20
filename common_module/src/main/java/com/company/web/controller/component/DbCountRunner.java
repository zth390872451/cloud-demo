/*
package com.company.web.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.Collection;

*/
/**
 * 实现 CommandLineRunner 接口，将在应用启动后执行 run 方法
 *//*

@Component
public class DbCountRunner implements CommandLineRunner {

    protected final Logger logger = LoggerFactory.getLogger(DbCountRunner.class);

    private Collection<CrudRepository> repositories;
    @Autowired
    public DbCountRunner(Collection<CrudRepository> repositories) {
        this.repositories = repositories;
    }

    @Override
    public void run(String... strings) throws Exception {
        logger.info("应用启动成功!");
        logger.info("当前应用 dao层的repository 数量有:{}个",repositories.size());
        int i = 0;
        for (CrudRepository crudRepository :repositories){
            i++;
            logger.info("当前 第 {} 个 repository 名字为 {}",i,getRepositoryName(crudRepository.getClass()));
        }

    }

    public static String getRepositoryName(Class crudRepositoryClass) {
        for (Class repositoryInterface : crudRepositoryClass.getInterfaces()) {
            if (repositoryInterface.getName()!=null) {
                return repositoryInterface.getSimpleName();
            }
        }
        return "UnknownRepository";
    }

}*/
