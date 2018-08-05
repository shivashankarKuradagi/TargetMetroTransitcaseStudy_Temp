function validateAndSubmitForm(){
		$('#loadingMsg').show();
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
				  if(response.status){
						$('#result').html(response.message);
				  }	else {
					  $('#result').html(response.message).css("color", "red");
				  }
					
				},
				error: function(response){
					$('#loadingMsg').hide();
					$('#result').html(response.message).css("color", "red");
				}
		 });
}