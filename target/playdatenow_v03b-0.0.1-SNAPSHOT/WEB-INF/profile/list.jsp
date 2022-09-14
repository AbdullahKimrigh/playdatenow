<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.Date" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="/WEB-INF/include/head.jsp" />

<body>
	<jsp:include page="/WEB-INF/include/header.jsp" />
	
	<div id=pageHeader class="container-fluid p-2 bg-primary text-white text-center">
		<h2>Members</h2>
	</div>
		
	<div id=main class="container-fluid">
		
		<div id="profileCardArray" class="container m-5 d-flex flex-wrap">
			<c:forEach var="record" items="${profileList}">
				<div class="card m-3" style="width: 18rem;">
					<div class="card-body">
						<h5 class="card-title">${record.userName}</h5>
						<p class="card-text">${record.firstName} ${record.lastName}</p>
						
							<pre style="white-space: pre-wrap; max-height: 10rem; overflow: ellipsis;">${record.aboutMe}</pre>
						
					</div>
					<ul class="list-group list-group-flush">
						<li class="list-group-item">Joined: <fmt:formatDate value="${record.createdAt}" pattern="MMMM"/> <fmt:formatDate value="${record.createdAt}" pattern="yyyy"/></li>
						<li class="list-group-item"><a class="text-decoration-none" href="/profile/${record.id}">View Full Profile</a></li>
					</ul>
				</div>
			</c:forEach>
			
			
		</div> <!-- end profileCardArray -->
			
	</div> <!-- end main -->  
 	<jsp:include page="/WEB-INF/include/footerbuffer.jsp"/>
 	<jsp:include page="/WEB-INF/include/footer.jsp"/>
</body>
</html>