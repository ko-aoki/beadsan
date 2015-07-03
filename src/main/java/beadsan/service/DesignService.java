package beadsan.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import beadsan.dto.DesignDto;
import beadsan.dto.PageDto;
import beadsan.entity.TrnDesign;
import beadsan.repository.DesignRepository;
import beadsan.repository.UserRepository;

@Service
@Transactional
public class DesignService {

    @Autowired 
    private DesignRepository designRepo;
	@Autowired
	protected Mapper mapper;
	
	public PageDto<DesignDto> findDesignsByUserId(int userId, int curPage, int pageSize) {
		Page<TrnDesign> trnDesigns = designRepo.selectByMstUserIdOrderByUpdateDateAsc(new PageRequest(curPage, pageSize), userId);
		PageDto<DesignDto> pageDto = mapper.map(trnDesigns, PageDto.class);
		List<TrnDesign> contents = trnDesigns.getContent();
		ArrayList<DesignDto> designs = new ArrayList<DesignDto>();
		for (TrnDesign content : contents) {
			DesignDto designDto = mapper.map(content, DesignDto.class);
			designs.add(designDto);
		}
		pageDto.setContent(designs);
		return pageDto;
	}

    public TrnDesign create(TrnDesign design) {
        return designRepo.save(design);
    }

    public TrnDesign update(TrnDesign design) {
        return designRepo.save(design);
    }

    public void delete(Integer id) {
    	designRepo.delete(id);
    }

}
