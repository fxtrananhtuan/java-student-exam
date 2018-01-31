<%-- 
    Document   : fileuploading
    Created on : Nov 15, 2011, 4:28:53 PM
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
    <a4j:outputPanel ajaxRendered="true" rendered="true">
        <p:growl showSummary="true" showDetail="true" life="5000"></p:growl>
        <rich:panel style="padding:0" headerClass="outpanelHeader">
            <f:facet name="header">
                <rich:spacer height="4" />
            </f:facet>
            <h2 align="center"><h:outputText value="Assignment Section" /></h2>
            <center>
                <rich:separator align="center" width="29%" lineType="dashed"/> 
                <br/>
                <h:panelGrid columns="3" columnClasses="gridContent">
                    <rich:panel style="height:150px;width:250px">
                        <f:facet name="header">
                            <h:outputText value="Choose Assignment"/>
                        </f:facet>
                        <h:outputText value="Your Assignment:"/>              
                        <h:selectOneMenu value="#{mBUserFileUploading.assignmentID}">
                            <f:selectItem itemLabel="--Choose Your Assignment--" itemValue="#{0}"/>
                            <f:selectItems value="#{mBUserFileUploading.listAssignment}"/>
                        </h:selectOneMenu>
                        <br/>
                        <br/>
                        <a4j:commandButton value="Fetch Details" reRender="survey" action="#{mBUserFileUploading.fetchDetail()}"></a4j:commandButton>
                    </rich:panel>
                    <h:inputHidden />
                    <rich:panel id="up" rendered="#{not empty mBUserFileUploading.assignment}" style="height:250px;width:350px;align:right">
                        <f:facet name="header">
                            <h:outputText value="Your Assignment Information"/>
                        </f:facet>
                        <table>
                            <tr>
                                <td align="left"><h:outputText value="Subject"/></td>
                                <td align="center"><h:outputText value="#{mBUserFileUploading.assignment.subjectID.subjectName}"/></td>
                            </tr>
                            <tr>
                                <td align="left"><h:outputText value="End Date:"/></td>
                                <td align="center">
                                    <h:outputText value="#{mBUserFileUploading.assignment.endDate}">
                                        <f:convertDateTime dateStyle="short" pattern="dd-MM-yyyy"/> 
                                    </h:outputText></td>
                            </tr>
                            <tr>
                                <td align="left">Upload File:</td>
                                <td align="center">
                                    <a4j:region renderRegionOnly="true">
                                        <rich:fileUpload id="uploadControl" acceptedTypes="doc,docx" fileUploadListener="#{mBUserFileUploading.listener}" maxFilesQuantity="1" 
                                                         uploadControlLabel="Upload Now"
                                                         clearAllControlLabel="Remove" doneLabel="Your file uploaded successfull"  listHeight="50px" listWidth="200px">
                                            <a4j:support event="onchange" />
                                            <a4j:support event="onclear" reRender="uploadControl"/>
                                        </rich:fileUpload>
                                    </a4j:region>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2"><a4j:commandButton value="Submit" action="#{mBUserFileUploading.uploadAssignment()}" oncomplete="if(#{mBUserFileUploading.error==1}){#{rich:component('PanelSuccess')}.show();}"></a4j:commandButton></td>
                            </tr>
                            <tr>
                                <td colspan="2" style="color: red"><h:outputText value="(*)Note:Your assignment must be submit on same day of end date"/></td>
                            </tr>
                        </table>
                    </rich:panel>
                    <rich:panel id="survey">
                        <f:facet name="header">
                            <h:outputText value="Complete your survey now!!!"/>
                        </f:facet>
                        <rich:dataTable value="#{mBUserFileUploading.listSur}" var="survey" columnClasses="center" style="border: none;">
                            <rich:column style="border: none;">
                                <h:outputText value="#{survey.getQuestion()}"/>
                                <ul>
                                    <rich:dataTable value="#{survey.getTbSurveyAnswerList()}" var="ans" columnClasses="center" style="border: none;">
                                        <rich:column style="border: none;">
                                            <li type="square">  <h:outputText value="#{ans.answer}" /></li>
                                        </rich:column>
                                    </rich:dataTable>
                                </ul>
                            </rich:column>
                        </rich:dataTable>
                    </rich:panel>
                </h:panelGrid>
            </center>
        </rich:panel>
    </a4j:outputPanel>
</h:form>

<!-- Panel Upload Success-->
<rich:modalPanel id="PanelSuccess" height="150" headerClass="cuong">
    <f:facet name="header">
        <h:outputText value="Upload Successfully"></h:outputText>
    </f:facet>
    <f:facet name="controls">
        <h:panelGroup>
            <h:graphicImage value="/templateAdmin/img/cancel.png" style="cursor:pointer" id="close1"></h:graphicImage>
            <rich:componentControl for="PanelSuccess" attachTo="close1" operation="hide" event="onclick"></rich:componentControl>
        </h:panelGroup>
    </f:facet>
    <h:form>
        <h:panelGrid columns="2">
            <h:inputHidden/>
            <h:inputHidden/>
            <a4j:commandButton  value="Ok" style="width:100px" oncomplete="#{rich:component('PanelSuccess')}.hide()" reRender="up" >
                <f:setPropertyActionListener value="#{0}" target="#{mBUserFileUploading.error}"/>
            </a4j:commandButton>
            <a4j:commandButton  value="Cancel" style="width:100px" oncomplete="#{rich:component('PanelSuccess')}.hide()" reRender="up" >              
                <f:setPropertyActionListener value="#{0}" target="#{mBUserFileUploading.error}"/>
            </a4j:commandButton>
        </h:panelGrid>
    </h:form>
</rich:modalPanel>
