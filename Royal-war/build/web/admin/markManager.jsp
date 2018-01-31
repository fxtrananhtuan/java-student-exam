<%-- 
    Document   : markManager
    Created on : Nov 16, 2011, 8:19:34 AM
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
    <h2 class="ico_mug">MarkAssignment</h2>
</h:form>
<a4j:region renderRegionOnly="true">
    <h:form>
        <a4j:outputPanel ajaxRendered="true">
        <p:growl globalOnly="true" life="5000" showDetail="true" showSummary="true"/>
        <h:outputText value="StudentID: "/>
        <h:inputText id="txtStudentID" value="#{mbMarkStudentController.studentID}"/>
        <a4j:commandButton action="#{mbMarkStudentController.doSemAndSubject()}" value="Submit" reRender="markTable"/>
    </a4j:outputPanel>
    </h:form>
</a4j:region>

<h:form>    
    <rich:dataTable id="markTable" cellpadding="0" cellspacing="0" rowKeyVar="row" width="100%" border="0" rows="5" var="mark" value="#{mbMarkStudentController.listMark}" columnClasses="center">
        <rich:column width="30%" sortBy="#{mark.tbSubject.semID.semName}" filterBy="#{mark.tbSubject.semID.semName}" filterEvent="onkeyup">
            <f:facet name="header">
                <h:outputLabel value="Semester"/>
            </f:facet>
            <h:outputLabel value="#{mark.tbSubject.semID.semName}"/>
        </rich:column>

        <rich:column width="30%" sortBy="#{mark.tbSubject.subjectName}" filterBy="#{mark.tbSubject.subjectName}" filterEvent="onkeyup">
            <f:facet name="header">
                <h:outputLabel value="Subject"/>
            </f:facet>
            <h:outputLabel value="#{mark.tbSubject.subjectName}"/>
        </rich:column>

        <rich:column width="25%" sortBy="#{mark.mark}" filterBy="#{mark.mark}" filterEvent="onkeyup">
            <f:facet name="header">
                <h:outputLabel value="Mark"/>
            </f:facet>
            <h:outputLabel value="#{mark.mark}"/>
        </rich:column>

        <rich:column width="15%">
            <f:facet name="header">
                <h:outputLabel value="Action"/>
            </f:facet>

            <%--Update--%>
            <a4j:commandLink id="updateLink" oncomplete="#{rich:component('updatePanel')}.show()">
                <h:graphicImage value="icon/notebook.png" style="border:0;height:30px;width:30px"/>
                <f:setPropertyActionListener value="#{mark}" target="#{mbMarkStudentController.tbmark}"/>
            </a4j:commandLink>
            <rich:toolTip for="updateLink" value="Update #{mark.tbSubject.subjectName}"/>

            <%--Delete
            <a4j:commandLink id="deleteLink" oncomplete="#{rich:component('deletePanel')}.show()">
                <h:graphicImage value="icon/iconDelete.gif" style="border:0;height:30px;width:30px"/>
                <f:setPropertyActionListener value="#{mark}" target="#{mbMarkStudentController.tbmark}"/>
            </a4j:commandLink>
            <rich:toolTip for="deleteLink" value="Delete"/>--%>
        </rich:column>

        <%--Phan trang--%>
        <f:facet name="footer">
            <rich:datascroller maxPages="3" renderIfSinglePage="false"></rich:datascroller>
        </f:facet>
    </rich:dataTable>

</h:form>

<%--Insert Mark--%>
<a4j:region renderRegionOnly="true">
    <rich:simpleTogglePanel id="addPanel" switchType="ajax" label="Add New Subject Mark" opened="false">
        <h:form>
            <a4j:outputPanel ajaxRendered="true" rendered="true">
                <p:growl globalOnly="true" life="5000" showDetail="true"/>
                <h:panelGrid columns="4">                   

                    <h:outputText value="StudentID: "/>
                    <h:outputText id="studentID" value="#{mbMarkStudentController.studentID}">

                    </h:outputText><b style="color:red"></b>
                    <rich:message for="sutdentID" style="color:red"/>

                    <h:outputText value="Semester: "/>
                    <h:selectOneMenu value="#{mbMarkStudentController.semID}" id="semester" valueChangeListener="#{mbMarkStudentController.listSubject}">   
                        <f:selectItem itemLabel="choose Semester..." itemValue="#{0}"/>
                        <f:selectItems value="#{mbMarkStudentController.listSemester}"></f:selectItems>
                        <a4j:support event="onchange" reRender="subject"/>
                        <rich:beanValidator summary="Error"/>
                    </h:selectOneMenu><b style="color:red">*</b>
                    <rich:message for="semester" style="color:red"/>        

                    <h:outputText value="Subject: "/>
                    <h:selectOneMenu value="#{mbMarkStudentController.subjectID}" id="subject">
                        <f:selectItem itemLabel="Choose Subject..." itemValue="#{0}"/>
                        <f:selectItems value="#{mbMarkStudentController.listSubject}"></f:selectItems>
                        <rich:beanValidator summary="Error"/>
                    </h:selectOneMenu><b style="color:red">*</b>
                    <rich:message for="subject" style="color:red"/>

                    <h:outputText value="Mark: "/>
                    <h:inputText id="mark" value="#{mbMarkStudentController.tbmark1.mark}">
                        <rich:beanValidator summary="Error"/>
                    </h:inputText><b style="color:red">*</b>
                    <rich:message for="mark" style="color:red"/>

                </h:panelGrid>

                <a4j:commandButton value="Create" id="createbt" action="#{mbMarkStudentController.addNew()}" oncomplete="if(#{mbMarkStudentController.error==0})#{rich:component('successInsertPanel')}.show()"/>
                <rich:toolTip value="Add New Subject Mark"/>
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
                    <h:outputText value="StudentID: "/><b style="color:red"></b>
                    <h:outputLabel value="#{mbMarkStudentController.tbmark.tbStudent.studentID}" id="StudentID"/>
                    <rich:message for="StudentID"/>
                    
                    <h:outputText value="SubjectName: "/><b style="color:red"></b>
                    <h:outputText value="#{mbMarkStudentController.tbmark.tbSubject.subjectName}" id="subjectName">                       
                    </h:outputText>
                    <rich:message for="subjectName" style="color:red"/>
                    
                    <h:outputText value="Mark: "/><b style="color:red">*</b>
                    <h:inputText value="#{mbMarkStudentController.tbmark.mark}" id="mark">
                        <rich:beanValidator summary="Error "/>
                    </h:inputText>
                    <rich:message for="mark" style="color:red"/>

                </h:panelGrid>

                    <a4j:commandButton value="Update" id="updatebt" action="#{mbMarkStudentController.updateMark}" oncomplete="if(#{mbMarkStudentController.error==0}){#{rich:component('updatePanel')}.hide();#{rich:component('successUpdatePanel')}.show()}" />
                <rich:toolTip for="updatebt" value="Update Subject Mark"/>
            </h:form>
        </a4j:outputPanel>
    </rich:modalPanel>
</a4j:region>

<%--Modal panel Delete--%>
<rich:modalPanel id="deletePanel" autosized="false" width="300" height="100" moveable="true">
    <f:facet name="header">
        <h:outputText value="delete Subject Mark"/>
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
                <h:outputText value="Are you want to delete ?"/>
            </h:panelGrid>

            <a4j:commandButton value="Yes" id="yesbt" action="#{#}" reRender="markTable" oncomplete="if(#{mbSubjectController.error==0})#{rich:component('deletePanel')}.hide()" />
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
        <center>
            <a4j:outputPanel ajaxRendered="true">
                <p:growl globalOnly="true" life="5000" showDetail="true"/>
                <h:panelGrid columns="1">
                    <h:outputText value="Successful your Insert Action!!!"/>
                </h:panelGrid>

                <a4j:commandButton value="Ok" reRender="markTable" oncomplete="#{rich:component('successInsertPanel')}.hide()">
                    <f:setPropertyActionListener value="#{1}" target="#{mbMarkStudentController.error}"/>
                </a4j:commandButton>
            </a4j:outputPanel>
        </center>
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

            <a4j:commandButton value="Ok" reRender="markTable" oncomplete="#{rich:component('successUpdatePanel')}.hide()" >
                <f:setPropertyActionListener value="#{1}" target="#{mbMarkStudentController.error}"/>
            </a4j:commandButton>
        </a4j:outputPanel>
    </h:form>
</rich:modalPanel>