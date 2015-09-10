/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beadsan.repository;

import beadsan.entity.TrnDesign;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author ko-aoki
 */
public interface DesignRepository extends JpaRepository<TrnDesign, Integer> {
//    Page<TrnDesign> findByMstUserIdOrderByUpdateDateAsc(Pageable pageable,MstUser mstUser);
	@Query("select t from TrnDesign t where t.mstUserId.mstUserId = :mstUserId Order by t.updateDate desc")
    Page<TrnDesign> selectByMstUserIdOrderByUpdateDateAsc(Pageable pageable, @Param("mstUserId") int mstUserId);

    @Query("select t from TrnDesign t where t.mstUserId.mstUserId = :mstUserId and t.name = :designName Order by t.updateDate desc")
    TrnDesign selectByMstUserIdAndDesignName(@Param("mstUserId") int mstUserId, @Param("designName") String DesignName);
}