package com.lovecode.problem.config;

import ch.vorburger.exec.ManagedProcessException;
import ch.vorburger.mariadb4j.DBConfigurationBuilder;
import ch.vorburger.mariadb4j.springframework.MariaDB4jSpringService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Profile;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.util.Random;

@Configuration
@Profile("test")
@ConditionalOnClass({MariaDB4jSpringService.class, DataSource.class})
@EnableConfigurationProperties(value = {MariaDB4jProperties.class})
public class MariaDB4jConfiguration {

    private final MariaDB4jProperties mariaDB4jProperties;

    public MariaDB4jConfiguration(MariaDB4jProperties mariaDB4jProperties) {
        this.mariaDB4jProperties = mariaDB4jProperties;
    }

    @Bean
    @ConditionalOnMissingBean
    public MariaDB4jSpringService mariaDB4jSpringService() {
        MariaDB4jSpringService mariaDB4jSpringService = new MariaDB4jSpringService();
        DBConfigurationBuilder dBConfigurationBuilder = mariaDB4jSpringService.getConfiguration();
        dBConfigurationBuilder.addArg("--character-set-server=utf8");
        dBConfigurationBuilder.addArg("--lower_case_table_names=1");
        dBConfigurationBuilder.addArg("--collation-server=utf8_general_ci");
        dBConfigurationBuilder.addArg("--user=root");
        dBConfigurationBuilder.setPort(new Random().nextInt(1000) + 60000);

        String dataDir = StringUtils.isEmpty(mariaDB4jProperties.getDataDir()) ? dBConfigurationBuilder.getDataDir() : mariaDB4jProperties.getDataDir();
        String libDir = StringUtils.isEmpty(mariaDB4jProperties.getLibDir()) ? dBConfigurationBuilder.getLibDir() : mariaDB4jProperties.getLibDir();
        String socket = StringUtils.isEmpty(mariaDB4jProperties.getSocket()) ? dBConfigurationBuilder.getSocket() : mariaDB4jProperties.getSocket();
        Integer port = mariaDB4jProperties.getPort() == null ? dBConfigurationBuilder.getPort() : mariaDB4jProperties.getPort();
        String baseDir = StringUtils.isEmpty(mariaDB4jProperties.getBaseDir()) ? dBConfigurationBuilder.getBaseDir() : mariaDB4jProperties.getBaseDir();

        mariaDB4jSpringService.setDefaultBaseDir(baseDir);
        mariaDB4jSpringService.setDefaultDataDir(dataDir);
        mariaDB4jSpringService.setDefaultLibDir(libDir);
        mariaDB4jSpringService.setDefaultPort(port);
        mariaDB4jSpringService.setDefaultSocket(socket);
        return mariaDB4jSpringService;
    }

    @Bean
    @ConditionalOnMissingBean
    @DependsOn("mariaDB4jSpringService")
    public DataSource dataSource(DataSourceProperties dataSourceProperties, MariaDB4jSpringService mariaDB4jSpringService) throws ManagedProcessException {
        mariaDB4jSpringService.getDB().createDB(mariaDB4jProperties.getDatabaseName());
        DBConfigurationBuilder dBConfiguration = mariaDB4jSpringService().getConfiguration();

        return DataSourceBuilder
                .create()
                .url(dBConfiguration.getURL(mariaDB4jProperties.getDatabaseName()))
                .driverClassName(dataSourceProperties.determineDriverClassName())
                .username(dataSourceProperties.determineUsername())
                .password(dataSourceProperties.determinePassword())
                .build();
    }
}
