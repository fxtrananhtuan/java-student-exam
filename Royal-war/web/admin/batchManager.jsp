<%-- 
    Document   : batchManager
    Created on : Nov 16, 2011, 8:18:29 AM
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
    <h2 class="ico_mug">BatchManager</h2>
    <rich:dataTable id="batchTable" cellpadding="0" cellspacing="0" rowKeyVar="row" width="100%" border="0" rows="5" var="batch" value="#{mbBatchController.listBatch()}" columnClasses="center">
        <rich:column width="20%" sortBy="#{batch.batchName}" filterBy="#{batch.batchName}" filterEvent="onkeyup">
            <f:facet name="header">
                <h:outputLabel value="BatchName"/>
            </f:facet>
            <h:outputLabel value="#{batch.batchName}"/>
        </rich:column>
        <rich:column width="20%" sortBy="#{batch.courseID.courseName}" filterBy="#{batch.courseID.courseName}" filterEvent="onkeyup">
            <f:facet name="header">
                <h:outputLabel value="Course"/>
            </f:facet>
            <h:outputLabel value="#{batch.courseID.courseName}"/>
        </rich:column>
        <rich:column width="20%" sortBy="#{batch.startDate}" filterBy="#{batch.startDate}" filterEvent="onkeyup">
            <f:facet name="header">
                <h:outputLabel value="StartDate"/>
            </f:facet>
            <h:outputLabel value="#{batch.startDate}">
                <f:convertDateTime dateStyle="short" pattern="dd/MM/yyyy"/>
            </h:outputLabel>
        </rich:column>
        <rich:column width="20%" sortBy="#{batch.endDate}" filterBy="#{batch.endDate}" filterEvent="onkeyup">
            <f:facet name="header">
                <h:outputLabel value="EndDate"/>
            </f:facet>
            <h:outputLabel value="#{batch.endDate}">
                <f:convertDateTime dateStyle="short" pattern="dd/MM/yyyy"/>
            </h:outputLabel>
        </rich:column>
        <rich:column width="20%">
            <f:facet name="header">
                <h:outputLabel value="Action"/>
            </f:facet>

            <%--Update--%>
            <a4j:commandLink id="updateLink" oncomplete="#{rich:component('updatePanel')}.show()">
                <h:graphicImage value="icon/notebook.png" style="border:0;height:30px;width:30px"/>
                <f:setPropertyActionListener value="#{batch}" target="#{mbBatchController.tbBatch}"/>
            </a4j:commandLink>
            <rich:toolTip for="updateLink" value="Update #{batch.batchName}"/>

            <%--Delete--%>
            <a4j:commandLink id="deleteLink" oncomplete="#{rich:component('deletePanel')}.show()">
                <h:graphicImage value="icon/iconDelete.gif" style="border:0;height:30px;width:30px"/>
                <f:setPropertyActionListener value="#{batch}" target="#{mbBatchController.tbBatch}"/>
            </a4j:commandLink>
            <rich:toolTip for="deleteLink" value="Delete #{batch.batchName}"/>
        </rich:column>

        <%--Phan trang--%>
        <f:facet name="footer">
            <rich:datascroller maxPages="4" renderIfSinglePage="false"></rich:datascroller>
        </f:facet>
    </rich:dataTable>

</h:form>

<%--Insert Batch--%>
<a4j:region renderRegionOnly="true">
    <rich:simpleTogglePanel switchType="ajax" label="Add New Batch" opened="false" >
        <h:form>
            <a4j:outputPanel ajaxRendered="true">
                <p:growl globalOnly="true" life="5000" showDetail="true"/>



                <table>
                    <tr>
                        <td><h:outputText value="Course: "/></td>
                        <td>
                            <h:selectOneMenu value="#{mbBatchController.courseID}" id="course" valueChangeListener="#{mbBatchController.batchName}" >
                                <a4j:support event="onchange" reRender="batchName"/>
                                <f:selectItem itemLabel="Choose Course..." itemValue="0"/>
                                <f:selectItems value="#{mbBatchController.listCourseName()}"></f:selectItems>
                                <rich:beanValidator summary="Error"/>
                            </h:selectOneMenu>
                        </td>
                        <td width="150px">
                            <b style="color:red">*</b>
                            <rich:message for="course" style="color:red"/>
                        </td>
                        <td><h:outputText value="StartDay: "/></td>
                        <td>
                            <rich:calendar value="#{mbBatchController.tbBatch1.startDate}" id="startday" valueChangeListener="#{mbBatchController.batchName}">
                                <a4j:support event="oncollapse" reRender="batchName"/>
                            </rich:calendar>
                        </td>
                        <td width="150px">
                            <b style="color:red">*</b>
                            <rich:message for="startday" style="color:red"/>
                        </td>
                    </tr>

                    <tr>
                        <td><h:outputText value="Class: "/></td>
                        <td>
                            <h:selectOneMenu value="#{mbBatchController.chars}" id="char" valueChangeListener="#{mbBatchController.batchName}" >
                                <a4j:support event="onchange" reRender="batchName"/>
                                <f:selectItem itemLabel="Choose class..." itemValue=""/>
                                <f:selectItem itemLabel="A" itemValue="A"/>
                                <f:selectItem itemLabel="B" itemValue="B"/>
                                <f:selectItem itemLabel="C" itemValue="C"/>
                                <f:selectItem itemLabel="D" itemValue="D"/>
                                <f:selectItem itemLabel="E" itemValue="E"/>
                                <f:selectItem itemLabel="F" itemValue="F"/>
                                <rich:beanValidator summary="Error"/>
                            </h:selectOneMenu>
                            <rich:message for="char" style="color:red"/>
                        </td>
                        <td width="150px">
                            <b style="color:red">*</b>
                            <rich:message for="char" style="color:red"/>
                        </td>
                        <td><h:outputText value="EndDay: "/></td>
                        <td>
                            <rich:calendar value="#{mbBatchController.tbBatch1.endDate}" id="endday" >

                            </rich:calendar>
                        </td>
                        <td width="150px">
                            <b style="color:red">*</b>
                            <rich:message for="endday" style="color:red"/>
                        </td>
                    </tr>
                    <tr>
                        <td><h:outputText value="BatchName: "/></td>
                        <td><h:outputLabel id="batchName" value="#{mbBatchController.batchName}" >                       
                            </h:outputLabel>
                        </td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                </table>


                <a4j:commandButton value="Create" id="createbt" action="#{mbBatchController.addNew()}" oncomplete="if(#{mbBatchController.error==0})#{rich:component('successInsertPanel')}.show()"/>
                <br/><br/>
                <h:outputLabel value="(*) exist" style="color:red"/>

            </a4j:outputPanel>
        </h:form>
    </rich:simpleTogglePanel>
</a4j:region>

<%--Modal panel update--%>
<a4j:region renderRegionOnly="true">
    <rich:modalPanel id="updatePanel" autosized="false" width="300" height="300" moveable="true">
        <f:facet name="header">
            <h:outputText value="Update Batch"/>
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
                <table>
                    <tr>
                        <td><h:outputText value="BatchName: "/></td>
                        <td><h:outputText value="#{mbBatchController.tbBatch.batchName}" id="batchName"/></td>
                        <td width="100px"></td>
                    </tr>
                    <tr>
                        <td><h:outputText value="CourseName: "/></td>
                        <td><h:outputLabel value="#{mbBatchController.tbBatch.courseID.courseName}" id="courseName"/></td>
                        <td width="100px"></td>
                    </tr>
                    <tr>
                        <td><h:outputText value="StartDay: "/></td>
                        <td>
                            <rich:calendar value="#{mbBatchController.tbBatch.startDate}" id="startday"></rich:calendar>
                            </td>
                            <td width="100px">
                                <b style="color:red">*</b>
                            <rich:message for="startday" style="color:red"/>
                        </td>
                    </tr>
                    <tr>
                        <td><h:outputText value="EndtDay: "/></td>
                        <td>
                            <rich:calendar value="#{mbBatchController.tbBatch.endDate}" id="endday"></rich:calendar>
                            </td>
                            <td width="100px">
                                <b style="color:red">*</b>
                            <rich:message for="endday" style="color:red"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="3" align="center">
                            <a4j:commandButton value="Update" id="updatebt" action="#{mbBatchController.updateBatch()}" oncomplete="if(#{mbBatchController.error==0}){#{rich:component('updatePanel')}.hide();#{rich:component('successUpdatePanel')}.show()}" />
                        </td>

                    </tr>
                </table>


                <rich:toolTip for="updatebt" value="Update Batch #{mbBatchController.tbBatch.batchName}"/>
            </h:form>
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
    <h:form>
        <a4j:outputPanel ajaxRendered="true">
            <p:growl globalOnly="true" life="5000" showDetail="true"/>
            <h:panelGrid columns="1">
                <h:outputText value="Are you want to delete #{mbBatchController.tbBatch.batchName} ?"/>
            </h:panelGrid>

            <a4j:commandButton value="Yes" id="yesbt" action="#{mbBatchController.deleteBatch()}" oncomplete="if(#{mbBatchController.error==0}){#{rich:component('deletePanel')}.hide();#{rich:component('successDeletePanel')}.show()}if(#{mbBatchController.error==1}){#{rich:component('deletePanel')}.hide();#{rich:component('failedDeletePanel')}.show()}" />
            <rich:toolTip for="yesbt" value="Delete Batch #{mbBatchController.tbBatch.batchName}"/>
            <a4j:commandButton value="Cancel" reRender="batchTable" oncomplete="#{rich:component('deletePanel')}.hide()"/>
        </a4j:outputPanel>
    </h:form>
</rich:modalPanel>
</a4j:region>

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

            <a4j:commandButton value="Ok" reRender="batchTable" oncomplete="#{rich:component('successInsertPanel')}.hide()">
                <f:setPropertyActionListener value="#{1}" target="#{mbBatchController.error}"/>
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
        <center>
        <a4j:outputPanel ajaxRendered="true">
            <p:growl globalOnly="true" life="5000" showDetail="true"/>
            <h:panelGrid columns="1">
                <h:outputText value="Successful your Update Action!!!"/>
            </h:panelGrid>

            <a4j:commandButton value="Ok" reRender="batchTable" oncomplete="#{rich:component('successUpdatePanel')}.hide()">
                <f:setPropertyActionListener value="#{1}" target="#{mbBatchController.error}"/>
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

            <a4j:commandButton value="Ok" reRender="batchTable" oncomplete="#{rich:component('successDeletePanel')}.hide()">
                <f:setPropertyActionListener value="#{1}" target="#{mbBatchController.error}"/>
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
                <h:outputText value="This Batch Can't Delete, Please Delete All Student in this Batch first!!!!"/>
            </h:panelGrid>

            <a4j:commandButton value="Ok" reRender="batchTable" oncomplete="#{rich:component('failedDeletePanel')}.hide()">
                <f:setPropertyActionListener value="#{1}" target="#{mbBatchController.error}"/>
            </a4j:commandButton>
        </a4j:outputPanel>
        </center>
    </h:form>
</rich:modalPanel>
