/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package helperPack;

import beanInfo.TbStudent;
import javax.annotation.PostConstruct;

/**
 *
 * @author MesutOezil
 */
public class MBTempUser {

    
    private TbStudent stud;

    public TbStudent getStud() {
        return stud;
    }

    public void setStud(TbStudent stud) {
        this.stud = stud;
    }
    
    
    
    private String currentPage;

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    @PostConstruct
    public void init() {
        if (SessionTool.getSession("student") != null) {
            this.stud =(TbStudent) SessionTool.getSession("student");
        }
    }

    /** Creates a new instance of MBTempUser */
    public MBTempUser() {
        this.currentPage = "/user/home.jsp";
    }

    public String toHome() {
        this.currentPage = "/user/home.jsp";
        return "user";
    }

    public String toAssignments() {
        this.currentPage = "/user/assignment.jsp";
        return "user";
    }

    public String toFAQ() {
        this.currentPage = "/user/faq.jsp";
        return "user";
    }

    public String toFeedBack() {
        this.currentPage = "/user/feedback.jsp";
        return "user";
    }

    public String toFileUploading() {
        this.currentPage = "/user/fileuploading.jsp";
        return "user";
    }

    public String toMark() {
        this.currentPage = "/user/mark.jsp";
        return "user";
    }
}
