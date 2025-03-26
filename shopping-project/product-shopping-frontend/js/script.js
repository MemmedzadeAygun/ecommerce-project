let user_name = document.getElementById("user_name");
const userName = localStorage.getItem('username');
let profile = document.querySelector('.profile');
let card_shopping = document.querySelector('.card-shopping');

user_name.textContent = userName;

let logOutBtn = document.getElementById('logout-btn');

logOutBtn.addEventListener('click', (event) => {
    localStorage.removeItem('username');
    localStorage.removeItem('password');

    profile.style.display = "none";
    card_shopping.style.display = "none";
    event.target.textContent = "Log in";
    event.target.style.backgroundColor = "#fff";
    event.target.style.color = "black";
})

function checkUser() {
    const username = localStorage.getItem('username');
    const password = localStorage.getItem('password');

    if (!username || !password) {
        user_name.textContent = "";
        profile.style.display = "none";
        card_shopping.style.display = "none";
        logOutBtn.textContent = "Log in";
        logOutBtn.style.backgroundColor = "#fff";
        logOutBtn.style.color = "black";
    } else {
        profile.style.display = "block";
        card_shopping.style.display = "block";
    }
}

checkUser();

let profileImg=document.querySelector('.profile img');

profileImg.addEventListener('click',()=>{
    window.location.href="profile.html";
})

document.getElementById('shop').addEventListener('click',()=>{
    window.location.href="shop.html";
})

document.querySelector('.card-shopping').addEventListener('click',() => {
    window.location.href = "cart.html";
})