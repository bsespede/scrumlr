<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:page>
	<jsp:attribute name="title">
    	<spring:message code="dashboard.title"/>    	
    </jsp:attribute>
        
     <jsp:body>
     	<div class="row">
     		<div class="col-sm-6">
	     		<c:choose>
					<c:when test="${pendingTasks.isEmpty()}">
						<div class="alert alert-info" role="alert">
							<span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span> <spring:message code="dashboard.noPendingTasks"/>
						</div>
					</c:when>    
					<c:otherwise>
						<c:forEach items="${pendingTasks}" var="entry">
							<t:pendingProjectStoriesPanel project="${entry.key}" pendingStories="${entry.value}"/>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</div>
			<div class="col-sm-6">
				Here goes the timeline
			</div>
		</div>
    </jsp:body>    
</t:page>
