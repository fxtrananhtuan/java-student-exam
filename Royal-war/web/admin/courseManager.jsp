<%-- 
    Document   : courseManager
    Created on : Nov 16, 2011, 8:17:35 AM
    Author     : MesutOezil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@taglib prefix="rich" uri="http://richfaces.org/rich" %>
<%@taglib prefix="a4j" uri="http://richfaces.org/a4j" %>
<%@taglib prefix="p" uri="http://primefaces.prime.com.tr/ui" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<h:form>
    <h2 class="ico_mug">CourseManager</h2>
    <rich:dataTable id="courseTable" cellpadding="0" cellspacing="0" rowKeyVar="row" width="100%" border="0" rows="5" var="course" value="#{mbCourseController.listCourse()}" columnClasses="center">
        <rich:column width="10%">
            <f:facet name="header">
                <h:outputLabel value="STT"/>
            </f:facet>
            <h:outputLabel value="#{row+1}"/>
        </rich:column>
        <rich:column width="60%" sortBy="#{course.courseName}" filterBy="#{course.courseName}" filterEvent="onkeyup">
            <f:facet name="header">
                <h:outputLabel value="CourseName"/>
            </f:facet>
            <h:outputLabel value="#{course.courseName}"/>
        </rich:column>
        <rich:column width="30%">
            <f:facet name="header">
                <h:outputLabel value="Action"/>
            </f:facet>

            <%--Update--%>
            <a4j:commandLink id="updateLink"  oncomplete="#{rich:component('updatePanel')}.show()">
                <h:graphicImage value="icon/notebook.png" style="border:0;height:30px;width:30px"/>
                <f:setPropertyActionListener value="#{course}" target="#{mbCourseController.tbCourse}"/>
            </a4j:commandLink>
            <rich:toolTip for="updateLink" value="Update #{course.courseName}"/>

            <%--Delete--%>
            <a4j:commandLink id="deleteLink" oncomplete="#{rich:component('deletePanel')}.show()">
                <h:graphicImage value="icon/iconDelete.gif" style="border:0;height:30px;width:30px"/>
                <f:setPropertyActionListener value="#{course}" target="#{mbCourseController.tbCourse}"/>
            </a4j:commandLink>
            <rich:toolTip for="deleteLink" value="Delete #{course.courseName}"/>
        </rich:column>

        <%--Phan trang--%>
        <f:facet name="footer">
            <rich:datascroller maxPages="4" renderIfSinglePage="false"></rich:datascroller>
        </f:facet>
    </rich:dataTable>

</h:form>

<%--Insert Course--%>
<a4j:region renderRegionOnly="true">
    <rich:simpleTogglePanel switchType="ajax" label="Add New Course" opened="false">
        <h:form>
            <a4j:outputPanel ajaxRendered="true">
                <p:growl globalOnly="true" life="5000" showDetail="true"/>
                <h:panelGrid columns="4">
                    <h:outputText value="CourseName"/><b style="color:red">*</b>
                    <h:inputText id="courseName" value="#{mbCourseController.tbCourse1.courseName}">
                        <rich:beanValidator summary="Error"/>
                    </h:inputText>
                    <rich:message for="courseName" style="color:red"/>
                </h:panelGrid>

                    <a4j:commandButton value="Create" id="createbt" action="#{mbCourseController.addNew()}" oncomplete="if(#{mbCourseController.error==0})#{rich:component('successInsertPanel')}.show()"/>
                <rich:toolTip value="Add New Course #{mbCourseController.tbCourse.courseName}"/>
            </a4j:outputPanel>
        </h:form>
    </rich:simpleTogglePanel>
</a4j:region>




<%--Modal panel update--%>
<a4j:region renderRegionOnly="true">
    <rich:modalPanel id="updatePanel" autosized="false" width="250" height="150" moveable="true">
        <f:facet name="header">
            <h:outputText value="Update Course"/>
        </f:facet>
        <f:facet name="controls">
            <h:panelGroup>
                <h:graphicImage value="/icon/icondelete.jpg" id="close" height="25" width="25"/>
                <rich:componentControl for="updatePanel" attachTo="close" operation="hide" event="onclick"/>
            </h:panelGroup>
        </f:facet>

        <a4j:outputPanel ajaxRendered="true">
            <p:growl globalOnly="true" life="5000" showDetail="true"/>
            <center>
                <h:form>
                    <h:panelGrid columns="4" >
                        <h:outputText value="CourseID:"/><b style="color:red"></b>
                        <h:outputLabel value="#{mbCourseController.tbCourse.courseID}" id="courseID"/>
                        <rich:message for="courseID"/>
                        <h:outputText value="CourseName:"/><b style="color:red">(*)</b>
                        <h:inputText value="#{mbCourseController.tbCourse.courseName}" id="courseName">
                            <rich:beanValidator summary="Invalid Name"/>
                        </h:inputText>
                        <rich:message for="courseName" style="color:red" />
                    </h:panelGrid>

                    <a4j:commandButton value="Update" id="updatebt" action="#{mbCourseController.updateCourse()}" oncomplete="if(#{mbCourseController.error==0}){#{rich:component('updatePanel')}.hide();#{rich:component('successUpdatePanel')}.show()}" />
                    <rich:toolTip for="updatebt" value="Update Course #{mbCourseController.tbCourse.courseName}"/>
                </h:form>
            </center>
        </a4j:outputPanel>
    </rich:modalPanel>
</a4j:region>

<%--Modal panel Delete--%>
<a4j:region renderRegionOnly="true">
<rich:modalPanel id="deletePanel" autosized="false" width="300" height="100" moveable="true">
    <f:facet name="header">
        <h:outputText value="delete Course"/>
    </f:facet>
    <f:facet name="controls">
        <h:panelGroup>
            <h:graphicImage value="/icon/icondelete.jpg" id="closedelete" height="25" width="25"/>
            <rich:componentControl for="deletePanel" attachTo="closedelete" operation="hide" event="onclick"/>
        </h:panelGroup>
    </f:facet>
    <center>
        <h:form>
            <a4j:outputPanel ajaxRendered="true">
                <p:growl globalOnly="true" life="5000" showDetail="true"/>
                <h:panelGrid columns="1">
                    <h:outputText value="Are you want to delete #{mbCourseController.tbCourse.courseName} ?"/>
                </h:panelGrid>

                <a4j:commandButton value="Yes" id="yesbt" action="#{mbCourseController.deleteCourse()}" oncomplete="if(#{mbCourseController.error==0}){#{rich:component('deletePanel')}.hide();#{rich:component('successDeletePanel')}.show()}if(#{mbCourseController.error==1}){#{rich:component('deletePanel')}.hide();#{rich:component('failedDeletePanel')}.show()}if(#{mbCourseController.error==2}){#{rich:component('askDeletePanel')}.show()};#{rich:component('deletePanel')}.hide()">
                    
                </a4j:commandButton>
                <rich:toolTip for="yesbt" value="Delete Course #{mbCourseController.tbCourse.courseName}"/>
                <a4j:commandButton value="Cancel" oncomplete="#{rich:component('deletePanel')}.hide()"/>
            </a4j:outputPanel>
        </h:form>
    </center>
</rich:modalPanel>
</a4j:region>
<%--Modal panel insert Successful--%>
<rich:modalPanel id="successInsertPanel" autosized="false" width="300" height="100" moveable="true">
    <f:facet name="header">
        <h:outputText value="Insert successful"/>
    </f:facet>
    <h:form>
        <a4j:outputPanel ajaxRendered="true">
            <p:growl globalOnly="true" life="5000" showDetail="true"/>
            <h:panelGrid columns="1">
                <h:outputText value="Successful your Insert Action!!!"/>
            </h:panelGrid>

            <a4j:commandButton value="Ok" reRender="courseTable" oncomplete="#{rich:component('successInsertPanel')}.hide()">
                <f:setPropertyActionListener value="#{1}" target="#{mbCourseController.error}"/>
            </a4j:commandButton>
        </a4j:outputPanel>
    </h:form>
</rich:modalPanel>

<%--Modal panel update Successful--%>
<rich:modalPanel id="successUpdatePanel" autosized="false" width="300" height="100" moveable="true">
    <f:facet name="header">
        <h:outputText value="Update successful"/>
    </f:facet>
    <h:form>
        <a4j:outputPanel ajaxRendered="true">
            <p:growl globalOnly="true" life="5000" showDetail="true"/>
            <h:panelGrid columns="1">
                <h:outputText value="Successful your Update Action!!!"/>
            </h:panelGrid>

            <a4j:commandButton value="Ok" reRender="courseTable" oncomplete="#{rich:component('successUpdatePanel')}.hide()">
                <f:setPropertyActionListener value="#{1}" target="#{mbCourseController.error}"/>
            </a4j:commandButton>
        </a4j:outputPanel>
    </h:form>
</rich:modalPanel>

<%--Modal panel ask Delete--%>
<rich:modalPanel id="askDeletePanel" autosized="false" width="300" height="100" moveable="true">
    <f:facet name="header">
        <h:outputText value="Ask Delete"/>
    </f:facet>
    <h:form>
        <center>
        <a4j:outputPanel ajaxRendered="true">
            <p:growl globalOnly="true" life="5000" showDetail="true"/>
            <h:panelGrid columns="1">
                <h:outputText value="This Course have a semester or subject. Are you Want To Delete continue!!!"/>
            </h:panelGrid>

            <a4j:commandButton value="Yes" action="#{mbCourseController.askdeleteCourse()}" oncomplete="if(#{mbCourseController.error==0}){#{rich:component('askDeletePanel')}.hide();#{rich:component('successDeletePanel')}.show()}">
                
            </a4j:commandButton>
            <a4j:commandButton value="No" reRender="courseTable" oncomplete="#{rich:component('askDeletePanel')}.hide()">
                <f:setPropertyActionListener value="#{1}" target="#{mbCourseController.error}"/>
            </a4j:commandButton>
        </a4j:outputPanel>
        </center>
    </h:form>
</rich:modalPanel>

<%--Modal panel Delete Successful--%>
<rich:modalPanel id="successDeletePanel" autosized="false" width="300" height="100" moveable="true">
    <f:facet name="header">
        <h:outputText value="Delete successful"/>
    </f:facet>
    <h:form>
        <center>
        <a4j:outputPanel ajaxRendered="true">
            <p:growl globalOnly="true" life="5000" showDetail="true"/>
            <h:panelGrid columns="1">
                <h:outputText value="Successful your Delete Action!!!"/>
            </h:panelGrid>

            <a4j:commandButton value="Ok" reRender="courseTable" oncomplete="#{rich:component('successDeletePanel')}.hide()">
                <f:setPropertyActionListener value="#{1}" target="#{mbCourseController.error}"/>
            </a4j:commandButton>
        </a4j:outputPanel>
        </center>
    </h:form>
</rich:modalPanel>

<%--Modal panel Delete Failed--%>
<rich:modalPanel id="failedDeletePanel" autosized="false" width="300" height="100" moveable="true">
    <f:facet name="header">
        <h:outputText value="Delete failed"/>
    </f:facet>
    <h:form>
        <center>
        <a4j:outputPanel ajaxRendered="true">
            <p:growl globalOnly="true" life="5000" showDetail="true"/>
            <h:panelGrid columns="1">
                <h:outputText value="This Course Can't Delete, Please Delete All Student in this Batch of Course first!!!!"/>
            </h:panelGrid>

            <a4j:commandButton value="Ok" reRender="courseTable" oncomplete="#{rich:component('failedDeletePanel')}.hide()">
                <f:setPropertyActionListener value="#{1}" target="#{mbCourseController.error}"/>
            </a4j:commandButton>
        </a4j:outputPanel>
        </center>
    </h:form>
</rich:modalPanel>
