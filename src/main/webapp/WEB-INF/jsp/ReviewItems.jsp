<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<link href="resources/css/frame.css" rel="stylesheet">
<link href="resources/css/review.css" rel="stylesheet">
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
				</div>
				
				<div class="showItemsList" id ="akiniuList">
					<c:forEach items="${akiniai}" var="item"> 
						<div id="products" class="row list-group">
					        <div class="item  col-xs-4 col-lg-4">
					            <div class="thumbnail">
					                <img class="group list-group-image" src="http://placehold.it/400x250/000/fff" alt="" />
					                <div class="caption">
					                    <h4 class="group inner list-group-item-heading">
					                        ${item.title}</h4>
					                    <p class="group inner list-group-item-text">
					                    Gamintojas: ${item.brand} 
				                        Kaina: ${item.price} 
									    Kiekis: ${item.amount} 
										Rezervuotas kiekis: ${item.reserved} </p>
					                    <div class="row">
					                        <div class="col-xs-12 col-md-6">
					                            <a class="btn btn-success" href="http://www.jquery2dotnet.com">Atnaujinti</a>
					                        </div>
					                    </div>
					                </div>
					            </div>
					        </div>
					     </div>
					</c:forEach> 
				</div>
				
				<div class="showItemsList" id ="lesiuList">
					<c:forEach items="${lesiai}" var="item">
						<div id="products" class="row list-group">
					        <div class="item  col-xs-4 col-lg-4">
					            <div class="thumbnail">
					                <img class="group list-group-image" src="http://placehold.it/400x250/000/fff" alt="" />
					                <div class="caption">
					                    <h4 class="group inner list-group-item-heading">
					                        ${item.title}</h4>
					                    <p class="group inner list-group-item-text">
					                    Gamintojas: ${item.brand} 
				                        Kaina: ${item.price} 
									    Kiekis: ${item.amount} 
										Rezervuotas kiekis: ${item.reserved} </p>
					                    <div class="row">
					                        <div class="col-xs-12 col-md-6">
					                            <a class="btn btn-success" href="http://www.jquery2dotnet.com">Atnaujinti</a>
					                        </div>
					                    </div>
					                </div>
					            </div>
					        </div>
					        </div>
					</c:forEach> 
				</div>
				
				<div class="showItemsList" id ="sAkiniuList">
					<c:forEach items="${sAkiniai}" var="item"> 
						<div id="products" class="row list-group">
					        <div class="item  col-xs-4 col-lg-4">
					            <div class="thumbnail">
					                <img class="group list-group-image" src="http://placehold.it/400x250/000/fff" alt="" />
					                <div class="caption">
					                    <h4 class="group inner list-group-item-heading">
					                        ${item.title}</h4>
					                    <p class="group inner list-group-item-text">
					                    Gamintojas: ${item.brand} 
				                        Kaina: ${item.price} 
									    Kiekis: ${item.amount} 
										Rezervuotas kiekis: ${item.reserved} </p>
					                    <div class="row">
					                        <div class="col-xs-12 col-md-6">
					                            <a class="btn btn-success" href="http://www.jquery2dotnet.com">Atnaujinti</a>
					                        </div>
					                    </div>
					                </div>
					            </div>
					        </div>
					        </div>
					</c:forEach> 
				</div>
				
				<div class="showItemsList" id ="kitkaList">
					<c:forEach items="${kitka}" var="item"> 
						<div id="products" class="row list-group">
					        <div class="item  col-xs-4 col-lg-4">
					            <div class="thumbnail">
					                <img class="group list-group-image" src="http://placehold.it/400x250/000/fff" alt="" />
					                <div class="caption">
					                    <h4 class="group inner list-group-item-heading">
					                        ${item.title}</h4>
					                    <p class="group inner list-group-item-text">
					                    Gamintojas: ${item.brand} 
				                        Kaina: ${item.price} 
									    Kiekis: ${item.amount} 
										Rezervuotas kiekis: ${item.reserved} </p>
					                    <div class="row">
					                        <div class="col-xs-12 col-md-6">
					                            <a class="btn btn-success" href="http://www.jquery2dotnet.com">Atnaujinti</a>
					                        </div>
					                    </div>
					                </div>
					            </div>
					        </div>
					        </div>
					</c:forEach> 
				</div>
				
				
			</div>
		</div>
	</div>
				
	<script>
			function whichPrekeTypeIsChosen() {
				
			    var akiniaiP = document.getElementById("akiniuList");
			    var saulesP = document.getElementById("sAkiniuList");
				var lesiaiP = document.getElementById("lesiuList");
				var kitkaP = document.getElementById("kitkaList");
				akiniaiP.style.display = "none";
				saulesP.style.display = "none";
				lesiaiP.style.display = "none";
				kitkaP.style.display = "none";
			    var x = document.getElementById("prekiuTipai").value;
			    switch (x){
				    case "akiniai":
				    	akiniaiP.style.display = "flex";
				    	break;
				    
					case "sAkiniai":
				    	saulesP.style.display = "flex";
				    	break;
				    
					case "lesiai":
				    	lesiaiP.style.display = "flex";
				    	break;
				    
				    case "kitka":
				    	kitkaP.style.display = "flex";
				    	break;
				    	
				    default:
				    	break;
				  
			    }
			}
	</script>
</body>

</html>