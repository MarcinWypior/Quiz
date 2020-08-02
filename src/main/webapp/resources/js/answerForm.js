document.addEventListener("DOMContentLoaded", function() {

    console.log("skrypt się załadował");

    let errorsText = document.getElementById("text.errors");

    let inputText = document.getElementById("text");

    console.log("text z wnętrza błędu "+errorsText.innerText + "\n");

    if(errorsText.innerText!==""){
        console.log("są błędy");
        inputText.classList.add("errors");
    }

});