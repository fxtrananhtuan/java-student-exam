<%-- 
    Document   : faqManager
    Created on : Nov 16, 2011, 8:20:01 AM
    Author     : MesutOezil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@taglib prefix="rich" uri="http://richfaces.org/rich" %>
<%@taglib prefix="a4j" uri="http://richfaces.org/a4j" %>
<%@taglib prefix="p" uri="http://primefaces.prime.com.tr/ui" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<h:form>
    <h2 class="ico_mug">FAQManager</h2>
    <rich:dataTable id="faqTable" cellpadding="0" cellspacing="0" rowKeyVar="row" width="100%" border="0" rows="5" var="faq" value="#{mbFAQController.listFAQ()}" columnClasses="center">
        <rich:column width="10%">
            <f:facet name="header">
                <h:outputLabel value="FAQID"/>
            </f:facet>
            <h:outputLabel value="#{faq.faqid}"/>
        </rich:column>
        <rich:column width="30%" sortBy="#{faq.question}" filterBy="#{faq.question}" filterEvent="onkeyup">
            <f:facet name="header">
                <h:outputLabel value="Question"/>
            </f:facet>
            <h:outputLabel value="#{faq.question}"/>
        </rich:column>
        <rich:column width="20%" sortBy="#{faq.answer}" filterBy="#{faq.answer}" filterEvent="onkeyup">
            <f:facet name="header">
                <h:outputLabel value="Answer"/>
            </f:facet>
            <h:outputLabel value="#{faq.answer}"/>
        </rich:column>
        <rich:column width="20%" sortBy="#{faq.userName.userName}" filterBy="#{faq.userName.userName}" filterEvent="onkeyup">
            <f:facet name="header">
                <h:outputLabel value="Username"/>
            </f:facet>
            <h:outputLabel value="#{faq.userName.userName}"/>
        </rich:column>
        <rich:column width="20%">
            <f:facet name="header">
                <h:outputLabel value="Action"/>
            </f:facet>

            <%--Update--%>
            <a4j:commandLink id="updateLink" oncomplete="#{rich:component('updatePanel')}.show()">
                <h:graphicImage value="icon/notebook.png" style="border:0;height:30px;width:30px"/>
                <f:setPropertyActionListener value="#{faq}" target="#{mbFAQController.tbfaq}"/>
            </a4j:commandLink>
            <rich:toolTip for="updateLink" value="Update this FAQ"/>

            <%--Delete--%>
            <a4j:commandLink id="deleteLink" oncomplete="#{rich:component('deletePanel')}.show()">
                <h:graphicImage value="icon/iconDelete.gif" style="border:0;height:30px;width:30px"/>
                <f:setPropertyActionListener value="#{faq}" target="#{mbFAQController.tbfaq}"/>
            </a4j:commandLink>
            <rich:toolTip for="deleteLink" value="Delete this FAQ"/>
        </rich:column>

        <%--Phan trang--%>
        <f:facet name="footer">
            <rich:datascroller maxPages="4" renderIfSinglePage="false"></rich:datascroller>
        </f:facet>
    </rich:dataTable>

</h:form>

<%--Insert Subject--%>
<a4j:region renderRegionOnly="true">
    <rich:simpleTogglePanel switchType="ajax" label="Add New FAQ" opened="false">
        <h:form>
            <a4j:outputPanel ajaxRendered="true">
                <p:growl globalOnly="true" life="5000" showDetail="true"/>
                <h:panelGrid columns="4">
                    <h:outputText value="Question: "/>
                    <h:inputText id="question" value="#{mbFAQController.tbfaq1.question}">
                        <rich:beanValidator summary="Error"/>
                    </h:inputText><b style="color:red">*</b>
                    <rich:message for="question" style="color:red"/>

                    <h:outputText value="Answer: "/>
                    <h:inputTextarea id="answer" value="#{mbFAQController.tbfaq1.answer}">
                        <rich:beanValidator summary="Error"/>
                    </h:inputTextarea><b style="color:red">*</b>
                    <rich:message for="course" style="color:red"/>        
                    
                </h:panelGrid>

                    <a4j:commandButton value="Create" id="createbt" action="#{mbFAQController.addNew()}" oncomplete="if(#{mbFAQController.error==0})#{rich:component('successInsertPanel')}.show()"/>
                <rich:toolTip value="Add New Subject"/>
            </a4j:outputPanel>
        </h:form>
    </rich:simpleTogglePanel>
</a4j:region>

<%--Modal panel update--%>
<a4j:region renderRegionOnly="true">
    <rich:modalPanel id="updatePanel" autosized="false" width="300" height="200" moveable="true">
        <f:facet name="header">
            <h:outputText value="Update FAQ"/>
        </f:facet>
        <f:facet name="controls">
            <h:panelGroup>
                <h:graphicImage value="/icon/icondelete.jpg" id="close" height="25" width="25"/>
                <rich:componentControl for="updatePanel" attachTo="close" operation="hide" event="onclick"/>
            </h:panelGroup>
        </f:facet>

        <a4j:outputPanel ajaxRendered="true">
            <p:growl globalOnly="true" life="5000" showDetail="true"/>
            <h:form>
                <h:panelGrid columns="4">
                    
                    
                    <h:outputText value="Question: "/><b style="color:red">*</b>
                    <h:inputText value="#{mbFAQController.tbfaq.question}" id="question">
                        <rich:beanValidator summary="Invalid"></rich:beanValidator>
                    </h:inputText>
                    <rich:message for="question" style="color:red"/>
                    
                    <h:outputText value="Answer: "/><b style="color:red"></b>
                    <h:inputTextarea value="#{mbFAQController.tbfaq.answer}" id="answer">
                        <rich:beanValidator summary="Invalid"></rich:beanValidator>
                    </h:inputTextarea>
                    <rich:message for="answer" style="color:red"/>

                </h:panelGrid>

                    <a4j:commandButton value="Update" id="updatebt" action="#{mbFAQController.updateFAQ()}" oncomplete="if(#{mbFAQController.error==0}){#{rich:component('updatePanel')}.hide();#{rich:component('successUpdatePanel')}.show()}" />
                <rich:toolTip for="updatebt" value="Update this FAQ"/>
            </h:form>
        </a4j:outputPanel>
    </rich:modalPanel>
</a4j:region>

<%--Modal panel Delete--%>
<a4j:region renderRegionOnly="true">
<rich:modalPanel id="deletePanel" autosized="false" width="300" height="100" moveable="true">
    <f:facet name="header">
        <h:outputText value="delete Course"/>
    </f:facet>
    <f:facet name="controls">
        <h:panelGroup>
            <h:graphicImage value="/icon/icondelete.jpg" id="closedelete" height="25" width="25"/>
            <rich:componentControl for="deletePanel" attachTo="closedelete" operation="hide" event="onclick"/>
        </h:panelGroup>
    </f:facet>
    <h:form>
        <a4j:outputPanel ajaxRendered="true">
            <p:growl globalOnly="true" life="5000" showDetail="true"/>
            <h:panelGrid columns="1">
                <h:outputText value="Are you want to delete this FAQ ?"/>
            </h:panelGrid>

            <a4j:commandButton value="Yes" id="yesbt" action="#{mbFAQController.deleteFAQ()}" reRender="faqTable" oncomplete="if(#{mbFAQController.error==0}){#{rich:component('deletePanel')}.hide(); #{rich:component('successDeletePanel')}.show()}" />
            
            <a4j:commandButton value="Cancel" oncomplete="#{rich:component('deletePanel')}.hide()"/>
        </a4j:outputPanel>
    </h:form>
</rich:modalPanel>
</a4j:region>

<%--Modal panel insert Successful--%>
<rich:modalPanel id="successInsertPanel" autosized="false" width="300" height="100" moveable="true">
    <f:facet name="header">
        <h:outputText value="Insert successful"/>
    </f:facet>
    <h:form>
        <a4j:outputPanel ajaxRendered="true">
            <p:growl globalOnly="true" life="5000" showDetail="true"/>
            <h:panelGrid columns="1">
                <h:outputText value="Successful your Insert Action!!!"/>
            </h:panelGrid>

            <a4j:commandButton value="Ok" reRender="faqTable" oncomplete="#{rich:component('successInsertPanel')}.hide()" />
        </a4j:outputPanel>
    </h:form>
</rich:modalPanel>

<%--Modal panel update Successful--%>
<rich:modalPanel id="successUpdatePanel" autosized="false" width="300" height="100" moveable="true">
    <f:facet name="header">
        <h:outputText value="Update successful"/>
    </f:facet>
    <h:form>
        <a4j:outputPanel ajaxRendered="true">
            <p:growl globalOnly="true" life="5000" showDetail="true"/>
            <h:panelGrid columns="1">
                <h:outputText value="Successful your Update Action!!!"/>
            </h:panelGrid>

            <a4j:commandButton value="Ok" reRender="faqTable" oncomplete="#{rich:component('successUpdatePanel')}.hide()" />
        </a4j:outputPanel>
    </h:form>
</rich:modalPanel>

<%--Modal panel Delete Successful--%>
<rich:modalPanel id="successDeletePanel" autosized="false" width="300" height="100" moveable="true">
    <f:facet name="header">
        <h:outputText value="Delete successful"/>
    </f:facet>
    <h:form>
        <a4j:outputPanel ajaxRendered="true">
            <p:growl globalOnly="true" life="5000" showDetail="true"/>
            <h:panelGrid columns="1">
                <h:outputText value="Successful your Delete Action!!!"/>
            </h:panelGrid>

            <a4j:commandButton value="Ok" reRender="faqTable" oncomplete="#{rich:component('successDeletePanel')}.hide()" />
        </a4j:outputPanel>
    </h:form>
</rich:modalPanel>

