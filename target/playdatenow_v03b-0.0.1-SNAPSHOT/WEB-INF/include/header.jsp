<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- <div id=header class="container-fluid">
	<h1 id="siteTitle">playdatenow_v03b</h1>
	<div id="header-top-vert">
		<p class="header-profile-text">${user.userName}</p>
		<p class="header-profile-text">Email: ${user.email}</p>
		<a href="/logout">Logout</a>
	</div>
</div> --%>

<!-- <div id="navBar" class="container-fluid">
	<a href="/home">Home</a> 
	<a href="/house">House</a> 
	<a href="/playdate">Playdate</a>
	<a href="/twintwo">Twintwo</a>
</div> -->



<%-- <header class="p-3 bg-black">
	<div class="container-fluid">
		<div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
			<a href="/"
				class="d-flex align-items-center mb-2 mb-lg-0 text-dark text-decoration-none">
				<svg class="bi me-2" width="40" height="32" role="img"
					aria-label="Bootstrap">
					<use xlink:href="#bootstrap" /></svg>
			</a>

			<ul
				class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
				<li><a href="/home" class="nav-link px-2 link-light">Home</a></li>
				<li><a href="/house" class="nav-link-light px-2 light">House</a></li>
				<li><a href="/playdate" class="nav-link px-2">Playdate</a></li>
				<li><a href="/twintwo" class="nav-link px-2 text-white">Twintwo</a></li>
				<li class="nav-item"><a class="nav-link" href="#">Parents</a></li>
			</ul>

			<div class="dropdown text-end">
				<a href="#"
					class="d-block link-dark text-decoration-none dropdown-toggle text-white"
					data-bs-toggle="dropdown" aria-expanded="false"
				> 
					${user.userName}
				</a>
				<ul class="dropdown-menu text-small">
					<li><a class="dropdown-item text-dark" href="/profile/${user.id}">Profile</a></li>
					<li><hr class="dropdown-divider"></li>
					<li><a class="dropdown-item text-dark" href="/logout">Sign out</a></li>
				</ul>
			</div>
		</div>
	</div>
</header>  --%>

<nav class="navbar navbar-expand navbar-dark bg-dark" aria-label="Second navbar example">
	<div class="container-fluid">
      <!-- <a class="navbar-brand" href="#">Always expand</a> -->
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarsExample02" aria-controls="navbarsExample02" aria-expanded="false" aria-label="Toggle navigation">
		  <span class="navbar-toggler-icon"></span>
		</button>

	<div class="collapse navbar-collapse" id="navbarsExample02">
		<ul class="navbar-nav me-auto">
			<li class="nav-item"><h1 class="text-white">PlayDateNOW</h1></li>
			<!-- <li class="nav-item"><a class="nav-link active" aria-current="page" href="/">Home</a></li> -->
			<li class="nav-item"><a class="nav-link" href="/playdate">Playdates</a></li>
			<li class="nav-item"><a class="nav-link" href="/profile">Members</a></li>
		</ul>
        <div class="dropdown text-end">
<!--         <div class="dropdown-menu text-end dropdown-menu-right"> -->
				<a href="#"
					class="d-block link-dark text-decoration-none dropdown-toggle text-white"
					data-bs-toggle="dropdown" aria-expanded="false"
				> 
					${authUser.userName}
				</a>
				<ul class="dropdown-menu text-small">
					<li><a class="dropdown-item text-dark" href="/profile/${authUser.id}">Profile</a></li>
					<li><hr class="dropdown-divider"></li>
					<li><a class="dropdown-item text-dark" href="/logout">Sign out</a></li>
				</ul>
			</div>
      </div>
    </div>
</nav>


<!-- 		<ul class="navbar-nav">
      		<li class="nav-item">
        		<a class="nav-link" href="#">Link 1</a>
      		</li>
      		<li class="nav-item">
        		<a class="nav-link" href="#">Link 2</a>
      		</li>
      		<li class="nav-item">
        		<a class="nav-link" href="#">Link 3</a>
      		</li>
    	</ul> -->