<%-- 
    Document   : templateAdmin
    Created on : Nov 15, 2011, 8:58:01 PM
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
            <link rel="stylesheet" type="text/css" media="all" href="templateAdmin/css/style.css" />
            <link rel="Stylesheet" type="text/css" href="templateAdmin/css/smoothness/jquery-ui-1.7.1.custom.css"  />	
            <link rel="stylesheet" type="text/css" href="templateAdmin/markitup/skins/markitup/style.css" />
            <link rel="stylesheet" type="text/css" href="templateAdmin/markitup/sets/default/style.css" />
            <link rel="stylesheet" type="text/css" href="templateAdmin/css/superfish.css" media="screen">
            <link rel="stylesheet" type="text/css" media="all" href="css/style.css" />
            <link rel="Stylesheet" type="text/css" href="css/smoothness/jquery-ui-1.7.1.custom.css"  />	
            <link rel="stylesheet" type="text/css" href="markitup/skins/markitup/style.css" />
            <link rel="stylesheet" type="text/css" href="markitup/sets/default/style.css" />
            <link rel="stylesheet" type="text/css" href="css/superfish.css" media="screen">
            <link href="blueSky/skin.css" rel="stylesheet" type="text/css"/>
            <link href="../blueSky/skin.css" rel="stylesheet" type="text/css"/>
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
            <div id="container" class="container">
                <div id="header">
                    <div id="profile_info">
                        <p:graphicImage alt="image" id="avatar" url="/templateAdmin/img/avatar.jpg"></p:graphicImage>

                        <h:form><p>Welcome <strong><h:commandLink value="#{mBTempAdmin.username}" action="#{mBTempAdmin.toChangePassword()}"></h:commandLink></strong>.</p><h:commandLink value="logout" action="#{mBLoginAdmin.logOut()}" style="color:white;font-weight:bold"></h:commandLink></h:form>
                            </div>
                            <div id="logo"><h1><a href="/">Nghia Dep Trai</a></h1></div>

                        </div>
                        <div id="content">

                            <!--menuuuuuuuuuuuuuuuuuu-->
                    <h:form>
                        <div id="top_menu" class="clearfix">
                            <ul class="sf-menu">
                                <li class="current">
                                    <a4j:commandLink value="Account Manager" reRender="include" action="#{mBTempAdmin.toAccountManager()}" rendered="#{not empty mBLoginAdmin.isBigAdmin}"></a4j:commandLink><!-- First level MENU -->
                                </li>
                                <li class="">
                                    <a4j:commandLink value="Course" rendered="#{not empty mBLoginAdmin.isAdmin}"><span class="sf-sub-indicator"> »</span></a4j:commandLink>

                                    <ul>
                                        <li>
                                            <a4j:commandLink value="Course Manager" action="#{mBTempAdmin.toCourseManager()}" reRender="include"></a4j:commandLink>
                                        </li>
                                        <li class="current">
                                            <a4j:commandLink value="Semester Manager" action="#{mBTempAdmin.toSemesterManager()}" reRender="include"></a4j:commandLink>
                                        </li>
                                        <li class="">
                                            <a4j:commandLink value="Subject Manager" action="#{mBTempAdmin.toSubjectManager()}" reRender="include"></a4j:commandLink>				
                                        </li>
                                        <li class="">
                                            <a4j:commandLink value="Batch Manager" action="#{mBTempAdmin.toBatchManager()}" reRender="include"></a4j:commandLink>
                                        </li>
                                    </ul>
                                </li>                            
                                <li class="">
                                    <a4j:commandLink value="Student Manager" action="#{mBTempAdmin.toStudentManager()}" rendered="#{not empty mBLoginAdmin.isAdmin}" reRender="include" ></a4j:commandLink>                              
                                </li>
                                <li class="">
                                    <a4j:commandLink value="Assignment Manager" reRender="include"><span class="sf-sub-indicator"> »</span></a4j:commandLink>
                                    <ul>
                                        <li>
                                        <a4j:commandLink value="Register Assignment" action="#{mBTempAdmin.toAssignmentManager()}" reRender="include"></a4j:commandLink>
                                        </li>
                                        <li class="current">
                                            <a4j:commandLink value="Register Student For Assignment" action="#{mBTempAdmin.toRegisterStudentForAssignment()}" reRender="include"></a4j:commandLink>
                                        </li>
                                        <li class="">
                                            <a4j:commandLink value="Register Survey For Assignment" action="#{mBTempAdmin.toRegisterSurveyForAssignment()}" reRender="include"></a4j:commandLink>				
                                        </li>
                                    </ul>
                                </li>
                                <li class="">
                                    <a4j:commandLink value="View File Uploaded" action="#{mBTempAdmin.toViewFileUploaded()}" reRender="include"></a4j:commandLink>
                                    </li>
                                    <li class="">
                                    <a4j:commandLink value="Mark Manager" action="#{mBTempAdmin.toMarkManager()}" reRender="include"></a4j:commandLink>
                                    </li>
                                    <li class="">
                                    <a4j:commandLink value="Feedback&FAQ"><span class="sf-sub-indicator"> »</span></a4j:commandLink><!-- First level MENU -->
                                        <ul>
                                            <li>
                                            <a4j:commandLink value="FAQ Manager" action="#{mBTempAdmin.toFAQManager()}" reRender="include"></a4j:commandLink>
                                            </li>
                                            <li>
                                            <a4j:commandLink value="FeedBack Manager" action="#{mBTempAdmin.toFeedBackManager()}" reRender="include"></a4j:commandLink>
                                            </li>
                                        </ul>
                                    </li>
                                    <li class="">
                                    <a4j:commandLink value="Survey Manager" rendered="#{not empty mBLoginAdmin.isAdmin}"><span class="sf-sub-indicator"> »</span></a4j:commandLink>
                                        <ul>
                                            <li>
                                            <a4j:commandLink value="Question Manager" action="#{mBTempAdmin.toQuestionManager()}" reRender="include"></a4j:commandLink>
                                            </li>
                                            <li class="current">
                                            <a4j:commandLink value="Answer Manager" action="#{mBTempAdmin.toAnswerManager()}" reRender="include"></a4j:commandLink>
                                            </li>
                                            <li class="">
                                            <a4j:commandLink value="Survey Statistics" action="#{mBTempAdmin.toSurveyStatistics()}" reRender="include"></a4j:commandLink>				
                                            </li>
                                        </ul>
                                    </li>
                                </ul>
                            </div>
                    </h:form>
                    <!--menuuuuuuuuuuuuuuuuuu-->


                    <!--noidungggggggggggg-->
                    <div id="content_main" class="clearfix">
                        <a4j:include id="include" viewId="#{mBTempAdmin.currentPage}"></a4j:include>
                        </div>

                        <!--noidungggggggggg-->


                        <!-- footerrrrrrrrr-->
                        <div id="footer" class="clearfix">
                            <p class="left">AdminTheme - Royal Education</p>
                            <p class="right">© 2011 AdminTheme by Mr Nghia</p>
                        </div>
                        <!-- footerrrrrrrrr-->
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
