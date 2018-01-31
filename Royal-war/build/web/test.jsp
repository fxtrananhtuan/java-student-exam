<%-- 
    Document   : test
    Created on : Nov 23, 2011, 1:30:58 AM
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

<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            <title>JSP Page</title>
        </head>
        <body>
            <h:form>
                <label><strong>Username</strong></label>
                <h:inputText id="username" value="#{mBLoginAdmin.username}">
                <rich:beanValidator/>
            </h:inputText><b style="color:red">(*)</b>
            <h:message errorClass="displayerror" for="username" ></h:message>
                <br />
                <label><strong>Password</strong></label>
            <h:inputSecret id="password" value="#{mBLoginAdmin.password}">
                <rich:beanValidator/>
            </h:inputSecret><b style="color:red">(*)</b>
            <h:message errorClass="displayerror" for="password" ></h:message>
                <br />
                <strong>Remember Me</strong><h:selectBooleanCheckbox value="#{mBLoginAdmin.remember}" ></h:selectBooleanCheckbox>
                <br />
                <a4j:commandButton value="Login" action="#{mBLoginAdmin.checkLogin()}"></a4j:commandButton>
            <a4j:commandLink value="Forgot your username or password?" action="#"></a4j:commandLink>                         
        </h:form>
    </body>
</html>
</f:view>
