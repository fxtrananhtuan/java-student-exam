package beanInfo;

import beanInfo.TbSurvey;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.2.0.v20110202-r8913", date="2011-12-09T16:45:51")
@StaticMetamodel(TbSurveyAnswer.class)
public class TbSurveyAnswer_ { 

    public static volatile SingularAttribute<TbSurveyAnswer, Integer> count;
    public static volatile SingularAttribute<TbSurveyAnswer, TbSurvey> surveyID;
    public static volatile SingularAttribute<TbSurveyAnswer, Integer> answerID;
    public static volatile SingularAttribute<TbSurveyAnswer, String> answer;

}