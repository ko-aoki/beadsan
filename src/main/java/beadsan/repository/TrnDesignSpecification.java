package beadsan.repository;

import beadsan.entity.MstTag;
import beadsan.entity.TrnDesign;
//import beadsan.entity.TrnDesign_;
//import beadsan.entity.TrnTag;
import beadsan.entity.TrnTag;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class TrnDesignSpecification {

    /**
     * 名称でデザインを検索する。前方一致。
     */
    public static Specification<TrnDesign> nameContains(String name) {
        return StringUtils.isEmpty(name) ? null : new Specification<TrnDesign>() {
            @Override
            public Predicate toPredicate(Root<TrnDesign> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.like(root.get("name"), name + "%");
            }
        };
    }

    /**
     * タグでデザインを検索する。中間一致。
     */
    public static Specification<TrnDesign> tagContains(String tag) {
        return StringUtils.isEmpty(tag) ? null : new Specification<TrnDesign>() {
            @Override
            public Predicate toPredicate(Root<TrnDesign> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

                Join<TrnDesign, TrnTag> trnTag = root.join("trnTagCollection");
                Join<TrnTag, MstTag> mstTag = trnTag.join("mstTagId");

                return cb.like(mstTag.get("name"), "%" + tag + "%");
            }
        };
    }
}