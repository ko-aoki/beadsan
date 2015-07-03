package beadsan.repository;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import beadsan.App;
import beadsan.entity.MstUser;
import beadsan.entity.TrnDesign;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
@IntegrationTest({"server.port:0",
        "spring.datasource.url:jdbc:postgresql://192.168.56.101:5432/devdb"}) // (1)
public class DesignRepositoryTest {

    @Autowired private DesignRepository designRepo;
    @Autowired private UserRepository userRepo;

    //------------------------------------------------- find all
    
//    @Test
//    public void testFindAll() {
//        List<TrnDesign> trnDesign = designRepo.findAll();
//        assertThat(trnDesign.toString(), equalTo("[Africa, Asia, Europe, North America, South America, Oceania, Antarctica]"));
//    }
    
    @Test
    public void testFindByMstUserIdOrderByUpdateDateAsc() {
    	Pageable pageable = new PageRequest(0, 2);
    	MstUser mstUser = userRepo.findOne(1);
    	
//    	Page<TrnDesign> findByMstUserIdOrderByUpdateDateAsc = designRepo.findByMstUserIdOrderByUpdateDateAsc(mstUser, pageable);
//        assertThat(findByMstUserIdOrderByUpdateDateAsc, equalTo("[Africa, Asia, Europe, North America, South America, Oceania, Antarctica]"));
    }
//    //------------------------------------------------- find all with sort
//
//    @Test
//    public void testFindAllWithIds() {
//        Sort ascendingName = new Sort(Direction.ASC, "name");
//        List<Continent> continents = continentRepo.findAll(ascendingName);
//        
//        assertThat(continents.toString(), equalTo("[Africa, Antarctica, Asia, Europe, North America, Oceania, South America]"));
//    }
//    
//    //------------------------------------------------- insert
//    
//    @Test
//    public void testInsert() {
//        Continent another = new Continent();
//        another.setName("another");
//
//        Continent continentInserted = continentRepo.save(another);
//        continentRepo.flush();
//        
//        assertThat(continentInserted.getName(), equalTo("another"));
//        String name = jdbcTemplate.queryForObject("SELECT cont_name FROM cont WHERE cont_id = ?", 
//                                                  String.class,
//                                                  continentInserted.getId());
//        assertThat(name, equalTo("another"));
//    }
//    
//    //------------------------------------------------- update
//    
//    @Test
//    public void testUpdate() {
//        
//        Continent northAmerica = em.find(Continent.class, 4);
//        northAmerica.setName("N. America");
//        
//        Continent updated = continentRepo.saveAndFlush(northAmerica);
//        
//        assertThat(updated.getName(), equalTo("N. America"));
//        
//        String name = jdbcTemplate.queryForObject("SELECT cont_name FROM cont WHERE cont_id = ?", 
//                                                    String.class,
//                                                    4);
//        assertThat(name, equalTo("N. America"));    
//    }

}