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
			
			<div class="active" >
				<a href="login">Login</a>
			</div>
			
		    <div class="menu-link" >
				<a href="register">Registration</a>
			</div>
			
		</div>

		<!--- Page content --->
		<div class="main">
			<p> We're gonna change some stuff over here. If you wanna change, you have to start somewhere, why not here? </p>
			

			<form id="changeForm" method="POST" class="Update-info-form" role="form" action = "logingIn" modelAttribute = "client"> 
				
				<div class="form-group">
					<label for="email" class="change-information-control-label">Email</label>
					<input type="email" id="email" name= "email" Placeholder="Email" class="form-control" >
				</div>
				
				<div class="form-group">
					<label for="password" class="change-information-control-label">Password</label>
					<input type="password" id="password" name= "password" placeholder="Password" class="form-control">
				</div>
				
				<button type="login" class="update-button">Log in</button>
			
			</form>
			
		</div>
	    <script type="text/javascript">
		$(document).ready(function(){
			
			$("#changeForm").validate({
				rules:
				{	
					email: {
						required: true,
						email: true,
						maxlength: 50
					},
					password: {
						required: true,
						minlength: 5,
						maxlength: 40
					}
				},
					messages:{

						email: {
							required: ' Please enter email',
							email: ' Please enter valid email'
						},
						password: {
							required: " Please enter password"
						}
					
					}
					
			});
		});
			
		</script>

	</body>
	
</html>