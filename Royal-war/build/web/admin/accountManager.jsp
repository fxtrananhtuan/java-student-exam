<%-- 
    Document   : accountManager
    Created on : Nov 16, 2011, 8:17:16 AM
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
        border-color: #084577  ;

    }
</style>
<h:form>

    <rich:dataTable id="accountTable" var="list" value="#{mBAccountManager.listAcc}" width="100%" rows="5" columnClasses="center">
        <rich:column sortBy="#{list.userName}" filterBy="#{list.userName}" filterEvent="onkeyup" headerClass="cuong1" >
            <f:facet name="header">
                <h:outputLabel value="UserName"></h:outputLabel>
            </f:facet>
            <h:outputText value="#{list.userName}"></h:outputText>
        </rich:column>   
        <rich:column sortBy="#{list.fullName}" filterBy="#{list.fullName}" filterEvent="onkeyup" headerClass="cuong1">
            <f:facet name="header">
                <h:outputLabel value="FullName"></h:outputLabel>
            </f:facet>            
            <h:outputText value="#{list.fullName}"></h:outputText>            
        </rich:column>           

        <rich:column sortBy="#{list.getRoleID().getRoleName()}" filterBy="#{list.getRoleID().getRoleName()}" filterEvent="onkeyup" headerClass="cuong1">
            <f:facet name="header">
                <h:outputLabel value="Role Name"></h:outputLabel>
            </f:facet>
            <h:outputText value="#{list.getRoleID().getRoleName()}"></h:outputText>
        </rich:column>

        <rich:column sortBy="#{list.getConvertStatus()}" filterBy="#{list.getConvertStatus()}" filterEvent="onkeyup" headerClass="cuong1">
            <f:facet name="header">
                <h:outputLabel value="Status"></h:outputLabel>
            </f:facet> 
            <h:outputText value="#{list.getConvertStatus()}"></h:outputText>               
        </rich:column> 

        <rich:column headerClass="cuong1">
            <f:facet name="header">
                <h:outputLabel value="Action"></h:outputLabel>                
            </f:facet>
            <a4j:commandLink id="uAccount" oncomplete="#{rich:component('Panelupdate')}.show()">
                <h:graphicImage value="/icon/notebook.png" width="30" height="30"></h:graphicImage>
                <f:setPropertyActionListener value="#{list.getRoleID().getRoleID()}" target="#{mBAccountManager.roleID}"/>
                <f:setPropertyActionListener value="#{list}" target="#{mBAccountManager.accUpdate}"></f:setPropertyActionListener>
                <f:setPropertyActionListener value="#{0}" target="#{mBAccountManager.error}"/>
            </a4j:commandLink>
            <rich:toolTip for="uAccount" value="Update Account #{list.userName}"></rich:toolTip>

            <a4j:commandLink id="detailAccount" oncomplete="#{rich:component('PanelDetail')}.show()">
                <h:graphicImage value="/icon/plus.png" width="30" height="30"></h:graphicImage>
                <f:setPropertyActionListener value="#{list}" target="#{mBAccountManager.accUpdate}"></f:setPropertyActionListener>
            </a4j:commandLink>
            <rich:toolTip for="detailAccount" value="Detail Account #{list.userName}"></rich:toolTip>

        </rich:column>

        <!--                    PHÂN TRANG-->
        <f:facet name="footer">
            <rich:datascroller maxPages="3" renderIfSinglePage="false"></rich:datascroller>
        </f:facet>    
    </rich:dataTable>

</h:form>


<!--        ADD Account-->
<a4j:region renderRegionOnly="true">
    <rich:simpleTogglePanel id="addAccount" switchType="ajax" label="Add Account" headerClass="cuong">

        <h:form>    
            <a4j:outputPanel ajaxRendered="true" rendered="true">
                <p:growl showSummary="true" showDetail="true" life="5000"></p:growl>
                <div>
                    <table>
                        <tr>
                            <td><h:outputText value="UserName:"/></td>
                            <td><h:inputText id="userName" value="#{mBAccountManager.acc.userName}"  >
                                    <rich:beanValidator/>       
                                </h:inputText>
                            </td>
                            <td width="150px"><b style="color:red">(*)</b><h:message for="userName" styleClass="red"/></td>
                            <td><h:outputText value="Role"/></td>    
                            <td><h:selectOneMenu value="#{mBAccountManager.roleID}" style="width:150px">
                                    <f:selectItem itemLabel="Please choose role..." itemValue="#{0}"/>
                                    <f:selectItems value="#{mBAccountManager.listRole}"/>
                                </h:selectOneMenu>
                            </td>
                            <td><b style="color:red">(*)</b></td>
                        </tr>
                        <tr>
                            <td><h:outputText value="Password:"/></td>
                            <td><h:inputSecret id="password" value="#{mBAccountManager.acc.password}">
                                    <rich:beanValidator/>
                                </h:inputSecret>
                            </td>        
                            <td width="150px"><b style="color:red">(*)</b><h:message for="password" styleClass="red"/></td>
                            <td rowspan="2"><h:outputText value="Gender:"/></td>
                            <td rowspan="2">
                                <h:selectOneRadio value="#{mBAccountManager.acc.gender}"  layout="pageDirection">
                                    <f:selectItem itemLabel="Male" itemValue="True" />
                                    <f:selectItem itemLabel="Female" itemValue="False"/>
                                </h:selectOneRadio>
                            </td>
                            <td></td>
                        </tr>
                        <tr>

                            <td><h:outputText value="Phone:"/></td>
                            <td><h:inputText id="phone" value="#{mBAccountManager.acc.phone}" >
                                    <rich:beanValidator/>
                                </h:inputText>
                            </td>
                            <td width="150px"><b style="color:red">(*)</b><h:message for="phone" styleClass="red"/></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td><h:outputText value="FullName:"/></td>
                            <td><h:inputText id="fullName" value="#{mBAccountManager.acc.fullName}">
                                    <rich:beanValidator/>
                                </h:inputText>
                            </td>
                            <td width="150px"><b style="color:red">(*)</b><h:message for="fullName" styleClass="red"/></td>
                            <td rowspan="2"><h:outputText value="Status:"/></td>
                            <td rowspan="2">
                                <h:selectOneRadio value="#{mBAccountManager.acc.status}"  layout="pageDirection">
                                    <f:selectItem itemLabel="Active" itemValue="True" />
                                    <f:selectItem itemLabel="InActive" itemValue="False"/>
                                </h:selectOneRadio>
                            </td>
                            <td></td>
                        </tr>
                        <tr>
                            <td><h:outputText value="Email:"/></td>
                            <td><h:inputText id="email" value="#{mBAccountManager.acc.email}" >
                                    <rich:beanValidator/>
                                </h:inputText>
                            </td>
                            <td width="150px"><b style="color:red">(*)</b><h:message for="email" styleClass="red"/></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td><h:outputText value="Address:"/></td>
                            <td>
                                <h:inputText id="address" value="#{mBAccountManager.acc.address}">
                                    <rich:beanValidator/>
                                </h:inputText>
                            </td>
                            <td width="150px"><b style="color:red">(*)</b><h:message for="address" styleClass="red"/></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td colspan="6">
                                <a4j:commandButton style="width:100px" value="Insert"  action="#{mBAccountManager.insertAccount()}" oncomplete="if(#{mBAccountManager.error==1})#{rich:component('PanelAddSuccess')}.show()">
                                </a4j:commandButton>
                            </td>
                        </tr>
                    </table>
                    <br/>
                    <br/>
                    <b style="color:red">(*):is required</b>
                </div>
            </a4j:outputPanel>
        </h:form>
    </rich:simpleTogglePanel>
</a4j:region>


<!--update account-->

<a4j:region renderRegionOnly="true">
    <rich:modalPanel id="Panelupdate" width="700" height="320" moveable="false" headerClass="cuong" >
        <f:facet name="header">
            <h:outputText value="Update Account"></h:outputText>
        </f:facet>
        <f:facet name="controls">
            <h:panelGroup>
                <h:graphicImage value="/templateAdmin/img/cancel.png" style="cursor:pointer" id="close"></h:graphicImage>
                <rich:componentControl for="Panelupdate" attachTo="close" operation="hide" event="onclick"></rich:componentControl>
            </h:panelGroup>
        </f:facet>

        <h:form>
            <a4j:outputPanel ajaxRendered="true" rendered="true" >
                <p:growl showSummary="true" showDetail="true" life="5000"></p:growl>
                <fieldset style="background-color: white;border: 1px dashed black">
                    <legend style="font-weight: bold">Information</legend>
                    <table>
                        <tr>
                            <td>
                                <h:outputLabel styleClass="forLabel" value="UserName:"/>
                            </td>
                            <td>
                                <h:outputText styleClass="forInput"  value="#{mBAccountManager.accUpdate.userName}"/>
                            </td>
                            <td width="200px"></td>
                            <td>
                                <h:outputLabel styleClass="forLabel" value="Role:">
                                </h:outputLabel>
                            </td>
                            <td>
                                <h:selectOneMenu value="#{mBAccountManager.roleID}" style="width:150px" >
                                    <f:selectItem itemLabel="Please choose role..." itemValue="#{0}"/>
                                    <f:selectItems value="#{mBAccountManager.listRole}"/>
                                </h:selectOneMenu>
                            </td>
                            <td><b style="color:red">(*)</b></td>
                        </tr>
                        <tr>
                            <td>
                                <h:outputLabel styleClass="forLabel" value="FullName:">
                                </h:outputLabel>
                            </td>
                            <td>
                                <h:inputText id="upFullName" styleClass="forInput" value="#{mBAccountManager.accUpdate.fullName}">
                                    <rich:beanValidator/>
                                </h:inputText>
                            </td>
                            <td width="200px"><b style="color:red">(*)</b><h:message styleClass="displayerror" for="upFullName"/></td>
                            <td rowspan="2">
                                <h:outputLabel styleClass="forLabel" value="Gender:"/>
                            </td>
                            <td rowspan="2">
                                <h:selectOneRadio value="#{mBAccountManager.accUpdate.gender}" layout="pageDirection">
                                    <f:selectItem itemLabel="Male" itemValue="True"></f:selectItem>
                                    <f:selectItem itemLabel="Female" itemValue="False"></f:selectItem>
                                </h:selectOneRadio>
                            </td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>
                                <h:outputLabel styleClass="forLabel" value="Email:">
                                </h:outputLabel>

                            </td>
                            <td>
                                <h:inputText id="upEmail" styleClass="forInput" value="#{mBAccountManager.accUpdate.email}">
                                    <rich:beanValidator/>
                                </h:inputText>
                            </td>
                            <td width="200px"><b style="color:red">(*)</b><h:message styleClass="displayerror" for="upEmail"/></td>
<td></td>
                        </tr>
                        <tr>
                            <td>
                                <h:outputLabel styleClass="forLabel" value="Phone:">
                                </h:outputLabel>

                            </td>
                            <td>
                                <h:inputText id="upPhone" styleClass="forInput" value="#{mBAccountManager.accUpdate.phone}">
                                    <rich:beanValidator/>
                                </h:inputText>
                            </td>
                            <td width="200px"><b style="color:red">(*)</b><h:message styleClass="displayerror" for="upPhone"/></td>
                            <td rowspan="2">
                                <h:outputLabel styleClass="forLabel" value="Status:"></h:outputLabel>
                            </td>
                            <td rowspan="2">
                                <h:selectOneRadio value="#{mBAccountManager.accUpdate.status}" layout="pageDirection">
                                    <f:selectItem itemLabel="Active" itemValue="True"></f:selectItem>
                                    <f:selectItem itemLabel="InActive" itemValue="False"></f:selectItem>
                                </h:selectOneRadio>
                            </td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>
                                <h:outputLabel styleClass="forLabel" value="Address:">
                                </h:outputLabel>

                            </td>
                            <td>
                                <h:inputText id="upAddress" styleClass="forInput" value="#{mBAccountManager.accUpdate.address}">
                                    <rich:beanValidator/>
                                </h:inputText>
                            </td>
                            <td width="200px"><b style="color:red">(*)</b><h:message styleClass="displayerror" for="upAddress"/></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td colspan="6">
                                <a4j:commandButton style="width:100px"  value="Update" action="#{mBAccountManager.updateAccount()}" oncomplete="if(#{mBAccountManager.error==1}){#{rich:component('Panelupdate')}.hide();#{rich:component('PanelSuccess')}.show();}">
                                </a4j:commandButton>
                            </td>
                        </tr>
                    </table>
                    <br/>
                    <b style="color:red">(*):is required</b>
                </fieldset>
            </a4j:outputPanel>
        </h:form>

    </rich:modalPanel>
</a4j:region>



<!-- Panel Update Account Success-->
<rich:modalPanel id="PanelSuccess" height="150" headerClass="cuong">
    <f:facet name="header">
        <h:outputText value="Update Successfully"></h:outputText>
    </f:facet>
    <f:facet name="controls">
        <h:panelGroup>
            <h:graphicImage value="/templateAdmin/img/cancel.png" style="cursor:pointer" id="close1"></h:graphicImage>
            <rich:componentControl for="PanelSuccess" attachTo="close1" operation="hide" event="onclick"></rich:componentControl>
        </h:panelGroup>
    </f:facet>
    <h:form>
        <h:panelGrid columns="2">
            <h:inputHidden/>
            <h:inputHidden/>
            <a4j:commandButton  value="Ok" style="width:100px" oncomplete="#{rich:component('PanelSuccess')}.hide()" reRender="accountTable"></a4j:commandButton>
            <a4j:commandButton  value="Cancel" style="width:100px" oncomplete="#{rich:component('PanelSuccess')}.hide()" reRender="accountTable"></a4j:commandButton>

        </h:panelGrid>
    </h:form>
</rich:modalPanel>


<!-- Panel Add Account Success-->
<rich:modalPanel id="PanelAddSuccess" height="100" headerClass="cuong">
    <f:facet name="header">
        <h:outputText value="Add Account Successfully"></h:outputText>
    </f:facet>
    <f:facet name="controls">
        <h:panelGroup>
            <h:graphicImage value="/templateAdmin/img/cancel.png" style="cursor:pointer" id="close2"></h:graphicImage>
            <rich:componentControl for="PanelAddSuccess" attachTo="close2" operation="hide" event="onclick"></rich:componentControl>
        </h:panelGroup>
    </f:facet>
    <h:form>
        <h:panelGrid columns="2">
            <h:inputHidden/>
            <h:inputHidden/>
            <a4j:commandButton  value="Ok" style="width:100px" oncomplete="#{rich:component('PanelAddSuccess')}.hide()" reRender="accountTable">
                <f:setPropertyActionListener value="#{0}" target="#{mBAccountManager.error}"/>
            </a4j:commandButton>
            <a4j:commandButton  value="Cancel" style="width:100px" oncomplete="#{rich:component('PanelAddSuccess')}.hide()" reRender="accountTable">
                <f:setPropertyActionListener value="#{0}" target="#{mBAccountManager.error}"/>
            </a4j:commandButton>
        </h:panelGrid>
    </h:form>
</rich:modalPanel>

<!--Panel Detail-->

<rich:modalPanel id="PanelDetail" headerClass="cuong" moveable="false" width="450" height="200"  >
    <f:facet name="header">
        <h:outputText value="Account Detail"></h:outputText>
    </f:facet>
    <f:facet name="controls">
        <h:panelGroup>
            <h:graphicImage value="/templateAdmin/img/dialog-close.png" style="cursor:pointer" id="close3"></h:graphicImage>
            <rich:componentControl for="PanelDetail" attachTo="close3" operation="hide" event="onclick"></rich:componentControl>
        </h:panelGroup>
    </f:facet>

    <h:form>   
        <a4j:outputPanel ajaxRendered="true" rendered="true" >
            <p:growl showSummary="true" showDetail="true" life="5000"></p:growl>
            <div>
                <fieldset style="background-color: white;border: 1px dashed black"><legend style="font-weight: bold">Information</legend>
                    <table>
                        <tr>
                            <td><h:outputText  styleClass="forLabel" value="UserName:"/></td>
                            <td><h:outputText styleClass="forInput" value="#{mBAccountManager.accUpdate.userName}"/>
                            </td>
                            <td width="50px"></td>
                            <td><h:outputText  styleClass="forLabel" value="Role"/></td>    
                            <td><h:outputText styleClass="forInput" value="#{mBAccountManager.accUpdate.roleID.roleName}"/>
                            </td>
                        </tr>
                        <tr>
                            <td><h:outputText  styleClass="forLabel" value="FullName:"/></td>
                            <td>
                                <h:outputText styleClass="forInput" value="#{mBAccountManager.accUpdate.fullName}"/>
                            </td>       
                            <td width="50px"></td>
                            <td><h:outputText  styleClass="forLabel" value="Gender:"/></td>
                            <td>
                                <h:outputText styleClass="forInput" value="#{mBAccountManager.accUpdate.convertGender}"/>
                            </td>
                        </tr>
                        <tr>
                            <td><h:outputText  styleClass="forLabel" value="Phone:"/></td>
                            <td><h:outputText styleClass="forInput" value="#{mBAccountManager.accUpdate.phone}"/>
                            </td>
                            <td width="50px"></td>
                            <td><h:outputText  styleClass="forLabel" value="Status:"/></td>
                            <td>
                                <h:outputText styleClass="forInput" value="#{mBAccountManager.accUpdate.convertStatus}"/>
                            </td>
                        </tr>
                        <tr>
                            <td><h:outputText  styleClass="forLabel" value="Email:"/></td>
                            <td><h:outputText styleClass="forInput" value="#{mBAccountManager.accUpdate.email}" />
                            </td>
                            <td width="50px"></td>
                            <td><h:outputText styleClass="forLabel" value="Address:"/></td>
                            <td>
                                <h:outputText styleClass="forInput" value="#{mBAccountManager.accUpdate.address}"/>
                            </td>
                        </tr>
                    </table>
                </fieldset>
            </div>
        </a4j:outputPanel>
        <a4j:commandButton style="width:100px"  value="Ok"  oncomplete="#{rich:component('PanelDetail')}.hide()">
        </a4j:commandButton>
    </h:form>
</rich:modalPanel>
