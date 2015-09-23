/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beadsan.repository;

import beadsan.entity.MstTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

/**
 * @author ko-aoki
 */
public interface MstTagRepository extends JpaRepository<MstTag, Integer> {

    MstTag findByName(@Param("name") String name);

}