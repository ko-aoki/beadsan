package beadsan;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Created by ko-aoki on 2015/11/01.
 */
@Configuration
@EnableJpaAuditing(auditorAwareRef = "beadsanAuditorAware")
public class DataBaseConfig {
}
