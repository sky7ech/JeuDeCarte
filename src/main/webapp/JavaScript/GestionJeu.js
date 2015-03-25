$('#Afficher').click(function (event) {
	var i = 1
	$.ajax({
		url: "/webapi/playerpoker/",
		type: "GET",
		data: { },
		dataType: "json",
		success: function(json){
			json.forEach(function(elem,index,array) {
				var j = "#joueur" + i;
				$(j).text(elem.pseudo);
			});
		},
		error: function(xhr, status, errorThrown){
			alert( "Sorry, there was a problem!" );
			console.log( "Error: " + errorThrown );
			console.log( "Status: " + status );
			console.dir( xhr );
		},
		complete: function(xhr,status){
			alert("The request is complete!");
		}
	});
});


$('#Relancer').click(function(event){
		url: "/webapi/playerpoker/" + $('#Relance').value,
		type: "PUT",
		data: { 
			$('#Relance').value
			readCookie("pseudo");
		},
		dataType: "json",
		success: function(json){
			alert("Votre mise a était pris en compte")
		},
		error: function(xhr, status, errorThrown){
			alert( "Votre mise n'a pas était pris en compte" );
			console.log( "Error: " + errorThrown );
			console.log( "Status: " + status );
			console.dir( xhr );
		},
		complete: function(xhr,status){
			alert("The request is complete!");
		}
	});
});

})

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