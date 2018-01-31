package beanInfo;

import beanInfo.TbAccount;
import beanInfo.TbStudentAssignment;
import beanInfo.TbSubject;
import beanInfo.TbSurvey;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.2.0.v20110202-r8913", date="2011-12-09T16:45:51")
@StaticMetamodel(TbAssignment.class)
public class TbAssignment_ { 

    public static volatile SingularAttribute<TbAssignment, Date> startDate;
    public static volatile ListAttribute<TbAssignment, TbStudentAssignment> tbStudentAssignmentList;
    public static volatile SingularAttribute<TbAssignment, TbSubject> subjectID;
    public static volatile SingularAttribute<TbAssignment, String> assignmentFile;
    public static volatile SingularAttribute<TbAssignment, Integer> assignmentID;
    public static volatile SingularAttribute<TbAssignment, Boolean> active;
    public static volatile ListAttribute<TbAssignment, TbSurvey> tbSurveyList;
    public static volatile SingularAttribute<TbAssignment, TbAccount> userName;
    public static volatile SingularAttribute<TbAssignment, Date> endDate;

}