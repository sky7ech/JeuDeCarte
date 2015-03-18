$('#NewButton').click(function (event) {
    $.ajax({
    	url: "/books",
   		data: {
   			author: $('#newAuteur').val(),
   			title: $('#newTitre').val(),	
    	},
    	type: "POST",
    	dataType : "json",
    	success:function(json) {
    		$("#output").empty();
     		$("#output").append("Nouveau livre : ID : " + json.id + " Titre : " + json.title + " Auteur :" + json.author); 
    	},
    	error: function( xhr, status, errorThrown ) {
        	alert( "Sorry, there was a problem!" );
        	console.log( "Error: " + errorThrown );
        	console.log( "Status: " + status );
        	console.dir( xhr );
    	},
    	complete: function( xhr, status ) {
        	alert( "The request is complete!" );
    	}
		});
	});