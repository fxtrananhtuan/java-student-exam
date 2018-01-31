package beanInfo;

import beanInfo.TbCourse;
import beanInfo.TbStudent;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.2.0.v20110202-r8913", date="2011-12-09T16:45:51")
@StaticMetamodel(TbBatch.class)
public class TbBatch_ { 

    public static volatile SingularAttribute<TbBatch, Date> startDate;
    public static volatile ListAttribute<TbBatch, TbStudent> tbStudentList;
    public static volatile SingularAttribute<TbBatch, TbCourse> courseID;
    public static volatile SingularAttribute<TbBatch, String> batchName;
    public static volatile SingularAttribute<TbBatch, Integer> batchID;
    public static volatile SingularAttribute<TbBatch, Date> endDate;

}