package beadsan.security;

import beadsan.dto.UserInfo;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Created by ko-aoki on 2015/11/01.
 */
@Component
public class BeadsanAuditorAware implements AuditorAware<UserInfo> {

    @Override
    public UserInfo getCurrentAuditor() {
        return this.getCurrentAccount();
    }

    public UserInfo getCurrentAccount() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        BeadsanUserDetails springSecurityUser = (BeadsanUserDetails) securityContext.getAuthentication().getPrincipal();
        return springSecurityUser.getUserInfo();
    }
}
