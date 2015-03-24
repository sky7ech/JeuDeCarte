$('#CreationCompte').click(function (event) {
	if($('#NewPseudo').val() != '' && $('#NewMDP').val() != '' &&  $('#NewNom').val() != '' && $('#NewPrenom').val()!= ''){
		if($("#NewMDP").val() == $("#VerifMDP").val()){
			$.ajax({
				url: "JeuDeCarte/webapi/player/",
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
		}
		else {
			$("#output").text("Mot de passe different");
		}
	}
	else{
		$("#output").text("Champ vide")
	}
});

PageConnexion.onclick = function(){
	window.location.href = "Connexion.html";
}

$('#Connexion').click(function (event) {
	$.ajax({
		url: "JeuDeCarte/webapi/player/" + $('#pseudo').val() ,
		contentType : 'application/json',
		data: JSON.stringify({
			pseudo : $('#pseudo').val(),
			mdp : $('#MDP').val(),
			
		}),
		type: 'GET',
		dataType : "json",
		success:function(json) {
			window.location.href = "EcranJeu.html";
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