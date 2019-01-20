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
					<form id="editPrekeForm" name="editPrekeForm" role="form" method="POST" action="edittingItem" modelAttribute = "item">
					
						<div class="form-group">
						
							<label for="tipas" class="change-information-control-label">Prekes tipas*</label>
							<select value="${item.type}" id="type" name="type" onchange="whichPrekeTypeIsChosen()" >
								  <option value="akiniai">Akinių remeliai</option>
								  <option value="lesiai">Kontaktiniai lęšiai</option>
								  <option value="sAkiniai">Akiniai nuo saulės</option>
								  <option value="kitka">Kita</option>
							</select>
						</div>
						
						<div class="form-group">
							<label for="kaina" class="new-information-control-label">Kaina</label>
							<input type="text" name= "price" id="price" class="form-control" value="${item.price}" placeholder="Kaina">
							<input type="hidden" name= "id" id="price" class="form-control" value="${item.id}" placeholder="Kaina">
							
						</div>
						
						<div class="form-group">
							<label for="gamintojas" class="new-information-control-label">Gamintojas</label>
							<input type="text" name= "brand" id="brand" class="form-control"value="${item.brand}" placeholder="Gamintojas">
						</div>
						
						<div class="form-group">
							<label for="pavadinimas" class="new-information-control-label">Pavadinimas*</label>
							<input type="text" name= "title" id="title" class="form-control" value="${item.title}" placeholder="Pavadinimas">
						</div>
						
						<div class="form-group">
							<label for="kiekis" class="new-information-control-label">Kiekis*</label>
							<input type="text" name= "amount" id="amount" class="form-control" value="${item.amount}" placeholder="Kiekis">
						</div>
						
						<button type="submit" id="editButton" class="editPreke-button" onclick="return confirm('Ar tikrai norite redaguoti šią prekę?')">Redaguoti</button>
					
					</form>
				</div>
				
			</div>
		</div>
	</div>
	<script type="text/javascript" >
	$(document).ready(function (){   
		$("#addPrekeForm").validate({
			rules:
			{	
				type: {
					required: true
				},
				price: {
					required: true
				},
				brand: {
					required: true,
					maxlength: 30
				},
				title: {
					required: true,
					maxlength: 50
				},
				amount: {
					required: true
				}
				
			},
				messages:{
					type: {
						required: 'Pasirinkite prekės tipą'
					},
					price: {
						required:'Įveskite prekės kainą'
					},
			        brand: {
						required: 'Įveskite prekės gamintoją',
						maxlength: 'Prekės gamintojas negali viršyti 30 simbolių'
					},
					title: {
						required: 'Įveskite prekės pavadinimą',
						maxlength: 'Prekės pavadinimas negali viršyti 50 simbolių'
					},
					amount: {
						required:'Įveskite prekės kiekį'
					}
				}
		        
				
		});
		 $('#editButton').click(function() {
		        $("#editPrekeForm").valid();
		    });
	});
	
	</script>
</body>

</html>