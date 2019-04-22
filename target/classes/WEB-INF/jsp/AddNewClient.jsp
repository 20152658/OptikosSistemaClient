<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<link href="resources/css/frame.css" rel="stylesheet">
<link href="resources/css/SellStuff.css" rel="stylesheet">
<link href='http://fonts.googleapis.com/css?family=Arizonia' rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
<script src="https://cdn.plot.ly/plotly-latest.min.js" type="text/javascript"></script>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js" type="text/javascript"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js" type="text/javascript"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.13.1/jquery.validate.min.js"></script>
</head>

<body>
	<div id="wrapper">	
		<div id="headline">
			<h1>Akiu Optika</h1>
		</div>
				
		<div id="container">
		<!--- Side navigation --->
				<div class="sidenav">
				
					<div class="menu-link" >
						<a href="home" class="fas fa-home" > Pagrindinis puslapis</a>
					</div>
					
					<div class="menu-link">
						<a href="clients" class="fas fa-users"> Klientai</a>
					</div>
					
					<div class="menu-link">
						<a href="newOrder" class="fas fa-plus"> Naujas Pardavimas</a>
					</div>
					
					<div class="menu-link">
						<a href="addNewItem" class="fas fa-glasses"> Nauja Preke</a>
					</div>
			
					<div class="menu-link">
						<a href="reviewItems" class="fas fa-file-alt"> Inventorius </a>
					</div>
					
					<div class="menu-link">
						<a href="reviewOrders"  class="fas fa-file-invoice" > Pardavimu istorija</a>
					</div>
		
					<div class="menu-link" >
						<a href="logout" class="fas fa-sign-out-alt" > Atsijungti</a>
					</div>
					
				</div>
	
		<!--- Page content --->
			<div class="main" id="addingNEW">
				<div class="newItems">
					
					<form id="addClientForm" name="addClientForm" method="POST" role="form" action="addingNewClient" modelAttribute="client">
						<div class="form-group">
							<label for="Vardas*" class="new-information-control-label">Pavadinimas*</label>
							<input type="text" name="name" id="name" class="form-control" />
						</div>
						<div class="form-group">
							<label for="Pavardė" class="new-information-control-label">Pavardė</label>
							<input type="text" name="surname" id="surname" class="form-control" />
						</div>
						<div class="form-group">
							<label for="El. paštas" class="new-information-control-label">El. paštas</label>
							<input type="text" name="email" id="email" class="form-control" placeholder="el@pastas.lt"/>
						</div>
						<div class="form-group">
							<label for="Telefono numeris" class="new-information-control-label">Telefono_numeris</label>
							<input type="text" name="phoneNumber" id="phoneNumber" class="form-control" placeholder="+370..."/>
						</div>
						<button type="submit" id="addClientButton" class="addClient-button" onclick="return confirm('Ar tikrai norite pridėti šį klientą?')">Pridėti</button>
			
					</form>
				</div>
				
			</div>
		</div>
	</div>
	<script type="text/javascript" >
	$(document).ready(function (){   
		$("#addClientForm").validate({
			rules:
			{	
				name: {
					required: true,
					maxlength: 20
				}
			},
			messages:
			{
				name: {
					required: 'Nurodykite kliento vardą',
					maxlength: 'Vardas negali viršyti 20 simbolių'
				}
			}
		});
		 $('#addClientButton').click(function() {
		        $("#addClientForm").valid();
		});
	});
	
	
	</script>
</body>

</html>