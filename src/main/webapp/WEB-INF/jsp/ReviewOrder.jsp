
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
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
				<div class="main">
					<fmt:parseDate value="${sale.date}" var="dateObject" pattern="yyyy-MM-dd HH:mm:ss" />
					<p>Pardavimo data: <fmt:formatDate value="${dateObject }" pattern="yyyy-MM-dd" /></p>	
					<p>Pardavimo laikas: <fmt:formatDate value="${dateObject }" pattern="HH:mm " /></p>		
					<p>Užsakymai: ${sale.orders}</p> 	
					
					<table class="table table-striped custab" id = "soldItems">
					    <thead>
					        <tr>
					            <th>Pavadinimas</th>
					            <th>Gamintojas</th>
					            <th>Kaina</th>
					        </tr>
					    </thead>
				
						<c:forEach items="${items}" var="item"> 
				             <tr>
				            	<td> ${item.title} </td>
				            	<td> ${item.brand} </td>
				            	<td class="sumTd"> <fmt:formatNumber value="${item.price}" type="currency" currencySymbol=""/> </td>
				            </tr>
			            </c:forEach>
			             <tr>
			            	<td class="font-weight-bold" >Iš viso: </td>
			            	<td>  </td>
			            	<td class="sumTd"> <fmt:formatNumber value="${sale.sum}" type="currency" currencySymbol=""/> </td>
				         </tr>
		            </table>
		            <div id="Orders">
		            	<h2> Užsakymo informacija</h2>
		            	<form id="sellOrder" name="OrderForm" role="form" method="POST" action="changingOrderStatus" modelAttribute="order">
		            		<input type="hidden" type="number" id="id" name="id" value="${order.id}"/>
		            		<input type="hidden" type="number" id="clientId" name="clientId" value="${order.clientId}"/>
		            		<input type="hidden" type="number" id="deposit" name="deposit" value="${order.deposit}"/>
		            		<input type="hidden" type="text" id="lensType" name="lensType" value="${order.lensType}"/>
		            		<input type="hidden" type="text" id="estimatedDate" name="estimatedDate" value="${order.estimatedDate}"/>
			            	<p> Klientas: ${client.name} ${client.surname}</p>
			            	<p> Planuojama pagaminimo data: ${order.estimatedDate}</p>
			            	<p> Pagaminta?  
			            	<label class="switch">
							  <input type="checkbox" value="${order.inProgress}" name="inProgress" id="inProgress" onchange="changeProgressValue()"/>
							  <span class="slider"></span>
							</label></p>
							<div id="demo2"><p></p></div>
			            	<p> Atsiimta? <label class="switch">
							  <input type="checkbox" value="${order.completed}" name="completed" id="completed" onchange="changeComplValue()"/>
							  <span class="slider"></span>
							</label></p>
							<button class="btn" onclick="return confirm('Ar tikrai norite išsaugoti pakeitimus?')" >Išsaugoti pakeitimus</button>
						</form>
		            </div>
				</div>
			</div>
		</div>	
		<script type="text/javascript">
			function changeProgressValue(){
				var inprogress = document.getElementById("inProgress");
				if(inprogress.value){
					inprogress.value = true;
				}else{
					inprogress.value=false;
				}
				
			}
			function changeComplValue(){
				var compl = document.getElementById("completed");
				if(compl.value){
					compl.value = true;
				}else{
					compl.value=false;
				}
				
			}
		
		</script>
	</body>
</html>