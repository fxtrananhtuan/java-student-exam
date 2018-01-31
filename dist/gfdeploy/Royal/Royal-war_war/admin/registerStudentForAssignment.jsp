<%-- 
    Document   : registerStudentForAssignment
    Created on : Dec 5, 2011, 6:50:23 PM
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
            <a4j:commandLink id="rStudent" action="#{mBRegisterStudentForAssignment.registerStudent()}" reRender="studTable">
                <h:graphicImage value="/icon/plus.png" width="30" height="30"></h:graphicImage>
                <f:setPropertyActionListener value="#{list}" target="#{mBRegisterStudentForAssignment.assignment}"/>
                <f:setPropertyActionListener value="#{1}" target="#{mBRegisterStudentForAssignment.method}"/>
            </a4j:commandLink>
            <rich:toolTip for="rStudent" value="Add student for this assignment"></rich:toolTip> 
            <a4j:commandLink id="eStudent" action="#{mBRegisterStudentForAssignment.listRegisted()}" reRender="studTable">
                <h:graphicImage value="/icon/notebook.png" width="30" height="30"></h:graphicImage>
                <f:setPropertyActionListener value="#{list}" target="#{mBRegisterStudentForAssignment.assignment}"/>
                <f:setPropertyActionListener value="#{2}" target="#{mBRegisterStudentForAssignment.method}"/>
            </a4j:commandLink>
            <rich:toolTip for="eStudent" value="Edit student for this assignment"></rich:toolTip>    
        </rich:column>

        <!--                    PHÂN TRANG-->
        <f:facet name="footer">
            <rich:datascroller maxPages="3" renderIfSinglePage="false"></rich:datascroller>
        </f:facet>    
    </rich:dataTable>
</h:form>

<h:form>
    <a4j:outputPanel ajaxRendered="true" rendered="true">
        <p:growl showSummary="true" showDetail="true" life="5000"></p:growl>

        <rich:panel id="studPanel" rendered="#{not empty mBRegisterStudentForAssignment.listStudent}" headerClass="cuong">
            <f:facet name="header">
                <h:outputText value="List student can be added to assignment"/>
            </f:facet>
            <rich:dataTable width="100%" id="studTable" value="#{mBRegisterStudentForAssignment.listStudent}"  var="listStud" rows="2" columnClasses="center">
                <rich:column  sortBy="#{listStud.studentID}" filterBy="#{listStud.studentID}" filterEvent="onkeyup" headerClass="cuong1">
                    <f:facet name="header">
                        <h:outputLabel value="Student ID"></h:outputLabel>
                    </f:facet> 
                    <h:outputText value="#{listStud.studentID}"></h:outputText>               
                </rich:column>
                <rich:column  sortBy="#{listStud.fullName}" filterBy="#{listStud.fullName}" filterEvent="onkeyup" headerClass="cuong1">
                    <f:facet name="header">
                        <h:outputLabel value="FullName"></h:outputLabel>
                    </f:facet> 
                    <h:outputText value="#{listStud.fullName}"></h:outputText>               
                </rich:column>
                <rich:column  sortBy="#{listStud.email}" filterBy="#{listStud.email}" filterEvent="onkeyup" headerClass="cuong1">
                    <f:facet name="header">
                        <h:outputLabel value="Email"></h:outputLabel>
                    </f:facet> 
                    <h:outputText value="#{listStud.email}"></h:outputText>               
                </rich:column>
                <rich:column  sortBy="#{listStud.batchID.batchName}" filterBy="#{listStud.batchID.batchName}" filterEvent="onkeyup" headerClass="cuong1">
                    <f:facet name="header">
                        <h:outputLabel value="Batch"></h:outputLabel>
                    </f:facet> 
                    <h:outputText value="#{listStud.batchID.batchName}"></h:outputText>               
                </rich:column>
                <rich:column  sortBy="#{listStud.batchID.courseID.courseName}" filterBy="#{listStud.batchID.courseID.courseName}" filterEvent="onkeyup" headerClass="cuong1">
                    <f:facet name="header">
                        <h:outputLabel value="Course"></h:outputLabel>
                    </f:facet> 
                    <h:outputText value="#{listStud.batchID.courseID.courseName}"></h:outputText>               
                </rich:column>
                <rich:column  sortBy="#{listStud.batchID.courseID.courseName}" filterBy="#{listStud.batchID.courseID.courseName}" filterEvent="onkeyup" headerClass="cuong1">
                    <f:facet name="header">
                        <h:outputLabel value="Action"></h:outputLabel>
                    </f:facet> 
                    <h:selectBooleanCheckbox value="#{mBRegisterStudentForAssignment.checked[listStud]}" >
                    </h:selectBooleanCheckbox>
                </rich:column>
                <f:facet name="footer">
                    <rich:datascroller maxPages="3" ajaxSingle="false" renderIfSinglePage="true"  ></rich:datascroller>
                </f:facet>
            </rich:dataTable>
        </rich:panel>
        <a4j:commandButton action="#{mBRegisterStudentForAssignment.manipulateAssginment()}" value="Submit"/>
    </a4j:outputPanel>
</h:form>
