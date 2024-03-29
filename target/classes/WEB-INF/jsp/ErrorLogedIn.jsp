<!DOCTYPE html>
<html>
	<head>
	<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
	<link href="resources/css/frame.css" rel="stylesheet">
    <link href="resources/css/side-navigation.css" rel="stylesheet">
	<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
	<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
	</head>
	
	<body>	
		<!--- Side navigation --->
		<div class="sidenav">
		
			<div class="menu-link" >
				<a href="home" class="fas fa-home" > Home</a>
			</div>
			
			<div class="menu-link">
				<a href="newOrder" class="fas fa-plus"> New Order</a>
			</div>
			
			<div class="menu-link">
				<a href="addNewItem" class="fas fa-glasses"> Add Preke</a>
			</div>
	
			<div class="menu-link">
				<a href="reviewItems" class="fas fa-file-alt"> Review Items</a>
			</div>
			
			<div class="menu-link">
				<a href="reviewOrders"  class="fas fa-file-invoice" > Review Orders</a>
			</div>

			<div class="menu-link" >
				<a href="logout" class="fas fa-sign-out-alt" >Log out</a>
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