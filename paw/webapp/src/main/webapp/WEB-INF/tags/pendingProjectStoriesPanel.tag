<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@tag description="NavBar Button" pageEncoding="UTF-8"%>
<%@attribute name="project" required="true" type="ar.edu.itba.models.Project"%>
<%@attribute name="pendingStories" required="true" type="java.util.Map"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:staticPanel panelId="project-${project.code()}">
	<jsp:attribute name="title">
		${project.name()}
	</jsp:attribute>
	
	<jsp:body>
		<c:forEach items="${pendingStories}" var="entry">
				<t:collapsiblePanel panelId="story-${entry.key.storyId()}">
					<jsp:attribute name="title">
						${entry.key.title()}
					</jsp:attribute>
					<jsp:attribute name="list">
						<c:forEach items="${entry.value}" var="task">
							<li class="list-group-item">
								<t:pendingTaskPanel task="${task}"/>
							</li>
						</c:forEach>
					</jsp:attribute>
				</t:collapsiblePanel>
		</c:forEach>
	</jsp:body>
</t:staticPanel>