<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<style>
	.navbar{
		background-color: #739eb7!important;
	}
	.navbar-brand, #navSearchButton{
		color: white!important;
	    font-weight: bold;
	}
	#navbarSupportedContent,.btn-outline-warning{
	    background-color: #779db8;
		border-color: #caced3!important;
		color: white!important;
		margin:5px;
	}
</style>

<nav class="navbar navbar-expand-lg navbar-light bg-light sticky-top">
  <a class="navbar-brand" href="home.do">LARPup</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a id="navSearchButton" class="nav-link" href="searchPage.do">Search<span class="sr-only">(current)</span></a>
      </li>
      <c:if test="${ not empty sessionScope.myUser }">
	      <li class="nav-item">
	        <a id="navSearchButton" class="nav-link" href="addStory.do">Add Story</a>
	      </li>
      </c:if>
    </ul>
    <c:choose> 
      <c:when test="${ not empty sessionScope.myUser }"> 
        <form class="form-inline my-2 my-lg-0" action="displayUser.do" method="GET">
          <input type="hidden" name="userId" value="${sessionScope.myUser.id}"/>
          <button class="btn btn-sm btn-outline-warning my-2 my-sm-0" type="submit">Profile</button>
        </form>
        <form class="form-inline my-2 my-lg-0" action="logout.do" method="GET">
          <button class="btn btn-sm btn-outline-warning my-2 my-sm-0" type="submit">Logout</button>
        </form>
      </c:when>
	  <c:otherwise>
	    <form class="form-inline my-2 my-lg-0" action="authorize.do" method="POST">
	      <input class="form-control mr-sm-2" name="email" type="email" placeholder="email" aria-label="email">
	      <input class="form-control mr-sm-2" name="password" type="password" placeholder="password" aria-label="password">
	      <button class="btn btn-sm btn-outline-warning my-2 my-sm-0" type="submit">Login</button>
	    </form>
	      <form class="form-inline my-2 my-lg-0" action="createUserForm.do" method="GET">
	      <button class="btn btn-sm btn-outline-warning my-2 my-sm-0"type="submit">Sign Up</button>
	      </form>
	  </c:otherwise>
    </c:choose>
  </div>
</nav>
