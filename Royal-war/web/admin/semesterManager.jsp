<%-- 
    Document   : semesterManager
    Created on : Nov 16, 2011, 8:17:53 AM
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
    <h2 class="ico_mug">SemesterManager</h2>
    <rich:dataTable id="semesterTable" cellpadding="0" cellspacing="0" rowKeyVar="row" width="100%" border="0" rows="5" var="semester" value="#{mbSemesterController.listSemester()}" columnClasses="center">
        <rich:column width="10%">
            <f:facet name="header">
                <h:outputLabel value="SemesterID"/>
            </f:facet>
            <h:outputLabel value="#{semester.semID}"/>
        </rich:column>
        <rich:column width="30%" sortBy="#{semester.semName}" filterBy="#{semester.semName}" filterEvent="onkeyup">
            <f:facet name="header">
                <h:outputLabel value="SemesterName"/>
            </f:facet>
            <h:outputLabel value="#{semester.semName}"/>
        </rich:column>
        <rich:column width="30%" sortBy="#{semester.courseID.courseName}" filterBy="#{semester.courseID.courseName}" filterEvent="onkeyup">
            <f:facet name="header">
                <h:outputLabel value="Course"/>
            </f:facet>
            <h:outputLabel value="#{semester.courseID.courseName}"/>
        </rich:column>
        <rich:column width="30%">
            <f:facet name="header">
                <h:outputLabel value="Action"/>
            </f:facet>

            <%--Update--%>
            <a4j:commandLink id="updateLink" oncomplete="#{rich:component('updatePanel')}.show()">
                <h:graphicImage value="icon/notebook.png" style="border:0;height:30px;width:30px"/>
                <f:setPropertyActionListener value="#{semester}" target="#{mbSemesterController.semester}"/>
            </a4j:commandLink>
            <rich:toolTip for="updateLink" value="Update #{semester.semName}"/>

            <%--Delete
            <a4j:commandLink id="deleteLink" oncomplete="#{rich:component('deletePanel')}.show()">
                <h:graphicImage value="icon/iconDelete.gif" style="border:0;height:30px;width:30px"/>
                <f:setPropertyActionListener value="#{semester}" target="#{mbSemesterController.semester}"/>
            </a4j:commandLink>
            <rich:toolTip for="deleteLink" value="Delete #{semester.semName}"/>--%>
        </rich:column>

        <%--Phan trang--%>
        <f:facet name="footer">
            <rich:datascroller maxPages="4" renderIfSinglePage="false"></rich:datascroller>
        </f:facet>
    </rich:dataTable>

</h:form>
<a4j:region renderRegionOnly="true">
    <%--Insert Semester--%>
    <rich:simpleTogglePanel switchType="ajax" label="Add New Semester" opened="false">
    <h:form>
        <a4j:outputPanel ajaxRendered="true">
            <p:growl globalOnly="true" life="5000" showDetail="true"/>
            <h:panelGrid columns="4">
                <h:outputText value="SemesterName: "/><b style="color:red">*</b>
                <h:inputText id="semesterName" value="#{mbSemesterController.semester1.semName}">
                    <rich:beanValidator summary="Error"/>
                </h:inputText>
                <rich:message for="semesterName" style="color:red"/>
                <h:outputText value="Course: "/><b style="color:red">*</b>
                <h:selectOneMenu value="#{mbSemesterController.courseID}" id="course" >
                    <f:selectItem itemLabel="Choose Course..." itemValue="0"/>
                    <f:selectItems value="#{mbSemesterController.listCourseName()}"></f:selectItems>
                    <rich:beanValidator summary="Error"/>
                </h:selectOneMenu>
                <rich:message for="course" style="color:red"/>
 
            </h:panelGrid>

                <a4j:commandButton value="Create" id="createbt" action="#{mbSemesterController.addNew()}" oncomplete="if(#{mbSemesterController.error==0})#{rich:component('successInsertPanel')}.show()"/>
                <rich:toolTip value="Add New Semester #{mbSemesterController.semester1.semName}"/>
        </a4j:outputPanel>
    </h:form>
</rich:simpleTogglePanel>
</a4j:region>
    <%--Modal panel update--%>
    <a4j:region renderRegionOnly="true">
<rich:modalPanel id="updatePanel" autosized="false" width="300" height="200" moveable="true">
    <f:facet name="header">
        <h:outputText value="Update Semester"/>
    </f:facet>
    <f:facet name="controls">
        <h:panelGroup>
            <h:graphicImage value="/icon/icondelete.jpg" id="close" height="25" width="25"/>
            <rich:componentControl for="updatePanel" attachTo="close" operation="hide" event="onclick"/>
        </h:panelGroup>
    </f:facet>

    <a4j:outputPanel ajaxRendered="true">
        <p:growl globalOnly="true" life="5000" showDetail="true"/>
        <h:form>
            <h:panelGrid columns="4">
                <h:outputText value="SemID: "/><b style="color:red"></b>
                <h:outputLabel value="#{mbSemesterController.semester.semID}" id="semID"/>
                <rich:message for="semID"/>
                <h:outputText value="SemName: "/><b style="color:red">*</b>
                <h:inputText value="#{mbSemesterController.semester.semName}" id="SemName">
                    <rich:beanValidator summary="Invalid"></rich:beanValidator>
                </h:inputText>
                <rich:message for="SemName" style="color:red"/>
                <h:outputText value="CourseName: "/><b style="color:red"></b>
                <h:outputLabel value="#{mbSemesterController.semester.courseID.courseName}" id="courseName"/>
                <rich:message for="courseName" style="color:red"/>
                
            </h:panelGrid>
            
            <a4j:commandButton value="Update" id="updatebt" action="#{mbSemesterController.updateSemester()}"  oncomplete="if(#{mbSemesterController.error==0}){#{rich:component('updatePanel')}.hide();#{rich:component('successUpdatePanel')}.show()}" />
            <rich:toolTip for="updatebt" value="Update Semester #{mbSemesterController.semester.semName}"/>
        </h:form>
    </a4j:outputPanel>
</rich:modalPanel>
    </a4j:region>
<%--Modal panel Delete--%>
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
    <h:form>
        <a4j:outputPanel ajaxRendered="true">
            <p:growl globalOnly="true" life="5000" showDetail="true"/>
            <h:panelGrid columns="1">
                <h:outputText value="Are you want to delete #{mbSemesterController.semester.semName} ?"/>
            </h:panelGrid>

            <a4j:commandButton value="Yes" id="yesbt" action="#{mbSemesterController.deleteSemester}" reRender="semesterTable" oncomplete="if(#{mbSemesterController.error==0})#{rich:component('deletePanel')}.hide()" />
            <rich:toolTip for="yesbt" value="Delete Semester #{mbSemesterController.semester.semName}"/>
            <a4j:commandButton value="Cancel" oncomplete="#{rich:component('deletePanel')}.hide()"/>
        </a4j:outputPanel>
    </h:form>
</rich:modalPanel>

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

            <a4j:commandButton value="Ok" reRender="semesterTable" oncomplete="#{rich:component('successInsertPanel')}.hide()">
                <f:setPropertyActionListener value="#{1}" target="#{mbSemesterController.error}"/>
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

            <a4j:commandButton value="Ok" reRender="semesterTable" oncomplete="#{rich:component('successUpdatePanel')}.hide()">
                <f:setPropertyActionListener value="#{1}" target="#{mbSemesterController.error}"/>
            </a4j:commandButton>
        </a4j:outputPanel>
    </h:form>
</rich:modalPanel>