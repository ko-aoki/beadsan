package beadsan.service;

import beadsan.dto.DesignDto;
import beadsan.entity.MstPalette;
import beadsan.entity.MstUser;
import beadsan.entity.TrnDesign;
import beadsan.repository.DesignRepository;
import beadsan.repository.PaletteRepository;
import beadsan.repository.UserRepository;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class DesignService {

    @Autowired 
    private DesignRepository designRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private PaletteRepository paletteRepo;

	@Autowired
	protected Mapper mapper;
	
	public Page<DesignDto> findDesignsByUserId(int userId, int curPage, int itemsPerPage) {

		Page<TrnDesign> trnDesigns = designRepo.selectByMstUserIdOrderByUpdateDateAsc(new PageRequest(curPage - 1, itemsPerPage), userId);
		List<TrnDesign> contents = trnDesigns.getContent();
		ArrayList<DesignDto> designs = new ArrayList<DesignDto>();
		for (TrnDesign content : contents) {
			DesignDto designDto = mapper.map(content, DesignDto.class);
			designs.add(designDto);
		}
		PageImpl page = new PageImpl(designs, new PageRequest(curPage - 1, 1), trnDesigns.getTotalElements());
		return page;
	}

	public TrnDesign findDesignsByUserIdAndDesignName(int userId, String designName) {
		TrnDesign trnDesign = designRepo.selectByMstUserIdAndDesignName(userId, designName);
		return trnDesign;
	}

	public TrnDesign save(TrnDesign design) {

		// 既存データチェック
		TrnDesign trnDesign = designRepo.selectByMstUserIdAndDesignName(
				design.getMstUserId().getMstUserId(), design.getName());
		if (trnDesign != null) {
			// 更新
			trnDesign.setDesign(design.getDesign());
			return designRepo.save(trnDesign);
		}
		// 新規作成
		MstUser mstUser = userRepo.findOne(design.getMstUserId().getMstUserId());
		design.setMstUserId(mstUser);
		final MstPalette mstPalette = paletteRepo.selectByPaletteCd(design.getMstPaletteId().getPaletteCd());
		design.setMstPaletteId(mstPalette);
		return designRepo.save(design);
    }

	public void deleteDesignByName(Integer userId, String designName) {

		TrnDesign trnDesign = this.findDesignsByUserIdAndDesignName(userId, designName);
		designRepo.delete(trnDesign);
	}

	public void delete(Integer id) {
    	designRepo.delete(id);
    }

}
