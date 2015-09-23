/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beadsan.repository;

import beadsan.entity.TrnDesign;
import beadsan.entity.TrnTag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author ko-aoki
 */
public interface TrnTagRepository extends JpaRepository<TrnTag, Integer> {

    @Query("select t from TrnTag t where t.trnDesignId.trnDesignId = :trnDesignId and t.mstTagId.mstTagId = :mstTagId")
   TrnTag findByTrnDesignIdAndMstTagId(@Param("trnDesignId") int trnDesignId, @Param("mstTagId") int mstTagId);
}