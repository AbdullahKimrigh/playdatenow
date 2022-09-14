<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.Date"%>
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
					<div id="playdateCard" class="card p-3 d-md-flex justify-content-start">
						
						<div class="card p-2 mb-2">
							<p class="m-0 text-secondary" style="font-size: 0.8rem;">
								playdateName
							</p>
						
							<p class="m-0" style="font-size: 2rem;">
								<a href= "/playdate/${playdate.id}" style="text-decoration: none">${playdate.eventName}</a>
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
							<a href="/rsvp/${rsvp.id}"><button class="btn btn-secondary mb-2">Cancel </button></a>
						</div>
						
						<form:form action='/rsvp/edit' method='post' modelAttribute='rsvp'>
						
							<form:input type="hidden"  path="id" />
							
							<div class="form-floating mb-3">
								<form:select path="rsvpStatus" class="form-control" id="rsvpStatus" placeholder="rsvpStatus" >
									<form:option value="In" path="rsvpStatus">In</form:option>
									<form:option value="Maybe" path="rsvpStatus">Maybe</form:option>
									<form:option value="Out" path="rsvpStatus">Out</form:option>
								</form:select>
								<form:label path="rsvpStatus" for="rsvpStatus">Status</form:label>
							</div>
							
							<div class="form-floating mb-3">
								<form:input path="kidCount" type="number" class="form-control" id="kidCount" placeholder="kidCount" min="0" step="1" value="0"/>
								<form:label path="kidCount" for="kidCount"># of Kids</form:label>
								<p class="text-danger"><form:errors path="kidCount" />
							</div>
							
							<div class="form-floating mb-3">
								<form:input path="adultCount" type="number" class="form-control" id="adultCount" placeholder="adultCount" min="0" step="1" value="0"/>
								<form:label path="adultCount" for="adultCount"># of Adults</form:label>
								<p class="text-danger"><form:errors path="adultCount" />
							</div>
		
							<div class="form-floating mb-3">
								<form:textarea path="comment" type="text" class="form-control" id="comment" placeholder="comment" style="height: 10rem;"/>
								<form:label path="comment" for="comment">Comment</form:label>
								<p class="text-danger"><form:errors path="comment" />
							</div>							
							<div>
								<button type="submit" class="btn btn-primary w-100">Update</button>
							</div>
						</form:form>
	
					</div> <!-- end playdateCard -->
				<!-- </div> --> <!-- playdateContainer -->
				</div> <!-- end col -->
				<div class="col">
				</div> <!-- end col -->
			</div> <!-- end row -->
	</div> <!-- end main -->

	<jsp:include page="/WEB-INF/include/footerbuffer.jsp"/>
	<jsp:include page="/WEB-INF/include/footer.jsp"/>
	
</body>
</html>