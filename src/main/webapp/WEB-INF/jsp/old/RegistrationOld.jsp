<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<link href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
		<link href="resources/css/login-registration.css" rel="stylesheet">
		<script src="//netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
		<script src="http://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>
		<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/jquery-validation@1.17.0/dist/jquery.validate.js"></script>
	</head>
	
	<body>
		<div class="container">
			<form id = "registrationForm" class="form-horizontal" role="form" action="registering" modelAttribute = "client"  onsubmit = "return validation()">
				<h2>Registration</h2>
				<div class="form-group">
					<input type="text" placeholder="First Name" name = "name" id = "name" class="form-control" autofocus>
				</div>
				
				<div class="form-group">
					<input type="text" placeholder="Last Name" name = "surname" id = "surname" class="form-control" autofocus>
				</div>
				
				<div class="form-group">
					<input type="email" id="email" placeholder="Email*" name = "email" id = "email" class="form-control" name= "email">
				</div>
				
				<div class="form-group">
					<input type="password" id="password" name="password" placeholder="Password*" class="form-control">
				</div>
				<div class="form-group">
					<input type="password" placeholder="Confirm Password*" name = "repeatPassword" id = "repeatPassword" class="form-control">	
				</div>
				
				<div class="form-group">
					<input type="date" name = "dateOfBirth" id = "dateOfBirth" class="form-control">
					<div class="col-sm-9">
						<span class="help-block"> Please enter your birth date here *</span>
					</div>
				</div>
				
				<div class="form-group">
					<input type="tel" id="phone" name="phone" placeholder="Phone number" class="form-control">
					<div class="col-sm-9">
						<span class="help-block"> Your phone number won't be disclosed anywhere </span>
					</div>
				</div>
				
				<div class="form-group">
					<input type="number" id="height" name="height" placeholder="Height in centimetres*" class="form-control">
				</div>
				
				 <div class="form-group">
					<input type="number" id="weight" name="weight" placeholder="Weight in kilograms*" class="form-control">
				</div>
			
				
				<div class="form-group">
					<select type="text" class="form-control" name = "gender" id = "gender" placeholder="Gender">
					<option>Male</option>
					<option>Female</option>
					</select>
				</div>
						
				<div class="form-group">
					<div class="col-sm-9 col-sm-offset-3">
						<span class="help-block"> *Required fields</span>
					</div>
				</div>
				<button type="submit" class="button -Register">Register</button>
			</form> <!-- /form -->
		</div> <!-- ./container -->
		<script type="text/javascript">
		$(document).ready(function(){
			
			$("#registrationForm").validate({
				rules:
				{	
					name: {
						required: true,
						maxlength: 20
					},
					surname: {
						required: true,
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
						equalTo: "#password"
					},
					dateOfBirth: {
						required: true
					},
					phone: {
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
						
						name: {
							required: " Please enter password"
						},
				        lsurname: {
							required: " Please enter password"
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