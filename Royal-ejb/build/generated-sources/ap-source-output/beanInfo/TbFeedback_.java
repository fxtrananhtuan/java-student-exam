package beanInfo;

import beanInfo.TbStudent;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.2.0.v20110202-r8913", date="2011-12-09T16:45:51")
@StaticMetamodel(TbFeedback.class)
public class TbFeedback_ { 

    public static volatile SingularAttribute<TbFeedback, String> feedbackContent;
    public static volatile SingularAttribute<TbFeedback, Integer> feedbackID;
    public static volatile SingularAttribute<TbFeedback, String> feedbackDate;
    public static volatile SingularAttribute<TbFeedback, Boolean> status;
    public static volatile SingularAttribute<TbFeedback, TbStudent> studentID;

}