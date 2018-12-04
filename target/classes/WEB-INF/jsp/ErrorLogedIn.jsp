<!DOCTYPE html>
<html>
	<head>
	<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <link href="resources/css/side-navigation.css" rel="stylesheet">
	<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
	<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
	</head>
	
	<body>	
		<!--- Side navigation --->
		<div class="sidenav">
		
			<div class="active" >
				<a class="activee" href="home">Home</a>
			</div>
			
			<div class="menu-link" >
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
		<div class="main" id="error">
			<div class="error" >
				<img src="https://i2.wp.com/moderndayms.com/wp-content/uploads/2015/05/oops.jpg?w=400" alt="Error-oops">
				<p> Looks like something went wrong :( </p>
				<p> Here some boring explanation on what happened: </p>
				<p id="error-exp"></p> 
			</div>
		</div>

	</body>
	
</html>