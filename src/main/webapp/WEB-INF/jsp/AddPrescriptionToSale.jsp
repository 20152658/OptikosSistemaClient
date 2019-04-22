<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
	<head>
	<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
	<link href="resources/css/frame.css" rel="stylesheet">
    <link href="resources/css/SellStuff.css" rel="stylesheet">
    <link href='http://fonts.googleapis.com/css?family=Arizonia' rel='stylesheet' type='text/css'>
	<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
	<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
	<script src="http://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
	<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/jquery-validation@1.17.0/dist/jquery.validate.js"></script>
	<style>
	input {
		width: 50%;
	}
	</style>
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
			<div class="main" >
				<p>id ${newSale.id}</p>
				<p>orders ${newSale.orders}</p>
				<p>items ${newSale.items}</p>
				<p>date ${newSale.date}</p>
				<p>sum ${newSale.sum}</p>
				<p> Ar noretumete pasirinkti klienta?</p>
				<p> //kliento dropdown su option blank</p>
				<p> Pasirinkus klienta pasirodo laukai siulantys ivest atsiemimo data. </p>
				<p> Ir kazkas su bendra suma, avansu, likutine verte. </p>
				
				
				<p> Parodo kiek reikia moketi. Leidzia ivest kiek klientas sumokejo, parodo graza. </p>
			</div>
	</div>
		
	<script type="text/javascript" >

		
	</script>
		
</body>
</html>