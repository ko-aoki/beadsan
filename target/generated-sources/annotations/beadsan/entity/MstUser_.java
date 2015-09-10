package beadsan.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MstUser.class)
public abstract class MstUser_ {

	public static volatile SingularAttribute<MstUser, String> firstName;
	public static volatile SingularAttribute<MstUser, String> lastName;
	public static volatile CollectionAttribute<MstUser, TrnDesign> trnDesignCollection;
	public static volatile SingularAttribute<MstUser, Date> updateDate;
	public static volatile SingularAttribute<MstUser, String> nickName;
	public static volatile SingularAttribute<MstUser, Integer> versionNo;
	public static volatile SingularAttribute<MstUser, Date> insertDate;
	public static volatile SingularAttribute<MstUser, Integer> mstUserId;
	public static volatile SingularAttribute<MstUser, String> mailAddress;

}

