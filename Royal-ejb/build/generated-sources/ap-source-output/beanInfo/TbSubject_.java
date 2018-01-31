package beanInfo;

import beanInfo.TbAssignment;
import beanInfo.TbMark;
import beanInfo.TbSemester;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.2.0.v20110202-r8913", date="2011-12-09T16:45:51")
@StaticMetamodel(TbSubject.class)
public class TbSubject_ { 

    public static volatile ListAttribute<TbSubject, TbAssignment> tbAssignmentList;
    public static volatile SingularAttribute<TbSubject, String> subjectName;
    public static volatile SingularAttribute<TbSubject, TbSemester> semID;
    public static volatile SingularAttribute<TbSubject, Integer> subjectID;
    public static volatile ListAttribute<TbSubject, TbMark> tbMarkList;

}