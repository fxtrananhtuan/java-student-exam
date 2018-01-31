package beanInfo;

import beanInfo.TbMarkPK;
import beanInfo.TbStudent;
import beanInfo.TbSubject;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.2.0.v20110202-r8913", date="2011-12-09T16:45:51")
@StaticMetamodel(TbMark.class)
public class TbMark_ { 

    public static volatile SingularAttribute<TbMark, TbStudent> tbStudent;
    public static volatile SingularAttribute<TbMark, TbSubject> tbSubject;
    public static volatile SingularAttribute<TbMark, TbMarkPK> tbMarkPK;
    public static volatile SingularAttribute<TbMark, Integer> mark;

}