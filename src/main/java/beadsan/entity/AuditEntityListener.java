package beadsan.entity;

import beadsan.dto.UserInfo;
import beadsan.security.BeadsanUserDetails;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

/**
 * Created by ko-aoki on 2015/10/31.
 */
public class AuditEntityListener {

    @PrePersist
    public void prePersist(AuditEntity entity) {

        entity.setInsertDate(new Date());
        entity.setVersionNo(1);
        try {
            SecurityContext securityContext = SecurityContextHolder.getContext();
            BeadsanUserDetails principal =
                    (BeadsanUserDetails)securityContext.getAuthentication().getPrincipal();
            entity.setInsertUser(principal.getUserInfo().getUserId());
        } catch (Exception e) {
            // なにもしない
            e.printStackTrace();
        }
    }

    @PreUpdate
    public void preUpdate(AuditEntity entity) {

        entity.setUpdateDate(new Date());
        entity.setVersionNo(entity.getVersionNo() + 1);
        try {
            SecurityContext securityContext = SecurityContextHolder.getContext();
            BeadsanUserDetails principal =
                    (BeadsanUserDetails)securityContext.getAuthentication().getPrincipal();
            entity.setUpdatetUser(principal.getUserInfo().getUserId());
        } catch (Exception e) {
            // なにもしない
            e.printStackTrace();
        }
    }
}
