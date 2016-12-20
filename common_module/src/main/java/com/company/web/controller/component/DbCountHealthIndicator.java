/*
package com.company.web.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.CompositeHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthAggregator;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.Collection;

*/
/*
 * 定义自己的health indicator
 *//*

@Component
public class DbCountHealthIndicator implements HealthIndicator {

    private CrudRepository crudRepository;
    @Autowired
    public DbCountHealthIndicator(CrudRepository crudRepository) {
        this.crudRepository = crudRepository;
    }
    @Override
    public Health health() {
        try {
            long count = crudRepository.count();
            if (count >= 0) {
                return Health.up().withDetail("count", count).build();
            } else {
                return Health.unknown().withDetail("count", count).build();
            }
        } catch (Exception e) {
            return Health.down(e).build();
        }
    }

    @Autowired
    private HealthAggregator healthAggregator;

    @Bean
    public HealthIndicator DbCountHealthIndicator(Collection<CrudRepository> repositories) {
        CompositeHealthIndicator compositeHealthIndicator = new
                CompositeHealthIndicator(healthAggregator);
        for (CrudRepository repository: repositories) {
            String name = DbCountRunner.getRepositoryName(repository.getClass());
            compositeHealthIndicator.addHealthIndicator(name, new DbCountHealthIndicator(repository));
        }
        return compositeHealthIndicator;
    }

}
*/
