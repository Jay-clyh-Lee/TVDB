<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<jsp:include page="includes/head.jsp" />

<body>
	<div class="container-fluid">
			
		<div class="row justify-content-center mt-3">
			<div class="col-12 col-md-7">
				<form:form method="POST" action="/shows/create" modelAttribute="show">
					<div class="row justify-content-center">
						<form:input type="hidden" path="id"/>
						
						<div class="form-group col-12 col-md-6">
							<form:label path="name">
								Title:
								<form:errors path="name"/>
								<form:input type="text" path="name" class="form-control"/>
							</form:label>
						</div>
						
						<div class="form-group col-12 col-md-6">
							<form:label path="network">
								Network:
								<form:errors path="network"/>
								<form:input type="text" path="network" class="form-control"/>
							</form:label>
						</div>
							
						<div class="form-group col-12 col-md-6">
							<form:label path="description">
								Description:
								<form:errors path="description"/>
								<form:input type="text" path="description" class="form-control"/>
							</form:label>
						</div>
						
						<div id="buttons">			
							<button class="btn btn-success mt-2 col-7 col-md-7">Submit</button>
						</div>
					</div>
				</form:form>
				<a href="/shows" class="btn btn-primary mt-2 col-7 col-md-7">Cancel</a> 
			</div>
		</div>
	</div>
</body>