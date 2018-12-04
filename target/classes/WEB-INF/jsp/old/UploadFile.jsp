<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
     "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <link href="resources/css/side-navigation.css" rel="stylesheet">
	<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
	<script src="https://code.jquery.com/jquery-1.11.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script>
    <script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/additional-methods.min.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>File Upload</title>
    <style media="screen" type="text/css"> 
    
    	#customButton{
    	width: 20%;
    	padding: 5px;
    	color: white;
    	background-color: #828282;
    	margin-top: 3%;
    	border-color: black;
    	border-radius: 3px;
    	}
    	#upload{
    	width: 10%;
    	padding: 5px;
    	color: white;
    	background-color: #828282;
    	margin-top: 2%;
    	border-color: black;
    	border-radius: 3px;
    	}
    	#customText{
    	line-height: 3;
    	}
		table{
		    border: 1px solid black;
		    width: 60%;
		    height: 20px;
		    border-collapse: collapse;
		    background-color: #f7f8f9;
		}
		tr, td {
		}
		tr {
		    text-align: center;
        }
	</style>
</head>
<body>
		<!--- Side navigation --->
		<div class="sidenav">
		
			<div class="menu-link" >
				<a href="home">Home</a>
			</div>
			
			<div class="menu-link" >
				<a href="changeInformation">Change information</a>
			</div>
			
		    <div class="active" >
				<a href="uploadFile">Upload file</a>
			</div>
			
			<div class="menu-link" >
				<a href="reviewData">Review data</a>
			</div>
			
			<div class="logout" >
				<a href="logout">Log out</a>
			</div>
			
		</div>
	
	<!--- Page content --->
    <div align="center" class="main">
     <form id="uploadForm" method="post" action="saveFile" enctype="multipart/form-data">
            <input id="real" type="file" size="20" accept=".hrm" style="display:none " name="fileUpload"/>
            <br>
            <table>
                
                <tr>
                    <td style="line-height: 3; font-weight: bold">Pick file (only .hrm files allowed):</td>
                </tr>
                <tr>
                    <td><button type="button" id="customButton" name="fileUpload" >Choose file</button></td>
                </tr>
                <tr>
                    <td><span id="customText">No file chosen</span></td>
                </tr>

            </table>
            <input id="upload" type="submit" value="Upload" />
            <span id="onSubmit"></span>
        </form>  
    </div>
    <script type="text/javascript">
            document.getElementById("upload").style.visibility = "hidden";
    		const realFile = document.getElementById("real");
    		const customButton = document.getElementById("customButton");
    		const customText = document.getElementById("customText");
    		 
    		customButton.addEventListener("click", function(){
    			realFile.click();
    		});
    		
    		realFile.addEventListener("change", function(){
    			var realFileValue = realFile.value;
    			var fileExt = realFileValue.split('.').pop(); 
    			if(realFileValue && fileExt == 'hrm'){
    				customText.innerHTML = realFileValue.match(/[\/\\]([\w\d\s\.\-\(\)]+)$/)[1];
    				document.getElementById("upload").style.visibility = "visible";
    			}
    			else{
    				customText.innerHTML = "No file chosen";
    				document.getElementById("upload").style.visibility = "hidden";
    			}
    		});
	</script>
</body>
</html>
