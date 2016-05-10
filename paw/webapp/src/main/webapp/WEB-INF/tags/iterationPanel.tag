<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@tag description="NavBar Button" pageEncoding="UTF-8"%>
<%@attribute name="project" required="true" type="ar.edu.itba.models.Project" %>
<%@attribute name="iteration" required="true" type="ar.edu.itba.models.Iteration" %>
<%@attribute name="panelParent" required="false" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>


<t:collapsiblePanel panelId="iteration-${iteration.number()}" panelParent="${panelParent}">
    <jsp:attribute name="title">Iteration #${iteration.number()}</jsp:attribute>	
        
    <jsp:attribute name="actions">
        <a href="${pageContext.request.contextPath}/project/${project.code()}/iteration/${iteration.iterationId()}" class="btn btn-default btn-xs" >
            <span class="glyphicon glyphicon-share-alt" aria-hidden="true"></span> Go to iteration
        </a>
        <t:dropdownEditDelete href="${pageContext.request.contextPath}/project/${project.code()}/iteration/${iteration.iterationId()}"/>
    </jsp:attribute>
    
    <jsp:attribute name="list">
        
    </jsp:attribute>
    
    <jsp:body>
        <div class="row">
            <div class="col-sm-12">
                <b>Start date</b> ${iteration.startDate()}<br>
                <b>End date</b> ${iteration.endDate()}<br>
            </div>
        </div>
    </jsp:body>
</t:collapsiblePanel>