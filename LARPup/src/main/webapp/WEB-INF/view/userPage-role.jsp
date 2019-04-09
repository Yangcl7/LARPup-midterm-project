<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link rel="stylesheet" href="css/card.css">

<div class="row">
<h3>Your Roles</h3>
</div>

<div class="row">
    <c:forEach var="role" items="${roleList}">
      <div class="card col-4 cardRoleExp">
          <img class="card-img-top" src="resources/images/cardpics/larpconHead.jpg" alt="Card image cap">
        <div class="card-head">
         <h5 class="card-title">${role.eventUserInfo.name }</h5>
        </div>
        <div class="card-body cardDescription">
           <p>${role.eventUserInfo.description}<br>
        </div>
        <div class="card-footer">
        <div class="row justify-content-center">
           <div class="centerContent">Story: ${role.event.story.name}<br>
           Event: ${role.event.name}</div>
        </div>
        <div class="row justify-content-center">
          <a class="btn btn-success btn-sm space" role="button" aria-pressed="true" href="displayStory.do?storyId=${story.id}">GO TO STORY</a>
          <a class="btn btn-success btn-sm space" role="button" aria-pressed="true" href="displayEvent.do?eventId=${role.event.id}">GO TO EVENT</a>
        </div>
      </div>
      </div>
    </c:forEach>
</div>