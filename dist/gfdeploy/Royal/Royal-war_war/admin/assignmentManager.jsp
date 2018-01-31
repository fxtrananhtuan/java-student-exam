<%-- 
    Document   : assignmentManager
    Created on : Nov 16, 2011, 8:19:03 AM
    Author     : MesutOezil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@taglib prefix="rich" uri="http://richfaces.org/rich" %>
<%@taglib prefix="p" uri="http://primefaces.prime.com.tr/ui" %>
<%@taglib prefix="a4j" uri="http://richfaces.org/a4j" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<!--Assignment Manager-->
<h:form>

    <rich:dataTable id="assignmentTable" var="list" value="#{mBAssignmentManager.listAssignment}" width="100%" rows="5" columnClasses="center">
        <rich:column sortBy="#{list.assignmentID}" filterBy="#{list.assignmentID}" filterEvent="onkeyup" headerClass="cuong1" >
            <f:facet name="header">
                <h:outputLabel value="Assignment"></h:outputLabel>
            </f:facet>
            <h:outputText value="#{list.assignmentID}"></h:outputText>
        </rich:column>   
        <rich:column sortBy="#{list.getSubjectID().getSubjectName()}" filterBy="#{list.getSubjectID().getSubjectName()}" filterEvent="onkeyup" headerClass="cuong1">
            <f:facet name="header">
                <h:outputLabel value="Subject"></h:outputLabel>
            </f:facet>            
            <h:outputText value="#{list.getSubjectID().getSubjectName()}"></h:outputText>            
        </rich:column>           

        <rich:column sortBy="#{list.startDate}" filterBy="#{list.startDate}" filterEvent="onkeyup" headerClass="cuong1">
            <f:facet name="header">
                <h:outputLabel value="Start Date"></h:outputLabel>
            </f:facet>
            <h:outputText value="#{list.startDate}">
                <f:convertDateTime dateStyle="sort" pattern="dd-MM-yyyy"/>
            </h:outputText>
        </rich:column>

        <rich:column sortBy="#{list.endDate}" filterBy="#{list.endDate}" filterEvent="onkeyup" headerClass="cuong1">
            <f:facet name="header">
                <h:outputLabel value="End Date"></h:outputLabel>
            </f:facet> 
            <h:outputText value="#{list.endDate}">
                <f:convertDateTime dateStyle="sort" pattern="dd-MM-yyyy"/>
            </h:outputText>               
        </rich:column> 
        <rich:column sortBy="#{list.convertStatus()}" filterBy="#{list.convertStatus()}" filterEvent="onkeyup" headerClass="cuong1">
            <f:facet name="header">
                <h:outputLabel value="Status"></h:outputLabel>
            </f:facet> 
            <h:outputText value="#{list.convertStatus()}"></h:outputText>               
        </rich:column> 
        <rich:column  sortBy="#{list.getUserName().getUserName()}" filterBy="#{list.getUserName().getUserName()}" filterEvent="onkeyup" headerClass="cuong1">
            <f:facet name="header">
                <h:outputLabel value="Uploader"></h:outputLabel>
            </f:facet> 
            <h:outputText value="#{list.getUserName().getUserName()}"></h:outputText>               
        </rich:column>
        <rich:column>
            <f:facet name="header">
                <h:outputLabel value="Action"></h:outputLabel>
            </f:facet>
            <a4j:commandLink id="uAssignment" oncomplete="#{rich:component('Panelupdate')}.show()">
                <h:graphicImage value="/icon/notebook.png" width="30" height="30"></h:graphicImage>
                <f:setPropertyActionListener value="#{list}" target="#{mBAssignmentManager.assignment}"/>
            </a4j:commandLink>
            <rich:toolTip for="uAssignment" value="Update Assignment"></rich:toolTip>
            <a4j:commandLink id="download" action="#{mBAssignmentManager.toDowndload()}" oncomplete="#{rich:component('confirmDownload')}.show()">
                <h:graphicImage value="/icon/download.png" width="30" height="30">
                </h:graphicImage>
                <f:setPropertyActionListener value="#{list.assignmentFile}" target="#{mBAssignmentManager.n}"/>
                <f:setPropertyActionListener value="/admin/assignment" target="#{mBAssignmentManager.p}"/>
            </a4j:commandLink>
            <rich:toolTip for="download" value="Downdload this file"></rich:toolTip>
            <a4j:commandLink id="delete"  oncomplete="#{rich:component('confirmDelete')}.show()">
                <h:graphicImage value="/icon/iconDelete.gif" width="30" height="30">
                </h:graphicImage>
                <f:setPropertyActionListener value="#{list}" target="#{mBAssignmentManager.assignmentToDel}"/>
            </a4j:commandLink>
            <rich:toolTip for="delete" value="Delete this assignment"></rich:toolTip>
        </rich:column>

        <!--                    PHÂN TRANG-->
        <f:facet name="footer">
            <rich:datascroller maxPages="3" renderIfSinglePage="false"></rich:datascroller>
        </f:facet>    
    </rich:dataTable>

</h:form>





<!-- Add Assignment-->
<div id="addPanel">
    <a4j:region renderRegionOnly="true">
        <h:form>
            <rich:panel id="addPanel">
                <table>
                    <tr>
                        <td><h:outputText value="Course:"/></td>
                        <td>
                            <h:selectOneMenu style="width:150px" value="#{mBAssignmentManager.courseID}" valueChangeListener="#{mBAssignmentManager.listSemeter}">
                                <f:selectItem itemLabel="Please Select Course" itemValue="#{0}"/>
                                <f:selectItems value="#{mBAssignmentManager.listCourse}"/>
                                <a4j:support event="onchange"  reRender="sem"/>
                            </h:selectOneMenu><b style="color:red">(*)</b>
                        </td>
                        <td width="100px"></td>
                        <td><h:outputText value="Start Date"/></td>
                        <td>
                            <rich:calendar datePattern="dd-MM-yyyy"  value="#{mBAssignmentManager.assignment.startDate}" />
                            <b style="color:red">(*)</b>
                        </td>
                    </tr>
                    <tr>
                        <td><h:outputText value="Semester:"/></td>
                        <td>
                            <h:selectOneMenu style="width:150px" id="sem" value="#{mBAssignmentManager.semID}" valueChangeListener="#{mBAssignmentManager.listSubject}">
                                <f:selectItem itemLabel="Please Select Semester" itemValue="#{0}"/>
                                <f:selectItems value="#{mBAssignmentManager.listSemeter}"/>
                                <a4j:support event="onchange" reRender="subject"/>
                            </h:selectOneMenu><b style="color:red">(*)</b>
                        </td>
                        <td width="100px"></td>
                        <td><h:outputText value="End Date"/></td>
                        <td >
                            <rich:calendar datePattern="dd-MM-yyyy"  value="#{mBAssignmentManager.assignment.endDate}" />
                            <b style="color:red">(*)</b>
                        </td>
                    </tr>
                    <tr>
                        <td><h:outputText value="Subject:"/></td>
                        <td>
                            <h:selectOneMenu style="width:150px" id="subject" value="#{mBAssignmentManager.subjectID}" >
                                <f:selectItem itemLabel="Please Select Subject" itemValue="#{0}"/>
                                <f:selectItems value="#{mBAssignmentManager.listSubject}"/>
                            </h:selectOneMenu><b style="color:red">(*)</b>
                        </td>
                        <td width="100px"></td>
                        <td rowspan="2">
                            <h:outputText value="Status"/>
                        </td>
                        <td rowspan="2">
                            <h:selectOneRadio value="#{mBAssignmentManager.assignment.active}" layout="pageDirection">
                                <f:selectItem itemLabel="Active" itemValue="True"/>
                                <f:selectItem itemLabel="InActive" itemValue="False"/>
                            </h:selectOneRadio>
                        </td>
                    </tr>
                    <tr>
                        <td><h:outputText value="Upload File"/></td>
                        <td>
                            <rich:fileUpload id="uploadControl" acceptedTypes="doc,docx" fileUploadListener="#{mBAssignmentManager.listener}" maxFilesQuantity="1" 
                                             uploadControlLabel="Upload Now"
                                             clearAllControlLabel="Remove" doneLabel="Your file uploaded successfull"  listHeight="50px">
                                <a4j:support event="onchange" />
                                <a4j:support event="onclear" reRender="uploadControl"/>
                            </rich:fileUpload>
                        </td>
                        <td width="100px"><b style="color:red">(*)</b></td>
                    </tr>
                </table>
                <a4j:outputPanel ajaxRendered="true" rendered="true">
                    <p:growl showSummary="true" showDetail="true" life="5000"></p:growl>
                    <a4j:commandButton value="Add Assignment" action="#{mBAssignmentManager.insertOneAssignment()}"
                                       oncomplete="if(#{mBAssignmentManager.error==1})#{rich:component('insertSuccess')}.show()"/>
                    <b style="color:red">(*):is required</b>
                </a4j:outputPanel>  
            </rich:panel>
        </h:form>
    </a4j:region>
</div>


<!-- Update Panel-->
<a4j:region renderRegionOnly="true">
    <rich:modalPanel id="Panelupdate" width="800" height="300" headerClass="cuong">
        <f:facet name="header">
            <h:outputText value="Update Assignment"></h:outputText>
        </f:facet>
        <f:facet name="controls">
            <h:panelGroup>
                <h:graphicImage value="/templateAdmin/img/cancel.png" style="cursor:pointer" id="close"></h:graphicImage>
                <rich:componentControl for="Panelupdate" attachTo="close" operation="hide" event="onclick"></rich:componentControl>
            </h:panelGroup>
        </f:facet>
        <h:form>
            <a4j:outputPanel ajaxRendered="true" rendered="true">
                <p:growl showSummary="true" life="5000"></p:growl>

                <fieldset style="background-color: white;border: 1px dashed black">
                    <legend style="font-weight: bold">Information</legend>
                    <rich:panel id="addPanel">
                        <table>
                            <tr>

                                <td><h:outputText value="Start Date"/></td>
                                <td>
                                    <rich:calendar datePattern="dd-MM-yyyy"  value="#{mBAssignmentManager.assignment.startDate}" />
                                </td>
                                <td width="100px"></td>
                                <td><h:outputText value="End Date"/></td>
                                <td>
                                    <rich:calendar datePattern="dd-MM-yyyy"  value="#{mBAssignmentManager.assignment.endDate}" />
                                </td>
                            </tr>
                            <tr>
                                <td><h:outputText value="Upload File"/></td>
                                <td colspan="2">
                                    <a4j:region renderRegionOnly="true">
                                        <rich:fileUpload id="uploadControl1" acceptedTypes="doc,docx" fileUploadListener="#{mBAssignmentManager.listener}" maxFilesQuantity="1" 
                                                         uploadControlLabel="Upload Now"
                                                         clearAllControlLabel="Remove" doneLabel="Your file uploaded successfull"  listHeight="50px">
                                            <a4j:support event="onchange" />
                                            <a4j:support event="onclear" reRender="uploadControl1"/>
                                        </rich:fileUpload>
                                    </a4j:region>
                                </td>
                                <td>
                                    <h:outputText value="Status"/>
                                </td>
                                <td>
                                    <h:selectOneRadio value="#{mBAssignmentManager.assignment.active}" layout="pageDirection">
                                        <f:selectItem itemLabel="Active" itemValue="True"/>
                                        <f:selectItem itemLabel="InActive" itemValue="False"/>
                                    </h:selectOneRadio>
                                </td>
                            </tr>
                        </table>
                        <a4j:outputPanel ajaxRendered="true" rendered="true">
                            <p:growl showSummary="true" showDetail="true" life="5000"></p:growl>
                            <a4j:commandButton value="Add Assignment" action="#{mBAssignmentManager.updateAssignment()}"
                                               oncomplete="if(#{mBAssignmentManager.error==1}){#{rich:component('updateSuccess')}.show();#{rich:component('Panelupdate')}.hide();}"/>
                        </a4j:outputPanel>  
                    </rich:panel>
                </fieldset>
            </a4j:outputPanel>
        </h:form>
    </rich:modalPanel>
</a4j:region>

<!--Panel Update Success-->

<rich:modalPanel id="updateSuccess" height="150" headerClass="cuong">
    <f:facet name="header">
        <h:outputText value="Update Successfully"></h:outputText>
    </f:facet>
    <f:facet name="controls">
        <h:panelGroup>
            <h:graphicImage value="/templateAdmin/img/cancel.png" style="cursor:pointer" id="close1"></h:graphicImage>
            <rich:componentControl for="updateSuccess" attachTo="close1" operation="hide" event="onclick"></rich:componentControl>
        </h:panelGroup>
    </f:facet>
    <h:form>
        <h:panelGrid columns="2">
            <h:inputHidden/>
            <h:inputHidden/>
            <a4j:commandButton  value="Ok" style="width:100px" oncomplete="#{rich:component('updateSuccess')}.hide()" reRender="assignmentTable">
                <f:setPropertyActionListener value="#{0}" target="#{mBAssignmentManager.error}"/>
            </a4j:commandButton>
            <a4j:commandButton  value="Cancel" style="width:100px" oncomplete="#{rich:component('updateSuccess')}.hide()" reRender="assignmentTable">
                <f:setPropertyActionListener value="#{0}" target="#{mBAssignmentManager.error}"/>
            </a4j:commandButton>
        </h:panelGrid>
    </h:form>
</rich:modalPanel>

<!--Insert Success-->

<rich:modalPanel id="insertSuccess" height="50" headerClass="cuong">
    <f:facet name="header">
        <h:outputText value="Insert Successfully"></h:outputText>
    </f:facet>
    <f:facet name="controls">
        <h:panelGroup>
            <h:graphicImage value="/templateAdmin/img/cancel.png" style="cursor:pointer" id="close2"></h:graphicImage>
            <rich:componentControl for="insertSuccess" attachTo="close2" operation="hide" event="onclick"></rich:componentControl>
        </h:panelGroup>
    </f:facet>
    <h:form>
        <h:panelGrid columns="2">
            <h:inputHidden/>
            <h:inputHidden/>
            <a4j:commandButton  value="Ok" style="width:100px" oncomplete="#{rich:component('insertSuccess')}.hide()" reRender="assignmentTable,addPanel">
                <f:setPropertyActionListener value="#{0}" target="#{mBAssignmentManager.error}"/>
            </a4j:commandButton>
            <a4j:commandButton  value="Cancel" style="width:100px" oncomplete="#{rich:component('insertSuccess')}.hide()" reRender="assignmentTable,addPanel">
                <f:setPropertyActionListener value="#{0}" target="#{mBAssignmentManager.error}"/>
            </a4j:commandButton>
        </h:panelGrid>
    </h:form>
</rich:modalPanel>

<!--Confirm Download-->

<rich:modalPanel id="confirmDownload" height="50" headerClass="cuong">
    <f:facet name="header">
        <h:outputText value="Do you want to download?"></h:outputText>
    </f:facet>
    <f:facet name="controls">
        <h:panelGroup>
            <h:graphicImage value="/templateAdmin/img/cancel.png" style="cursor:pointer" id="close3"></h:graphicImage>
            <rich:componentControl for="confirmDownload" attachTo="close3" operation="hide" event="onclick"></rich:componentControl>
        </h:panelGroup>
    </f:facet>
    <h:form>
        <h:commandButton value="Download" action="#{mBDownLoadFile.toDownload()}" />
    </h:form>
</rich:modalPanel>

<!--Confirm Delete-->
<a4j:region renderRegionOnly="true">
<rich:modalPanel id="confirmDelete" height="50" headerClass="cuong">
    <f:facet name="header">
        <h:outputText value="Do you want to delete?"></h:outputText>
    </f:facet>
    <f:facet name="controls">
        <h:panelGroup>
            <h:graphicImage value="/templateAdmin/img/cancel.png" style="cursor:pointer" id="close4"></h:graphicImage>
            <rich:componentControl for="confirmDelete" attachTo="close4" operation="hide" event="onclick"></rich:componentControl>
        </h:panelGroup>
    </f:facet>
    <h:form>
        <a4j:outputPanel ajaxRendered="true" rendered="true">
            <p:growl showSummary="true" showDetail="true" life="5000"></p:growl>
            <h:panelGrid columns="2">
                <h:inputHidden/>
                <h:inputHidden/>
                <a4j:commandButton  value="Delete" style="width:100px" action="#{mBAssignmentManager.toDelete()}" oncomplete="if(#{mBAssignmentManager.error==1}){#{rich:component('deleteSuccess')}.show();#{rich:component('confirmDelete')}.hide();}" >
                    <f:setPropertyActionListener value="#{0}" target="#{mBAssignmentManager.error}"/>
                </a4j:commandButton>
                <a4j:commandButton  value="Cancel" style="width:100px" oncomplete="#{rich:component('confirmDelete')}.hide()" reRender="assignmentTable">
                    <f:setPropertyActionListener value="#{0}" target="#{mBAssignmentManager.error}"/>
                </a4j:commandButton>
            </h:panelGrid>
        </a4j:outputPanel>
    </h:form>
</rich:modalPanel>
</a4j:region>

<!--Delete Success-->

<rich:modalPanel id="deleteSuccess" height="50" headerClass="cuong">
    <f:facet name="header">
        <h:outputText value="Delete Successfully"></h:outputText>
    </f:facet>
    <f:facet name="controls">
        <h:panelGroup>
            <h:graphicImage value="/templateAdmin/img/cancel.png" style="cursor:pointer" id="close5"></h:graphicImage>
            <rich:componentControl for="deleteSuccess" attachTo="close5" operation="hide" event="onclick"></rich:componentControl>
        </h:panelGroup>
    </f:facet>
    <h:form>
        <h:panelGrid columns="2">
            <h:inputHidden/>
            <h:inputHidden/>
            <a4j:commandButton  value="Ok" style="width:100px" oncomplete="#{rich:component('deleteSuccess')}.hide()" reRender="assignmentTable">
                <f:setPropertyActionListener value="#{0}" target="#{mBAssignmentManager.error}"/>
            </a4j:commandButton>
            <a4j:commandButton  value="Cancel" style="width:100px" oncomplete="#{rich:component('deleteSuccess')}.hide()" reRender="assignmentTable">
                <f:setPropertyActionListener value="#{0}" target="#{mBAssignmentManager.error}"/>
            </a4j:commandButton>
        </h:panelGrid>
    </h:form>
</rich:modalPanel>