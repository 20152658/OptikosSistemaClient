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
						<a href="home" class="fas fa-home" > Home</a>
					</div>
					
					<div class="menu-link">
						<a href="newOrder" class="fas fa-plus"> New Order</a>
					</div>
					
					<div class="active">
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
			<div class="main" id="addingNEW">
				<div class="newItems">
					<form id="addPrekeForm" method="POST" action="addingNewItem" modelAttribute = "preke">
					
						<div class="form-group">
						
							<label for="tipas" class="change-information-control-label">Prekes tipas*</label>
							<select id="prekiuTipai" name="prekesTipas" onchange="whichPrekeTypeIsChosen()" >
								  <option disabled selected value> -- Prekės Tipas -- </option>
								  <option value="akiniai">Akinių remeliai</option>
								  <option value="lesiai">Kontaktiniai lęšiai</option>
								  <option value="sAkiniai">Akiniai nuo saulės</option>
								  <option value="kitka">Kita</option>
							</select>
						</div>
						
						<div class="form-group">
							<label for="kaina" class="new-information-control-label">Kaina</label>
							<input type="text" name= "prekesKaina" class="form-control" placeholder="Kaina">
						</div>
						
						<div class="form-group">
							<label for="Gamintojas" class="new-information-control-label">Gamintojas</label>
							<input type="text" name= "gamintojas" class="form-control" placeholder="Gamintojas">
						</div>
						
						<div class="form-group">
							<label for="Pavadinimas" class="new-information-control-label">Pavadinimas*</label>
							<input type="text" name= "pavadinimas" class="form-control" placeholder="Pavadinimas">
						</div>
						
						<div class="form-group">
							<label for="Kaina" class="new-information-control-label">Kiekis*</label>
							<input type="text" name= "prekesKiekis" class="form-control" placeholder="Kiekis">
						</div>
						
						<button type="submit" class="addPreke-button">Pridėti</button>
					
					</form>
				</div>
				
			</div>
		</div>
	</div>
	<script>
			function whichPrekeTypeIsChosen() {
			    var x = document.getElementById("prekiuTipai").value;
			    if (x==="kitka"){
			    	document.getElementById("demo").innerHTML = "Paragraph changed!";
			    }else{
			    document.getElementById("demo").innerHTML = x;
			    }
			    
			}
	</script>
</body>

</html>