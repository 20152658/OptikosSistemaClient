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
					
					<div class="active">
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
			<div class="main" id=sellMain>
				<div class="show">
					<div class="showWindow" id="showWindow">
					
					</div>
						
					<div class="totalWindow" id= "totalWindow">
						<p> Iš viso:  </p>
						
					</div>					
				</div>
				
				<div class="pick">
					<div class="pickerHead">
						<form id="sellPrekeForm" name="sellPrekeForm" role="form" method="POST" action="sellingItem" modelAttribute="sale">
							<input type="hidden" type="text" id="items" name="items"/>
							<input type="hidden" type="number" id="sum" name="sum"/>
							<input type="hidden" type="text" id="orders" name="orders"/>
							<button type="submit" id="doneButton" class="fas fa-check"> </button> 
						</form>	
					</div>
					<div class="col-lg-12 searchHead">
			            <input type="search" class="form-control" id="input-search" placeholder="Prekių paieška..." >
			        </div>
			        <div class="myItems">
						<div class="searchable-container">
							<div class="items col-xs-12 col-sm-12 col-md-6 col-lg-6">
				               <div class="info-block block-info clearfix">
				               		<h4>Pridėti lęšius</h4>
				               		<h5>Įveskite lęšio kainą:</h5>
				                    <input type="number" id="lesioKaina"  class= "form-control" placeholder="Lęšio kaina"/>
									<button class="fas fa-plus" id="addButton" onclick="addLesis()"></button>
				                </div>
				            </div>
							<c:forEach items="${item}" var="item"> 
					            <div class="items col-xs-12 col-sm-6 col-md-6 col-lg-6">
					               <div class="info-block block-info clearfix">
					                    <h5>${item.type}</h5>
					                    <h4>${item.title}</h4>
					                    <p>Kaina: <fmt:formatNumber value="${item.price}" type="currency" currencySymbol=""/> € </p>
					                    <h5 class="inStart">Kiekis: ${item.amount}</h5>
					                    <button class="fas fa-plus inEnd" id="addButton"  onclick="addItem(${item.id}, '${item.title}' ,${item.price})"></button>
					                    
					                </div>
					            </div>
				          	</c:forEach>
				        </div>
			        </div>
					
					<div class="myMenuElementPickers">
					    <div id="dioptrijuPicker">
							
					    </div>
					</div>
				</div>
			</div>
		</div>
	</div>
		
	<script type="text/javascript" >

	
	 $(function() {    
	        $('#input-search').on('keyup', function() {
	          var rex = new RegExp($(this).val(), 'i');
	            $('.searchable-container .items').hide();
	            $('.searchable-container .items').filter(function() {
	                return rex.test($(this).text());
	            }).show();
	        });
	    });
	
		var itemList="";
		var price =0;
		var totalWindow = document.getElementById("totalWindow");
		var showWindow = document.getElementById("showWindow");
		var position = document.getElementById("mainPicker");
		
		function addItem(id,title, pprice){
			var node = document.createElement("p");
			node.textContent= title+ " "+ pprice +" €";
			showWindow.appendChild(node);
			itemList=itemList + parseInt(id) + ",";
			document.getElementById("items").value=itemList;
			price=parseFloat(price)+parseFloat(pprice);
			totalWindow.textContent="Iš viso: "+price.toFixed(2)+" €";
			document.getElementById("sum").value=price;
		}
		
		function addLesis(){
			var node = document.createElement("p");
			pprice = parseFloat(document.getElementById("lesioKaina").value);
			node.textContent= "Lęšiai akiniams" + " " + pprice.toFixed(2)+" €";
			showWindow.appendChild(node);
			price=parseFloat(price)+parseFloat(pprice);
			totalWindow.textContent="Iš viso: "+price.toFixed(2);
			document.getElementById("sum").value=price;
			document.getElementById("orders").value = "order";
		}
	</script>
		
</body>
</html>