/**
 * 
 */
 
 function valider(dateDeNaissance, ageMinimum){
	
	
	var dateOfBirth = new Date(dateDeNaissance);

	var toDay = new Date();
	
	var bOk = true;
	var diffYear = toDay.getFullYear()-dateOfBirth.getFullYear();
	
	if(diffYear<ageMinimum){
		bOk=false;
		alert("vous devez avoir au moins "+ageMinimum);
	}
	return bOk;
	
}