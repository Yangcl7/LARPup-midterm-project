<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<jsp:include page="/WEB-INF/components/bootstrapHead.jsp"></jsp:include>
<head>
<meta charset="UTF-8">
<title>Main Page</title>
<link rel="stylesheet" href="css/card.css">
<link rel="stylesheet" href="css/main.css?v=2">
</head>
<body>
<jsp:include page="/WEB-INF/components/navbar.jsp"></jsp:include>

<div class="container">
  <div class="row"><div class="col"></div></div>
  <div class="row">
    <div class="col">
      <div class="banner-container">
      	<div class="banner-img">
      		<img src="resources/images/storypics/bigBattle3.jpg" />
      	</div>
	      <h3>LARP UP!</h3>
      </div>
      <div class="row">
	      <div class="col-1"></div>
	      <div class="col">
		      <div class="quote">
			      <p>"Cool new experiences, interesting new perspectives, and so on. Larping combines the draw of alternate roles with a definite physicality, and thus affects its players in ways most other mediums can’t."
			      	<br><strong>- J. Tuomas Harviainen</strong>
			      </p>
		      </div>
	      </div>
	      <div class="col-1"></div>
      </div>
    </div>
  </div>
</div>
<div class="container">
  <div class="row">
        <c:forEach var="story" items="${recentStoryList }">
          <div class="card col-4 cardStory">
            <img class="card-img-top" src="resources/images/cardpics/openbook.jpg" alt="Card image cap">
            <div class="card-head">
              <h5 class="card-title">${story.name }</h5>
            </div>
            <div class="card-body cardDescription">
              <p>${story.description}</p>
            </div>
            <div class="card-footer justify-content-center">
              <div class="row justify-content-center">
              ${story.address.city }, ${story.address.state }
              </div>
              <div class="row justify-content-center">
              <a class="btn btn-success btn-sm" role="button" aria-pressed="true" href="displayStory.do?storyId=${story.id }">GO TO STORY</a>
              </div>
              </div>
            </div>
        </c:forEach>
  </div>
</div>

</body>
<jsp:include page="/WEB-INF/components/bootstrapFoot.jsp"></jsp:include>
</html>