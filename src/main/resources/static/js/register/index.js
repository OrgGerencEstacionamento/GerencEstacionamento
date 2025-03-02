let eye = document.getElementById('eye');
let password = document.getElementById('password');

function changeEye() {
    if(eye.src.includes("eye2.png")) {
        eye.src = "images/eye1.png";
        password.type = "text";
        return;
    }

    password.type = "password";
    eye.src = "images/eye2.png";
}