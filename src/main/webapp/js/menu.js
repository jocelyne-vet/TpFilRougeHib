/**
 * 
 */
 
 // Script lien entete actif ou inactif

var divEntete = document.getElementById("entete");

const collection = document.getElementsByClassName("menu");

alert("toto");
alert("liens"+collection.length);
for (var i = 0; i < liens.length; i++) {
    liens[i].addEventListener("click", function () {
        alert("test");
        var current = document.getElementsByClassName("active");
        current[0].className = current[0].className.replace("active", "");
        this.className += " active";
    });
}  