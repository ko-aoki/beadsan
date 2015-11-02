package beadsan.entity;

import beadsan.dto.UserInfo;
import beadsan.security.BeadsanUserDetails;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
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
        try {
            SecurityContext securityContext = SecurityContextHolder.getContext();
            BeadsanUserDetails principal =
                    (BeadsanUserDetails)securityContext.getAuthentication().getPrincipal();
            UserInfo userInfo = principal.getUserInfo();
            entity.setInsertUser(new MstUser(userInfo.getUserId()));
        } catch (Exception e) {
            // なにもしない
        }
    }

    @PreUpdate
    public void preUpdate(AuditEntity entity) {

        entity.setUpdateDate(new Date());
        try {
            SecurityContext securityContext = SecurityContextHolder.getContext();
            BeadsanUserDetails principal =
                    (BeadsanUserDetails)securityContext.getAuthentication().getPrincipal();
            UserInfo userInfo = principal.getUserInfo();
            entity.setUpdatetUser(new MstUser(userInfo.getUserId()));
        } catch (Exception e) {
            // なにもしない
        }
    }
}
