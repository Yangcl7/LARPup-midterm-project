<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/components/bootstrapHead.jsp"></jsp:include>
<meta charset="UTF-8">
<title>Display User</title>
<link rel="stylesheet" href="css/search.css">
</head>
<body>
<jsp:include page="/WEB-INF/components/navbar.jsp"></jsp:include>


<div class="container">
	<div class="row">
		<div class="col-1"></div>
		<div class="col box">
			<div class="row">
				<div class="col-1"></div>
				<div class="col"><br><br>
					<div class="row">
						<form action="searchByString.do" method="GET">
							<div class="input-group mb-3">
								<div class="input-group-prepend">
									<button class="btn btn-sm btn-primary" name="searchFor" value="users" type="submit">Go</button>
								</div>
								<input name="searchString" type="text" class="form-control" placeholder="Search for users" aria-label="" aria-describedby="basic-addon1">
							</div>
						</form>
					</div>
					<div class="row">
						<form action="searchByString.do" method="GET">
							<div class="input-group mb-3">
								<div class="input-group-prepend">
									<button class="btn btn-sm btn-primary" name="searchFor" value="stories" type="submit">Go</button>
								</div>
								<input name="searchString" type="text" class="form-control" placeholder="Search for stories" aria-label="" aria-describedby="basic-addon1">
							</div>
						</form>
					</div>
					<div class="row">
						<form action="searchByString.do" method="GET">
							<div class="input-group mb-3">
								<div class="input-group-prepend">
									<button class="btn btn-sm btn-primary" name="searchFor" value="events" type="submit">Go</button>
								</div>
								<input name="searchString" type="text" class="form-control" placeholder="Search for events" aria-label="" aria-describedby="basic-addon1">
							</div>
						</form>
					</div>
				</div>
				<div class="col-1"></div>
			</div>
		</div>
		<div class="col box box-right">
			<h5>${nothingFound }</h5>
			<c:if test="${not empty resultClass }">
				<c:forEach var="result" items="${searchResult}">
						<c:choose>
							<c:when test="${resultClass == 'user'}">
								<a href="displayUser.do?userId=${result.id}">${result }</a><br><br>
							</c:when>
							<c:when test="${resultClass == 'story' }">
								<a href="displayStory.do?storyId=${result.id}">${result }</a><br><br>
							</c:when>
							<c:when test="${resultClass == 'event' }">
								<a href="displayEvent.do?eventId=${result.id}">${result }</a><br><br>
							</c:when>
						</c:choose>
				</c:forEach>
			</c:if>	
		</div>
		<div class="col-1"></div>
	</div>




	<div class="row"></div>
</div>

<jsp:include page="/WEB-INF/components/bootstrapFoot.jsp"></jsp:include>
</body>
</html>