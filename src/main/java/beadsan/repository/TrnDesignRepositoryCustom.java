/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beadsan.repository;

import beadsan.entity.TrnDesign;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author ko-aoki
 */
public interface TrnDesignRepositoryCustom {

    Page<TrnDesign> selectByNameAndTag(Pageable pageable, @Param("designName") String designName, @Param("tag") String tag);

}