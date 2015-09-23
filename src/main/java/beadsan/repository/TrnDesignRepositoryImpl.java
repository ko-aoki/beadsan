package beadsan.repository;

import beadsan.entity.TrnDesign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import javax.persistence.EntityManager;

/**
 * Created by ko-aoki on 2015/09/20.
 */
public class TrnDesignRepositoryImpl implements TrnDesignRepositoryCustom {

    @Autowired
    EntityManager entityManager;

    @Override
    public Page<TrnDesign> selectByNameAndTag(
            Pageable pageable, @Param("designName") String designName, @Param("tag") String tag) {

        return null;
    }

}
