package beanInfo;

import beanInfo.TbBatch;
import beanInfo.TbSemester;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.2.0.v20110202-r8913", date="2011-12-09T16:45:51")
@StaticMetamodel(TbCourse.class)
public class TbCourse_ { 

    public static volatile ListAttribute<TbCourse, TbSemester> tbSemesterList;
    public static volatile SingularAttribute<TbCourse, Integer> courseID;
    public static volatile ListAttribute<TbCourse, TbBatch> tbBatchList;
    public static volatile SingularAttribute<TbCourse, String> courseName;

}