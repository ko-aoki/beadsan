package beadsan.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TrnDesign.class)
public abstract class TrnDesign_ {

	public static volatile SingularAttribute<TrnDesign, Date> updateDate;
	public static volatile SingularAttribute<TrnDesign, String> design;
	public static volatile SingularAttribute<TrnDesign, MstPalette> mstPaletteId;
	public static volatile SingularAttribute<TrnDesign, String> name;
	public static volatile SingularAttribute<TrnDesign, Integer> versionNo;
	public static volatile SingularAttribute<TrnDesign, Date> insertDate;
	public static volatile SingularAttribute<TrnDesign, MstUser> mstUserId;
	public static volatile SingularAttribute<TrnDesign, Integer> trnDesignId;

}

