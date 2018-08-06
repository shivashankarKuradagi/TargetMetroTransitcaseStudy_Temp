function validateAndSubmitForm(){
		$('#loadingMsg').show();
		$('#result').hide();
		$('#result').empty();
		var route = $('#busRoute').val();
		var stop = $('#stop').val();
		var direction = $('#direction').val();
		
		var metrotransitRequest = {"stop":stop,"route":route,"direction":direction};
		
		 $.ajax({
				type: "POST",
				contentType: 'application/json; charset=utf-8',
				url: "/getWatingTime",
				dataType : 'json',
				data: JSON.stringify(metrotransitRequest),
				success: function( response ){
					$('#loadingMsg').hide();
					$('#result').show();
				  if(response.status){
					  var result = "BUS ROUTE : " + response.route +"<br> BUS STOP NAME : " 
					  + response.stop + "<br> DIRECTION : " + response.direction + "<br><br> Waiting Time : " +response.message;
						$('#result').html(result).css("color", "#187018");
				  }	else {
					  $('#result').html(response.message).css("color", "red");
				  }
					
				},
				error: function(response){
					$('#loadingMsg').hide();
					$('#result').show();
					$('#result').html(response.responseJSON.message).css("color", "red");
				}
		 });
}