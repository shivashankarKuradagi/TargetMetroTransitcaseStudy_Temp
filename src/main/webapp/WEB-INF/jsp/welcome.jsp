<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/module.js"></script>
<link rel="stylesheet" type="text/css" href="css/style.css"/>
<title>welcome page</title>
</head>
<body>

	<div align="center">
		Welcome <br> <br>
	</div>


	<div align="left">
		<form name="test" id="test" onsubmit="return false">

			<div class="rowStyle" >
				<lable class="lables">BUS ROUTE :</lable>
				<input type="text" id="busRoute"  class="inputField" id='busRoute'>
			</div>

			<div class="rowStyle" >
				<label class="lables" >BUS STOP NAME :</label>
				<input type="text" id="stop"  class="inputField"  id='stop'>
			</div>
			<div  class="rowStyle">
				<label class="lables">DIRECTION :</label>
				<select id="direction" >
					<option value="1">South</option>
					<option value="2">East</option>
					<option value="3">West</option>
					<option value="4">North</option>
				</select>
			</div>
			<div class="rowStyle">
			<button onclick="validateAndSubmitForm()" class="button"> Submit</button>
			</div>
		</form>
	</div>


	<div id='loadingMsg' hidden="true" class="rowStyle result">Getting information...</div>


	<div id='result' class="rowStyle result"></div>

</body>
</html>