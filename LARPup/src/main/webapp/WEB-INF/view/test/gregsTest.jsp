<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<jsp:include page="/WEB-INF/components/bootstrapHead.jsp"></jsp:include>
<head>
<meta charset="UTF-8">
<title>Gregs Test Page</title>
</head>
<body>
<jsp:include page="/WEB-INF/components/navbar.jsp"></jsp:include>
I MADE IT!!! WELCOME TO THE GREGS TEST JSP!!!<br>
<a href="/story/displayStory.do?storyId=1">LINK TO STORY</a><br>
<a href="/story/addStory.do?">LINK TO ADD STORY</a><br>
<a href="/home/home.do">BACK TO HOME</a>
</body>
<jsp:include page="/WEB-INF/components/bootstrapFoot.jsp"></jsp:include>
</html>