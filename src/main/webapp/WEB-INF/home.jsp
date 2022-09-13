<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.Date" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<jsp:include page="include/head.jsp" />

<body>
	<jsp:include page="include/header.jsp" />
	
	<div id=pageHeader class="container-fluid p-2 bg-primary text-white text-center">
		<h2>Home</h2>
	</div>

	<div id=main class="container-fluid mainContainer">	
		<div id="welcome" class="container" style="width: 80rem">
			<h2>Welcome, ${authUser.firstName} ${authUser.lastName}</h2>
			<h3>Thanks for being part of our growing community.</h3> 
		</div>

		<c:choose>
			<c:when test="${errorMsg != null}">
				<div id="errorMsg" class="container listContainerStyle" style="width: 80rem">
					<p class="errorText">${errorMsg}</p>
				</div>	
			</c:when>
			<c:otherwise></c:otherwise>
		</c:choose> 



	</div>
	
	<jsp:include page="include/footerbuffer.jsp"/>
	<jsp:include page="include/footer.jsp"/>
			
			
</body>
</html>