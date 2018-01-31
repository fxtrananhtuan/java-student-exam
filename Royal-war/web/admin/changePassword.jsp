<%-- 
    Document   : changePassword
    Created on : Nov 19, 2011, 10:55:32 AM
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
<style>
    .cuong{
        background: #212121;
        border-color: #084577        
    }
    .cuong1{
        background: #d3d3d3
            border-color:#084577
    }
    .cuong2{
        background: #736356;
        border-color: #084577  
    }
</style>

<h:form>
    <a4j:outputPanel ajaxRendered="true" rendered="true">
        <p:growl showSummary="true" showDetail="true" life="5000"></p:growl>
        <rich:panel headerClass="cuong" >     
            <f:facet name="header">
                <h:outputText value="Change your Password"></h:outputText>
            </f:facet>
            <fieldset style="font-weight: bold"><legend>Update Information</legend>
                <h:outputText value="Old Password"></h:outputText><br/>
                <b style="color:red">(*)</b><br/>
                <h:inputSecret id="old" value="#{mBChangePass.oldPassword}">
                    <rich:beanValidator/>
                </h:inputSecret>
                <h:panelGroup id="fail1" styleClass="info_div" rendered="#{empty mBChangePass.incorrect}">
                    <span style="color: red" >
                        <h:outputText value="Old Password is incorrect!" rendered="#{empty mBChangePass.incorrect}"></h:outputText>
                    </span>
                </h:panelGroup>
                <h:message for="old" styleClass="displayerror"></h:message><br/>
                <h:outputText value="New Password"></h:outputText><br/>
                <b style="color:red">(*)</b><br/>
                <h:inputSecret id="new" value="#{mBChangePass.newPassword}">
                    <rich:beanValidator/>
                </h:inputSecret>
                <h:message for="new" styleClass="displayerror"></h:message><br/>
                <h:outputText value="Confirm Password"></h:outputText><br/>
                <b style="color:red">(*)</b><br/>
                <h:inputSecret id="confirm" value="#{mBChangePass.confirmPassword}">
                    <rich:beanValidator/>
                </h:inputSecret>
                <h:panelGroup id="fail" styleClass="info_div" rendered="#{empty mBChangePass.equal}">
                    <span style="color: red" >
                        <h:outputText value="Confirm password not match!" rendered="#{empty mBChangePass.equal}"></h:outputText>
                    </span>
                </h:panelGroup>
                <h:message for="confirm" styleClass="displayerror"></h:message><br/>
                <a4j:commandButton value="Submit" action="#{mBChangePass.change()}" oncomplete="if(#{mBChangePass.success!=null}){ #{rich:component('changePass')}.show();}">                 
                </a4j:commandButton><br/>
            </fieldset>
        </rich:panel>
    </a4j:outputPanel>
    <b style="color:red">(*):is required</b>
</h:form>

<!-- Panel Change Password Success-->
<rich:modalPanel id="changePass" height="100" headerClass="cuong">
    <f:facet name="header">
        <h:outputText value="Change Password Successfully"></h:outputText>
    </f:facet>
    <f:facet name="controls">
    </f:facet>
    <h:form>
        <h:panelGrid columns="2">
            <h:inputHidden/>
            <h:inputHidden/>
            <a4j:commandButton  value="Ok" style="width:100px" action="#{mBChangePass.toLogin()}">
                <f:setPropertyActionListener value="/admin/home.jsp" target="#{mBTempAdmin.currentPage}"/>
            </a4j:commandButton>
            <a4j:commandButton  value="Cancel" style="width:100px" action="#{mBChangePass.toLogin()}">
                <f:setPropertyActionListener value="/admin/home.jsp" target="#{mBTempAdmin.currentPage}"/>
            </a4j:commandButton>
        </h:panelGrid>
    </h:form>
</rich:modalPanel>


