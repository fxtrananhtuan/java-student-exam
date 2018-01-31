<%-- 
    Document   : templateUser
    Created on : Nov 13, 2011, 8:10:02 PM
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
            <link href="../blueSky/skin.css" rel="stylesheet" type="text/css"/>
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
            </style>
        </head>
        <body>
            <a4j:keepAlive beanName="mBTempUser"/>
            <div id="content"><div class="inner_copy"><a href="http://www.freetemplatesonline.com/">Free Web Templates</a> <a href="http://www.websitetemplates.org/">Website Templates</a> <a href="http://www.webdesign.org/website-design">Website Design</a></div></div>
            <div id="templatemo_header_wrapper">
                <div id="templatemo_header">
                    <div id="site_title">
                        <h1><a target="_parent" href="http://facebook-templates.net/">
                                <h:graphicImage alt="Site Title" url="/template/images/templatemo_logo.png"/>
                                <span>Royal Education</span>
                            </a></h1>
                    </div>
                    <p>how can vocational and academic courses make you stand out from the crowd of applicants and hopefuls?</p>

                </div> <!-- end of templatemo_header -->
                <div id="site_title2" class="nghiaboxshadow">
                    <rich:panel>
                        <h:panelGrid columns="2">
                            <h:form>
                                Welcome:<h:commandLink value="#{mBTempUser.stud.studentID}" action="#" style="font-family: Arial;font-size: 20px;font-weight:bold"></h:commandLink>
                                <br/><h:commandLink value="Logout" action="#{mbLoginStudent.logOut()}"/>
                            </h:form> 
                        </h:panelGrid>
                    </rich:panel>
                </div>

            </div> <!-- end of templatemo_menu_wrapper -->

            <div id="templatemo_menu_wrapper" align="center">
                <h:panelGroup id="templatemo_menu" layout="block">
                    <h:form>
                        <ul>
                            <li><a4j:commandLink value="Home" action="#{mBTempUser.toHome()}"></a4j:commandLink></li>
                            <li><a4j:commandLink value="Assignments" action="#{mBTempUser.toAssignments()}"></a4j:commandLink></li>
                            <li><a4j:commandLink value="File Uploading" action="#{mBTempUser.toFileUploading()}"></a4j:commandLink></li>
                            <li><a4j:commandLink value="Mark" action="#{mBTempUser.toMark()}"></a4j:commandLink></li>
                            <li><a4j:commandLink value="FeedBack" action="#{mBTempUser.toFeedBack()}"></a4j:commandLink></li>
                            <li><a4j:commandLink value="FAQ" action="#{mBTempUser.toFAQ()}"></a4j:commandLink></li>
                            </ul>    	
                    </h:form>
                </h:panelGroup>
            </div>
            <div id="templatemo_content_wrapper">
                <a4j:include viewId="#{empty mBTempUser.currentPage ? 'home.jsp' : mBTempUser.currentPage}"></a4j:include>
            </div>
            <div class="space"></div>


            <div id="templatemo_footer_wrapper">

                <div id="templatemo_footer">
                    <h:form>
                        <ul class="footer_menu">

                            <li><a4j:commandLink value="Home" action="#{mBTempUser.toHome()}"></a4j:commandLink></li>
                            <li><a4j:commandLink value="Assignments" action="#{mBTempUser.toAssignments()}"></a4j:commandLink></li>
                            <li><a4j:commandLink value="File Uploading" action="#{mBTempUser.toFileUploading()}"></a4j:commandLink></li>
                            <li><a4j:commandLink value="Mark" action="#{mBTempUser.toMark()}"></a4j:commandLink></li>
                            <li><a4j:commandLink value="FeedBack" action="#{mBTempUser.toFeedBack()}"></a4j:commandLink></li>
                            <li><a4j:commandLink value="FAQ" action="#{mBTempUser.toFAQ()}"></a4j:commandLink></li>
                            </ul>
                    </h:form>
                    Copyright &copy; 2048 <a href="#">Royal Education</a> | 
                    Designed by <a target="_parent" href="http://facebook-templates.net/">Mr Nghia</a> | 
                    Validate <a href="http://validator.w3.org/check?uri=referer">XHTML</a> &amp; <a href="http://jigsaw.w3.org/css-validator/check/referer">CSS</a>
                </div>

            </div>
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
