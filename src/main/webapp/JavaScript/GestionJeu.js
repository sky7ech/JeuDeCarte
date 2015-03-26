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
	$.ajax({
		url: "/webapi/playerpoker/" + $('#Relance').value + "?pseudo= {" + readCookie("pseudo") + "}&idTable={ " + readCookie(idTable)+ "}"
		 "/,
		/{mise}?pseudo={pseudo}&idTable={idTable}")
		type: "PUT",
		data: { 
			
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

function afficherflop(){
	$.ajax({
		url:"/webapi/tablepoker/ "+ readCookie("idtable") + "/"
		type : "GET"
		data{

		}
		dataType: "json"
		success:function(json){
				$("#Flop1").html(("<img src=\"CSS/Image/"+flop1 + ".jpg\" alt=\"...\" class=\"img-rounded\" height=\"120\" width=\"87\">");)
				$("#Flop2").html(("<img src=\"CSS/Image/"+flop2 + ".jpg\" alt=\"...\" class=\"img-rounded\" height=\"120\" width=\"87\">");)
				$("#Flop3").html(("<img src=\"CSS/Image/"+flop3 + ".jpg\" alt=\"...\" class=\"img-rounded\" height=\"120\" width=\"87\">");)
			};
		}
	})
}

function afficherturn(){
	$.ajax({
		url:"/webapi/tablepoker/ "+ readCookie("idtable") + "/"
		type : "GET"
		data{

		}
		dataType: "json"
		success:function(json){
				$("#Turn").html(("<img src=\"CSS/Image/"+turn + ".jpg\" alt=\"...\" class=\"img-rounded\" height=\"120\" width=\"87\">");)
			};
		}
	})
}

function afficherriver(){
	$.ajax({
		url:"/webapi/tablepoker/ "+ readCookie("idtable") + "/"
		type : "GET"
		data{

		}
		dataType: "json"
		success:function(json){
				$("#River").html(("<img src=\"CSS/Image/"+river+ ".jpg\" alt=\"...\" class=\"img-rounded\" height=\"120\" width=\"87\">");)
			};
		}
	})
}