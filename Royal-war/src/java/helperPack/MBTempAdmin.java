/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package helperPack;

import java.io.Serializable;
import javax.annotation.PostConstruct;

/**
 *
 * @author MesutOezil
 */
public class MBTempAdmin implements Serializable {

    private String currentPage;
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    @PostConstruct
    public void init() {
        if (SessionTool.getSession("username") != null) {
            this.username = SessionTool.getSession("username").toString();
        } else {
            this.username = "Guest";
        }
    }

    /** Creates a new instance of MBTempAdmin */
    public MBTempAdmin() {

        this.currentPage = "/admin/home.jsp";

    }

    public String toAccountManager() {

        if (SessionTool.getSession("isAdmin") == null) {
            return "";
        } else {
            this.currentPage = "/admin/accountManager.jsp";
            return "admin";
        }


    }

    public String toCourseManager() {
        if (SessionTool.getSession("isAdmin") == null) {
            return "";
        } else {
            this.currentPage = "/admin/courseManager.jsp";
            return "admin";
        }

    }

    public String toSemesterManager() {
        if (SessionTool.getSession("isAdmin") == null) {
            return "";
        } else {
            this.currentPage = "/admin/semesterManager.jsp";
            return "admin";
        }

    }

    public String toSubjectManager() {
        if (SessionTool.getSession("isAdmin") == null) {
            return "";
        } else {
            this.currentPage = "/admin/subjectManager.jsp";
            return "admin";
        }

    }

    public String toBatchManager() {
        if (SessionTool.getSession("isAdmin") == null) {
            return "";
        } else {
            this.currentPage = "/admin/batchManager.jsp";
            return "admin";
        }

    }

    public String toStudentManager() {
        if (SessionTool.getSession("isAdmin") == null) {
            return "";
        } else {
            this.currentPage = "/admin/studentManager.jsp";
            return "admin";
        }

    }

    public String toAssignmentManager() {
        this.currentPage = "/admin/assignmentManager.jsp";
        return "admin";
    }

    public String toViewFileUploaded() {
        this.currentPage = "/admin/viewFileUploaded.jsp";
        return "admin";
    }

    public String toMarkManager() {
        this.currentPage = "/admin/markManager.jsp";
        return "admin";
    }

    public String toFeedBackManager() {
        this.currentPage = "/admin/feedbackManager.jsp";
        return "admin";
    }

    public String toFAQManager() {
        this.currentPage = "/admin/faqManager.jsp";
        return "admin";
    }

    public String toRegisterStudentForAssignment(){
        this.currentPage="/admin/registerStudentForAssignment.jsp";
        return "admin";
    }
    
    public String toRegisterSurveyForAssignment(){
        this.currentPage="/admin/registerSurveyForAssignment.jsp";
        return "admin";
    }
    public String toQuestionManager() {
        if (SessionTool.getSession("isAdmin") == null) {
            return "";
        } else {
            this.currentPage = "/admin/questionManager.jsp";
            return "admin";
        }

    }

    public String toAnswerManager() {
        if (SessionTool.getSession("isAdmin") == null) {
            return "";
        } else {
            this.currentPage = "/admin/answerManager.jsp";
            return "admin";
        }

    }

    public String toSurveyStatistics() {
        if (SessionTool.getSession("isAdmin") == null) {
            return "";
        } else {
            this.currentPage = "/admin/surveyStatistics.jsp";
            return "admin";
        }


    }

    public String toChangePassword() {
        this.currentPage = "/admin/changePassword.jsp";
        return "admin";
    }

    public String checkLogin() {
        if (SessionTool.getSession("login") != null) {
            return "";
        } else {
            return "login";
        }

    }

    public String thu() {
        return this.currentPage = "/admin/surveyStatistics.jsp";
    }
}
