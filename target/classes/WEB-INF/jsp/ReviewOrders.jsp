<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
					
					<div class="active">
						<a href="reviewOrders"  class="fas fa-file-invoice" > Pardavimu istorija</a>
					</div>
		
					<div class="menu-link" >
						<a href="logout" class="fas fa-sign-out-alt" > Atsijungti</a>
					</div>
					
				</div>

		<!--- Page content --->
			<div class="main">
				<div class="datePickingContainer">
					<div class="datePicking" id="calendarDIV">
						<form id="filterDate" name="filterDate" role="form" method="POST" action="reviewOrdersFiltered" modelAttribute="salesFiltered">
							<div class="form-group">
							  <div id="datepicker-group" class="input-group date" data-date-format="yyyy-mm-dd">
							    <input class="form-control" name="dateFrom" id="dateFrom" type="text" value="${salesFiltered.dateFrom}" placeholder="YYYY-MM-DD"  />
							    <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
							  </div>
							</div>
							<div class="form-group">
							  <div id="datepicker-group2" class="input-group date" data-date-format="yyyy-mm-dd">
							    <input class="form-control" name="dateTo" id="dateTo" type="text" value="${salesFiltered.dateTo}" placeholder="YYYY-MM-DD"  />
							    <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
							  </div>
							</div>
							<div class="form-group">
								<input type="checkbox" value="${salesFiltered.showSales}" name="showSales" id="showSales" onchange="changeSalesValue()"/> Rodyti pardavimus 
							</div>
							<div class="form-group">
								<input type="checkbox" value="${salesFiltered.showOrders}" name="showOrders" id="showOrders" onchange="changeShowOrdersValue()"/> Rodyti užsakymus
							</div>
							<div class="form-group">
								<input type="checkbox" value="${salesFiltered.showOrdersInProgress}" name="showOrdersInProgress" id="showOrdersInProgress" onchange= "changeProgressValue()"/> Rodyti neužbaigtus užsakymus
							</div>
							<div class="form-group">
								<input type="checkbox" value="${salesFiltered.showCompletedOrders}" name="showCompletedOrders" id="showCompletedOrders" onchange="changeComplValue()"/> Rodyti įvykdytus užsakymus
							</div>
							 <a id="submitButton" href="reviewOrdersFiltered">
					    		<button  class="btn"> Filtruoti </button>
							</a>
					    </form>
					</div>
				
					<div class="kontainer">
					    <div class="row col-md-6 col-md-offset-2 custyle">
						    <table class="table table-striped custab">
							    <thead>
							        <tr>
							            <th>Pardavimo nr</th>
							            <th>Data ir laikas</th>
							            <th>Pardavimo suma</th>
							            <th class="text-center">Peržiūra</th>
							        </tr>
							    </thead>
							    
					            <c:forEach items="${sales}" var="sale"> 
								<fmt:parseDate value="${sale.date}" var="dateObject" pattern="yyyy-MM-dd HH:mm:ss" />
					             <tr>
					            	<td> ${sale.id} </td>
					            	<td> <fmt:formatDate value="${dateObject }" pattern="yyyy-MM-dd HH:mm" /> </td>
					            	<td class="sumTd"> <fmt:formatNumber value="${sale.sum}" type="currency" currencySymbol=""/> </td>
					            	<td  class="text-center"><a href="reviewOrder?saleId=${sale.id}" class="btn btn-info btn-xs"><span class="glyphicon glyphicon-edit"></span> Peržiūrėti</a></td>
					            </tr>
					            </c:forEach>
						    </table>
						    <a href="downloadPDF">
						    	<button class="btn"> PDF </button>
							</a>
					    </div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	
	<script type="text/javascript">
	
	$(document).ready(function() {
		
		 $("#datepicker-group").datepicker({
			    format: "yyyy-mm-dd",
			    todayHighlight: true,
			    autoclose: true,
			    clearBtn: true,
			    weekStart: 1
			  });
			  
		  $("#datepicker-group2").datepicker({
			    format: "yyyy-mm-dd",
			    todayHighlight: true,
			    autoclose: true,
			    clearBtn: true,
			    weekStart: 1
			  });
			prepareStuff();	  
		});
	function prepareStuff(){
	      
		  var showOrd = stringToBoolean("${salesFiltered.showOrders}");
		  var showSal = stringToBoolean("${salesFiltered.showSales}");
		  var inpr = stringToBoolean("${salesFiltered.showOrdersInProgress}");
		  var compl = stringToBoolean("${salesFiltered.showCompletedOrders}");
		  if(inpr){
			  document.getElementById("showOrdersInProgress").checked = true;
		  }
		  if(compl){
			  document.getElementById("showCompletedOrders").checked = true;
		  }
		  if(showOrd){
			  document.getElementById("showOrders").checked = true;
		  }
		  if(showSal){
			  document.getElementById("showSales").checked = true;
		  }
	 }
	 
	 function stringToBoolean(string){
	    switch(string.toLowerCase().trim()){
	        case "true": case "yes": case "1": return true;
	        case "false": case "no": case "0": case null: return false;
	        default: return Boolean(string);
	    }
	}

	function changeShowOrdersValue(){
		var orderval = stringToBoolean(document.getElementById("showOrders").value);
		var orders =  document.getElementById("showOrders");
		
		if(orderval){
			orders.value = false;
		}else{
			orders.value=true;
			document.getElementById("showOrdersInProgress").value = true;
			document.getElementById("showCompletedOrders").value = true;
		}
	}
	
	function changeComplValue(){
		var inprogress = document.getElementById("showCompletedOrders");
		if(inprogress.value){
			inprogress.value = false;
		}else{
			inprogress.value=true;
		}
	}
	function changeProgressValue(){
		var inprogress = document.getElementById("showOrdersInProgress");
		if(inprogress.value){
			inprogress.value = false;
		}else{
			inprogress.value=true;
		}
	}
	function changeSalesValue(){
		var inprogress = document.getElementById("showSales");
		if(inprogress.value){
			inprogress.value = false;
		}else{
			inprogress.value=true;
		}
	}
	
	</script>

</body>

</html>