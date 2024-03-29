<!DOCTYPE html>
<html>
	<head>
	<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
	<link href="resources/css/frame.css" rel="stylesheet">
    <link href="resources/css/SellStuff.css" rel="stylesheet">
    <link href='http://fonts.googleapis.com/css?family=Arizonia' rel='stylesheet' type='text/css'>
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
	<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
	<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
	</head>
	
	<body>	
		<div id="wrapper">	
		
			<div id="headline">
				<h1>Akiu Optika</h1>
			</div>
			
			<div id="container">
				<!--- Side navigation --->
				<div class="sidenav">
				
					<div class="active" >
						<a href="home" class="fas fa-home" > Pagrindinis puslapis</a>
					</div>
					
					<div class="menu-link">
						<a href="clients" class="fas fa-users"> Klientai</a>
					</div>
					
					<div class="menu-link">
						<a href="newOrder" class="fas fa-plus"> Naujas Pardavimas</a>
					</div>
					
					<div class="menu-link">
						<a href="reviewOrders"  class="fas fa-file-invoice" > Pardavimu istorija</a>
					</div>
		
					<div class="menu-link" >
						<a href="logout" class="fas fa-sign-out-alt" > Atsijungti</a>
					</div>
					
				</div>
	
			<!--- Page content --->
				<div class="main">
					<p> Sveiki ${employee.name}, </p>
					<p> Esate prisjunges kaip ${employee.type}. </p>
				</div>
			</div>
		</div>
	</body>
	
</html>