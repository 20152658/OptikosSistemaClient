<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
					<a href="home" class="fas fa-home" > Home</a>
				</div>
				
				<div class="active">
					<a href="newOrder" class="fas fa-plus"> New Order</a>
				</div>
				
				<div class="menu-link">
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
			<div class="main" id=sellMain>
				<div class="show">
					
					<div class="showWindow">
					
					
						<p> Some item </p>
						<p> one moore </p>
						<p> Some item </p>
						<p> one moore </p>
						<p> Some item </p>
						<p> one moore </p>
						<p> Some item </p>
						<p> one moore </p>
						<p> Some item </p>
						<p> one moore </p>
						<p> Some item </p>
						<p> one moore </p>
						<p> Some item </p>
						<p> one moore </p>
						<p> Some item </p>
						<p> one moore </p>
						<p> Some item </p>
						<p> one moore </p>
						<p> Some item </p>
						<p> one moore </p>
						<p> Some item </p>
						<p> one moore </p>
						<p> Some item </p>
						<p> one moore </p>
						<p> Some item </p>
						<p> one moore </p>
						<p> Some item </p>
						<p> one moore </p>
						<p> Some item </p>
						<p> one moore </p>
						<p> Some item </p>
						<p> one moore </p>
						<p> Some item </p>
						<p> one moore </p>
						<p> Some item </p>
						<p> one moore </p>
						<p> Some item </p>
						<p> one moore </p>
						<p> Some item </p>
						<p> one moore </p>
						<p> Some item </p>
						<p> one moore </p>
					</div>
						
					<div class="totalWindow">
							<p> Iš viso:  </p>
					</div>
					
				</div>
				
				<div class="pick">
					<div class="pickerHead">
							<button onclick="goBack()" id="atgalButton" class="fas fa-angle-left"> Atgal</button>
					</div>
					<div class="myMenuElementPickers">
						<div class="littlePicker" id="mainPicker"> 
							<button onclick="showNext(this)" id="akiniai" class="button -akiniai">Akiniai</button> 
							<button onclick="showNext(this)" id="lesiai" class="button -lesiai">Lęšiai</button> 
							<button onclick="showNext(this)" id="saulesAkiniai" class="button -saules akiniai">Saulės Akiniai</button> 
							<button onclick="showNext(this)" id="kitka" class="button -kitka">Kitos prekės</button> 
						</div>
						<div class="littlePicker" id="akiniaiPicker"> 
							<button onclick="showNext(this)" id="akiniaiSu" class="button -akiniai -Su">Su Dioptrijomis</button> 
							<button onclick="showNext(this)" id="akiniaiBe" class="button -akiniai -Be">Be Dioptrijų</button> 
						</div>
						<div class="littlePicker" id="saulesPicker"> 
							<button onclick="showNext(this)" id="saulesSu" class="button -saules -Su">Su Dioptrijomis</button> 
							<button onclick="showNext(this)" id="saulesBe" class="button -saules -Be">Be Dioptrijų</button> 
						</div>
						<div class="littlePicker" id="lesiaiPicker"> 
							<p> hello </p>
							<c:forEach items="${lesiai}" var="preke"> 
								<p>${preke.id}</p>    
   								<c:out value="${preke.pavadinimas}"/>
   								<c:out value="${preke.prekesKiekis}"/>
							</c:forEach>
							
						</div>
						<div class="littlePicker" id="kitkaPicker"> 
							<p> hello </p>
						</div>
					</div>
				</form>
				
			</div>
		</div>
	</div>
		
		<script type="text/javascript" >
			
			function showNext(caller) {
					
				var mainP = document.getElementById("mainPicker");
			    var akiniaiP = document.getElementById("akiniaiPicker");
			    var saulesP = document.getElementById("saulesPicker");
				var lesiaiP = document.getElementById("lesiaiPicker");
				var kitkaP = document.getElementById("kitkaPicker");
				
				switch (caller){
					case document.getElementById("akiniai"):
						mainP.style.display = "none";
						akiniaiP.style.display = "flex";
						position=akiniaiP;
						break;
						
					case document.getElementById("lesiai"):
						mainP.style.display = "none";
						lesiaiP.style.display = "flex";
						position=lesiaiP;
						break;
						
					case document.getElementById("saulesAkiniai"):
						mainP.style.display = "none";
						saulesP.style.display = "flex";
						position=saulesP;
						break;
						
					case document.getElementById("kitka"):
						mainP.style.display = "none";
						kitkaP.style.display = "flex";
						position=kitkaP;
						break;
						
					default:
					break;
				}
					
			}
			var position = document.getElementById("mainPicker");
			
			function goBack(){
				var mainP = document.getElementById("mainPicker");
				position.style.display="none";
				mainP.style.display="flex";
				
			}
		</script>
		
	</body>
	
	
</html>