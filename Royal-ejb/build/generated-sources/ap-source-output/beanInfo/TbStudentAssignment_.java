package beanInfo;

import beanInfo.TbAssignment;
import beanInfo.TbStudent;
import beanInfo.TbStudentAssignmentPK;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.2.0.v20110202-r8913", date="2011-12-09T16:45:51")
@StaticMetamodel(TbStudentAssignment.class)
public class TbStudentAssignment_ { 

    public static volatile SingularAttribute<TbStudentAssignment, TbStudent> tbStudent;
    public static volatile SingularAttribute<TbStudentAssignment, TbStudentAssignmentPK> tbStudentAssignmentPK;
    public static volatile SingularAttribute<TbStudentAssignment, String> fileUpload;
    public static volatile SingularAttribute<TbStudentAssignment, Integer> mark;
    public static volatile SingularAttribute<TbStudentAssignment, TbAssignment> tbAssignment;

}