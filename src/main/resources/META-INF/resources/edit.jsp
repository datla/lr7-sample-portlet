<%@ include file="/init.jsp"%>

<portlet:defineObjects />

<portlet:renderURL var="viewURL">
	<portlet:param name="mvcPath" value="/META-INF/resources/view.jsp"></portlet:param>
</portlet:renderURL>

<portlet:actionURL name="addEntry" var="addEntryURL"></portlet:actionURL>

<aui:form	action="<%=addEntryURL%>" name="<portlet:namespace/>fm">

	<aui:fieldset>
		<aui:input name="type"></aui:input>
		<aui:input name="description"></aui:input>
	</aui:fieldset>

	<aui:button-row>
		<aui:button type="submit"></aui:button>
		<aui:button onClick="<%= viewURL.toString() %>" type="cancel"></aui:button>
	</aui:button-row>

</aui:form>