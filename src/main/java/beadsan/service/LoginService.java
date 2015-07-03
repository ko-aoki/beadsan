package beadsan.service;

import javax.transaction.Transactional;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import beadsan.dto.LoginDto;
import beadsan.entity.MstUser;
import beadsan.repository.UserRepository;

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

    /**
     * ログイン処理を行います.
     * @return
     */
    public LoginDto login(LoginDto loginDto) {
    	MstUser mstUser = userRepo.findByMailAddress(loginDto.getMailAddress());
    	LoginDto res = mapper.map(loginDto, LoginDto.class);
    	if (mstUser != null) {
        	res = mapper.map(mstUser, LoginDto.class);
        	res.setLogined(true);
    	} else {
    		res.setLogined(false);
    		res.setMessage("メールアドレスかパスワードがまちがっています。");
    	}
    	return res;
    	
    }

}
