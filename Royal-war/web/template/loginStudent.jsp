<%-- 
    Document   : loginStudent
    Created on : Dec 8, 2011, 2:55:54 PM
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
            <link href="blueSky/skin.css" rel="stylesheet" type="text/css"/>
            <link href="template/css/style.css" rel="stylesheet" type="text/css"/>
            <link href="../template/css/style.css" rel="stylesheet" type="text/css"/>
            <link href="css/style.css" rel="stylesheet" type="text/css"/>
            <p:resources/>
            <style>
                .displayerror{
                    color: firebrick
                }
                .cuong{
                    background: #212121;
                    border-color: #084577        
                }
                .red{
                    color: red
                }
                .forLabel{
                    font-weight: bold;
                }
                .forInput{
                    font-style: italic
                }
                .nghia{
                    background-image: url(template/images/form_BG.png);
                    height : 320px;
                    width: 569px;

                }
            </style>
        </head>
        <body>
            <div id="content"><div class="inner_copy"><a href="http://www.freetemplatesonline.com/">Free Web Templates</a> <a href="http://www.websitetemplates.org/">Website Templates</a> <a href="http://www.webdesign.org/website-design">Website Design</a></div></div>
            <div id="templatemo_header_wrapper">
                <div id="templatemo_header">
                    <div id="site_title">
                        <h1><a target="_parent" href="http://facebook-templates.net/">
                                <h:graphicImage alt="Site Title" url="/template/images/templatemo_logo.png"/>
                                <span>Royal Education</span>
                            </a></h1>
                    </div>
                    <p>With the jobs market at its most competitive in recent memory, how can vocational and academic courses make you stand out from the crowd of applicants and hopefuls?</p>

                </div> <!-- end of templatemo_header -->
                <div id="site_title2" class="nghiaboxshadow">

                </div>
            </div> <!-- end of templatemo_menu_wrapper -->

            <div id="templatemo_menu_wrapper">
                <div id="templatemo_menu">


                </div> <!-- end of templatemo_menu -->
            </div>

            <div id="templatemo_content_wrapper" align="center">
                <h:panelGroup layout="block" styleClass="nghia">
                    <br/>
                    <br/>
                    <br/>
                    <br/>
                    <br/>
                    <h:form>
                        <a4j:outputPanel ajaxRendered="true" rendered="true">
                            <p:growl globalOnly="true" life="5000" showDetail="true"/>
                            <h:panelGrid columns="3" styleClass="cuong">
                                <h:outputText value="Username :" style="color: white" ></h:outputText><b style="color:red">*</b>
                                <h:inputText value="#{mbLoginStudent.studentID}" id="studentID">
                                    <rich:beanValidator summary="Error"/>
                                </h:inputText>
                                
                                <h:inputHidden/>
                                <h:inputHidden/>
                                <rich:message for="studentID" style="color:red"/>
                                
                               

                                <h:outputText value="Password :" style="color: white"></h:outputText><b style="color:red">*</b>
                                <h:inputSecret value="#{mbLoginStudent.password}" styleClass="pass" id="passw">
                                    <rich:beanValidator summary="Error"/>
                                </h:inputSecret>
                                
                                <h:inputHidden/>
                                <h:inputHidden/>
                                <rich:message for="passw" style="color:red"/>
                                
                                
                                <h:outputText value="Remember Me" style="color: white"></h:outputText>
                                <h:selectBooleanCheckbox value="#{mbLoginStudent.remember}"></h:selectBooleanCheckbox>
                                <h:inputHidden/>
                                

                                <h:inputHidden/><h:inputHidden/><h:inputHidden/>
                                
                                <a4j:commandButton value="Sign In" style="width:100px;height:30px" action="#{mbLoginStudent.checkLogin()}"/> 
                                <h:inputHidden/>
                                <a4j:commandLink value="Forgot password?" oncomplete="#{rich:component('forgotPass')}.show()" style="margin-left:15px;color: red"/>
                            </h:panelGrid>
                        </a4j:outputPanel>
                    </h:form>
                </h:panelGroup>
            </div>



            <div id="templatemo_footer_wrapper">

                <div id="templatemo_footer">

                    Copyright &copy; 2048 <a href="#">Royal Education</a> | 
                    Designed by <a target="_parent" href="http://facebook-templates.net/">Mr Nghia</a> | 
                    Validate <a href="http://validator.w3.org/check?uri=referer">XHTML</a> &amp; <a href="http://jigsaw.w3.org/css-validator/check/referer">CSS</a>
                </div>

            </div>
            
            <!--Forgot Password Dialog-->
                    <rich:modalPanel id="forgotPass" autosized="true" width="400">
                        <f:facet name="header">
                            <h:outputText value="Forgot Password System"/>
                        </f:facet>
                        <f:facet name="controls">
                            <h:panelGroup>
                                <h:graphicImage value="/icon/icondelete.jpg" id="hideForgot" width="18" height="18" styleClass="hidelink"/>
                                <rich:componentControl for="forgotPass" attachTo="hideForgot" operation="hide" event="onclick"/>
                            </h:panelGroup>
                        </f:facet>
                        <h:form>
                            <a4j:outputPanel ajaxRendered="true">
                                <h:panelGrid columns="3">
                                    <h:outputLabel value="StudentID: "/><b style="color:red">(*)</b>
                                    <h:inputText value="#{mbForgotPass.studentID}" style="width:200"  id="student">
                                        <rich:beanValidator/>
                                    </h:inputText>
                                    
                                    <h:inputHidden/>
                                    <h:panelGroup/>
                                    <h:message for="student" id="usernameM" style="color:red"/>

                                    <h:outputLabel value="Email: "/><b style="color:red">(*)</b>
                                    <h:inputText value="#{mbForgotPass.email}" style="width:200" id="email">
                                        <rich:beanValidator/>
                                    </h:inputText>

                                    <h:inputHidden/>
                                    <h:panelGroup/>
                                    <h:message for="email" id="emailM" style="color:red"/>
                                    
                                    <h:outputLabel value="FullName: "/><b style="color:red">(*)</b>
                                    <h:inputText value="#{mbForgotPass.fullname}" style="width:200" id="fullname">
                                        <rich:beanValidator/>
                                    </h:inputText>

                                    <h:inputHidden/>
                                    <h:panelGroup/>
                                    <h:message for="fullname" id="fullnameM" style="color:red"/>

                                    <h:outputLabel value="Phone: " /><b style="color:red">(*)</b>
                                    <h:inputText value="#{mbForgotPass.phone}" style="width:200" id="phone">
                                        <rich:beanValidator/>
                                    </h:inputText>

                                    <h:inputHidden/>
                                    <h:panelGroup/>
                                    <h:message for="phone" id="phoneM" style="color:red" />
                                    
                                </h:panelGrid>
                            </a4j:outputPanel>
                            <a4j:commandButton value="Send"
                                               action="#{mbForgotPass.forgotPassword()}"
                                               reRender="emailM, usernameM,fullnameM,phoneM"
                                               oncomplete="if(#{mbForgotPass.error==0})#{rich:component('forgotPass')}.hide();">
                                <f:setPropertyActionListener value="#{1}" target="#{mbForgotPass.error}"/>
                            </a4j:commandButton>
                            <br/>
                            <br/>
                            <b style="color:red">(*):is required</b>
                        </h:form>
                    </rich:modalPanel>
            
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

