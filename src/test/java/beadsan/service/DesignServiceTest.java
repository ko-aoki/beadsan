package beadsan.service;

import beadsan.App;
import beadsan.dto.DesignDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
@IntegrationTest({"server.port:0",
        "spring.datasource.url:jdbc:postgresql://192.168.56.101:5432/devdb"}) // (1)
public class DesignServiceTest {

    @Autowired 
    private DesignService designService;

	@Test
	public void testFindDesignsByUserId() {
		Page<DesignDto> design = designService.findDesignsByUserId(1, 0, 2);

		System.out.println(design);
	}

}
