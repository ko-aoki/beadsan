package beadsan.security;

import beadsan.dto.LoginDto;
import beadsan.entity.MstUser;
import beadsan.repository.UserRepository;
import beadsan.security.BeadsanUserDetails;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author ko-aoki
 */
@Service("beadsanAuthService")
@Transactional
public class BeadsanAuthService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

//    @Autowired
//	protected Mapper mapper;
//
	@Override
	public BeadsanUserDetails loadUserByUsername(String mailAddress) throws UsernameNotFoundException {
System.out.println("============" + "mailAddress:" + mailAddress + "==========");

		MstUser user = userRepo.findByMailAddress(mailAddress);
		if (user != null) {
			return new BeadsanUserDetails(user);
		}
		return null;
	}
}
