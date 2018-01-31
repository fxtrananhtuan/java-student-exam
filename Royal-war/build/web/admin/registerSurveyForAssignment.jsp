<%-- 
    Document   : registerSurveyForAssignment
    Created on : Dec 5, 2011, 6:52:07 PM
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
    <rich:dataTable id="assignmentTable" var="list" value="#{mBRegisterStudentForAssignment.listAssignment}" width="100%" rows="5" columnClasses="center">
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
            <a4j:commandLink id="editSurvey" action="#{mBRegisterSurvey.registSurvey()}" reRender="surTable">
                <h:graphicImage value="/icon/plus.png" width="30" height="30"></h:graphicImage>
                <f:setPropertyActionListener value="#{list}" target="#{mBRegisterSurvey.assignment}"/>
            </a4j:commandLink>
            <rich:toolTip for="editSurvey" value="Edit Survey of This Assignment"></rich:toolTip>                
        </rich:column>

        <!--                    PHÂN TRANG-->
        <f:facet name="footer">
            <rich:datascroller maxPages="3" renderIfSinglePage="false"></rich:datascroller>
        </f:facet>    
    </rich:dataTable>
</h:form>

<!-- Panel Survey  -->

<h:form>
    <a4j:outputPanel ajaxRendered="true" rendered="true">
        <p:growl showSummary="true" showDetail="true" life="5000"></p:growl>
        <rich:panel id="surveyPanel" rendered="#{not empty mBRegisterSurvey.listSurvey}" headerClass="cuong">
            <f:facet name="header">
                <h:outputText value="List survey can be added to assignment"/>
            </f:facet>
            <rich:dataTable id="surTable" value="#{mBRegisterSurvey.listSurvey}"  var="survey" columnClasses="center" rows="2" width="100%" >              
                <rich:column width="50%" filterBy="#{survey.question}" sortBy="#{survey.question}" >
                    <f:facet name="header">
                        <h:outputText value="Question"/>
                    </f:facet>
                    <h:outputText value="#{survey.question}"/>
                </rich:column>
                <rich:column  width="50%">
                    <f:facet name="header">
                        <h:outputText value="Action"/>
                    </f:facet>
                    <h:selectBooleanCheckbox value="#{mBRegisterSurvey.check[survey]}"/>
                </rich:column>
                <!--                    PHÂN TRANG-->
                <f:facet name="footer">
                    <rich:datascroller ajaxSingle="false" maxPages="3" renderIfSinglePage="false"></rich:datascroller>
                </f:facet>
            </rich:dataTable>

        </rich:panel>
        <a4j:commandButton value="Submit" action="#{mBRegisterSurvey.addSurvey()}"/>
    </a4j:outputPanel>
</h:form>