package beadsan.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MstPalette.class)
public abstract class MstPalette_ {

	public static volatile CollectionAttribute<MstPalette, TrnDesign> trnDesignCollection;
	public static volatile SingularAttribute<MstPalette, Date> updateDate;
	public static volatile SingularAttribute<MstPalette, String> paletteName;
	public static volatile SingularAttribute<MstPalette, Integer> mstPaletteId;
	public static volatile SingularAttribute<MstPalette, Integer> versionNo;
	public static volatile SingularAttribute<MstPalette, Date> insertDate;
	public static volatile SingularAttribute<MstPalette, String> paletteCd;

}

