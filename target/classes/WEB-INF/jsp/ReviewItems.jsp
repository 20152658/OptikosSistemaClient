<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<link href="resources/css/side-navigation.css" rel="stylesheet">
<script src="https://cdn.plot.ly/plotly-latest.min.js" type="text/javascript"></script>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js" type="text/javascript"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js" type="text/javascript"></script>
</head>

<body>
	<!--- Side navigation --->
	<div class="sidenav">

		<div class="menu-link">
			<a href="home">Home</a>
		</div>
		
		<div class="menu-link">
			<a href="newOrder">New Order</a>
		</div>

		<div class="active">
			<a href="reviewItems">Review Items</a>
		</div>
		
		<div class="menu-link">
			<a href="reviewOrders">Review Orders</a>
		</div>

		<div class="logout">
			<a href="logout">Log out</a>
		</div>
	</div>

	<!--- Page content --->
	<div class="main">
		<form action="${file.fileId}/reviewData" modelAttribute="clientFiles" modelAttribute="heartRateDataList">
			
			<p>Select which of the uploaded files you want to view. First is
			oldest. Last is newest</p>
			<select name="clientFiles" onchange="location = this.value;" >
				<c:forEach var="file" items="${clientFiles}">
					<Option value="${file.fileId}" onselect="${file.fileId}/reviewData">${file.fileName}</Option>
				</c:forEach>
			</select>

			<div id="myDiv"></div>
			<label for="Average" class="change-information-control-label" Style="margin-left: 80px;">Average: </label>
			<label id="average"></label>
			<br>
			<label for="Median" class="change-information-control-label" Style="margin-left: 80px;">Variance: </label>
			<label id="variance"></label>
		</form>
					<script type="text/javascript">
				var variables = [<c:out value='${heartRateData}' />];
				var sum = 0;
				var sumVariance = 0;
				var average = 0;
				var variance = 0;
				var x = [];
				for (var i = 0; i < variables.length; i++) {
					x.push(i);
					sum += variables[i];	
				}
				average = sum/variables.length;
				document.getElementById("average").innerHTML = average.toFixed(3);
				for (var i = 0; i < variables.length; i++){
					sumVariance += Math.pow(variables[i] - average, 2);
				}
				variance = sumVariance/(variables.length-1);
				document.getElementById("variance").innerHTML = variance.toFixed(3);

				var trace = {
					x : x,
					y : variables,
					type : 'scatter'
				};
				var layout = {
					title : 'Heart rate ',
				
					showlegend : false
				};
				var data = [ trace ];
				Plotly.newPlot('myDiv', data, layout);
			</script>
	</div>


</body>

</html>