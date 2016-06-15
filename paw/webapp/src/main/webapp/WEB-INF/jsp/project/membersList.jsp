<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="bs" tagdir="/WEB-INF/tags/bs" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<t:page>
	<jsp:attribute name="title">
		<spring:message code="project.list.title"/>
	</jsp:attribute>	
	<jsp:body>
	<c:url var="url" value="/project/${project.code()}/members"/>
	<div class="row">
		<div class="col-sm-12">	
				<form:form modelAttribute="addMemberForm" action="${url}/new" method="POST">
					<c:if test="${user eq project.admin()}">
						<form:hidden path="projectCode" value ="${project.code()}"/>
						<bs:inputGroupBtn path="member" label="Add as member" typeahead="${usernames}" placeholder="Adding dudes to the project might be a good idea"/>	
					</c:if>			
				</form:form>
			<t:staticPanel panelId="members">			
				<jsp:attribute name="title">
					<spring:message code="project.title"/>
				</jsp:attribute>				
				<jsp:body>					
					<t:memberList admin="${project.admin()}" members="${members}" user="${user}" url="${url}"/>
				</jsp:body>
				
			</t:staticPanel>
			</div>
		</div>
	</jsp:body>
</t:page>