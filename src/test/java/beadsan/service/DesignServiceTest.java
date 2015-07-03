package beadsan.service;

import org.dozer.Mapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import beadsan.App;
import beadsan.dto.DesignDto;
import beadsan.dto.PageDto;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
@IntegrationTest({"server.port:0",
        "spring.datasource.url:jdbc:postgresql://192.168.56.101:5432/devdb"}) // (1)
public class DesignServiceTest {

    @Autowired 
    private DesignService designService;
	@Autowired
	protected Mapper mapper;

	@Test
	public void testFindDesignsByUserId() {
		PageDto<DesignDto> design = designService.findDesignsByUserId(1, 0, 2);

		System.out.println(design);
	}

}
