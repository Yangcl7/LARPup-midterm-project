<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<jsp:include page="/WEB-INF/components/bootstrapHead.jsp"></jsp:include>
<head>
<meta charset="UTF-8">
<title>Adams Test Page</title>
</head>
<body>
<jsp:include page="/WEB-INF/components/navbar.jsp"></jsp:include>

	<form action="/event/displayEvent.do" method="GET">
		<label>Event ID:</label>
		<input type="text" name="eventId">
		<button type="submit">Submit</button>
	</form>
		<br>
	<form action="/event/newEventForm.do">
		<label>Story ID:</label>
		<input type="text" name="storyId">
		<button type="submit">NEW EVENT</button>
	</form>
	<a class="btn btn-primary" href="/search/searchPage.do">Search page</a>





<a href="/home/home.do">BACK TO HOME</a>
</body>
<jsp:include page="/WEB-INF/components/bootstrapFoot.jsp"></jsp:include>
</html>