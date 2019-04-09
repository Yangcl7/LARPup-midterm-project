<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<jsp:include page="/WEB-INF/components/bootstrapHead.jsp"></jsp:include>
<head>
<meta charset="UTF-8">
<title>Main Page</title>
</head>
<body>
	<jsp:include page="/WEB-INF/components/navbar.jsp"></jsp:include>
	<div class="container">
		<div class="alert alert-danger" role="alert">No account found!!! This account might not be active, please contact: <a href="mailto:admin@larpup.com">Admin</a></div>

		<br>

		<div class="form-row">
			<div class="col-md-2 col-3">

				<form class="form-group" action="createUserForm.do" method="GET">
					<button class="btn btn-success" type="submit">Join the fight!!!</button>
				</form>
				<br>
				<form class="form-group" action="home.do">
					<button class="btn btn-danger">Return Home</button>
				</form>
			</div>
		</div>
	</div>


</body>
<jsp:include page="/WEB-INF/components/bootstrapFoot.jsp"></jsp:include>
</html>








