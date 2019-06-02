
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<link href="resources/css/frame2.css" rel="stylesheet">
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
					
					<div class="active">
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
				<div class="kontainer2">
				
					<div class="col-lg-12 searchHead">
			            <input type="search" class="form-control" id="input-search" placeholder="Klientų paieška..." >
			        </div>
			        
				    <div class="row col-md-6 col-md-offset-2 custyle">
				    
				    <table class="table table-striped custab">
				    <thead>
				        <tr>
				            <th>Vardas ir pavarde</th>
				            <th>Tel nr.</th>
				            <th>El. paštas</th>
				            <th class="text-center">Veiksmas</th>
				        </tr>
				    </thead>
				    
				        <tr>
			            	<td> ... </td>
			            	<td> ... </td>
			            	<td> ... </td>
			            	
			            	<td class="text-center"><a href="addNewClient" class="btn btn-info btn-xs"><span class="glyphicon glyphicon-plus-sign"></span> Pridėti</a></td>
			            </tr>
			            
			            <c:forEach items="${clients}" var="client"> 
			              <tr class= "items">
			            	<td> ${client.name} ${client.surname} </td>
			            	<td> ${client.phoneNumber} </td>
			            	<td> ${client.email} </td>			            	
			            	<td class="text-center"><a href="editClient?clientId=${client.id}" class="btn btn-info btn-xs"><span class="glyphicon glyphicon-edit"></span> Peržiūra</a></td>
			              </tr>
			            </c:forEach>
			            
			           
					            
				    </table>
				    </div>
				</div>
				
			</div>
			
			
		</div>
	</div>
	<script type="text/javascript" >
	$(function() {    
        $('#input-search').on('keyup', function() {
          var rex = new RegExp($(this).val(), 'i');
            $('.items').hide();
            $('.items').filter(function() {
                return rex.test($(this).text());
            }).show();
        });
    });
	</script>


</body>

</html>