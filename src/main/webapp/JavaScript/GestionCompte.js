$('#CreationCompte').click(function (event) {
	if($('#NewPseudo').val() != '' && $('#NewMDP').val() != '' &&  $('#NewNom').val() != '' && $('#NewPrenom').val()!= ''){
		if($("#NewMDP").val() == $("#VerifMDP").val()){
			$.ajax({
				url: "/jeudecarte/player",
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
		url: "/jeudecarte/player/" + $('#pseudo').val() ,
		contentType : 'application/json',
		data: JSON.stringify({
			pseudo : $('#pseudo').val(),
			mdp : $('#MDP').val(),

		}),
		type: 'GET',
		dataType : "json",
		success:function(json) {
			createCookie("idtable",1,7)
			createCookie("pseudo",pseudo,7)
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

function createCookie(name,value,days) {
	if (days) {
		var date = new Date();
	date.setTime(date.getTime()+(days*24*60*60*1000));
		var expires = "; expires="+date.toGMTString();
	}
	else var expires = "";
	document.cookie = name+"="+value+expires+"; path=/";
}
function readCookie(name) {
	var nameEQ = name + "=";
	var ca = document.cookie.split(';');
	for(var i=0;i < ca.length;i++) {
		var c = ca[i];
		while (c.charAt(0)==' ') c = c.substring(1,c.length);
		if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);
	}
	return null;
}
function afficherSession() {
	var html = 'Tu es ';
	if (readCookie("pseudo") == null) {
		window.location.href = "index.html";
	} else {
		html = html + readCookie("pseudo");
		$("#session").html(html);
	}
}
function eraseCookie(name) {
	createCookie(name,"",-1);
}