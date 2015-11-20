package beadsan;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        RequestMatcher csrfRequestMatcher = new RequestMatcher() {

            private Pattern allowedMethods = Pattern.compile("^GET$");

            // CSRF対象外URL:
            private AntPathRequestMatcher[] requestMatchers = {
//                    new AntPathRequestMatcher("/index.hml"),
                    new AntPathRequestMatcher("/api/user/login"),
                    new AntPathRequestMatcher("/api/user"),
                    new AntPathRequestMatcher("/api/user/auth")
            };

            @Override
            public boolean matches(HttpServletRequest request) {

                if (allowedMethods.matcher(request.getMethod()).matches()) {
                    return false;
                }

                for (AntPathRequestMatcher rm : requestMatchers) {
                    if (rm.matches(request)) {
                        return false;
                    }
                }
                return true;
            }
        };

        http.authorizeRequests()
                .antMatchers(
                        "/bower_components/**/*",
                        "/images/**",
                        "/scripts/**",
                        "/scripts/**/*",
                        "/styles/**",
                        "/views/**",
                        "/index.html",
                        "/api/user/login",
                        "/api/user",
                        "/api/user/auth"
                ).permitAll()
                .anyRequest().authenticated()   //上記にマッチしなければ未認証エラー
                .and()
                .csrf()
                .requireCsrfProtectionMatcher(csrfRequestMatcher)
                .csrfTokenRepository(this.csrfTokenRepository());
    }

    private CsrfTokenRepository csrfTokenRepository() {
        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
        repository.setHeaderName("X-XSRF-TOKEN");
        return repository;
    }
}
