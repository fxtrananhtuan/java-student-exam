package beanInfo;

import beanInfo.TbAccount;
import beanInfo.TbAssignment;
import beanInfo.TbSurveyAnswer;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.2.0.v20110202-r8913", date="2011-12-09T16:45:51")
@StaticMetamodel(TbSurvey.class)
public class TbSurvey_ { 

    public static volatile ListAttribute<TbSurvey, TbAssignment> tbAssignmentList;
    public static volatile SingularAttribute<TbSurvey, Integer> surveyID;
    public static volatile SingularAttribute<TbSurvey, Boolean> status;
    public static volatile ListAttribute<TbSurvey, TbSurveyAnswer> tbSurveyAnswerList;
    public static volatile SingularAttribute<TbSurvey, TbAccount> userName;
    public static volatile SingularAttribute<TbSurvey, String> question;

}