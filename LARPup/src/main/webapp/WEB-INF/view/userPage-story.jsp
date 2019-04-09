<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link rel="stylesheet" href="css/card.css">

<c:choose>
  <c:when test="${not empty ownerStoryList }">
  
    <div class="row">
      <h3>Your Stories <a class="btn btn-primary btn-sm" role="button" aria-pressed="true" href="addStory.do">ADD A STORY</a></h3>
    </div>

    <div class="row">
      <c:forEach var="story" items="${ownerStoryList}">
        <div class="card col-4 cardStory">
          <img class="card-img-top" src="resources/images/cardpics/openbook.jpg" alt="Card image cap">
        <div class="card-head">
          <h5 class="card-title">${story.name }</h5>
        </div>
        <div class="card-body cardDescription">
          <p>${story.description}<br>
        </div>
        <div class="card-footer justify-content-center">
          <div class="row justify-content-center">
          <div>${story.address.city }, ${story.address.state }</div>
          </div>
          <div class="row justify-content-center">
          <a class="btn btn-success btn-sm" role="button" aria-pressed="true" href="displayStory.do?storyId=${story.id }">GO TO STORY</a>
          </div>
        </div>
        </div>
      </c:forEach>
    </div>
  </c:when>
  <c:otherwise>
    <h3> You do not have any stories you own. <a class="btn btn-primary btn-sm space" role="button" aria-pressed="true" href="addStory.do">ADD A STORY</a></h3>
  </c:otherwise>
</c:choose>



<c:choose>
  <c:when test="${not empty involvedStoryList }">
    <div class="row">
      <h3>Stories you are involved in</h3>
    </div>
  
    <div class="row">
      <c:forEach var="story" items="${involvedStoryList}">
        <div class="card col-4 cardStory">
          <img class="card-img-top" src="resources/images/cardpics/openbook.jpg" alt="Card image cap">
        <div class="card-head">
         <h5 class="card-title">${story.name }</h5>
        </div>
        <div class="card-body cardDescription">
         <p>${story.description}<br>
        </div>
        <div class="card-footer">
        <div class="row justify-content-center">
         <div>${story.address.city }, ${story.address.state }</div>
        </div>
        <div class="row justify-content-center">
         <a class="btn btn-success btn-sm space" role="button" aria-pressed="true" href="displayStory.do?storyId=${story.id }">GO TO STORY</a>
        </div>
        </div>
        </div>
      </c:forEach>
    </div>
  
  </c:when>
  <c:otherwise>
    <h3>You are currently not involved in any stories </h3>
  </c:otherwise>
</c:choose>
