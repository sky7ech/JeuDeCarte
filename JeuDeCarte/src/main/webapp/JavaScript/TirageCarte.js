function tirageAleatoire(){
	var couleur1 = Math.floor(Math.random() *4) +1;
	var type1 = Math.floor(Math.random() * 13) +1;
	$("#Carte1").append("<img src=\"../CSS/Image/"+type1 + "_" + couleur1 + ".jpg\" alt=\"...\" class=\"img-rounded\" height=\"120\" width=\"87\">");
	do{
		couleur2 = Math.floor(Math.random() *4) +1;
		type2 = Math.floor(Math.random() * 13) +1;
	}
	while(couleur1 == couleur2 && type1==type2)
	
	$("#Carte2").append("<img src=\"../CSS/Image/"+type2 + "_" + couleur2 + ".jpg\" alt=\"...\" class=\"img-rounded\" height=\"120\" width=\"87\">");
}
tirageAleatoire();


Flop.onclick=function(){
	var couleur1 = Math.floor(Math.random() *4) +1;
	var type1 = Math.floor(Math.random() * 13) +1;
	$("#Flop1").html("<img src=\"../CSS/Image/"+type1 + "_" + couleur1 + ".jpg\" alt=\"...\" class=\"img-rounded\" height=\"120\" width=\"87\">");
	var couleur1 = Math.floor(Math.random() *4) +1;
	var type1 = Math.floor(Math.random() * 13) +1;
	$("#Flop2").html("<img src=\"../CSS/Image/"+type1 + "_" + couleur1 + ".jpg\" alt=\"...\" class=\"img-rounded\" height=\"120\" width=\"87\">");
	var couleur1 = Math.floor(Math.random() *4) +1;
	var type1 = Math.floor(Math.random() * 13) +1;
	$("#Flop3").html("<img src=\"../CSS/Image/"+type1 + "_" + couleur1 + ".jpg\" alt=\"...\" class=\"img-rounded\" height=\"120\" width=\"87\">");
}

Turn1.onclick=function(){
	var couleur1 = Math.floor(Math.random() *4) +1;
	var type1 = Math.floor(Math.random() * 13) +1;
	$("#Turn").html("<img src=\"../CSS/Image/"+type1 + "_" + couleur1 + ".jpg\" alt=\"...\" class=\"img-rounded\" height=\"120\" width=\"87\">");
}

River1.onclick=function(){
	var couleur1 = Math.floor(Math.random() *4) +1;
	var type1 = Math.floor(Math.random() * 13) +1;
	$("#River").html("<img src=\"../CSS/Image/"+type1 + "_" + couleur1 + ".jpg\" alt=\"...\" class=\"img-rounded\" height=\"120\" width=\"87\">");
}

Relancer.onclick=function() {
	var mise = Relance.value;
	$("#ActionJ1").text("Relance à " + mise + " $")
}