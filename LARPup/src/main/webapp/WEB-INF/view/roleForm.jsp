<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>
<jsp:include page="/WEB-INF/components/bootstrapHead.jsp"></jsp:include>
<head>
<meta charset="UTF-8">
<title>Role Form</title>
</head>
<body>
<jsp:include page="/WEB-INF/components/navbar.jsp"></jsp:include>

<div class="container">
<div class="row justify-content-center">
<div class="col-6">

<form:form action="${action}" method="POST" modelAttribute="inputDTO">
  <div class="form-row">
    <div class="form-group col-md-12">
      <form:input type="hidden" path="id" value="${role.id}"/>
      <form:input type="hidden" path="storyId" value="${story.id}"/>
      <form:label path="name" >Name </form:label>
      <form:input class="form-control" type="text" path="name" value="${role.name }"/>
      <form:errors type="text" path="name" />
    </div>
  </div>
  <div class="form-row">
    <div class="form-group col-md-12">
      <form:label path="name" >Description </form:label>
      <form:textarea class="form-control" rows="5" type="text" path="description" value="${role.description }"/>
      <form:errors type="text" path="description" />
    </div>
  </div>
  <button type="submit" class="btn btn-primary" type="submit">Commit</button>
</form:form>

</div>
</div>
</div>

</body>
<jsp:include page="/WEB-INF/components/bootstrapFoot.jsp"></jsp:include>
</html>