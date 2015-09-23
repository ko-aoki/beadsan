package beadsan.service;

import beadsan.dto.HeaderDto;
import beadsan.dto.LoginDto;
import beadsan.dto.PageDto;
import beadsan.dto.UserDto;
import beadsan.entity.MstUser;
import beadsan.repository.MstUserRepository;
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

/**
 * ログイン画面のサービスクラス.
 * @author ko-aoki
 */
@Service
@Transactional
public class UserService {

    @Autowired
	protected Mapper mapper;

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private MstUserRepository mstUserRepo;

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
			headerDto.setNickname(principal.getUserInfo().getNickname());
			headerDto.setAuth(true);

		} catch(AuthenticationException e) {
			outLoginDto.setMessage("メールアドレスかパスワードがまちがっています。");
			headerDto.setAuth(false);
		}
		return pageDto;
    }

	/**
	 * ユーザ登録処理を行います.
	 * @return
	 */
	public UserDto registerUser(UserDto userDto) {

		UserDto outDto = mapper.map(userDto, UserDto.class);
		MstUser user = mstUserRepo.findByMailAddress(userDto.getMailAddress());
		if (user != null) {
			outDto.setErrorMessage("同じメールアドレスが登録されています。他のメールアドレスを指定してください。");
			return outDto;
		}
		MstUser mstUser = mapper.map(userDto, MstUser.class);
		mstUserRepo.save(mstUser);

		outDto.setMessage("ユーザを登録しました。");
		return outDto;
	}

}
