package beanInfo;

import beanInfo.TbCourse;
import beanInfo.TbSubject;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.2.0.v20110202-r8913", date="2011-12-09T16:45:51")
@StaticMetamodel(TbSemester.class)
public class TbSemester_ { 

    public static volatile SingularAttribute<TbSemester, Integer> semID;
    public static volatile SingularAttribute<TbSemester, TbCourse> courseID;
    public static volatile SingularAttribute<TbSemester, String> semName;
    public static volatile ListAttribute<TbSemester, TbSubject> tbSubjectList;

}