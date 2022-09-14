<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.Date"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<jsp:include page="/WEB-INF/include/head.jsp" />

<body>
	<jsp:include page="/WEB-INF/include/header.jsp" />
	
	<div id=pageHeader class="container-fluid p-2 bg-primary text-white text-center">
		<h2>Playdate Management</h2>
	</div>

	<div id="main" class="container-fluid">
			<div class="row mt-3">
				<div class="col">
				</div> <!-- end col -->
				<div class="col-sm-7">
					<c:if test="${validationErrorMsg != null}">
				        <div class="alert alert-danger" role="alert">
				        	${validationErrorMsg}
				        </div>
				    </c:if>
					<div id="playdateCard" class="card p-3 d-md-flex justify-content-start">
						<div class="d-flex justify-content-end">
							<a href="/playdate"><button class="btn btn-secondary mb-2">Cancel</button></a>
						</div>
						
						<form:form action='/playdate/new' method='post' modelAttribute='playdate'>
						
							<div class="form-floating mb-3">
								<form:select path="eventStatus" class="form-control" id="eventStatus" placeholder="eventStatus" >
									<form:option value="It's on" path="eventStatus">It's on</form:option>
									<form:option value="Canceled" path="eventStatus">Canceled</form:option>
									<form:option value="Pending" path="eventStatus">Pending</form:option>
								</form:select>
								<form:label path="eventStatus" for="eventStatus">Playdate status</form:label>
								<p class="text-danger"><form:errors path="eventStatus" />
							</div>
							
							<div class="form-floating mb-3">
								<form:input path="eventName" type="text" class="form-control" id="eventName" placeholder="eventName"/>
								<form:label path="eventName" for="playdateName">Playdate Name</form:label>
								<div id="eventNameHelp" class="form-text">If left blank, your event will be listed as "Playdate."" Suggestion: enter a descriptive name (e.g., arts & crafts, bike ride, scooters, legos, etc.).</div>
								<p class="text-danger"><form:errors path="eventName" />
							</div>
							
							<div class="form-floating mb-3">
								<form:input path="locationName" type="text" class="form-control" id="locationName" placeholder="locationName"/>
								<form:label path="locationName" for="locationName">Location Name</form:label>
								<div id="locationHelp" class="form-text">Examples: Our Home or Central Park or Gertie's Ice Cream, etc.</div>
								<p class="text-danger"><form:errors path="locationName" />
							</div>
							
							<div class="form-floating mb-3">
								<form:input path="eventDate" type="date" class="form-control" id="eventDate" placeholder="eventDate"/>
								<form:label path="eventDate" for="eventDate">Event Date</form:label>
								<p class="text-danger"><form:errors path="eventDate" /></p>
							</div>
							
							<div class="form-floating mb-3">
								<form:select path="startTimeTxt" class="form-control" id="startTimeTxt" placeholder="startTimeTxt" >
									<c:forEach items="${startTimeList}" var="record">
										<form:option value="${record}" path="startTimeTxt">${record}</form:option>
									</c:forEach>
								</form:select>
								<form:label path="startTimeTxt" for="startTimeTxt">Start Time:</form:label>
								<p class="text-danger"><form:errors path="startTimeTxt" />
							</div> 
							
							<div class="form-floating mb-3">
								<form:input path="locationAddy" type="text" class="form-control" id="locationAddy" placeholder="locationAddy"/>
								<form:label path="locationAddy" for="locationAddy">Location Address</form:label>
								<p class="text-danger"><form:errors path="locationAddy" />
							</div>
							
							
							<div class="form-floating mb-3">
								<form:textarea path="eventDescription" type="eventDescription" class="form-control" id="eventDescription" placeholder="eventDescription" style="height: 10rem;"/>
								<form:label path="eventDescription" for="eventDescription">Playdate Description:</form:label>
								<p class="text-danger"><form:errors path="eventDescription" />
							</div>
							
							<div class="form-floating mb-3">
								<form:input path="maxCountKids" type="number" class="form-control" id="maxCountKids" placeholder="maxCountKids" min="1" step="1" value="1"/>
								<form:label path="maxCountKids" for="maxCountKids">Max. Number Kids</form:label>
								<p class="text-danger"><form:errors path="maxCountKids" />
							</div>
							
							<div>
								<button type="submit" class="btn btn-primary w-100">Create New Playdate</button>
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