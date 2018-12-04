<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<link href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet">
		<link href="resources/css/login-registration.css" rel="stylesheet">
		<script src="//netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>	
		<script src="http://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>
		<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/jquery-validation@1.17.0/dist/jquery.validate.js"></script>
	</head>
	
	
	<body>
		<div class="grandParentContainer">
			<div class="parentContainer">
				<div align="center">
					<form id = "loginForm" method = "POST" class="loginForm" role="form" action = "logingIn" modelAttribute = "client" >
						<h2>Login</h2>
						<div class="form-group">
							<input type="email" id="email" name = "email" placeholder="Email" class="form-control" >
						</div>
						
						<div class="form-group">
							<input type="password" id="password" name = "password" placeholder="Password" class="form-control">
						</div>
						
						<div class="error ">${error}</div>
						<button type="login" class="button -Login">Login</button>
						<div class="col-sm-9">
							<span class="help-block"><a href="register">I don't have an account yet! </a></span>
						</div>
					</form>

					<script type="text/javascript">
					$(document).ready(function(){
						
						$("#loginForm").validate({
							rules:
							{	
								email: {
									required: true,
									email: true
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
				</div>
			</div>
		</div>
	</body>
</html>