package beanInfo;

import beanInfo.TbAssignment;
import beanInfo.TbFAQ;
import beanInfo.TbRole;
import beanInfo.TbSurvey;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.2.0.v20110202-r8913", date="2011-12-09T16:45:51")
@StaticMetamodel(TbAccount.class)
public class TbAccount_ { 

    public static volatile ListAttribute<TbAccount, TbAssignment> tbAssignmentList;
    public static volatile SingularAttribute<TbAccount, String> phone;
    public static volatile SingularAttribute<TbAccount, String> address;
    public static volatile SingularAttribute<TbAccount, String> email;
    public static volatile SingularAttribute<TbAccount, Boolean> status;
    public static volatile ListAttribute<TbAccount, TbFAQ> tbFAQList;
    public static volatile SingularAttribute<TbAccount, Boolean> gender;
    public static volatile SingularAttribute<TbAccount, String> userName;
    public static volatile ListAttribute<TbAccount, TbSurvey> tbSurveyList;
    public static volatile SingularAttribute<TbAccount, String> fullName;
    public static volatile SingularAttribute<TbAccount, TbRole> roleID;
    public static volatile SingularAttribute<TbAccount, String> password;

}