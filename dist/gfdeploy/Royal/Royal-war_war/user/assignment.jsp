<%-- 
    Document   : assignment
    Created on : Nov 15, 2011, 4:28:29 PM
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
                        <h:selectOneMenu value="#{mBUserAssignment.assignmentID}">
                            <f:selectItem itemLabel="--Choose Your Assignment--" itemValue="#{0}"/>
                            <f:selectItems value="#{mBUserAssignment.listAssignment}"/>
                        </h:selectOneMenu>
                        <br/>
                        <br/>
                        <h:commandButton value="Fetch Details" action="#{mBUserAssignment.fetchDetail()}">
                        </h:commandButton>
                    </rich:panel>
                    <h:inputHidden />
                    <rich:panel rendered="#{not empty mBUserAssignment.assignment}" style="height:150px;align:right">
                        <f:facet name="header">
                            <h:outputText value="Your Assignment Information"/>
                        </f:facet>
                        <table>
                            <tr>
                                <td align="left"><h:outputText value="Subject"/></td>
                                <td align="center"><h:outputText value="#{mBUserAssignment.assignment.subjectID.subjectName}"/></td>
                            </tr>
                            <tr>
                                <td align="left"><h:outputText value="End Date:"/></td>
                                <td align="center">
                                    <h:outputText value="#{mBUserAssignment.assignment.endDate}">
                                        <f:convertDateTime dateStyle="short" pattern="dd-MM-yyyy"/>
                                    </h:outputText></td>
                            </tr>
                            <tr>
                                <td align="left">Download Here:</td>
                                <td align="center">
                                    <a4j:commandLink value="Your Assignment" action="#{mBUserAssignment.toDownload()}" oncomplete="#{rich:component('confirmDownload')}.show()">
                                        <f:setPropertyActionListener value="#{mBUserAssignment.assignment.assignmentFile}" target="#{mBUserAssignment.n}"/>
                                        <f:setPropertyActionListener value="/admin/assignment" target="#{mBUserAssignment.p}"/>
                                    </a4j:commandLink>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2" style="color: red"><h:outputText value="(*)Note:Your assignment must be submit on same day of end date"/></td>
                            </tr>
                        </table>
                    </rich:panel>
                </h:panelGrid>
            </center>
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