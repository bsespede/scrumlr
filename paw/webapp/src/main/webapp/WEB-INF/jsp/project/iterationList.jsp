<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:page>
	<jsp:attribute name="title">
    	Project ${project.name}
    </jsp:attribute>
    
    <jsp:attribute name="actions">
	    <a href="#" class="btn btn-primary btn-sm">
			<span class="glyphicon glyphicon-plus" aria-hidden="true"></span> New iteration
		</a>
		<a href="#" class="btn btn-default btn-sm">
			<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> Edit project
		</a>
		<a href="#" class="btn btn-danger btn-sm">
			<span class="glyphicon glyphicon-trash" aria-hidden="true"></span> Delete project
		</a>
     </jsp:attribute>

     <jsp:body>
			<div class="panel-group" aria-multiselectable="true">
				<t:descriptionPanel project="${project}"/>
				<t:iterationsPanel projectCode="${project.code}" iterations="${iterations}"/>
			</div>
    </jsp:body>    
</t:page>
    
</body>

</html>