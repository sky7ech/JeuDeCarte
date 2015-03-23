$('#CreationCompte').click(function (event) {
    $.ajax({
    	url: "v1/player",
    	contentType : 'application/json',
   		data: JSON.stringify({
   			pseudo: $('#NewPseudo').val(),
   			mdp: $('#NewMDP').val(),
   			nom : $('#NewNom').val(),
   			prenom : $('#NewPrenom').val(),
    	}),
    	type: 'POST',
    	dataType : "json",
    	success:function(json) {
    		$("#output").append("Compte Creer"); 
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

