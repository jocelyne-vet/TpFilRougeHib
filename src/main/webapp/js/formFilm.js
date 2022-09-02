function valideSeanceBis() {

	var bOk = true;
	var existSeance = false;
	const tabDateSeance = [];
	var toDay = new Date();
	var allInputs = document.getElementsByTagName("input");
	for (var i = 0; i < allInputs.length; i++) {
		var nom = allInputs[i].name;
		if (nom == "dateSeance") {
			existSeance=true;

			heure = allInputs[i + 1].value;

			date = allInputs[i].value;

			dateSeance = new Date(date + " " + heure);

			if (dateSeance < toDay) {
				alert("La date " + allInputs[i].value + "est inférieure à la date d aujourd'hui");
				bOk = false;
			}

			
			var dateFind = tabDateSeance.find(
				date => date.getTime() === dateSeance.getTime()
			);
			if (!isNaN(dateFind)) {
				bOk = false;
				alert("Toutes les séances doivent être différentes !");
			}
			tabDateSeance.push(dateSeance);
		}
	}
	
	if(!existSeance){
		alert("Vous devez créer au moins une séance.")
		bOk = false;
	}
	return bOk;

}



function valideSeance() {
	toDay = new Date();
	var allInputs = document.getElementsByTagName("input");
	bOk = true;

	for (var i = 0; i < allInputs.length; i++) {

		var nom = allInputs[i].name;


		if (allInputs[i].name == "dateSeance") {

			date_input = new Date(allInputs[i].value)
			if (date_input <= toDay) {
				bOk = false;
				alert("La date " + allInputs[i].value + "est inférieure à aujourd'hui");
			}

		}

	}

	return bOk;
}


/*    */
index = 0;



function ajouterSeance() {

	index++;


	var divFoo = document.getElementById("nouvelleSeance");
	// Create an input element for date of birth
	var LS = document.createElement("label");
	LS.setAttribute("for", "dateSeance" + index);
	LS.innerText = " Date : ";
	var DS = document.createElement("input");
	DS.setAttribute("type", "date");
	DS.setAttribute("name", "dateSeance");
	DS.setAttribute("required", "required");
	/* DS.setAttribute("placeholder", "DS"); */
	var LTS = document.createElement("label")
	LTS.setAttribute("for", "heureSeance")
	LTS.innerText = " Heure : ";
	var TS = document.createElement("input");
	TS.setAttribute("type", "time");
	TS.setAttribute("id", "heureSeance");
	TS.setAttribute("name", "heureSeance");
	TS.setAttribute("required", "required");
	/*  TS.setAttribute("placeholder", "TS"); */
	var LCS = document.createElement("label");
	LCS.setAttribute("for", "cs" + index)
	LCS.innerText = "Supprimer ";
	var CS = document.createElement("input");
	CS.setAttribute("type", "checkbox");
	CS.setAttribute("id", "cs" + index);
	CS.setAttribute("name", "cs" + index);
	CS.setAttribute('onclick', 'removeSeance (this.id);')

	var HS = document.createElement("input");
	HS.setAttribute("type", "hidden");
	HS.setAttribute("id", "idSeance");
	HS.setAttribute("name", "idSeance");
	HS.setAttribute('value', "");


	var div = document.createElement("div");
	div.className = "seance";
	div.id = "nvelle" + index;

	div.append(LCS);
	div.append(CS);
	div.append(LS);
	div.append(DS);
	div.append(LTS);
	div.append(TS);
	div.append(HS);

	divFoo.append(div);
	/* let text = document.getElementById("demo").nodeName;
	document.getElementById("demo").innerHTML = text; */
}

function removeSeance(idDInputC) {

	document.getElementById(idDInputC).parentElement.remove();
}
