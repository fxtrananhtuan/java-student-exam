<%-- 
    Document   : login
    Created on : Nov 16, 2011, 11:22:46 PM
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
            <title>Login Page</title>
            <link rel="stylesheet" type="text/css" media="all" href="templateAdmin/css/style.css" />
            <link rel="Stylesheet" type="text/css" href="templateAdmin/css/smoothness/jquery-ui-1.7.1.custom.css"  />
            <link rel="stylesheet" type="text/css" media="all" href="css/style.css" />
            <link rel="Stylesheet" type="text/css" href="css/smoothness/jquery-ui-1.7.1.custom.css"  />
            <link href="blueSky/skin.css" rel="stylesheet" type="text/css"/>

            <style>
                .displayerror{
                    color: firebrick
                }
                .cuong{
                    background: #212121;
                    border-color: #084577        
                }
            </style>
        </head>
        <body>
            <a4j:outputPanel ajaxRendered="true" rendered="true">
                <p:growl showSummary="true" globalOnly="true" life="5000"></p:growl>           
                <div  id="login_container">
                    <div  id="header">

                        <div id="logo"><h1><a href="/">AdmintTheme</a></h1></div>

                    </div><!-- end header -->

                    <div id="login" class="section">

                        <h:panelGroup id="fail" styleClass="info_div" layout="block" rendered="#{empty mBLoginAdmin.isError}">
                            <span class="ico_cancel">
                                <h:outputText value="Can not login to system!" rendered="#{empty mBLoginAdmin.isError}"></h:outputText>
                            </span>
                        </h:panelGroup>
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
                            <a4j:commandButton value="Login" action="#{mBLoginAdmin.checkLogin()}">                             
                            </a4j:commandButton>
                            <a4j:commandLink value="Forgot your username or password?" action="#"></a4j:commandLink>    
                            <br/>
                            <br/>
                            <b style="color:red">(*):is required</b>
                        </h:form>
                    </div>
                </div><!-- end container -->
            </a4j:outputPanel>
            <!-- processing -->

            <a4j:status onstart="#{rich:component('load')}.show()" onstop="#{rich:component('load')}.hide()" ></a4j:status>
            <rich:modalPanel id="load" headerClass="cuong" autosized="true">
                <f:facet name="header">
                    <h:outputText value="Processing"></h:outputText>
                </f:facet>
                <p:graphicImage value="icon/processing.gif"></p:graphicImage>
            </rich:modalPanel>
        </body>
    </html>
</f:view>
