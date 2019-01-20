<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <link href="resources/css/side-navigation.css" rel="stylesheet">
	<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
	<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
	<script src="http://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>
	<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/jquery-validation@1.17.0/dist/jquery.validate.js"></script>
	</head>
	
	<body>	
		<!--- Side navigation --->
		<div class="sidenav">
		
			<div class="menu-link" >
				<a href="welcome">Welcome</a>
			</div>
			
			<div class="menu-link" >
				<a href="login">Login</a>
			</div>
			
		    <div class="active" >
				<a href="register">Registration</a>
			</div>
			
		</div>

		<!--- Page content --->
		<div class="main">
			<p> We're gonna change some stuff over here. If you wanna change, you have to start somewhere, why not here? </p>
			

			<form id="changeForm" method="POST" class="Update-info-form" role="form" action = "registering" modelAttribute = "client"> 
				
				<div class="form-group">
					<label for="name" class="change-information-control-label">First name</label>
					<input type="text" id="name" name= "name" class="form-control" placeholder="First name">
				</div>
				
				<div class="form-group">
					<label for="surname" class="change-information-control-label">Last name</label>
					<input type="text" id="surname" name= "surname" class="form-control" placeholder="Last name">
				</div>
				
				<div class="form-group">
					<label for="email" class="change-information-control-label">Email</label>
					<input type="email" id="email" name= "email" class="form-control" placeholder="Email">
				</div>
				
				<div class="form-group">
					<label for="password" class="change-information-control-label">Password</label>
					<input type="password" id="newPassword" name= "newPassword" class="form-control" placeholder="Password">
				</div>
				
				<div class="form-group">
					<label for="password" class="change-information-control-label">Confirm Password</label>
					<input type="password" id="repeatPassword" name= "repeatPassword" class="form-control" placeholder="Confirm Password">	
				</div>
				
				<div>
					<label for="dateOfBirth" class="change-information-control-label">Date of birth</label>
					<input type="date" id="dateOfBirth" name="dateOfBirth" class="form-control"  placeholder="Date of birth">
				</div>
				
				<div class="form-group">
					<label for="phoneNumber" class="change-information-control-label">Phone number </label>
					<input name="phoneNumber" type="tel" id="phoneNumber" class="form-control"  placeholder="Phone number">
				</div>
				
				<div class="form-group">
						<label for="Height" class="change-information-control-label">Height</label>
						<input type="number" id="height" name= "height" class="form-control"  placeholder="Height">
				</div>
				
				 <div class="form-group">
						<label for="weight" class="change-information-control-label">Weight</label>
						<input type="number" id="weight" name= "weight" class="form-control"  placeholder="Weight">
				</div>
				
				<div class="form-group">
					<label for="gender" class="change-information-control-label">Gender</label>
					<select type="text" class="form-control" name = "gender" id = "gender" placeholder="Gender">
					<option>Male</option>
					<option>Female</option>
					</select>
				</div>
				
				<button type="submit" class="update-button">Register</button>
			
			</form>
			
		</div>
	    <script type="text/javascript">
		$(document).ready(function(){
			
			$("#changeForm").validate({
				rules:
				{	
					name: {
						required: false,
						maxlength: 20
					},
					surname: {
						required: false,
						maxlength: 30
					},
					email: {
						required: true,
						email: true,
						maxlength: 50
					},
					password: {
						required: true,
						minlength: 5,
						maxlength: 40
					},
					repeatPassword: {
						required: true,
						minlength: 5,
						maxlength: 40,
						equalTo: "#newPassword"
					},
					dateOfBirth: {
						required: true
					},
					phone: {
						required: false,
						number: true,
						maxlength: 11
					},
					height: {
						required: true,
						min: 1
					},
					weight: {
						required: true,
						min: 1
					}
				},
					messages:{
						
						name: {
						},
				        surname: {
							required: ""
						},
						email: {
							required: ' Please enter email',
							email: ' Please enter valid email'
						},
						password: {
							required: " Please enter password"
						},
						repeatPassword: {
							required: " Please enter password",
						    equalTo: "Please enter the same password again"
						},
						dateOfBirth: {
							required: " Please enter birth date"
						},
						phone: {
							required: ""
						},
						height: {
							required: " Please enter heigh"
						},
						weight: {
							required: " Please enter weight"
						}
					}
					
			});
		});
			
		</script>

	</body>
	
</html>