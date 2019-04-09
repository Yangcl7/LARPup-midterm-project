<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/components/bootstrapHead.jsp"></jsp:include>
<meta charset="UTF-8">
<title>Display User</title>
<link rel="stylesheet" href="css/user_page.css">
</head>
<body>
<jsp:include page="/WEB-INF/components/navbar.jsp"></jsp:include>
<div class="header">
	<div class="container">
		<div class="userPic">
			<div class="row">
				<div class="col-4">
					<img height="150px" src="${user.picture.url}">
					<c:if test="${sessionScope.myUser.id == user.id || sessionScope.myUser.role == 'admin'}">
						<div class="dropdown">
							<a class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
								Change Image
							</a>
							<div class="dropdown-menu">
								<form action="changeUserImage.do?userId=${user.id }" method="POST" class="px-1 py-1">
									<div class="form-group">
										<label for="newUrl">URL:</label> 
										<input type="text" class="form-control" name="newUrl" value="${user.picture.url }">
									</div>
									<button type="submit" class="btn btn-primary">Submit</button>
								</form>
							</div>
						</div>
					</c:if>
				</div>
				<div class="col-8 insetshadow">
					<h1>${user.nickname }</h1>
				</div>
			</div>
		</div>
	</div>
	<nav id="userTabs">
	  <div class="nav nav-tabs" id="nav-tab" role="tablist">
	    <a class="nav-item nav-link active" id="nav-info-tab" data-toggle="tab" href="#nav-info" role="tab" aria-controls="nav-info" aria-selected="true">Profile</a>
	    <a class="nav-item nav-link" id="nav-stories-tab" data-toggle="tab" href="#nav-stories" role="tab" aria-controls="nav-stories" aria-selected="false">Stories</a>
	    <a class="nav-item nav-link" id="nav-roles-tab" data-toggle="tab" href="#nav-roles" role="tab" aria-controls="nav-roles" aria-selected="false">Roles</a>
	    <c:if test="${sessionScope.myUser.role == 'admin' && sessionScope.myUser.id == user.id }">
	    	<a class="nav-item nav-link" id="nav-admin-tab" data-toggle="tab" href="#nav-admin" role="tab" aria-controls="nav-admin" aria-selected="false">Admin</a>
	  	</c:if>
	  </div>
	</nav>
</div>
<div class="container">
</div>

<div class="container white">
	<div class="tab-content" id="nav-tabContent">
	  <div class="tab-pane fade show active" id="nav-info" role="tabpanel" aria-labelledby="nav-info-tab">
		<div class="container">
			<div class="userDetails">
			  	<c:choose>
					<c:when test="${user.active}">
						<c:choose>
							<c:when test="${not empty action}">
								<jsp:include page="/WEB-INF/view/userPage-edit.jsp"></jsp:include>
							</c:when>
							<c:otherwise>
								<jsp:include page="/WEB-INF/view/userPage-display.jsp"></jsp:include>
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						<h5>This account is not active</h5>
						<br>
					    <c:if test="${sessionScope.myUser.role == 'admin'}">
							<div class="row">
								<a class="btn btn-sm btn-primary" href="reactivateUser.do?userId=${user.id}">Reactivate Account</a>
							</div>
	  					</c:if>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	  </div>
	  <div class="tab-pane fade" id="nav-stories" role="tabpanel" aria-labelledby="nav-stories-tab">
	    <div class="container">
	      <jsp:include page="/WEB-INF/view/userPage-story.jsp"></jsp:include>
	    </div>
	  </div>
	  <div class="tab-pane fade" id="nav-roles" role="tabpanel" aria-labelledby="nav-roles-tab">
	    <div class="container">
	      <jsp:include page="/WEB-INF/view/userPage-role.jsp"></jsp:include>
	    </div>
	  </div>	
	  <c:if test="${sessionScope.myUser.id == user.id || sessionScope.myUser.role == 'admin'}">
		<div class="tab-pane fade" id="nav-admin" role="tabpanel" aria-labelledby="nav-admin-tab">
		  <jsp:include page="/WEB-INF/view/admin.jsp"></jsp:include>
		</div>
	  </c:if>
	</div>
</div>
<jsp:include page="/WEB-INF/components/bootstrapFoot.jsp"></jsp:include>
</body>
</html>