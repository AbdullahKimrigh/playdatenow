<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.Date" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="/WEB-INF/include/head.jsp" />

<body>
	<jsp:include page="/WEB-INF/include/header.jsp" />
	
		<div id=pageHeader class="container-fluid p-2 bg-primary text-white text-center">
			<h2>Member Profile</h2>
		</div>
	
	<div id=main class="container-fluid">
		<div class="row mt-3">
			<div class="col">
			</div> <!-- end col -->
			<div class="col-sm-7">
				<c:if test="${permissionErrorMsg != null}">
			        <div class="alert alert-warning" role="alert">
			        	${permissionErrorMsg}
			        </div>
			    </c:if>
				<div id="profileCard" class="card p-3 d-md-flex justify-content-start">
					<div class="d-flex justify-content-between">
						<div class="card p-2 border-0">
							<p class="m-0 text-secondary" style="font-size: 0.8rem;">
								Joined

								<fmt:formatDate value="${userProfile.createdAt}" pattern="EEEE"/>,
								<fmt:formatDate value="${userProfile.createdAt}" pattern="MMMM dd"/>
								, 
								<fmt:formatDate value="${userProfile.createdAt}" pattern="yyyy"/>, 
								<fmt:formatDate value="${userProfile.createdAt}" pattern="h:mm a"/>
 							
 							</p>
						</div>
						<div>
							<c:if test="${userProfile.id == authUser.id}">
							   <a href= "/profile/${userProfile.id}/edit"><button class="btn btn-primary mb-2">Edit</button></a>
							</c:if>
						</div>
					</div>
					
					<div class="card p-2 border-0">
						<p class="m-0 text-secondary" style="font-size: 0.8rem;">Username</p>
						<p class="m-0">${userProfile.userName}</p>
					</div>
					
					<div class="card p-2 border-0">
						<p class="m-0 text-secondary" style="font-size: 0.8rem;">Email</p>
						<p class="m-0">${userProfile.email}</p>
					</div>
					
					<div class="card p-2 border-0">
						<p class="m-0 text-secondary" style="font-size: 0.8rem;">First name</p>
						<p class="m-0">${userProfile.firstName}</p>
					</div>
					
					<div class="card p-2 border-0">
						<p class="m-0 text-secondary" style="font-size: 0.8rem;">Last name</p>
						<p class="m-0">${userProfile.lastName}</p>
					</div>
					
					<div class="card p-2 border-0">
						<p class="m-0 text-secondary" style="font-size: 0.8rem;">About me</p>
						<pre class="textAreaReadOut">${userProfile.aboutMe}</pre>
					</div>
					
					<div class="card p-2 border-0">
						<p class="m-0 text-secondary" style="font-size: 0.8rem;">Location</p>
						<p class="m-0">${userProfile.city} -- ${userProfile.zipCode}</p>
					</div>
	
				</div> <!-- end profileCard -->
			</div> <!-- end col -->
			<div class="col">
			</div> <!-- end col -->
		</div> <!-- end row -->
	</div><!-- end main -->
 
 	<jsp:include page="/WEB-INF/include/footerbuffer.jsp"/>
 	<jsp:include page="/WEB-INF/include/footer.jsp"/>
</body>
</html>