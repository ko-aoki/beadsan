package beadsan;

/**
 * Hello world!
 *
 */
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import beadsan.entity.MstUser;
import beadsan.repository.UserRepository;

@EnableAutoConfiguration
@ComponentScan
@Import(AppConfig.class)
//public class App implements CommandLineRunner {
public class App {

	@Autowired
	private UserRepository userRepository;

//	@Override
//	public void run(String... args) throws Exception {
//		List<MstUser> mstUser = userRepository.findAll();
//		System.out.println("-------------------------------");
//		System.out.println(mstUser.get(0).getFirstName());
//
//	}

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}