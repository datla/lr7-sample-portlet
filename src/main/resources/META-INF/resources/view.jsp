<%@ include file="/init.jsp" %>

<portlet:defineObjects />
<jsp:useBean id="entries" class="java.util.ArrayList" scope="request" />

<liferay-ui:search-container>
	<liferay-ui:search-container-results results="<%= entries %>" />

	<liferay-ui:search-container-row
		className="com.liferay.docs.guestbook2.model.Entry"
		modelVar="refTypeEntry">

		<liferay-ui:search-container-column-text property="description" />

		<liferay-ui:search-container-column-text property="type" />


	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>


<aui:button-row cssClass="referencetype-buttons">
	<portlet:renderURL var="addEntryURL">
		<portlet:param name="mvcPath" value="/META-INF/resources/edit.jsp"></portlet:param>
	</portlet:renderURL>

	<aui:button onClick="<%= addEntryURL.toString() %>" value="Add Entry"></aui:button>

</aui:button-row>