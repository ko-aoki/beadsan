package beadsan.service;

import beadsan.dto.DesignDto;
import beadsan.entity.MstUser;
import beadsan.entity.TrnDesign;
import beadsan.entity.TrnFavoriteDesign;
import beadsan.repository.MstUserRepository;
import beadsan.repository.TrnDesignRepository;
import beadsan.repository.TrnFavoriteDesignRepository;
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
public class FavoriteDesignService {

    @Autowired
    private TrnFavoriteDesignRepository trnFavoriteDesignRepo;
    @Autowired
    private TrnDesignRepository trnDesignRepo;
    @Autowired
    private MstUserRepository mstUserRepo;

    @Autowired
    protected Mapper mapper;

    public Page<DesignDto> findFavoriteDesignsByUserId(int userId, int curPage, int itemsPerPage) {

        Page<TrnFavoriteDesign> trnFavoriteDesigns = trnFavoriteDesignRepo.selectByMstUserIdOrderByUpdateDateDesc(
                new PageRequest(curPage - 1, itemsPerPage), userId);

        List<TrnFavoriteDesign> contents = trnFavoriteDesigns.getContent();
        ArrayList<DesignDto> designs = new ArrayList<DesignDto>();
        for (TrnFavoriteDesign content : contents) {
            DesignDto designDto = mapper.map(content.getTrnDesignId(), DesignDto.class);
            designDto.setFavoriteId(content.getTrnFavoriteDesignId());
            designs.add(designDto);
        }
        PageImpl page = new PageImpl(designs, new PageRequest(curPage - 1, 1), trnFavoriteDesigns.getTotalElements());
        return page;
    }

    public TrnFavoriteDesign save(TrnFavoriteDesign favorite) {

        // 既存チェック
        TrnFavoriteDesign existFavoriteDesign = trnFavoriteDesignRepo.selectByMstUserIdAndDesignId(
                favorite.getMstUserId().getMstUserId(), favorite.getTrnDesignId().getTrnDesignId());
        if (existFavoriteDesign == null) {
            // 新規作成
            MstUser mstUser = mstUserRepo.findOne(favorite.getMstUserId().getMstUserId());
            TrnDesign trnDesign = trnDesignRepo.findOne(favorite.getTrnDesignId().getTrnDesignId());
            favorite.setMstUserId(mstUser);
            favorite.setTrnDesignId(trnDesign);
            return trnFavoriteDesignRepo.save(favorite);
        }
        return existFavoriteDesign;
    }

    public void delete(Integer mstUserId, Integer id) {

        TrnFavoriteDesign trnFavoriteDesign = trnFavoriteDesignRepo.selectByMstUserIdAndDesignId(mstUserId, id);
        // 他ユーザのお気に入りを消さない
        if (trnFavoriteDesign != null) {
            trnFavoriteDesignRepo.delete(trnFavoriteDesign.getTrnFavoriteDesignId());
        } else {
            throw new RuntimeException("不正削除 " + "削除対象ユーザ：" + mstUserId + " デザインID：" + id);
        }
    }

    public long countFavoriteDesigns(int designId) {
        return trnFavoriteDesignRepo.count(designId);
    }

}
