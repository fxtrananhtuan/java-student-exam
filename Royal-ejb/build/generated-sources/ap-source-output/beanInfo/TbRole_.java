package beanInfo;

import beanInfo.TbAccount;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.2.0.v20110202-r8913", date="2011-12-09T16:45:51")
@StaticMetamodel(TbRole.class)
public class TbRole_ { 

    public static volatile ListAttribute<TbRole, TbAccount> tbAccountList;
    public static volatile SingularAttribute<TbRole, String> roleName;
    public static volatile SingularAttribute<TbRole, Integer> roleID;

}