package beadsan.api;

import beadsan.dto.*;
import beadsan.security.BeadsanUserDetails;
import beadsan.service.UserService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/user")
public class UserRestController {
	
	@Autowired
	UserService userService;

	@Autowired
	protected Mapper mapper;

	@RequestMapping(value = "login", method = RequestMethod.POST)
	ResponseEntity<PageDto> login(@Validated @RequestBody LoginDto loginDto, HttpServletRequest request, HttpServletResponse response) {

		PageDto pageDto = userService.login(loginDto);

		if (pageDto.getHeaderDto().isAuth()) {
			CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
			if (csrf != null) {
				Cookie cookie = WebUtils.getCookie(request, "XSRF-TOKEN");
				String token = csrf.getToken();
				Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
				if ((cookie == null || token != null && !token.equals(cookie.getValue()))
						&& (authentication != null && authentication.isAuthenticated())) {
					cookie = new Cookie("XSRF-TOKEN", token);
					cookie.setPath("/");
					response.addCookie(cookie);
				}
			}
			return new ResponseEntity<>(pageDto, null, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(pageDto, null, HttpStatus.UNAUTHORIZED);
		}
	}

	@RequestMapping(value = "auth", method = RequestMethod.GET)
	ResponseEntity<PageDto> isAuth(@AuthenticationPrincipal BeadsanUserDetails userDetail) {

		PageDto pageDto = new PageDto();
		if (userDetail != null) {
			UserInfo userInfo = userDetail.getUserInfo();
			HeaderDto headerDto = mapper.map(userInfo, HeaderDto.class);
			headerDto.setAuth(true);
			LoginDto loginDto = mapper.map(userInfo, LoginDto.class);
			pageDto.setHeaderDto(headerDto);
			pageDto.setLoginDto(loginDto);

			return new ResponseEntity<>(pageDto, null, HttpStatus.OK);
		} else {
			HeaderDto headerDto =new HeaderDto();
			headerDto.setAuth(false);
			pageDto.setHeaderDto(headerDto);

			return new ResponseEntity<>(pageDto, null, HttpStatus.UNAUTHORIZED);
		}
	}

	@RequestMapping(value = "logout", method = RequestMethod.POST)
	void logout(HttpServletRequest request) {
		try {
			request.logout();
		} catch (ServletException e) {
			throw new RuntimeException(e);
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	ResponseEntity<UserDto> registerUser(
			@Validated @RequestBody UserDto userDto, HttpServletRequest request, HttpServletResponse response) {

		UserDto outDto = userService.registerUser(userDto);

		if (outDto.getErrorMessage() != null) {
			return new ResponseEntity<>(outDto, null, HttpStatus.NOT_ACCEPTABLE);
		} else {
			return new ResponseEntity<>(outDto, null, HttpStatus.CREATED);
		}
	}

	@RequestMapping(value = "password", method = RequestMethod.PUT)
	ResponseEntity<UserDto> updatePassword(
			@AuthenticationPrincipal BeadsanUserDetails userDetail,
			@Validated @RequestBody UserDto userDto, HttpServletRequest request, HttpServletResponse response) {

		if (!userDetail.getUserInfo().getMailAddress().equals(userDto.getMailAddress())) {
			UserDto errorUserDto = new UserDto();
			errorUserDto.setMessage("ログイン時と異なるメールアドレスです");
			return new ResponseEntity<>(null, null, HttpStatus.NOT_ACCEPTABLE);
		}
		UserDto outDto = userService.updatePassword(userDto);
		return new ResponseEntity<>(outDto, null, HttpStatus.OK);
	}

	@RequestMapping(value = "nickname", method = RequestMethod.PUT)
	ResponseEntity<UserDto> updateNickname(
			@AuthenticationPrincipal BeadsanUserDetails userDetail,
			@Validated @RequestBody UserDto userDto, HttpServletRequest request, HttpServletResponse response) {

		if (!userDetail.getUserInfo().getMailAddress().equals(userDto.getMailAddress())) {
			UserDto errorUserDto = new UserDto();
			errorUserDto.setMessage("ログイン時と異なるメールアドレスです");
			return new ResponseEntity<>(null, null, HttpStatus.NOT_ACCEPTABLE);
		}
		UserDto outDto = userService.updateNickname(userDto);
		return new ResponseEntity<>(outDto, null, HttpStatus.OK);
	}

}
