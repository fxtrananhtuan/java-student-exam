<%-- 
    Document   : subjectManager
    Created on : Nov 16, 2011, 8:18:08 AM
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
    <h2 class="ico_mug">SubjectManager</h2>
    <rich:dataTable id="subjectTable" cellpadding="0" cellspacing="0" rowKeyVar="row" width="100%" border="0" rows="5" var="subject" value="#{mbSubjectController.listSubject()}" columnClasses="center">
        <rich:column width="10%">
            <f:facet name="header">
                <h:outputLabel value="SubjectID"/>
            </f:facet>
            <h:outputLabel value="#{subject.subjectID}"/>
        </rich:column>
        <rich:column width="30%" sortBy="#{subject.subjectName}" filterBy="#{subject.subjectName}" filterEvent="onkeyup">
            <f:facet name="header">
                <h:outputLabel value="SubjectName"/>
            </f:facet>
            <h:outputLabel value="#{subject.subjectName}"/>
        </rich:column>
        <rich:column width="20%" sortBy="#{subject.semID.semName}" filterBy="#{subject.semID.semName}" filterEvent="onkeyup">
            <f:facet name="header">
                <h:outputLabel value="Semester"/>
            </f:facet>
            <h:outputLabel value="#{subject.semID.semName}"/>
        </rich:column>
        <rich:column width="20%" sortBy="#{subject.getSemID().getCourseID().getCourseName()}" filterBy="#{subject.getSemID().getCourseID().getCourseName()}" filterEvent="onkeyup">
            <f:facet name="header">
                <h:outputLabel value="Course"/>
            </f:facet>
            <h:outputLabel value="#{subject.getSemID().getCourseID().getCourseName()}"/>
        </rich:column>
        <rich:column width="20%">
            <f:facet name="header">
                <h:outputLabel value="Action"/>
            </f:facet>

            <%--Update--%>
            <a4j:commandLink id="updateLink" oncomplete="#{rich:component('updatePanel')}.show()">
                <h:graphicImage value="icon/notebook.png" style="border:0;height:30px;width:30px"/>
                <f:setPropertyActionListener value="#{subject}" target="#{mbSubjectController.tbSubject}"/>
            </a4j:commandLink>
            <rich:toolTip for="updateLink" value="Update #{subject.subjectName}"/>

            <%--Delete--
            <a4j:commandLink id="deleteLink" oncomplete="#{rich:component('deletePanel')}.show()">
                <h:graphicImage value="icon/iconDelete.gif" style="border:0;height:30px;width:30px"/>
                <f:setPropertyActionListener value="#{subject}" target="#{mbSubjectController.tbSubject}"/>
            </a4j:commandLink>
            <rich:toolTip for="deleteLink" value="Delete #{subject.subjectName}"/>--%>
        </rich:column>

        <%--Phan trang--%>
        <f:facet name="footer">
            <rich:datascroller maxPages="4" renderIfSinglePage="false"></rich:datascroller>
        </f:facet>
    </rich:dataTable>

</h:form>

<%--Insert Subject--%>
<a4j:region renderRegionOnly="true">
    <rich:simpleTogglePanel switchType="ajax" label="Add New Subject" opened="false">
        <h:form>
            <a4j:outputPanel ajaxRendered="true" rendered="true">
                <p:growl globalOnly="true" life="5000" showDetail="true"/>
                <h:panelGrid columns="4">
                    <h:outputText value="SubjectName: "/>
                    <h:inputText id="subjectName" value="#{mbSubjectController.tbSubject1.subjectName}">
                        <rich:beanValidator summary="Error"/>
                    </h:inputText><b style="color:red">*</b>
                    <rich:message for="subjectName" style="color:red"/>

                    <h:outputText value="Course: "/>
                    <h:selectOneMenu value="#{mbSubjectController.courseID}" id="course" valueChangeListener="#{mbSubjectController.listSemester}">   
                        <f:selectItem itemLabel="choose Course..." itemValue="#{0}"/>
                        <f:selectItems value="#{mbSubjectController.listCourse}"></f:selectItems>
                        <a4j:support event="onchange" reRender="semester"/>
                        <rich:beanValidator summary="Error"/>
                    </h:selectOneMenu><b style="color:red">*</b>
                    <rich:message for="course" style="color:red"/>        
                    
                    <h:outputText value="Semester: "/>
                    <h:selectOneMenu value="#{mbSubjectController.semesterID}" id="semester">
                        <f:selectItem itemLabel="Choose Semester..." itemValue="#{0}"/>
                        <f:selectItems value="#{mbSubjectController.listSemester}"></f:selectItems>
                        <rich:beanValidator summary="Error"/>
                    </h:selectOneMenu><b style="color:red">*</b>
                    <rich:message for="semester" style="color:red"/>

                </h:panelGrid>

                    <a4j:commandButton value="Create" id="createbt" action="#{mbSubjectController.addNew()}" oncomplete="if(#{mbSubjectController.error==0})#{rich:component('successInsertPanel')}.show()"/>
                <rich:toolTip value="Add New Subject #{mbSubjectController.tbSubject1.subjectName}"/>
            </a4j:outputPanel>
        </h:form>
    </rich:simpleTogglePanel>
</a4j:region>

<%--Modal panel update--%>
<a4j:region renderRegionOnly="true">
    <rich:modalPanel id="updatePanel" autosized="false" width="300" height="200" moveable="true">
        <f:facet name="header">
            <h:outputText value="Update Subject"/>
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
                    <h:outputText value="SubjectID: "/><b style="color:red"></b>
                    <h:outputLabel value="#{mbSubjectController.tbSubject.subjectID}" id="subjectID"/>
                    <rich:message for="subjectID"/>
                    <h:outputText value="SubjectName: "/><b style="color:red">*</b>
                    <h:inputText value="#{mbSubjectController.tbSubject.subjectName}" id="subjectName">
                        <rich:beanValidator summary="Invalid"></rich:beanValidator>
                    </h:inputText>
                    <rich:message for="subjectName" style="color:red"/>
                    <h:outputText value="Semester: "/><b style="color:red"></b>
                    <h:outputLabel value="#{mbSubjectController.tbSubject.semID.semName}" id="semesterName"/>
                    <rich:message for="semesterName" style="color:red"/>

                </h:panelGrid>

                <a4j:commandButton value="Update" id="updatebt" action="#{mbSubjectController.updateSubject()}" oncomplete="if(#{mbSubjectController.error==0}){#{rich:component('updatePanel')}.hide();#{rich:component('successUpdatePanel')}.show()}" />
                <rich:toolTip for="updatebt" value="Update Subject #{mbSubjectController.tbSubject.subjectName}"/>
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
                <h:outputText value="Are you want to delete #{mbSubjectController.tbSubject.subjectName} ?"/>
            </h:panelGrid>

            <a4j:commandButton value="Yes" id="yesbt" action="#{mbSemesterController.deleteSemester}" reRender="subjectTable" oncomplete="if(#{mbSubjectController.error==0})#{rich:component('deletePanel')}.hide()" />
            <rich:toolTip for="yesbt" value="Delete Subject #{mbSubjectController.tbSubject.subjectName}"/>
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

            <a4j:commandButton value="Ok" reRender="subjectTable" oncomplete="#{rich:component('successInsertPanel')}.hide()">
                <f:setPropertyActionListener value="#{1}" target="#{mbSubjectController.error}"/>
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

            <a4j:commandButton value="Ok" reRender="subjectTable" oncomplete="#{rich:component('successUpdatePanel')}.hide()" >
                <f:setPropertyActionListener value="#{1}" target="#{mbSubjectController.error}"/>
            </a4j:commandButton>
        </a4j:outputPanel>
    </h:form>
</rich:modalPanel>
