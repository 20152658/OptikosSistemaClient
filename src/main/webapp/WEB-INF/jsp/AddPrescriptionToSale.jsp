<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
	<head>
		<link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" />
		<link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker.css" rel="stylesheet" /> 
		<link href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css" rel="stylesheet" >
		<link href="//cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/e8bddc60e73c1ec2475f827be36e1957af72e2ea/build/css/bootstrap-datetimepicker.css" rel="stylesheet">
		<link href="resources/css/frame.css" rel="stylesheet">
	    <link href="resources/css/SellStuff.css" rel="stylesheet">
	    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
 
		<link href='http://fonts.googleapis.com/css?family=Arizonia' rel='stylesheet' type='text/css'>
	     
		<script src="http://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>
		<script type="text/javascript" src="//code.jquery.com/jquery-2.1.1.min.js"></script>
		<script type="text/javascript" src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
   	
		<!-- date picker stuff -->	   	

		<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/js/bootstrap-datepicker.js"></script>	   	

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
				
				<form id="sellOrder" name="OrderForm" role="form" method="POST" action="sellingOrder" modelAttribute="order">
					<div id="clientsWindow">
						<select id="clientId" name="clientId" onchange="IsClientChosen()" >
						<p> Ar norėtumetė pasirinkti klientą?</p>
							  <option value="null"> -- Pasirinkite klientą -- </option>
							  <c:forEach items="${clients}" var="client" >
							  	<option value="${client.id}">${client.surname} ${client.name} </option>
							  </c:forEach>
						</select>
					</div>
					
					<div class="itIsPrescription" id="calendarDIV">
					<p> Kada klientas galės atsiimti užsakymą? </p>
						<div class="form-group">
						  <div id="datepicker-group" class="input-group date" data-date-format="mm-dd-yyyy">
						    <input class="form-control" name="estimatedDate" id="estimatedDate" type="text" placeholder="YYYY/MM/DD"  />
						    <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
						  </div>
						</div>
					</div>
						
					<div class="payment" id="payment">	
						<div class="paymentTotal" id = "paymentTotal">
							<h5></h5>
						</div>
						
						<div class="avansasStuff" id="avansasStuff">
							<input type="number" name= "deposit" id="deposit" class="form-control" value="${order.deposit}" placeholder="Avansas" onchange="onavansaschange()">
							<div id = "priceLeftToPay">
								<h5> Bus likę sumokėti: <fmt:formatNumber value="${newSale.sum}" type="currency" currencySymbol=""/> € </h5>
							</div>
						</div>
						

					</div>
						<button type="submit" id="doneButton" class="fas fa-check" onclick="return confirm('Ar tikrai norite parduoti šias prekes?')"> </button> 
				</form>
				<form  id="sellSale" name="OrderForm" role="form" method="POST" action="sellingSale" modelAttribute="sale">
					<div class="payment" id="payment">	
						<div class="paymentTotal" id = "paymentTotal">
							<h5></h5>
						</div>
						
						<div class="salePayment" id="salePayment" >
							<input type="number" name= "clientMoney" id="clientMoney" placeholder="Duota pinigų suma" onchange="onclientmoneychange()">
							<div id = "changeForClient">
								<h5> </h5>
							</div>
						</div>
	
					</div>
					<button type="submit" id="doneButton" class="fas fa-check" onclick="return confirm('Ar tikrai norite parduoti šias prekes?')"> </button> 				
				</form>
			</div>
		</div>
    </div>
	<script type="text/javascript">
	var totalPrice = parseFloat("${newSale.sum}");
	var deposit = 0;
	var priceLeftToPay = totalPrice;
	
	
	$(document).ready(function() {
		prepareStuff();
          
		  $("#datepicker-group").datepicker({
		    format: "yyyy-mm-dd",
		    todayHighlight: true,
		    autoclose: true,
		    clearBtn: true,
		    weekStart: 1
		  });
		  
		});
	
	 function IsClientChosen(){ //rodyt kalendoriu kai pasirenki klienta
		 var x = document.getElementById("clientId").value;
		 var cald = document.getElementById("calendarDIV");
		 if (x === "null") {
			 cald.style.display = "none";
		 }else{
			 cald.style.display = "flex";
		 }
		 
	 }
	 
	 function onavansaschange(){ //rodyt kokia bus orderio likutine verte
		  var leftToPayWindow = document.getElementById("priceLeftToPay");
            deposit = parseFloat(document.getElementById("deposit").value);
            if(deposit < 0 ){
            	leftToPayWindow.textContent = "Avansas mažesnis už 0!";
            }else if (deposit){ 
	            priceLeftToPay = parseFloat(totalPrice) - deposit;
	            if(priceLeftToPay < 0 ){
	            	leftToPayWindow.textContent = "Avansas negali būti didesnis negu mokėtina suma!";
	            }else {
	            	leftToPayWindow.textContent = "Bus likę sumokėti: "+ priceLeftToPay.toFixed(2) + " €";
	            }
            }else{
            	leftToPayWindow.textContent = "Bus likę sumokėti: "+ totalPrice.toFixed(2) + " €";
            }
     }
	 
	 function prepareStuff(){
		 document.getElementById("paymentTotal").textContent =  "Mokėti iš viso:" + totalPrice.toFixed(2)+" €";
	      
		  var order;
		  order= "${newSale.orders}";
		  if (order=="null"){
			  var orderW = document.getElementById("sellOrder");
			  orderW.style.display = "none";
		  }else{
			  var saleW = document.getElementById("sellSale");
			  saleW.style.display = "none";
		  }
	 }
	 
	 function onclientmoneychange(){ //rodyt graza
		  var changeForClient = document.getElementById("changeForClient");
          var change = parseFloat(document.getElementById("clientMoney").value);
          if(change < 0 ){
          	changeForClient.textContent = "Negalimos neigiamos reikšmės";
          }else if (change){ 
	            change = change - parseFloat(totalPrice) ;
	            if(change < 0 ){
	            	changeForClient.textContent = "Duota pinigų suma nepakankama";
	            }else {
	            	changeForClient.textContent = "Grąža: "+ change.toFixed(2) + " €";
	            }
          }else{
          	changeForClient.textContent = "Įveskite duotą pinigų sumą";
          }
	 }

	</script>
		
</body>
</html>