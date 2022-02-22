package com.example.multipledatasources.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "productEntityManagerFactory",
        transactionManagerRef = "productTransactionManager",
        basePackages = {"com.example.multipledatasources.repository.dbpostgre"})
public class PostgreConfiguration {

    @Autowired
    private Environment env;

    @Bean(name = "productDatasource")
    public DataSource productDatasource(){
        DriverManagerDataSource datasource = new DriverManagerDataSource();

        datasource.setUrl(env.getProperty("postgre.datasource.url"));
        datasource.setUsername(env.getProperty("postgre.datasource.username"));
        datasource.setPassword(env.getProperty("postgre.datasource.password"));
        datasource.setDriverClassName(Objects.requireNonNull(env.getProperty("postgre.datasource.driver-class-name")));

        return datasource;
    }

    @Bean(name = "productEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();

        em.setDataSource(this.productDatasource());
        em.setPackagesToScan("com.example.multipledatasources.entities.posgrerepo");
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();

        em.setJpaVendorAdapter(adapter);

        Map<String,Object> properties = new HashMap<>();

        properties.put("hibernate.hbm2ddl.auto",
                env.getProperty("postgre.jpa.hibernate.ddl-auto"));
        properties.put("hibernate.dialect",
                env.getProperty("postgre.jpa.database-platform"));

        em.setJpaPropertyMap(properties);

        return em;

    }

    @Bean(name = "productTransactionManager")
    public PlatformTransactionManager transactionManager(){
        JpaTransactionManager manager = new JpaTransactionManager();
        manager.setEntityManagerFactory(entityManagerFactory().getObject());
        return manager;
    }
}
