<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="includes/head.jsp" />

<body>
	<div class="container-fluid">		
		<div class="row justify-content-center mt-3">
			<div id="active_area" class="col-12 ">
				<div class="d-flex justify-content-between">
					<h2><c:out value="${show.name}"/></h2>
					<a href="/shows">Back to Dashboard</a>
				</div>
				<div>
					<strong>Posted By: <c:out value="${poster}"/></strong>
				</div>
				<div>Network: <c:out value="${show.network}"/></div>
				<div>Description: <c:out value="${show.description}"/></div>
			</div>
		</div>
		
		<c:when test="${user_id == poster.id}">
			<div id="buttons">
				<a href="/shows/${id}/edit" class="btn btn-primary">Edit show</a>
			</div>
		</c:when>
	</div>
</body>