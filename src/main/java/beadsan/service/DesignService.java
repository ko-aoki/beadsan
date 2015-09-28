package beadsan.service;

import beadsan.dto.DesignDto;
import beadsan.entity.*;
import beadsan.repository.*;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class DesignService {

    @Autowired 
    private TrnDesignRepository trnDesignRepo;
	@Autowired
	private MstUserRepository mstUserRepo;
	@Autowired
	private MstPaletteRepository mstPaletteRepo;
	@Autowired
	private MstTagRepository mstTagRepo;
	@Autowired
	private TrnTagRepository trnTagRepo;

	@Autowired
	protected Mapper mapper;
	
	public Page<DesignDto> findDesignsByUserId(int userId, int curPage, int itemsPerPage) {

		Page<TrnDesign> trnDesigns = trnDesignRepo.selectByMstUserIdOrderByUpdateDateAsc(new PageRequest(curPage - 1, itemsPerPage), userId);
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
		TrnDesign trnDesign = trnDesignRepo.selectByMstUserIdAndDesignName(userId, designName);
		return trnDesign;
	}

	public Page<DesignDto> findDesignsByDesignNameAndTag(String designName, String tag, int curPage, int itemsPerPage) {
		Page<TrnDesign> trnDesigns =
				trnDesignRepo.
						findAll(
								Specifications.where(
										TrnDesignSpecification.nameContains(designName)
								).and(
										TrnDesignSpecification.tagContains(tag)
								),
								new PageRequest(curPage - 1, itemsPerPage)
						);
		List<TrnDesign> contents = trnDesigns.getContent();
		ArrayList<DesignDto> designs = new ArrayList<DesignDto>();
		for (TrnDesign content : contents) {
			DesignDto designDto = mapper.map(content, DesignDto.class);
			designs.add(designDto);
		}
		PageImpl page = new PageImpl(designs, new PageRequest(curPage - 1, 1), trnDesigns.getTotalElements());
		return page;
	}

	public TrnDesign save(TrnDesign design) {

		// 既存データチェック
		TrnDesign existingDesign = trnDesignRepo.selectByMstUserIdAndDesignName(
				design.getMstUserId().getMstUserId(), design.getName());
		if (existingDesign != null) {
			// 更新
			existingDesign.setDesign(design.getDesign());
			ArrayList<TrnTag> trnTags = new ArrayList<>();
			for (TrnTag tag : design.getTrnTagCollection()) {
				MstTag mstTag = mstTagRepo.findByName(tag.getMstTagId().getName());
				if (mstTag == null)  {
					mstTag = mstTagRepo.save(tag.getMstTagId());
				}
				TrnTag trnTag = trnTagRepo.findByTrnDesignIdAndMstTagId(
						existingDesign.getTrnDesignId(),
						mstTag.getMstTagId());
				if (trnTag == null) {
					trnTag = new TrnTag();
					trnTag.setTrnDesignId(existingDesign);
					trnTag.setMstTagId(mstTag);
					TrnTag saveTrnTag = trnTagRepo.save(trnTag);
					trnTags.add(saveTrnTag);
				} else {
					trnTags.add(trnTag);
				}

			}
			existingDesign.setTrnTagCollection(trnTags);
			TrnDesign savedTrnDesign = trnDesignRepo.save(existingDesign);
			return savedTrnDesign;
		}
		// 新規作成
		MstUser mstUser = mstUserRepo.findOne(design.getMstUserId().getMstUserId());
		design.setMstUserId(mstUser);
		final MstPalette mstPalette = mstPaletteRepo.selectByPaletteCd(design.getMstPaletteId().getPaletteCd());
		design.setMstPaletteId(mstPalette);
		Collection<TrnTag> trnTagCollection = design.getTrnTagCollection();
		design.setTrnTagCollection(null);	// キーがないため一度削除
		TrnDesign savedTrnDesign = trnDesignRepo.save(design); // 親テーブルのデータ作成
		ArrayList<TrnTag> trnTags = new ArrayList<>();
		for (TrnTag tag : trnTagCollection) {
			MstTag mstTag = mstTagRepo.findByName(tag.getMstTagId().getName());
			if (mstTag == null) {
				mstTag = mstTagRepo.save(tag.getMstTagId());
			}
			TrnTag trnTag = new TrnTag();
			trnTag.setMstTagId(mstTag);
			trnTag.setTrnDesignId(savedTrnDesign);
			TrnTag saveTrnTag = trnTagRepo.save(trnTag);
			trnTags.add(saveTrnTag);
		}
		savedTrnDesign.setTrnTagCollection(trnTags);
		return savedTrnDesign;
    }

	public void deleteDesignByName(Integer userId, String designName) {

		TrnDesign trnDesign = this.findDesignsByUserIdAndDesignName(userId, designName);
		trnDesignRepo.delete(trnDesign);
	}

	public void delete(Integer id) {
    	trnDesignRepo.delete(id);
    }

}
