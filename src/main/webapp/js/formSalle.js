function valider(){
    var bOk = true;
    
    var numeroSalle = document.getElementById("numero");
    var nbPlaces = document.getElementById("nbplaces");
    if(numeroSalle.value<=0){
        bOk=false;
        alert( "Vous devez saisir un numéro de salle supérieur à 0 ");
    }
    if(nbPlaces.value<=50){
        bOk=false;
        alert("Vous devez saisir un nombre de places supérieur à 50 ");
    }
    return bOk;
}