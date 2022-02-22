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
@EnableJpaRepositories(entityManagerFactoryRef = "userEntityManagerFactory",
        transactionManagerRef = "userTransactionManager",
basePackages = {"com.example.multipledatasources.repository.dbmysql"})
public class MySQLConfig {

    @Autowired
    private Environment env;

    @Bean(name = "userDatasource")
    public DataSource userDatasource(){
        DriverManagerDataSource datasource = new DriverManagerDataSource();
        datasource.setUrl(env.getProperty("mysql.datasource.url"));
        datasource.setUsername(env.getProperty("mysql.datasource.username"));
        datasource.setPassword(env.getProperty("mysql.datasource.password"));
        datasource.setDriverClassName(Objects.requireNonNull(env.getProperty("mysql.datasource.driver-class-name")));

        return datasource;
    }


    @Bean(name = "userEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean userEntityManagerFactory(){
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();

        em.setDataSource(this.userDatasource());
        em.setPackagesToScan("com.example.multipledatasources.entities.mysqlrepo");
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();

        em.setJpaVendorAdapter(adapter);

        Map<String,Object> properties = new HashMap<>();

        properties.put("hibernate.hbm2ddl.auto",
                env.getProperty("mysql.jpa.hibernate.ddl-auto"));
        properties.put("hibernate.dialect",
                env.getProperty("mysql.jpa.database-platform"));
        em.setJpaPropertyMap(properties);

        return em;

    }


    @Primary
    @Bean(name = "userTransactionManager")
    public PlatformTransactionManager userTransactionManager(){
        JpaTransactionManager manager = new JpaTransactionManager();
        manager.setEntityManagerFactory(userEntityManagerFactory().getObject());
        return manager;
    }
}
