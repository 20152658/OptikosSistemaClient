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
			<div class="parentContainer">
					<form id="loginForm" name="loginForm" method="POST" class="loginForm" role="form" action="logingIn" modelAttribute = "employee" >
						<div class="form-group">
							<input type="text" id="nickname" name="nickname" placeholder="Prisijungimo vardas" class="form-control" >
						</div>
						
						<div class="form-group">
							<input type="password" id="password" name="password" placeholder="Slaptažodis" class="form-control">
						</div>
						
						<button type="submit" id="doneButton"> Prisijungti  </button> 
						
					</form>

					<script type="text/javascript">
				
					
					$(document).ready(function(){
						
						$("#loginForm").validate({
							rules:
							{	
								email: {
									required: true
								},
								password: {
									required: true,
									minlength: 6,
									maxlength: 40
								}
							},
								messages:{
									
									email: {
										required: 'Įveskite prisijungimo vardą',
									},
									password: {
										required: " Įveskite slaptažodį"
									}
								}

								
						});
					});
					</script>	

			</div>
	
	</body>
</html>