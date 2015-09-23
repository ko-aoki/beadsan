package beadsan.repository;

import beadsan.entity.TrnDesign;
import beadsan.entity.TrnTag;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

/**
 * Created by ko-aoki on 2015/09/20.
 */
public class TrnDesignSpecification {

    public Specification<TrnDesign> nameContains(String name) {
        return StringUtils.isEmpty(name) ? null : new Specification<TrnDesign>() {
            @Override
            public Predicate toPredicate(Root<TrnDesign> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.like(root.get("name"), "%" + name + "%");
            }
        };
    }

// TODO JPA難しい。。
//    public Specification<TrnDesign> tagContains(String tag) {
//        return StringUtils.isEmpty(tag) ? null : new Specification<TrnDesign>() {
//            @Override
//            public Predicate toPredicate(Root<TrnDesign> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
//                Subquery<TrnDesign> sq = query.subquery(TrnDesign.class);
//                Root<TrnTag> ttRoot = sq.from(TrnTag.class);
//                sq.select(ttRoot.get(AB_.id).get(ABPK_.a));
//                sq.where(cb.equal(ab.get(AB_.id).get(ABPK_.b).get(B_.status), status));
//
//                Predicate p = cb.in(a).value(sq);
//                return cb.like(root.get("name"), "%" + name + "%");
//            }
//        };
//    }
}