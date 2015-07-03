package beadsan.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import beadsan.dto.DesignDto;
import beadsan.dto.LoginDto;
import beadsan.dto.PageDto;
import beadsan.service.DesignService;
import beadsan.service.LoginService;

@RestController
@RequestMapping("/api/")
public class LoginRestController {
	
	@Autowired
	LoginService loginService;

	@Autowired
	DesignService designService;

	@RequestMapping(value = "login", method = RequestMethod.POST)
	LoginDto login(@RequestBody LoginDto loginDto) {
		return loginService.login(loginDto);
	}

	@RequestMapping(value = "user/{userId}", method = RequestMethod.GET)
	PageDto<DesignDto> getLoginUserDesigns(@PathVariable Integer userId,
			@RequestParam("page") int curPage,
			@RequestParam("size") int pageSize) {
		PageDto<DesignDto> designs = designService.findDesignsByUserId(userId,
				curPage, pageSize);
		return designs;
	}


}
