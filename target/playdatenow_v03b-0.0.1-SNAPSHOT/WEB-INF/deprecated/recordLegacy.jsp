<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.Date" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="/WEB-INF/include/head.jsp" />

<body>
	<jsp:include page="/WEB-INF/include/header.jsp" />
	
	<div id=pageHeader class="container-fluid p-2 bg-primary text-white text-center">
		<h2>Rsvp Management</h2>
	</div>
	
		<div id="main" class="container-fluid">
			<div class="row mt-3">
				<div class="col">
				</div> <!-- end col -->
				<div class="col-sm-7">
						<div id="rsvpCard" class="card p-3 d-md-flex justify-content-start">
							
							<div class="card p-2 m-0">
								<p class="m-0 text-secondary" style="font-size: 0.8rem;">
									playdateName
								</p>
							
								<p class="m-0" style="font-size: 2rem;">
								<a href= "/playdate/${playdate.id}" style="text-decoration: none">${rsvp.playdateMdl.eventName}</a>
								</p>
							</div>
							
							<div class="d-flex justify-content-between">
								<div class="card p-2 border-0">
									<p class="m-0 text-secondary" style="font-size: 0.8rem;">
										Created
										<fmt:formatDate value="${rsvp.createdAt}" pattern="EEEE"/>,
										<fmt:formatDate value="${rsvp.createdAt}" pattern="MMMM dd"/>
										, 
										<fmt:formatDate value="${rsvp.createdAt}" pattern="yyyy"/>, 
										<fmt:formatDate value="${rsvp.createdAt}" pattern="h:mm a"/>
										</p>
								</div>
								<div>
									<a href= "/rsvp/${rsvp.id}/edit"><button class="btn btn-primary mb-2">Edit</button></a>
								</div>
							</div>
			
							<div class="card p-2 m-0 border-0">
								<p class="m-0 text-secondary" style="font-size: 0.8rem;">Status</p>
								<p class="m-0">${rsvp.rsvpStatus}</p>
							</div>
							
							<div class="card p-2 m-0 border-0">
								<p class="m-0 text-secondary" style="font-size: 0.8rem;">Created by</p>
								<p class="m-0">${rsvp.userMdl.userName}</p>
							</div>

							<div class="card p-2 border-0">
								<p class="m-0 text-secondary" style="font-size: 0.8rem;"># of Kids</p>
								<p class="m-0">${rsvp.kidCount}</p>
							</div>
							
							<div class="card p-2 border-0">
								<p class="m-0 text-secondary" style="font-size: 0.8rem;"># of Adults</p>
								<p class="m-0">${rsvp.adultCount}</p>
							</div>
							
							<div class="card p-2 border-0">
								<p class="m-0 text-secondary" style="font-size: 0.8rem;">Comment</p>
								<pre class="m-0">${rsvp.comment}</pre>
							</div>
							
														

					</div> <!-- end rsvpCard -->
			</div> <!-- end col -->
			<div class="col">
			</div> <!-- end col -->
		</div> <!-- end row -->
	</div><!-- end main -->
 
 	<jsp:include page="/WEB-INF/include/footerbuffer.jsp"/>
 	<jsp:include page="/WEB-INF/include/footer.jsp"/>
</body>
</html>