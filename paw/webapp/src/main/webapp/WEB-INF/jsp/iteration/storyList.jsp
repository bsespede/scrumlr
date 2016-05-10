<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:page>
	<jsp:attribute name="title">
    	Iteration #${iteration.number()} <small>Project ${project.name()}</small>
    </jsp:attribute>
    
    <jsp:attribute name="actions">
		<a href="${pageContext.request.contextPath}/project/${project.code()}/iteration/${iteration.iterationId()}/story/new" class="btn btn-primary btn-sm">
			<span class="glyphicon glyphicon-plus" aria-hidden="true"></span> New story
	 	</a>
     </jsp:attribute>

     <jsp:body>    
		 <div class="row">
			 <div class="col-md-6 col-sm-12">	
				<div class="panel-group" id="story-list" role="tablist" aria-multiselectable="true">
					<c:forEach items="${stories}" var="story">
							<t:storyPanel panelParent="#story-list" project="${project}" iteration="${iteration}" story="${story.key}" tasks="${story.value}"/>
					</c:forEach>
				</div>
			</div>
			<div class="col-md-6 col-sm-12">
				<t:backlogPanel project="${project}" backlog="${backlog}"/>
			</div>
		 </div>
    </jsp:body>    
</t:page>