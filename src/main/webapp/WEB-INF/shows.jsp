<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="includes/head.jsp" />

<body>
	<div class="container-fluid">
		
		<div class="row justify-content-center mt-3">
			<div id="active_area" class="col-7">
			 	<div class="d-flex">
			 		<h1>Welcome Back, <c:out value="${user_name}"/></h1>
					<a href="/logout">log out</a>
			 	</div>

				<h3>TVDB</h3>
				<table class="table">
					<thead>
						<th>Show</th>
						<th>Network</th>
					</thead>
					<c:forEach var="show" items="${shows}">
						<tr>
							<td>
								<a href="/shows/<c:out value="${show.id}"/>">
									<c:out value="${show.name}"/>
								</a>
							</td>
							<td>
								<c:out value="${show.network}"/>
							</td>
						</tr>
					</c:forEach>
				</table>
				
				<div id="buttons">
					<a href="/shows/new">Add a Show</a> 
				</div>
			</div>
		</div>
	</div>
</body>