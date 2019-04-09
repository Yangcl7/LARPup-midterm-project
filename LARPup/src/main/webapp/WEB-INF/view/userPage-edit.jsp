<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

	<div class="container">
		<h1>Edit Profile</h1>
		<br>
		<form:form action="${action}" method="POST" modelAttribute="userDTO">
			<div class="row">
				<div class="col">
					<div class="row"><form:label path="firstName">First Name</form:label> </div>
					<div class="row"><form:input path="firstName" class="form-control right-margin" type="text" value="${user.firstName}"/></div>
				</div>
				<div class="col">
					<div class="row"><form:label path="lastName">Last Name</form:label> </div>
					<div class="row"><form:input path="lastName" class="form-control right-margin" type="text" value="${user.lastName}"/></div>
				</div>
				<div class="col">
					<div class="row"><form:label path="nickname">Nickname</form:label> </div>
					<div class="row"><form:input path="nickname" class="form-control right-margin" type="text" value="${user.nickname}"/></div>
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col">
					<div class="row"><form:label path="email">Email Address</form:label> </div>
					<div class="row"><form:input path="email" class="form-control right-margin" type="email" value="${user.email}"/></div>
				</div>
				<div class="col">
					<div class="row"><form:label path="birthDate">Birthday</form:label> </div>
					<div class="row"><form:input path="birthDate" class="form-control right-margin" type="date" value="${user.birthDate}"/></div>
				</div>
				<div class="col"></div>
			</div>
			<br>
			<div class="row">
				<div class="col">
					<div class="row"><form:label path="street">Street</form:label> </div>
					<div class="row"><form:input path="street" class="form-control right-margin" type="text" value="${user.address.street}"/></div>
				</div>
				<div class="col"></div>
				<div class="col"></div>
			</div>
			<br>
			<div class="row">
				<div class="col">
					<div class="row"><form:label path="city">City</form:label> </div>
					<div class="row"><form:input path="city" class="form-control right-margin" type="text" value="${user.address.city}"/></div>
				</div>
				<div class="col">
					<div class="row"><form:label path="state">State</form:label> </div>
<%-- 					<div class="row"><form:input path="state" class="form-control right-margin" type="text" value="${user.address.state}"/></div>
 --%>			        <form:select path="state" class="form-control">
				        <c:forEach var="myState" items="${statesList.statesList}">
				          <c:choose>
				        	  <c:when test="${myState == user.address.state}">
				        	  	<form:option selected="selected" value="${myState}">${myState}</form:option>
				        	  </c:when>
					          <c:otherwise>
					          	<form:option value="${myState}">${myState}</form:option>
				          	</c:otherwise>
				          </c:choose>
				        </c:forEach>
			        </form:select>
				</div>
				<div class="col">
					<div class="row"><form:label path="zipcode">Zip</form:label> </div>
					<div class="row"><form:input path="zipcode" class="form-control right-margin" type="text" value="${user.address.zipcode}"/></div>
				</div>
			</div>
			<br><br>
			
			<c:if test="${sessionScope.myUser.id == user.id || sessionScope.myUser.role == 'admin'}">
				<div class="row">
					<div class="col-3">
						<button type="submit" class="btn btn-sm btn-primary">Save Changes</button>
					</div>
	
					<div class="col-3">
						<a href="deactivateUser.do?userId=${user.id}" class="btn btn-sm btn-danger">Deactivate Account</a>
					</div>
					<div class="col-6"></div>
				</div>
			</c:if>
			<form:input type="hidden" path="id" value="${user.id}"/>
		</form:form>
		<br>
		<c:if test="${sessionScope.myUser.id == user.id || sessionScope.myUser.role == 'admin'}">
			<div class="row">
				<div class="col-3">
					<button class="btn btn-sm btn-secondary dropdown-toggle" type="button"
						data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						Change Password 
					</button>
					<div class="dropdown-menu">
						<form action="resetPassword.do?userId=${user.id}" method="POST" class="px-4 py-3">
							<div class="form-group">
								<label for="newPass" >New Password</label> <input type="text"
									class="form-control" name="newPass">
								<button type="submit" class="btn btn-primary">Submit</button>
							</div>
						</form>
					</div>
				</div>
				<div class="col-9"></div>
			</div>
		</c:if>
	</div>
