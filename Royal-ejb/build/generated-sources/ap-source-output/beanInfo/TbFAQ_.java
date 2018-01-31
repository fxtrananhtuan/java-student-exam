package beanInfo;

import beanInfo.TbAccount;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.2.0.v20110202-r8913", date="2011-12-09T16:45:51")
@StaticMetamodel(TbFAQ.class)
public class TbFAQ_ { 

    public static volatile SingularAttribute<TbFAQ, Integer> faqid;
    public static volatile SingularAttribute<TbFAQ, String> answer;
    public static volatile SingularAttribute<TbFAQ, TbAccount> userName;
    public static volatile SingularAttribute<TbFAQ, String> question;

}