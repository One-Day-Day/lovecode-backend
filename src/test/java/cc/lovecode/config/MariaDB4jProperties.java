package cc.lovecode.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.boot.mariadb4j")
@Data
public class MariaDB4jProperties {
    private String dataDir;
    private String libDir;
    private String baseDir;
    private String socket;
    private Integer port;
    private String databaseName;
}
