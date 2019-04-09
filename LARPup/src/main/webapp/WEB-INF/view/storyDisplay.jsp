<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<jsp:include page="/WEB-INF/components/bootstrapHead.jsp"></jsp:include>
<head>
<meta charset="UTF-8">
<title>Display Story</title>
<link rel="stylesheet" href="css/card.css">
<link rel="stylesheet" href="css/main.css">
<link rel="stylesheet" href="css/storyDisplay.css">
</head>
<body>
<jsp:include page="/WEB-INF/components/navbar.jsp"></jsp:include>

<div class="container">
  <div class="row">
    <div class="col">
    	<div class="banner-container">
		    <img src="${story.genre.picture.url }" />
        </div>
        <div class="titleFormat">
	    <h1>${story.name}</h1>
	    <h5>Genre: ${story.genre.name}</h5>
	    <c:if test="${sessionScope.myUser.id == story.user.id }">
	    	<a class="btn btn-primary btn-sm" role="button" aria-pressed="true" href="modifyStory.do?storyId=${story.id }">EDIT STORY</a>
    	</c:if>
	    </div>
	    <div class="row justify-content-center">
	    <div class="col-10">
	    <p class="storyDescFormat">${story.description}</p>
	    </div>
	    </div>
	    <div class="locationFormat">
	    <h5>This story is located in ${story.address.city }, ${story.address.state } </h5>
	    </div>
    </div>
  </div>
  <div class="row"><div class="col">
    <h3>Roles for this Story
    <c:if test="${sessionScope.myUser.id == story.user.id }"><a class="btn btn-primary btn-sm" role="button" aria-pressed="true" href="addRole.do?storyId=${story.id }">Add Role</a></c:if>
    </h3><br>
    </div></div>  
    
<div class="container">
  <div class="row">
    <c:forEach var="role" items="${story.eventRoles}" varStatus="iteration">
      <div class="card col-4 cardRole">
        <img class="card-img-top" src="resources/images/cardpics/larpconHead.jpg" alt="Card image cap">
        <div class="card-header">
          <h5 class="card-title">${role.name }</h5>
        </div>
        <div class="card-body cardRoleSmall">
          <p> ${role.description } </p>
        </div>
        <c:if test="${sessionScope.myUser.id == story.user.id }">
        <div class="card-footer">
          <div class="row justify-content-center">
            <a class="btn btn-primary btn-sm" role="button" aria-pressed="true" href="modifyRole.do?roleId=${role.id }" >MODIFY</a>
          </div>
        </div>
        </c:if>
      </div>
    </c:forEach>
  </div>
</div>
    
    
  <div class="row"><div class="col">
    <h3>Events for this Story
    <c:if test="${sessionScope.myUser.id == story.user.id }"><a class="btn btn-primary btn-sm" role="button" aria-pressed="true" href="newEventForm.do?storyId=${story.id }">Add Event</a></c:if>
    </h3><br>
  </div></div>

<div class="container">
  <div class="row">
    <c:forEach var="event" items="${story.events}" varStatus="iteration">
      <div class="card col-4 cardEvent">
          <img class="card-img-top" src="resources/images/cardpics/castle.jpg" alt="Card image cap">
        <div class="card-header">
         <h5 class="card-title">${event.name }</h5>
        </div>
        <div class="card-body cardDescription">
         <p> ${event.description } </p>
        </div>
          <div class="row justify-content-center">
          <p class="centerContent cardDetail">${event.address.street }<br>
          ${event.address.city}, ${event.address.state}<br>
          ${event.date }</p>
          </div>
        <div class="card-footer">
          <div class="row justify-content-center">
           <a class="btn btn-success btn-sm" role="button" aria-pressed="true" href="displayEvent.do?eventId=${event.id }" >GO TO EVENT</a>
          </div>
        </div>
      </div>
    </c:forEach>
  </div>
</div>
</div>

</body>
<jsp:include page="/WEB-INF/components/bootstrapFoot.jsp"></jsp:include>
</html>