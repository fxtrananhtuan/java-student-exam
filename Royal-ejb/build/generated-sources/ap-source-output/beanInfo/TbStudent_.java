package beanInfo;

import beanInfo.TbBatch;
import beanInfo.TbFeedback;
import beanInfo.TbMark;
import beanInfo.TbStudentAssignment;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.2.0.v20110202-r8913", date="2011-12-09T16:45:51")
@StaticMetamodel(TbStudent.class)
public class TbStudent_ { 

    public static volatile ListAttribute<TbStudent, TbStudentAssignment> tbStudentAssignmentList;
    public static volatile SingularAttribute<TbStudent, String> phone;
    public static volatile SingularAttribute<TbStudent, String> email;
    public static volatile SingularAttribute<TbStudent, String> address;
    public static volatile SingularAttribute<TbStudent, Boolean> status;
    public static volatile ListAttribute<TbStudent, TbMark> tbMarkList;
    public static volatile SingularAttribute<TbStudent, String> studentID;
    public static volatile ListAttribute<TbStudent, TbFeedback> tbFeedbackList;
    public static volatile SingularAttribute<TbStudent, TbBatch> batchID;
    public static volatile SingularAttribute<TbStudent, String> fullName;
    public static volatile SingularAttribute<TbStudent, String> password;

}