<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="row">
	<div class="col">
		<div class="row"><label>First Name</label> </div>
		<div class="row"><input readonly="readonly" class="form-control right-margin" type="text" value="${user.firstName}"/></div>
	</div>
	<div class="col">
		<div class="row"><label>Last Name</label> </div>
		<div class="row"><input readonly="readonly" class="form-control right-margin" type="text" value="${user.lastName}"/></div>
	</div>
	<div class="col">
		<div class="row"><label>Nickname</label> </div>
		<div class="row"><input readonly="readonly" class="form-control right-margin" type="text" value="${user.nickname}"/></div>
	</div>
</div>
<br>
<div class="row">
	<div class="col">
		<div class="row"><label>Email Address</label> </div>
		<div class="row"><input readonly="readonly" class="form-control right-margin" type="email" value="${user.email}"/></div>
	</div>
	<div class="col">
		<div class="row"><label>Birthday</label> </div>
		<div class="row"><input readonly="readonly" class="form-control right-margin" type="date" value="${user.birthDate}"/></div>
	</div>
	<div class="col"></div>
</div>
<br>
<div class="row">
	<div class="col">
		<div class="row"><label>Street</label> </div>
		<div class="row"><input readonly="readonly" class="form-control right-margin" type="text" value="${user.address.street}"/></div>
	</div>
	<div class="col"></div>
	<div class="col"></div>
</div>
<br>
<div class="row">
	<div class="col">
		<div class="row"><label>City</label> </div>
		<div class="row"><input readonly="readonly" class="form-control right-margin" type="text" value="${user.address.city}"/></div>
	</div>
	<div class="col">
		<div class="row"><label>State</label> </div>
		<div class="row"><input readonly="readonly" class="form-control right-margin" type="text" value="${user.address.state}"/></div>	
	</div>
	<div class="col">
		<div class="row"><label>Zip</label> </div>
		<div class="row"><input readonly="readonly" class="form-control right-margin" type="text" value="${user.address.zipcode}"/></div>
	</div>
</div>
<br><br>
<c:if test="${user.id == sessionScope.myUser.id || sessionScope.myUser.role == 'admin'}">
	<div class="row">
		<a class="btn btn-sm btn-secondary" href="updateUserForm.do?userId=${user.id}">Edit Profile</a>
	</div>
</c:if>