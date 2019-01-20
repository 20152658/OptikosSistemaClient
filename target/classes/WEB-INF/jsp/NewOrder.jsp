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
						<a href="home" class="fas fa-home" > Pagrindinis puslapis</a>
					</div>
					
					<div class="active">
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
						<button onclick="goBack()" id="atgalButton" class="fas fa-angle-left"> Atgal</button>
						<form id="sellPrekeForm" name="sellPrekeForm" role="form" method="POST" action="sellingItem" modelAttribute = "item">
							<input type="hidden" type="text" id="title" name="title"/>
							<input type="hidden" type="number" id="price" name="price"/>
							<button type="submit" id="doneButton" onclick="return confirm('Ar tikrai norite parduoti šias prekes?')">Done</button> 
						</form>	
					</div>
					<div class="myMenuElementPickers">
						<div class="littlePicker" id="mainPicker"> 
							<button onclick="showNext(this)" id="akiniai" class="button -akiniai">Remeliai</button> 
							<button onclick="showNext(this)" id="lesiai" class="button -lesiai">Kontaktinai lęšiai</button> 
							<button onclick="showNext(this)" id="saulesAkiniai" class="button -saules akiniai">Saulės akiniai</button> 
							<button onclick="showNext(this)" id="kitka" class="button -kitka">Kitos prekės</button>
							<button onclick="showNext(this)" id="dioptrija" class="button -dioptrija">Akinių lęšiai</button>  
						</div>
						<div class="littlePicker" id="akiniaiPicker"> 
							<c:forEach items="${akiniai}" var="item">
	  								<button onclick="addItem(${item.id}, '${item.title}',${item.price})"> ${item.title} ${item.price} </button>
							</c:forEach> 	
						</div>
						<div class="littlePicker" id="saulesPicker"> 
							<c:forEach items="${sAkiniai}" var="item">
	  								<button onclick="addItem(${item.id}, '${item.title}',${item.price} )"> ${item.title} ${item.price} </button>
							</c:forEach> 
						</div>
						<div class="littlePicker" id="lesiaiPicker"> 
							<c:forEach items="${lesiai}" var="item">
	  								<button onclick="addItem(${item.id}, '${item.title}',${item.price})"> ${item.title} ${item.price} </button>
							</c:forEach> 
						</div>
						<div class="littlePicker" id="kitkaPicker"> 
							<c:forEach items="${kitka}" var="item">   
	  								<button onclick="addItem(${item.id}, '${item.title}' ,${item.price})"> ${item.title} ${item.price} </button>
							</c:forEach> 
					   </div>
					   <div class="littlePicker" id="dioptrijuPicker">
					   		<p>hello</p>
					   		
					   		
					   	   Uzsakovo_ID          int,
						   Desines_akies_sfera  decimal not null,
						   Kaires_akies_sfera   decimal not null,
						   Desines_akies_cilindras decimal,
						   Kaires_akies_cilindras decimal,
						   Desines_akies_asis   int,
						   Kaires_akies_asis    int,
						   Desines_akies_prizme decimal,
						   Kaires_akies_prizme  decimal,
						   Atstumas_tarp_vyzdziu_centru int not null,
						   Paskirtis            varchar(25) not null,
						   primary key (Recepto_ID)
					   		
					   </div>
					   
				</div>
			</div>
		</div>
	</div>
		
		<script type="text/javascript" >
		
			var itemList="";
			var price =0;
			var totalWindow = document.getElementById("totalWindow");
			var showWindow = document.getElementById("showWindow");
			
			function showNext(caller) {
					
				var mainP = document.getElementById("mainPicker");
			    var akiniaiP = document.getElementById("akiniaiPicker");
			    var saulesP = document.getElementById("saulesPicker");
				var lesiaiP = document.getElementById("lesiaiPicker");
				var kitkaP = document.getElementById("kitkaPicker");
				var dioptrijaP = document.getElementById("dioptrijuPicker");
				
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
						
					case document.getElementById("dioptrija"):
						mainP.style.display = "none";
						dioptrijaP.style.display = "flex";
						position=dioptrijaP;
						break;
						
					default:
					break;
				}
					
			}
			var position = document.getElementById("mainPicker");
			
			function addItem(id,title, pprice){
				var node = document.createElement("p");
				node.textContent= title+ " "+ pprice;
				showWindow.appendChild(node);
				itemList=itemList + parseInt(id) + ",";
				document.getElementById("title").value=itemList;
				price=parseFloat(price)+parseFloat(pprice);
				totalWindow.textContent="Iš viso: "+price.toFixed(2);
				document.getElementById("price").value=price;
			}
			
			function goBack(){
				var mainP = document.getElementById("mainPicker");
				position.style.display="none";
				mainP.style.display="flex";
				
			}
		</script>
		
	</body>
	
	
</html>