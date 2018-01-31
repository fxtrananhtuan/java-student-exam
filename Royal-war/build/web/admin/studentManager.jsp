<%-- 
    Document   : studentManager
    Created on : Nov 16, 2011, 8:18:43 AM
    Author     : MesutOezil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@taglib prefix="rich" uri="http://richfaces.org/rich" %>
<%@taglib prefix="a4j" uri="http://richfaces.org/a4j" %>
<%@taglib prefix="p" uri="http://primefaces.prime.com.tr/ui" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<style>
    .cuong{
        background: #212121;
        border-color: #084577        
    }
    .cuong1{
        background: #d3d3d3;
    }
    .cuong2{
        background: #736356;
        border-color: #084577  
    }

</style>
<h:form>
    <a4j:outputPanel ajaxRendered="true">
        <p:growl showSummary="true" showDetail="true"></p:growl>
    </a4j:outputPanel>
    <rich:dataTable id="studentTable" var="st" value="#{mbS.listStudent}"  width="100%" rows="5" columnClasses="center">
        <rich:column sortBy="#{st.studentID}" filterBy="#{st.studentID}" filterEvent="onkeyup" headerClass="cuong1" >
            <f:facet name="header">
                <h:outputLabel value="StudentID"></h:outputLabel>
            </f:facet>
            <h:outputText value="#{st.studentID}"></h:outputText>
        </rich:column>   
        <rich:column sortBy="#{st.fullName}" filterBy="#{st.fullName}" filterEvent="onkeyup" headerClass="cuong1">
            <f:facet name="header">
                <h:outputLabel value="fullname"></h:outputLabel>
            </f:facet>            
            <h:outputText value="#{st.fullName}"></h:outputText>            
        </rich:column>           

        <rich:column sortBy="#{st.getBatchID().getBatchName()}" filterBy="#{st.getBatchID().getBatchName()}" filterEvent="onkeyup" headerClass="cuong1">
            <f:facet name="header">
                <h:outputLabel value="Batch"></h:outputLabel>
            </f:facet>
            <h:outputText value="#{st.getBatchID().getBatchName()}"></h:outputText>
        </rich:column>
        <rich:column sortBy="#{st.getBatchID().getCourseID().getCourseName()}"  filterBy="#{st.getBatchID().getCourseID().getCourseName()}" filterEvent="onkeyup" headerClass="cuong1">
            <f:facet name="header">
                <h:outputLabel value="Course" ></h:outputLabel>
            </f:facet>
            <h:outputText value="#{st.getBatchID().getCourseID().getCourseName()}"></h:outputText>
        </rich:column>

        <rich:column sortBy="#{st.getConvertStatus()}" filterBy="#{st.getConvertStatus()}" filterEvent="onkeyup" headerClass="cuong1">
            <f:facet name="header">
                <h:outputLabel value="Status"></h:outputLabel>
            </f:facet> 
            <h:outputText value="#{st.getConvertStatus()}"></h:outputText>               
        </rich:column> 

        <rich:column headerClass="cuong1">
            <f:facet name="header">
                <h:outputLabel value="Action"></h:outputLabel>                
            </f:facet>
            <a4j:commandLink id="ustudent" oncomplete="#{rich:component('Panelupdate')}.show()">
                <h:graphicImage value="/icon/notebook.png" width="30" height="30"></h:graphicImage>
                <f:setPropertyActionListener value="#{st}" target="#{mbS.student}"></f:setPropertyActionListener>

            </a4j:commandLink>
            <rich:toolTip for="ustudent" value="Update student#{st.fullName}"></rich:toolTip>

            <a4j:commandLink id="dstudent" oncomplete="#{rich:component('Paneldelete')}.show()">
                <h:graphicImage value="/icon/iconDelete.gif" width="30" height="30"></h:graphicImage>
                <f:setPropertyActionListener value="#{st}" target="#{mbS.student}"></f:setPropertyActionListener>
            </a4j:commandLink>
            <rich:toolTip for="dstudent" value="Delete student#{st.fullName}"></rich:toolTip>

            <a4j:commandLink id="destudent" oncomplete="#{rich:component('Paneldetail')}.show()">
                <h:graphicImage value="/icon/plus.png" width="30" height="30"></h:graphicImage>
                <f:setPropertyActionListener value="#{st}" target="#{mbS.student}"></f:setPropertyActionListener>
            </a4j:commandLink>
            <rich:toolTip for="destudent" value="Detail student#{st.fullName}"></rich:toolTip>

        </rich:column>



        <!--                    PHÂN TRANG-->
        <f:facet name="footer">
            <rich:datascroller maxPages="3" renderIfSinglePage="false"></rich:datascroller>
        </f:facet>    
    </rich:dataTable>
</h:form>


<!--        ADD STUDENT-->
<a4j:region renderRegionOnly="true">
    <rich:simpleTogglePanel switchType="ajax" label="Add Student" headerClass="cuong" opened="false">
        <h:form>
            <a4j:outputPanel ajaxRendered="true" rendered="true">
                <p:growl showSummary="true" showDetail="true" life="5000"></p:growl>
                <div>
                    <table>
                        <tr>
                            <td><h:outputText value="FullName :" style="font:bold;font-size: small"/></td>
                            <td><h:inputText id="fullname" value="#{mbS.student1.fullName}" style="font:bold">
                                    <rich:beanValidator/>
                                </h:inputText></td>
                            <td width="150px"><b style="color:red">(*)</b><rich:message for="fullname" style="color:red"></rich:message></td>                
                            <td><h:outputText value="BatchName :" style="font:bold;font-size: small"/></td>
                            <td><h:selectOneMenu value="#{mbS.batchID}" style="width:150px">
                                    <f:selectItem itemLabel="Please choose BatchName..." itemValue="#{0}" ></f:selectItem>
                                    <f:selectItems value="#{mbS.listBatchName}"></f:selectItems>
                                </h:selectOneMenu>
                            </td>
                            <td><b  style="color:red">(*)</b></td>
                        </tr>

                        <tr>
                            <td><h:outputText value="Password :" style="font:bold;font-size: small"/></td>
                            <td><h:inputSecret id="password" value="#{mbS.password}" style="font:bold">
                                    <rich:beanValidator/>
                                </h:inputSecret>
                            </td>
                            <td width="150px"><b style="color:red">(*)</b><rich:message for="password" style="color:red"></rich:message></td>
                            <td rowspan="2"><h:outputText value="Status :" style="font:bold;font-size: small"/></td>
                            <td rowspan="2"><h:selectOneRadio value="#{mbS.student1.status}" style="font:bold" layout="pageDirection">
                                    <f:selectItem itemLabel="Active" itemValue="true"></f:selectItem>
                                    <f:selectItem itemLabel="InActive" itemValue="false"></f:selectItem>
                                </h:selectOneRadio>
                            </td>
                            <td></td>
                        </tr>

                        <tr>
                            <td><h:outputText value="Phone :" style="font:bold;font-size: small"/></td>
                            <td><h:inputText id="phone" value="#{mbS.student1.phone}" style="font:bold">
                                    <rich:beanValidator/>
                                </h:inputText></td>
                            <td width="150px"><b style="color:red">(*)</b><rich:message for="phone" style="color:red"></rich:message></td>
                            <td></td>
                        </tr>

                        <tr>
                            <td><h:outputText value="Email :" style="font:bold;font-size: small"/></td>
                            <td><h:inputText id="email" value="#{mbS.student1.email}" style="font:bold">
                                    <rich:beanValidator/>
                                </h:inputText>
                            </td>
                            <td width="150px"><b style="color:red">(*)</b><rich:message for="email" style="color:red"></rich:message></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>

                        <tr>
                            <td><h:outputText value="Address :" style="font:bold;font-size: small"/></td>
                            <td><h:inputText id="address" value="#{mbS.student1.address}" style="font:bold">
                                    <rich:beanValidator/>
                                </h:inputText>
                            </td>
                            <td width="150px"><b style="color:red">(*)</b><rich:message for="address" style="color:red"></rich:message></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>

                        <tr>
                            <td colspan="6">
                                <a4j:commandButton style="width:100px" value="Insert" action="#{mbS.insertStudent()}" oncomplete="if(#{mbS.error==0})#{rich:component('Paneladdsuccess')}.show()" actionListener="#{mBTempAdmin.toStudentManager()}"/>
                            </td>
                        </tr>
                    </table>
                </div>
            </a4j:outputPanel>
        </h:form>
    </rich:simpleTogglePanel>
</a4j:region>

<!--        MODAL UPDATE STUDENT-->
<a4j:region renderRegionOnly="true">
    <rich:modalPanel id="Panelupdate" moveable="false" width="650" height="300" headerClass="cuong">
        <f:facet name="header">
            <h:outputText value="Update student"></h:outputText>
        </f:facet>
        <f:facet name="controls">
            <h:panelGroup>
                <h:graphicImage value="/templateAdmin/img/cancel.png" style="cursor:pointer" id="close"></h:graphicImage>
                <rich:componentControl for="Panelupdate" attachTo="close" operation="hide" event="onclick"></rich:componentControl>
            </h:panelGroup>
        </f:facet>
        <a4j:outputPanel ajaxRendered="true" rendered="true">
            <p:growl  showSummary="true" showDetail="true"/>     
            <h:form>
                <div>
                    <table>
                        <tr>
                            <td><h:outputLabel value="StudentID :" style="font-size: small;font: bold"/></td>
                            <td><h:outputText value="#{mbS.student.studentID}" style="font: bold"></h:outputText></td>
                            <td></td>                
                            <td><h:outputLabel value="BatchName :" style="font-size: small;font: bold"></h:outputLabel></td>
                            <td><h:selectOneMenu value="#{mbS.student.batchID.batchID}" style="width:150px">
                                    <f:selectItems value="#{mbS.listBatchName}"></f:selectItems>
                                </h:selectOneMenu>
                            </td>
                            <td><b  style="color:red">(*)</b></td>
                        </tr>

                        <tr>
                            <td><h:outputLabel value="FullName :" style="font-size: small;font: bold" /></td>
                            <td><h:inputText id="fullname" value="#{mbS.student.fullName}"style="font: bold">
                                    <rich:beanValidator/>
                                </h:inputText>
                            </td>
                            <td width="150px"><b  style="color:red">(*)</b><rich:message for="fullname" style="color:red"></rich:message></td>
                            <td rowspan="2"><h:outputLabel value="Status :" style="font-size: small;font: bold"/></td>
                            <td rowspan="2"><h:selectOneRadio value="#{mbS.student.status}" layout="pageDirection">
                                    <f:selectItem itemLabel="Active" itemValue="true"></f:selectItem>
                                    <f:selectItem itemLabel="InActive" itemValue="false"></f:selectItem>
                                </h:selectOneRadio>
                            </td>
                            <td></td>
                        </tr>

                        <tr>
                            <td><h:outputLabel value="Phone :" style="font-size: small;font: bold"/></td>
                            <td><h:inputText id="phone" value="#{mbS.student.phone}" style="font: bold">
                                    <rich:beanValidator/>
                                </h:inputText></td>
                            <td width="150px"><b  style="color:red">(*)</b><rich:message for="phone" style="color:red"></rich:message></td>
                            <td></td>
                        </tr>

                        <tr>
                            <td><h:outputLabel value="Email :" style="font-size: small;font: bold"/></td>
                            <td> <h:inputText id="email" value="#{mbS.student.email}" style="font: bold">
                                    <rich:beanValidator/>
                                </h:inputText>
                            </td>
                            <td width="150px"><b  style="color:red">(*)</b><rich:message for="email" style="color:red"></rich:message></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>

                        <tr>
                            <td><h:outputLabel value="Address :" style="font-size: small;font: bold"/></td>
                            <td><h:inputText id="address" value="#{mbS.student.address}" style="font: bold">
                                    <rich:beanValidator/>
                                </h:inputText>
                            </td>
                            <td width="150px"><b  style="color:red">(*)</b><rich:message for="address" style="color:red"></rich:message></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>

                        <tr>
                            <td colspan="6">
                                <a4j:commandButton style="width:100px" value="Edit" action="#{mbS.updateStudent()}" oncomplete="if(#{mbS.error==0}){#{rich:component('Panelupdate')}.hide();#{rich:component('Panelupdatesuccess')}.show();}"/>
                            </td>
                        </tr>
                    </table>
                </div>
            </h:form>
        </a4j:outputPanel>
    </rich:modalPanel>        
</a4j:region>


<!--        MODAL DELETE-->
<a4j:region renderRegionOnly="true">
    <rich:modalPanel id="Paneldelete" height="100" headerClass="cuong">
        <f:facet name="header">
            <h:outputText value="Delete student"></h:outputText>
        </f:facet>
        <f:facet name="controls">
            <h:panelGroup>
                <h:graphicImage value="/templateAdmin/img/cancel.png" style="cursor:pointer" id="close1"></h:graphicImage>
                <rich:componentControl for="Paneldelete" attachTo="close1" operation="hide" event="onclick"></rich:componentControl>
            </h:panelGroup>
        </f:facet>
        <a4j:outputPanel ajaxRendered="true" rendered="true">
            <p:growl  showSummary="true" showDetail="true"/> 
            <h:form>
                <h:panelGrid columns="2">              
                    <h:panelGroup>
                        <h:outputLabel value="Are you sure?"/>
                        <h:outputLabel value="Delete Student #{mbS.student.studentID}"/>
                        <rich:spacer width="100%" height="9px"/>
                        <a4j:commandButton value="yes" action="#{mbS.deleteStudent()}" oncomplete="if(#{mbS.error==0}){#{rich:component('Paneldelete')}.hide();#{rich:component('Paneldeletesuccess')}.show();}"></a4j:commandButton>
                        <a4j:commandButton value="no" oncomplete="#{rich:component('Paneldelete')}.hide()"></a4j:commandButton>
                    </h:panelGroup>
                </h:panelGrid>

            </h:form>
        </a4j:outputPanel>
    </rich:modalPanel>
</a4j:region>

<!--    MODAL DETAIL-->
<a4j:region renderRegionOnly="true">
    <rich:modalPanel id="Paneldetail" moveable="false" width="270" height="300" headerClass="cuong">
        <f:facet name="header">
            <h:outputText value="Detail Student"></h:outputText>
        </f:facet>
        <f:facet name="controls">
            <h:panelGroup>
                <h:graphicImage value="/templateAdmin/img/cancel.png" style="cursor:pointer" id="close2"></h:graphicImage>
                <rich:componentControl for="Paneldetail" attachTo="close2" operation="hide" event="onclick"></rich:componentControl>
            </h:panelGroup>
        </f:facet>
        <h:form>
            <a4j:outputPanel ajaxRendered="true" rendered="true">
                <p:growl  showSummary="true" showDetail="true" life="5000"/>

                <h:panelGrid columns="3">
                    <h:outputLabel value="StudentID :" style="font-size: small;font: bold"/>
                    <h:outputLabel value="#{mbS.student.studentID}" style="font-size: small;font: bold"/>
                    <br/>
                    <br/>
                    <h:outputLabel value="BatchName :" style="font-size: small;font: bold"/>
                    <h:outputLabel value="#{mbS.student.batchID.batchName}" style="font-size: small;font: bold"/>
                    <br/>
                    <br/>                    
                    <h:outputLabel value="CourseName :" style="font-size: small;font: bold"/>
                    <h:outputLabel value="#{mbS.student.batchID.courseID.courseName}" style="font-size: small;font: bold"/>
                    <br/>
                    <br/>
                    <h:outputLabel value="FullName :" style="font-size: small;font: bold"/>
                    <h:outputLabel value="#{mbS.student.fullName}" style="font-size: small;font: bold"/>
                    <br/>
                    <br/>
                    <h:outputLabel value="Phone :" style="font-size: small;font: bold"/>
                    <h:outputLabel value="#{mbS.student.phone}" style="font-size: small;font: bold"/>
                    <br/>
                    <br/>
                    <h:outputLabel value="Email :" style="font-size: small;font: bold"/>
                    <h:outputLabel value="#{mbS.student.email}" style="font-size: small;font: bold"/>
                    <br/>
                    <br/>
                    <h:outputLabel value="Address :" style="font-size: small;font: bold"/>
                    <h:outputLabel value="#{mbS.student.address}" style="font-size: small;font: bold"/>
                    <br/>
                    <br/>   
                    <h:outputLabel value="Status :" style="font-size: small;font: bold"/>
                    <h:outputLabel value="#{mbS.student.convertStatus}" style="font-size: small;font: bold"/>
                    <br/>
                    <br/>
                </h:panelGrid>

            </a4j:outputPanel>
        </h:form>
    </rich:modalPanel>
</a4j:region>

<!--UPDATE SUCCESS-->
<rich:modalPanel id="Panelupdatesuccess" height="100" headerClass="cuong">
    <f:facet name="header">
        <h:outputText value="Update Student Successfully!"></h:outputText>
    </f:facet>
    <f:facet name="controls">
        <h:panelGroup>
            <h:graphicImage value="/templateAdmin/img/cancel.png" style="cursor:pointer" id="close3"></h:graphicImage>
            <rich:componentControl for="Panelupdatesuccess" attachTo="close3" operation="hide" event="onclick"></rich:componentControl>
        </h:panelGroup>
    </f:facet>
    <h:form>
        <h:panelGrid columns="2">
            <a4j:commandButton value="Ok" style="width:100px" reRender="studentTable" oncomplete="#{rich:component('Panelupdatesuccess')}.hide()">
                <f:setPropertyActionListener value="#{1}" target="#{mbS.error}"/>
            </a4j:commandButton>
        </h:panelGrid> 
    </h:form>
</rich:modalPanel> 

<!--ADD SUCCESS-->
<rich:modalPanel id="Paneladdsuccess" height="100" headerClass="cuong">
    <f:facet name="header">
        <h:outputText value="Add Student Successfully"></h:outputText>
    </f:facet>
    <f:facet name="controls">
        <h:panelGroup>
            <h:graphicImage value="/templateAdmin/img/cancel.png" style="cursor:pointer" id="close4"></h:graphicImage>
            <rich:componentControl for="Paneladdsuccess" attachTo="close4" operation="hide" event="onclick"></rich:componentControl>
        </h:panelGroup>
    </f:facet>
    <h:form>
        <h:panelGrid columns="2">
            <a4j:commandButton value="Ok" style="width:100px" reRender="studentTable" oncomplete="#{rich:component('Paneladdsuccess')}.hide()">
                <f:setPropertyActionListener value="#{1}" target="#{mbS.error}"/>
            </a4j:commandButton>
        </h:panelGrid> 
    </h:form>
</rich:modalPanel> 

<!--DELETE SUCCESS-->
<rich:modalPanel id="Paneldeletesuccess" height="100" headerClass="cuong">
    <f:facet name="header">
        <h:outputText value="Delete Student Successfully"></h:outputText>
    </f:facet>
    <f:facet name="controls">
        <h:panelGroup>
            <h:graphicImage value="/templateAdmin/img/cancel.png" style="cursor:pointer" id="close5"></h:graphicImage>
            <rich:componentControl for="Paneldeletesuccess" attachTo="close5" operation="hide" event="onclick"></rich:componentControl>
        </h:panelGroup>
    </f:facet>
    <h:form>
        <h:panelGrid columns="2">
            <h:inputHidden/>
            <h:inputHidden/>            
            <a4j:commandButton value="Ok" style="width:100px" reRender="studentTable" oncomplete="#{rich:component('Paneldeletesuccess')}.hide()"/>
        </h:panelGrid> 
    </h:form>
</rich:modalPanel> 
