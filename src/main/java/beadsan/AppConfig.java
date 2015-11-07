/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beadsan;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import javax.inject.Singleton;
import javax.sql.DataSource;

import net.sf.log4jdbc.Log4jdbcProxyDataSource;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author ko-aoki
 */
@Configuration
public class AppConfig {
    @Autowired
    DataSourceProperties properties;
    DataSource dataSource;

    @ConfigurationProperties("spring.datasource")
    @Bean(destroyMethod = "close")
    DataSource realDataSource() throws URISyntaxException {
        String url;
        String username;
        String password;

        String databaseUrl = System.getenv("DATABASE_URL");
        if (databaseUrl != null) {
            URI dbUri = new URI(databaseUrl);
            url = "jdbc:postgresql://" + dbUri.getHost() + ":" + dbUri.getPort() + dbUri.getPath();
            username = dbUri.getUserInfo().split(":")[0];
            password = dbUri.getUserInfo().split(":")[1];
        } else {
            url = this.properties.getUrl();
            username = this.properties.getUsername();
            password = this.properties.getPassword();
        }

        DataSourceBuilder factory = DataSourceBuilder
                .create(this.properties.getClassLoader())
                .url(url)
                .username(username)
                .password(password);
        this.dataSource = factory.build();
        return this.dataSource;
    }

    @Bean
    DataSource dataSource() {
        return new Log4jdbcProxyDataSource(this.dataSource);
    }

    @Singleton
    @Bean(name = "org.dozer.Mapper")
    public DozerBeanMapper dozerBean() {
		List<String> mappingFiles = Arrays.asList(
				"META-INF/dozer/user-mappings.xml",
				"META-INF/dozer/design-mappings.xml"
				);

      DozerBeanMapper dozerBean = new DozerBeanMapper();
      dozerBean.setMappingFiles(mappingFiles);
      return dozerBean;
    }

}