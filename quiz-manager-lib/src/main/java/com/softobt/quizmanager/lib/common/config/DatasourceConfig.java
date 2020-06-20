package com.softobt.quizmanager.lib.common.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author aobeitor
 * @since 6/9/20
 */
@Configuration
@EnableJpaRepositories(
        basePackages = {
                "com.softobt.quizmanager.**.repository",
        },
        transactionManagerRef = "quizTranxManager",
        entityManagerFactoryRef = "quizManagerFactory"
)
public class DatasourceConfig {
        @Value("${quiz.mgr.database.url}")
        private String databaseUrl;

        @Value("${quiz.mgr.database.username}")
        private String databaseUsername;

        @Value("${quiz.mgr.database.password}")
        private String databasePassword;

        @Value("${quiz.mgr.database.driver}")
        private String databaseDriver;

        @Value("${quiz.mgr.database.max-active:100}")
        private Integer maxActive;

        @Value("${quiz.mgr.database.dialect:}")
        private String hibernateDialect;

        @Value("${quiz.mgr.database.hbbm2ddl:none}")
        private String hibernateHBM2DDL;

        @Value("${quiz.mgr.database.show_sql:false}")
        private String hibernateShowSql;


        @Value("${quiz.mgr.database.max-lifetime:60000}")
        private Long maxLifeTime;

        @Value("${quiz.mgr.database.connection.timeout:60000}")
        private Long connectionTimeOut;


    @Primary
    @Bean(name = "quizManagerFactory")
    public LocalContainerEntityManagerFactoryBean dataEntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();

        factoryBean.setDataSource(dataSource());
        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factoryBean.setJpaPropertyMap(additionalProperties());
        factoryBean.setPackagesToScan(
                "com.softobt.quizmanager.**.models",
                "com.softobt.asgardian.control.models"
        );

        return factoryBean;
    }

    @Primary
    @Bean
    public DataSource dataSource() {

        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setConnectionTimeout(connectionTimeOut);
        hikariConfig.setMaxLifetime(maxLifeTime);
        hikariConfig.setUsername(databaseUsername);
        hikariConfig.setJdbcUrl(databaseUrl);
        hikariConfig.setPassword(databasePassword);
        hikariConfig.setMaximumPoolSize(maxActive);
        hikariConfig.setDriverClassName(databaseDriver);
        hikariConfig.setConnectionTestQuery("SELECT 1");

        return new HikariDataSource(hikariConfig);
    }

    private Map<String, String> additionalProperties() {
        Map<String, String> props = new HashMap<>();
        props.put("hibernate.hbm2ddl.auto", hibernateHBM2DDL);
        props.put("hibernate.show_sql", hibernateShowSql);
        props.put("hibernate.dialect", hibernateDialect);
        props.put("hibernate.physical_naming_strategy", "com.softobt.jpa.helpers.impl.PhysicalNamingStrategyImpl");


        return props;
    }

    @Primary
    @Bean(name = "quizTranxManager")
    @DependsOn(value = "quizManagerFactory")
    public PlatformTransactionManager cloudTransactionManager() {
        return new JpaTransactionManager(dataEntityManagerFactory().getObject());
    }
}
