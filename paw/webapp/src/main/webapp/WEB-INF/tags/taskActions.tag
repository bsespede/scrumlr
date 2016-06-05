<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@tag description="NavBar Button" pageEncoding="UTF-8"%>
<%@attribute name="url" required="true"%>
<%@attribute name="task" required="true" type="ar.edu.itba.models.Task"%>
<%@attribute name="user" required="true" type="ar.edu.itba.models.User"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<div class="dropdown fix-inline">
	<button class="btn btn-default btn-xs dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
		<span class="glyphicon glyphicon-cog" aria-hidden="true"></span> <spring:message code="taskActions.status"/>
		<span class="caret"></span>
	</button>
	<ul class="dropdown-menu">
		<c:if test="${task.status().getValue() != 2}">
			<li>					
		    	<form action="${url}/finished" method="POST">
					<button class="button-link" type="submit">
						<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> <spring:message code="taskActions.status.finished"/>
				    </button>
				</form>
			</li>
		</c:if>
		<c:if test="${task.status().getValue() != 1}">
			<li>					
		    	<form action="${url}/started" method="POST">
					<button class="button-link" type="submit">
						<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> <spring:message code="taskActions.status.started"/>
				    </button>
				</form>
			</li>
		</c:if>
		<c:if test="${task.status().getValue() != 0}">
			<li>					
		    	<form action="${url}/notStarted" method="POST">
					<button class="button-link" type="submit">
						<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> <spring:message code="taskActions.status.notStarted"/>
				    </button>
				</form>
			</li>
		</c:if>
	</ul>
</div>

