let popUp = document.querySelector(".pop_up");
let contentPopup = document.querySelector(".content_popup");

contentPopup.addEventListener('click', (event) => {
    event.stopPropagation();
});

function showOccupation() {
    if(popUp.style.display == "flex") {
        popUp.style.display = "none";
        return;
    }
    
    popUp.style.display = "flex";
}