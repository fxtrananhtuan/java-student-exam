<%-- 
    Document   : viewFileUploaded
    Created on : Nov 16, 2011, 8:19:21 AM
    Author     : MesutOezil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="rich" uri="http://richfaces.org/rich" %>
<%@taglib prefix="p" uri="http://primefaces.prime.com.tr/ui" %>
<%@taglib prefix="a4j" uri="http://richfaces.org/a4j" %>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<h:form>
    <rich:dataTable id="assignmentTable" var="list" value="#{mBFileUploadManager.listAssignment}" width="100%" rows="5" columnClasses="center">
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
            <a4j:commandLink id="download" action="#{mBFileUploadManager.listUploadFile()}" reRender="surTable">
                <h:graphicImage value="/icon/download.png" width="30" height="30"></h:graphicImage>
                <f:setPropertyActionListener value="#{list}" target="#{mBFileUploadManager.assignment}"/>
            </a4j:commandLink>
            <rich:toolTip for="download" value="Download this Assignment"></rich:toolTip>                
        </rich:column>

        <!--                    PHÂN TRANG-->
        <f:facet name="footer">
            <rich:datascroller maxPages="3" renderIfSinglePage="false"></rich:datascroller>
        </f:facet>    
    </rich:dataTable>
</h:form>

<!--Table Download-->

<h:form>
    <a4j:outputPanel ajaxRendered="true" rendered="true">
        <p:growl showSummary="true" life="5000"></p:growl>
        <rich:panel id="downloadPanel" headerClass="cuong" rendered="#{not empty mBFileUploadManager.listSA}">
            <f:facet name="header">
                <h:outputText value="Assignment Information"/>
            </f:facet>
            <rich:dataTable value="#{mBFileUploadManager.listSA}" var="sa" columnClasses="center" rows="5" width="100%">
                <rich:column>
                    <f:facet name="header">
                        <h:outputText value="Assignment"/>
                    </f:facet>
                    <h:outputText value="#{sa.tbAssignment.assignmentID}"/>
                </rich:column>
                <rich:column>
                    <f:facet name="header">
                        <h:outputText value="Student ID"></h:outputText>
                    </f:facet>
                    <h:outputText value="#{sa.tbStudent.studentID}"/>
                </rich:column>
                <rich:column>
                    <f:facet name="header">
                        <h:outputText value="Student Name"></h:outputText>
                    </f:facet>
                    <h:outputText value="#{sa.tbStudent.fullName}"/>
                </rich:column>
                <rich:column>
                    <f:facet name="header">
                        <h:outputText value="File Uploaded"></h:outputText>
                    </f:facet>
                    <a4j:commandLink id="download" value="#{sa.fileUpload}" action="#{mBFileUploadManager.toDowndload()}" oncomplete="#{rich:component('confirmDownload')}.show()">
                        <f:setPropertyActionListener value="#{sa.fileUpload}" target="#{mBFileUploadManager.n}"/>
                        <f:setPropertyActionListener value="#{sa.tbStudent.studentID}" target="#{mBFileUploadManager.sub}"/>
                        <f:setPropertyActionListener value="/admin/studentUpload/#{sa.tbStudent.studentID}/#{sa.tbAssignment.getSubjectID().subjectName}" target="#{mBFileUploadManager.p}"/>
                    </a4j:commandLink>
                </rich:column>
            </rich:dataTable>
        </rich:panel>
    </a4j:outputPanel>
</h:form>

<!--Confirm Download-->
<rich:modalPanel id="confirmDownload" height="60" headerClass="cuong">
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