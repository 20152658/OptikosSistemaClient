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
					
					<div class="menu-link">
						<a href="addNewItem" class="fas fa-glasses"> Add Preke</a>
					</div>
			
					<div class="active">
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
			<div class="main">
				<div class="pickPrekeType">
				
				
			
					<p>Kokias prekes norite peržiūrėti?</p>
					
					<select id="prekiuTipai" name="prekiuTipai" onchange="whichPrekeTypeIsChosen()" >
						  <option disabled selected value> -- Prekės Tipas -- </option>
						  <option value="akiniai">Akinių remeliai</option>
						  <option value="lesiai">Kontaktiniai lęšiai</option>
						  <option value="sAkinai">Akiniai nuo saulės</option>
						  <option value="kitka">Kita</option>
					</select>
				
					<p id="demo"></p>
				</div>
				
			</div>
		</div>
	</div>
	
	<c:forEach items="${lesiai}" var="preke"> 
								<p>${preke.id}</p>    
   								<c:out value="${preke.pavadinimas}"/>
   								<c:out value="${preke.prekesKiekis}"/>
							</c:forEach>
							
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