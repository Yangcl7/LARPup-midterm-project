<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html>
<jsp:include page="/WEB-INF/components/bootstrapHead.jsp"></jsp:include>
<head>
<meta charset="UTF-8">
<title>Event Form</title>
</head>
<body>
<jsp:include page="/WEB-INF/components/navbar.jsp"></jsp:include>

<div class="container">
<div class="row justify-content-center">
<div class="col-8">



<form:form action="${action}" method="POST" modelAttribute="eventDTO">
  <div class="form-row">
    <div class="form-group col-md-8">
      <form:input type="hidden" path="id" value="${event.id}"/>
      <form:input type="hidden" path="storyId" value="${event.story.id}"/>
      <form:label path="name" >Name </form:label>
      <form:input class="form-control" type="text" path="name" value="${event.name }" placeholder="Name"/>
      <form:errors type="text" path="name" />
    </div>
    <div class="form-group col-md-4">
      <form:label path="date">Date </form:label>
      <form:input class="form-control" type="datetime-local" path="date" value="${event.date }"/>
      <form:errors type="text" path="date" />
    </div>
  </div>
  <div class="form-row">
    <div class="form-group col-12">
      <form:label path="description">Description </form:label>
      <form:textarea class="form-control" type="text" rows="5" path="description" value="${event.description}" placeholder="Description"/>
      <form:errors type="text" path="description" />
    </div>
  </div>
  <div class="form-row">
    <div class="form-group col-12">
      <form:label path="street">Street </form:label>
      <form:input class="form-control" type="text" path="street" value="${event.address.street }" placeholder="Street" />
      <form:errors type="text" path="street" />
    </div>
  </div>
  <div class="form-row">
    <div class="form-group col-5">
      <form:label path="city">City </form:label>
      <form:input class="form-control" type="text" path="city" value="${event.address.city }" placeholder="City"/>
      <form:errors type="text" path="city" />
    </div>
    <div class="form-group col-5">
      <form:label path="state">State: </form:label>
      <form:select path="state" class="form-control">
        <c:forEach var="myState" items="${statesList.statesList}">
          <c:choose>
          <c:when test="${myState == event.address.state}"><form:option selected="selected" value="${myState}">${myState}</form:option></c:when>
          <c:otherwise><form:option value="${myState}">${myState}</form:option></c:otherwise>
          </c:choose>
        </c:forEach>
        </form:select>
      <form:errors type="text" path="state" />
    </div>
    <div class="form-group col-2">
      <form:label path="zipcode">Zip </form:label>
      <form:input class="form-control" type="text" path="zipcode" value="${event.address.zipcode }" placeholder="Zipcode"/>
      <form:errors type="text" path="zipcode" />
    </div>
  </div>
  <button type="submit" class="btn btn-primary">Commit</button>

</form:form>


</div>
</div>
</div>

</body>
<jsp:include page="/WEB-INF/components/bootstrapFoot.jsp"></jsp:include>
</html>