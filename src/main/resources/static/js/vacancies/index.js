let popUp = document.querySelector(".pop_up");
let contentPopup = document.querySelector(".content_popup");
let closeButton = document.getElementById("close-popup");

contentPopup.addEventListener('click', (event) => {
    event.stopPropagation();
});

function showOccupation() {
    if (popUp.style.display == "flex") {
        popUp.style.display = "none";
        return;
    }
    
    popUp.style.display = "flex";
}

// Fecha o popup ao clicar no botão "X"
closeButton.addEventListener("click", function () {
    popUp.style.display = "none";
});