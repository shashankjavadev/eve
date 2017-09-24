package com.foodkonnekt.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.ejb.HibernatePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan({ "com.foodkonnekt.configuration" })
@PropertySource({ "classpath:hibernate.properties" })
@EnableJpaRepositories("com.foodkonnekt.repository")
public class HibernateConfiguration {

    private static final String PROPERTY_NAME_DATABASE_DRIVER = "jdbc.driverClassName";
    private static final String PROPERTY_NAME_DATABASE_PASSWORD = "jdbc.password";
    private static final String PROPERTY_NAME_DATABASE_URL = "jdbc.url";
    private static final String PROPERTY_NAME_DATABASE_USERNAME = "jdbc.username";

    private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "hibernate.dialect";
    private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    private static final String PROPERTY_NAME_HIBERNATE_ORDER_INSERTS = "hibernate.order_inserts";
    private static final String PROPERTY_NAME_HIBERNATE_ORDER_UPDATES = "hibernate.order_updates";
    
    private static final String PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN = "entitymanager.packages.to.scan";

    @Autowired
    private Environment environment;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty(PROPERTY_NAME_DATABASE_DRIVER));
        dataSource.setUrl(environment.getRequiredProperty(PROPERTY_NAME_DATABASE_URL));
        dataSource.setUsername(environment.getRequiredProperty(PROPERTY_NAME_DATABASE_USERNAME));
        dataSource.setPassword(environment.getRequiredProperty(PROPERTY_NAME_DATABASE_PASSWORD));
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistence.class);
        entityManagerFactoryBean.setPackagesToScan(environment
                        .getRequiredProperty(PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN));

        entityManagerFactoryBean.setJpaProperties(hibProperties());
        return entityManagerFactoryBean;
    }

    private Properties hibProperties() {
        Properties properties = new Properties();
        properties.put(PROPERTY_NAME_HIBERNATE_DIALECT,
                        environment.getRequiredProperty(PROPERTY_NAME_HIBERNATE_DIALECT));
        properties.put(PROPERTY_NAME_HIBERNATE_SHOW_SQL,
                        environment.getRequiredProperty(PROPERTY_NAME_HIBERNATE_SHOW_SQL));
        
        properties.put(PROPERTY_NAME_HIBERNATE_ORDER_INSERTS,
                environment.getRequiredProperty(PROPERTY_NAME_HIBERNATE_ORDER_INSERTS));
        
        properties.put(PROPERTY_NAME_HIBERNATE_ORDER_UPDATES,
                environment.getRequiredProperty(PROPERTY_NAME_HIBERNATE_ORDER_UPDATES));
       // properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("initialPoolSize", "10");
        properties.put("minPoolSize", "5");
        properties.put("maxPoolSize", "50");
        properties.put("idleConnectionTestPeriod", "100");
        properties.put("acquireIncrement", "2");
        properties.put("maxStatements", "0");
        properties.put("maxIdleTime", "1800");
        properties.put("numHelperThreads", "3");
        properties.put("acquireRetryAttempts", "2");
        properties.put("acquireRetryDelay", "1000");
        properties.put("checkoutTimeout", "5000");
       //properties.put("hbm2ddl.auto", "create");
        
        return properties;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }
}
