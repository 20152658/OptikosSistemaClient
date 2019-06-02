<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<link href="resources/css/frame.css" rel="stylesheet">
<link href="resources/css/SellStuff.css" rel="stylesheet">
<link href="resources/css/Client-prescription.css" rel="stylesheet">
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
						<a href="reviewOrders"  class="fas fa-file-invoice" > Pardavimu istorija</a>
					</div>
		
					<div class="menu-link" >
						<a href="logout" class="fas fa-sign-out-alt" > Atsijungti</a>
					</div>
					
				</div>
	
		<!--- Page content --->
			<div class="main" id="addingNEW">
				<div class = "editingClient-Prescription">
					<div class="editClient">
						<form id="editClientForm" name="editClientForm" method="POST" role="form" action="edittingClient" modelAttribute="client">
							<div class="form-group" id="hiddenId">
								<input type="text" name="id" id="id" class="form-control" value="${client.id}" />
							</div>
							<div class="form-group">
								<label for="Vardas*" class="new-information-control-label">Vardas*</label>
								<input type="text" name="name" id="name" class="form-control" value="${client.name}" />
							</div>
							<div class="form-group">
								<label for="Pavardė" class="new-information-control-label">Pavardė</label>
								<input type="text" name="surname" id="surname" class="form-control" value="${client.surname}" />
							</div>
							<div class="form-group">
								<label for="El. paštas" class="new-information-control-label">El. paštas</label>
								<input type="text" name="email" id="email" class="form-control" value="${client.email}" placeholder="el@pastas.lt"/>
							</div>
							<div class="form-group">
								<label for="Telefono numeris" class="new-information-control-label">Telefono numeris</label>
								<input type="text" name="phoneNumber" id="phoneNumber" class="form-control" value="${client.phoneNumber}" placeholder="+370..."/>
							</div>
							<button type="submit" id="addClientButton" class="addClient-button" onclick="return confirm('Ar tikrai norite koreguoti šį klientą?')">Išsaugoti kliento pakeitimus</button>
				         </form>
					</div>
					<div class="editPrescription">
						<form id="addPrescriptionForm" name="addPrescriptionForm" method="POST" role="form" action="addingPrescription" modelAttribute="prescription">
							
							<div class="Akys">
							
								<div class="Desine">
							   		<div class="form-group">
										<label for="rightEyeSphere" class="new-information-control-label">Dešinės akies sfera*</label>
										<input type="number" name= "rightEyeSphere" id="rightEyeSphere" class="form-control" value="${prescription.rightEyeSphere}" placeholder="Dešinės akies sfera*">
									</div>		
									<div class="form-group">
										<label for="rightEyeCylinder" class="new-information-control-label">Dešinės akies cilindras*</label>
										<input type="number" name= "rightEyeCylinder" id="rightEyeCylinder" class="form-control" value="${prescription.rightEyeCylinder}" placeholder="Dešinės akies cilindras*">
									</div>
									<div class="form-group">
										<label for="rightEyeAxis" class="new-information-control-label">Dešinės akies ašis</label>
										<input type="number" name= "rightEyeAxis" id="rightEyeAxis" class="form-control" value="${prescription.rightEyeAxis}" placeholder="Dešinės akies ašis">
									</div>
									<div class="form-group">
										<label for="rightEyePrism" class="new-information-control-label">Dešinės akies prizmė</label>
										<input type="number" name= "rightEyePrism" id="rightEyePrism" class="form-control" value="${prescription.rightEyePrism}" placeholder="Dešinės akies prizmė">
									</div>
								</div>
							
								<div class="Kaire">							
									<div class="form-group">
										<label for="leftEyeSphere" class="new-information-control-label">Kairės akies sfera*</label>
										<input type="number" name= "leftEyeSphere" id="leftEyeSphere" class="form-control" value="${prescription.leftEyeSphere}" placeholder="Kairės akies sfera*">
									</div>
									
									<div class="form-group">
										<label for="leftEyeCylinder" class="new-information-control-label">Kairės akies cilindras*</label>
										<input type="number" name= "leftEyeCylinder" id="leftEyeCylinder" class="form-control" value="${prescription.leftEyeCylinder}" placeholder="Kairės akies cilindras*">
									</div>
									
									<div class="form-group">
										<label for="leftEyeAxis" class="new-information-control-label">Kairės akies ašis</label>
										<input type="number" name= "leftEyeAxis" id="leftEyeAxis" class="form-control" value="${prescription.leftEyeAxis}" placeholder="Kairės akies ašis">
									</div>
									<div class="form-group">
										<label for="leftEyePrism" class="new-information-control-label">Kairės akies prizmė</label>
										<input type="number" name= "leftEyePrism" id="leftEyePrism" class="form-control" value="${prescription.leftEyePrism}" placeholder="Kairės akies prizmė">
									</div>
								</div>
							
							</div>
							
							<div class="form-group">
								<label for="distanceBetweenPupils" class="new-information-control-label">Atstumas tarp vyzdžių*</label>
								<input type="number" name= "distanceBetweenPupils" id="distanceBetweenPupils" value="${prescription.distanceBetweenPupils}"  class="form-control" placeholder="Atstumas tarp vyzdžių*">
							</div>
							
							<div id="hiddenId" class="form-group">
								<input type="number" name= "clientId" id="clientId" value="${prescription.clientId}"  class="form-control" >
							</div>
							
							<div id="hiddenId" class="form-group">
								<input type="number" name= "id" id="id" value="${prescription.id}"  class="form-control" >
							</div>
							
							<div class="form-group">
								<label for="purpose" class="new-information-control-label">Paskirtis</label>
								<input type="text" name= "purpose" id="purpose" class="form-control" value="${prescription.purpose}"  placeholder="Paskirtis">
							</div>
							
							<button  id="submitPrescriptionButton" class="addPrescription-button" onclick="return confirm('Ar tikrai norite pridėti šį receptą?')">Išsaugoti receptą</button>
					   
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" >

	$(document).ready(function (){   
		$("#addPrescriptionForm").validate({
			rules:
			{	
				rightEyeSphere: {
					required: true
				},
				leftEyeSphere: {
					required: true
				},
				rightEyeCylinder: {
					required: true
				},
				leftEyeCylinder: {
					required: true
				},
				distanceBetweenPupils: {
					required: true,
				},
				purpose: {
					maxlength: 25,
				}
			},
			messages:
			{
				rightEyeSphere: {
					required: 'Pasirinkite dešinės akies sferą'
				},
				leftEyeSphere: {
					required: 'Pasirinkite kairės akies sferą'
				},
				rightEyeCylinder: {
					required: 'Pasirinkite dešinės akies cilindrą'
				},
				leftEyeCylinder: {
					required: 'Pasirinkite kairės akies cilindrą'
				},
				distanceBetweenPupils: {
					required: 'Pasirinkite atstumą tarp vyzdžių'
				},
				purpose: {
					maxlength: 'Priežastis negali viršyti 25 simbolių'
				}
			}
				
			});
		
		 $('#addPrescriptionButton').click(function() {
		        $("#addPrescriptionForm").valid();
		    });
		 
		 $("#editClientForm").validate({
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
			        $("#editClientForm").valid();
			});
		
	});

	
	</script>
</body>

</html>