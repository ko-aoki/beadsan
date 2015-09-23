/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beadsan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import beadsan.entity.MstUser;

/**
 *
 * @author ko-aoki
 */
public interface MstUserRepository extends JpaRepository<MstUser, Integer> {

	MstUser findByMailAddress(@Param("mailAddress") String mailAddress);

}