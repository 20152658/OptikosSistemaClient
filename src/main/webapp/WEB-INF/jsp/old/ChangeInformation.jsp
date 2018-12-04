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
	<style>
	input {
		width: 50%;
	}
	</style>
	</head>
	
	<body>	
		<!--- Side navigation --->
		<div class="sidenav">
		
			<div class="menu-link" >
				<a href="home">Home</a>
			</div>
			
			<div class="active" >
				<a href="changeInformation">Change information</a>
			</div>
			
		    <div class="menu-link" >
				<a href="uploadFile">Upload file</a>
			</div>
			
			<div class="menu-link" >
				<a href="reviewData">Review data</a>
			</div>
			
			<div class="logout" >
				<a href="logout">Log out</a>
			</div>
			
		</div>

		<!--- Page content --->
		<div class="main">
			<p> We're gonna change some stuff over here. If you wanna change, you have to start somewhere, why not here? </p>
			

			<form id="changeForm" class="Update-info-form" role="form" action = "changingInformation" modelAttribute = "client"> 

				<div class="form-group">
					<label for="name" class="change-information-control-label">First name</label>
					<input type="text" id="name" name= "name" value="${client.name}" class="form-control">
				</div>
				
				<div class="form-group">
					<label for="surname" class="change-information-control-label">Last name</label>
					<input type="text" id="surname" name= "surname" value="${client.surname}" class="form-control">
				</div>
												
				<div class="form-group">
					<label for="email" class="change-information-control-label">Email</label>
					<input type="email" id="email" name= "email" value="${client.email}" class="form-control" >
				</div>
				
				<div class="form-group">
					<label for="password" class="change-information-control-label">Password</label>
					<input type="password" id="newPassword" name= "newPassword" placeholder="Password" class="form-control">
				</div>
				
				<div class="form-group">
					<label for="password" class="change-information-control-label">Confirm Password</label><input type="password" id="repeatPassword" name= "repeatPassword" placeholder="Confirm Password" class="form-control">	
				</div>
				
				<div class="form-group">
					<label for="phoneNumber" class="change-information-control-label">Phone number </label>
					<input name="phoneNumber" type="tel" id="phoneNumber" value="${client.phone}" class="form-control">
				</div>
				
				<div class="form-group">
						<label for="Height" class="change-information-control-label">Height</label>
						<input type="number" id="height" name= "height" value="${client.height}" class="form-control">
				</div>
				
				 <div class="form-group">
						<label for="weight" class="change-information-control-label">Weight</label>
						<input type="number" id="weight" name= "weight" value="${client.weight}" class="form-control">
				</div>
				
				<button type="submit" class="update-button" style="width: 10%;
    	padding: 5px;
    	color: white;
    	background-color: #828282;
    	margin-top: 2%;
    	margin-bottom: 2%;
    	border-color: black;
    	border-radius: 3px;">Update</button>
			
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
					newPassword: {
						required: true,
						minlength: 5,
						maxlength: 40
					},
					repeatPassword: {
						required: true,
						minlength: 5,
						maxlength: 40,
						equalTo: "#password"
					},
					phoneNumber: {
						required: true,
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

						email: {
							required: ' Please enter email',
							email: ' Please enter valid email'
						},
						newPassword: {
							required: " Please enter password"
						},
						confirmPassword: {
							required: " Please enter password",
						    equalTo: "Please enter the same password again"
						},
						phoneNumber: {
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