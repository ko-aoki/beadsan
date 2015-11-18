/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beadsan.repository;

import beadsan.cqdto.DesignAndCountDto;
import beadsan.entity.TrnFavoriteDesign;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author ko-aoki
 */
public interface TrnFavoriteDesignRepository extends JpaRepository<TrnFavoriteDesign, Integer> {

    @Query("select t from TrnFavoriteDesign t where t.mstUserId.mstUserId = :mstUserId Order by t.updateDate desc")
    Page<TrnFavoriteDesign> selectByMstUserIdOrderByUpdateDateDesc(Pageable pageable, @Param("mstUserId") int mstUserId);

    @Query("select t from TrnFavoriteDesign t where t.mstUserId.mstUserId = :mstUserId and t.trnDesignId.trnDesignId = :designId")
    TrnFavoriteDesign selectByMstUserIdAndDesignId(@Param("mstUserId") int mstUserId, @Param("designId") int designId);

    @Query("select t from TrnFavoriteDesign t where t.mstUserId.mstUserId = :mstUserId and t.trnFavoriteDesignId = :favoriteDesignId")
    TrnFavoriteDesign selectByMstUserIdAndFavoriteDesignId(
            @Param("mstUserId") int mstUserId, @Param("favoriteDesignId") int favoriteDesignId);

    @Query("select COUNT(t) from TrnFavoriteDesign t where t.trnDesignId.trnDesignId = :designId")
    long count(@Param("designId") int designId);

    @Query("select NEW beadsan.cqdto.DesignAndCountDto(t.trnDesignId, count(t.trnDesignId.trnDesignId)) from TrnFavoriteDesign t" +
            " group by t.trnDesignId order by count(t.trnDesignId.trnDesignId) desc")
    Page<DesignAndCountDto> selectGroupByTrnDesignId(Pageable pageable);

}