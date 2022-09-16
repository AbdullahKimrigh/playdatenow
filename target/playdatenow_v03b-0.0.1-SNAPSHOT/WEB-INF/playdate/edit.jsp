<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.Date" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="/WEB-INF/include/head.jsp" />

<body>
	<jsp:include page="/WEB-INF/include/header.jsp" />
	
	<div id=pageHeader class="container-fluid p-2 bg-primary text-white text-center">
		<h2>Playdate Management</h2>
	</div>
	
	<div id="main" class="container-fluid">
		<div id="pageRow1" class="row mt-3">
			<div id="leftPageCol" class="col">
			</div> <!-- end leftPageCol -->
			<div id="centerPageCol" class="col-sm-7">
				<c:if test="${validationErrorMsg != null}">
			        <div class="alert alert-danger" role="alert">
			        	${validationErrorMsg}
			        </div>
			    </c:if>
				<div id="playdateCard" class="card p-3 d-md-flex justify-content-start">
					<div id="creatorOrganizerTopButton"  class="d-flex justify-content-between">
						
						<c:choose>
							<c:when test="${playdate.createdAt != null}">
							<div id="creatorOrganizer" class="card p-2 border-0">
								<p class="m-0 text-secondary" style="font-size: 0.8rem;">Created by
									<c:choose>
										<c:when test="${playdate.userMdl.id == authUser.id}">
											you (${playdate.userMdl.userName})
										</c:when>
										<c:otherwise>
											${playdate.userMdl.userName}
										</c:otherwise>
									</c:choose> 
									on 
									<fmt:formatDate value="${playdate.createdAt}" pattern="EEEE"/>, <fmt:formatDate value="${playdate.createdAt}" pattern="MMMM dd"/>, <fmt:formatDate value="${playdate.createdAt}" pattern="yyyy"/>, <fmt:formatDate value="${playdate.createdAt}" pattern="h:mm a"/>
								</p>
								<c:if test="${playdate.userMdl.id == authUser.id}">
									<p class="m-0 text-secondary">You are the organizer of this event.</p>
								</c:if>
							</div> <!-- end creatorOrganizer -->
							</c:when>
							<c:otherwise> 
							<div></div>
							</c:otherwise>
						</c:choose>
						
						<a href="/playdate/${playdate.id}"><button class="btn btn-secondary  mb-2">Cancel</button></a>
					</div> <!-- end creatorOrganizerTopButton -->
					<div id="eventDeetsAndRsvpRow" class="row mt-3">
						<div id="playdateInfoCol" class="col">
							<form:form action='/playdate/edit' method='post' modelAttribute='playdate'> <!-- begin playdateEditForm -->
								<form:input type="hidden"  path="id" />
								
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
									<form:label path="eventName" for="eventName">Playdate Name</form:label>
									<div id="eventNameHelp" class="form-text">If left blank, your event will be listed as "Playdate." Suggestion: enter a descriptive name (e.g., arts & crafts, bike ride, scooters, legos, etc.).</div><p class="text-danger"><form:errors path="eventName" />
									<p class="text-danger"><form:errors path="eventName" /></p>
								</div>
								
								<div class="form-floating mb-3">
									<form:input path="locationName" type="text" class="form-control" id="locationName" placeholder="locationName"/>
									<form:label path="locationName" for="locationName">Location Name</form:label>
									<div id="locationHelp" class="form-text">Examples: Our Home or Central Park or Gertie's Ice Cream, etc.</div><p class="text-danger">
									<p class="text-danger"><form:errors path="locationName" /></p>
								</div>
								
								<div class="form-floating mb-3">
									<form:input path="eventDate" type="date" class="form-control" id="eventDate" placeholder="eventDate"/>
									<form:label path="eventDate" for="eventDate">Event Date</form:label>
									<p class="text-danger"><form:errors path="eventDate" /></p>
								</div>
								
								<div class="form-floating mb-3">
									<form:select path="startTimeTxt" class="form-control" id="startTimeTxt" placeholder="startTimeTxt" >
										<c:forEach items="${startTimeList}" var="record">
											<c:choose>
												<c:when test="${record == playdate.startTimeTxt}">
													<form:option value="${record}" path="startTimeTxt" selected="true">${record}</form:option>
												</c:when>
												<c:otherwise>
													<form:option value="${record}" path="startTimeTxt">${record}</form:option>
												</c:otherwise>
											</c:choose>
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
									<form:input path="maxCountKids" type="number" class="form-control" id="maxCountKids" placeholder="maxCountKids" min="1" step="1"/>
									<form:label path="maxCountKids" for="maxCountKids">Max. Kids Number</form:label>
									<p class="text-danger"><form:errors path="maxCountKids" />
								</div>
								
								<div>
									<button type="submit" class="btn btn-primary">Update</button>
								</div> 
								
							</form:form> <!-- end playdateEditForm -->
						</div> <!-- end playdateInfoCol -->
						
						<div id="rsvpEtcCol" class="col">
							<!-- <div id="maxSpace" style="display: flex; flex-direction: column; justify-content: space-between;"> this line is just not working -->
									<div id="rsvpTrackingCard" class="card p-3 d-md-flex justify-content-start mb-3">
										<p class="m-0 text-secondary text-center">RSVP Tracking</p>
										<table class="table table-responsive mt-2 table-borderless table-sm">
											<thead class="table-light align-top">
												<tr>
													<th scope="col">RSVPs</th>
													<th scope="col">RSVPed Adults</th>
													<th scope="col">Max Kids</th>
													<th scope="col">RSVPed Kids</th>
													<th scope="col">Open Spots</th>													
												</tr>
											</thead>
											<tbody>
												<tr>
													<td>${rsvpCount}</td>
													<td>${sumRsvpDotAdultsCount}</td>
													<td>${playdate.maxCountKids}</td>
													<td>${sumRsvpDotKidsCount}</td>
													<td>${openKidsSpots}</td>
												</tr>
											</tbody>
										</table>
									</div>
									
									<div id="rsvpCard" class="card p-3 d-md-flex justify-content-start">
									
									<c:choose>
										<c:when test="${rsvpExistsCreatedByAuthUser}">
											<div class="d-flex justify-content-center">
												<div class="card p-2 border-0">
													<p class="m-0 text-secondary" style="font-size: 0.8rem;">
														You RSVPed on
														<fmt:formatDate value="${rsvpObjForAuthUser.createdAt}" pattern="EEEE"/>,
														<fmt:formatDate value="${rsvpObjForAuthUser.createdAt}" pattern="MMMM dd"/>
														, 
														<fmt:formatDate value="${rsvpObjForAuthUser.createdAt}" pattern="yyyy"/>, 
														<fmt:formatDate value="${rsvpObjForAuthUser.createdAt}" pattern="h:mm a"/>
														</p>
												</div>
											</div>
							
											<div class="card p-2 m-0 border-0">
												<p class="m-0 text-secondary" style="font-size: 0.8rem;">Status</p>
												<p class="m-0">${rsvpObjForAuthUser.rsvpStatus}</p>
											</div>
				
											<div class="card p-2 border-0">
												<p class="m-0 text-secondary" style="font-size: 0.8rem;"># of Kids</p>
												<p class="m-0">${rsvpObjForAuthUser.kidCount}</p>
											</div>
											
											<div class="card p-2 border-0">
												<p class="m-0 text-secondary" style="font-size: 0.8rem;"># of Adults</p>
												<p class="m-0">${rsvpObjForAuthUser.adultCount}</p>
											</div>
											
											<div class="card p-2 border-0">
												<p class="m-0 text-secondary" style="font-size: 0.8rem;">Comment</p>
												<pre class="m-0">${rsvpObjForAuthUser.comment}</pre>
											</div>
											
											<div class="card p-2 border-0 d-md-flex justify-content-center bg-light ">
												<p class="m-0 text-center text-secondary">Need to update your RSVP?<br>Click Cancel or Update to return to the main playdate screen.</p>
											</div>
		
										</c:when>
										<c:otherwise>
											<div class="card p-2 border-0 d-md-flex justify-content-center bg-light ">
												<p class="m-0 text-center text-secondary">You have not RSVPed for this event yet.<br>You can enter your RSVP once you click Cancel or Update to return to the main playdate screen.</p>
											</div>
										</c:otherwise>
									</c:choose>
								</div> <!-- end rsvpCard -->
								
								<div id="otherActionsCard" class="card mt-3 p-3 d-flex justify-content-end">
									<c:choose >
								        <c:when test="${not hasOneOrMoreRsvp}">
								        	<div class="card p-2 border-0">
										        <p>Other Actions:</p>
											</div>
											<div>
										        <form action="/playdate/${playdate.id}" method="post">
												    <input type="hidden" name="_method" value="delete">
												    <button class="btn btn-danger">Delete this Playdate</button>
												</form>
											</div>
								        </c:when>
								        <c:otherwise>
									        <div class="card p-2 border-0">
									        	<p>This event has rsvp records, so it cannot be deleted.<br>
									        	It can only be deleted if/when all RSVPS deleted.<br>
									        	If this event is no longer happening, update the Status to be "Cancelled."</p>
									        </div>
								        </c:otherwise>
								    </c:choose>
								</div> <!-- end otherActionsCard -->
							<!-- </div> --> <!-- end maxSpace -->
						</div> <!-- end rsvpEtcCol-->
					</div> <!-- end eventDeetsAndRsvpRow -->
					<div id="rsvpListRow" class="row m-1">	
						<p class="text-center m-1" style="font-size: 1.5rem;">Rsvp List</p>
						
							<table class="table table-striped table-hover table-bordered table-responsive mt-2">
							<thead>
								<tr>
									<th scope="col">Name</th>
									<th scope="col">Status</th>
									<th scope="col"># of Kids</th>
									<th scope="col"># of Adults</th>
									<th scope="col">Comment</th>
									
								</tr>
							</thead>
							<tbody>
								<c:forEach var="record" items="${rsvpList}">
									<tr>
										<td><a class="text-decoration-none" href="/profile/${record.userMdl.id}">${record.userMdl.userName}</a></td>
										<td>${record.rsvpStatus}</td>
										<td>${record.kidCount}</td>
										<td>${record.adultCount}</td>
										<td>${record.comment}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div> <!-- end rsvpListRow -->	
				</div> <!-- end playdateCard -->
				
			</div> <!-- end centerPageCol -->
			<div id="rightPageCol" class="col">
			</div> <!-- end rightPageCol -->
		</div> <!-- end pageRow1 -->
	</div> <!-- end main -->
 	
 	<jsp:include page="/WEB-INF/include/footerbuffer.jsp"/>
 	<jsp:include page="/WEB-INF/include/footer.jsp"/>
</body>
</html>