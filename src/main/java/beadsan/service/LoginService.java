package beadsan.service;

import beadsan.dto.HeaderDto;
import beadsan.dto.LoginDto;
import beadsan.dto.PageDto;
import beadsan.repository.UserRepository;
import beadsan.security.BeadsanUserDetails;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.logging.Handler;

/**
 * ログイン画面のサービスクラス.
 * @author ko-aoki
 */
@Service
@Transactional
public class LoginService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
	protected Mapper mapper;

	@Autowired
	private AuthenticationManager authManager;

    /**
     * ログイン処理を行います.
     * @return
     */
    public PageDto login(LoginDto loginDto) {

		PageDto pageDto = new PageDto();
		LoginDto outLoginDto = mapper.map(loginDto, LoginDto.class);
		pageDto.setLoginDto(outLoginDto);
		HeaderDto headerDto = new HeaderDto();
		pageDto.setHeaderDto(headerDto);

		//Spring Security認証処理
		Authentication authResult = null;
		try {
			Authentication request = new UsernamePasswordAuthenticationToken(
					loginDto.getMailAddress(), loginDto.getPassword());
			authResult = authManager.authenticate(request);
			SecurityContextHolder.getContext().setAuthentication(authResult);
			BeadsanUserDetails principal = (BeadsanUserDetails)authResult.getPrincipal();
			headerDto.setNickName(principal.getUserInfo().getNickName());
			headerDto.setAuth(true);

		} catch(AuthenticationException e) {
			outLoginDto.setMessage("メールアドレスかパスワードがまちがっています。");
			headerDto.setAuth(false);
		}
		return pageDto;
    }

}
