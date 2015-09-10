package beadsan.security;

import beadsan.dto.LoginDto;
import beadsan.dto.UserInfo;
import beadsan.entity.MstUser;
import lombok.Data;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

/**
 * Created by ko-aoki on 2015/07/08.
 */
@Data
public class BeadsanUserDetails extends User {

    private final UserInfo userInfo;

    public BeadsanUserDetails(MstUser user) {
        super(
                user.getFirstName() + user.getLastName(),
                user.getPassword(),
                AuthorityUtils.createAuthorityList("DEFAULT_USER")
        );

        // ここだとmapperが機能しないので
        this.userInfo = new UserInfo();
        this.userInfo.setFirstName(user.getFirstName());
        this.userInfo.setLastName(user.getLastName());
        this.userInfo.setUserId(user.getMstUserId());
        this.userInfo.setMailAddress(user.getMailAddress());
        this.userInfo.setNickname(user.getNickname());

    }
}
