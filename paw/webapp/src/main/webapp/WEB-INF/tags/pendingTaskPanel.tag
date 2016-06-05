<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@tag description="NavBar Button" pageEncoding="UTF-8"%>
<%@attribute name="task" required="true" type="ar.edu.itba.models.Task" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<c:url value="/task/${task.taskId()}" var="url"/>

<t:collapsiblePanel panelId="task-${task.taskId()}" panelParent="${panelParent}" >
	<jsp:attribute name="titleInfo">
		<t:taskStatusBadge status="${task.status()}"/> 
		<span class="label label-default">${task.score().value}</span> 
		<t:priorityLabel priority="${task.priority()}"/>  
	</jsp:attribute>

	<jsp:attribute name="title">
		${task.title()}
	</jsp:attribute>
		
	<jsp:attribute name="actions">
		<t:taskActions task="${task}" user="${user}" url="${url}"/>
	</jsp:attribute>
	
	<jsp:body>
		<div class="row">
            <div class="col-sm-12">
				<c:choose>
				    <c:when test="${task.description().isPresent()}">
				    	<p><strong><spring:message code="taskPanel.description.label"/></strong> ${task.description().get()}</p>
				    </c:when>    
				    <c:otherwise>
				    	<p><strong><spring:message code="taskPanel.description.label"/></strong> <spring:message code="taskPanel.description.empty"/></p>
				    </c:otherwise>
				</c:choose>
            </div>
        </div>	
	</jsp:body>
	
</t:collapsiblePanel>