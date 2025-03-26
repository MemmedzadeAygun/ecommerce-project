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
        profile.style.display="flex";
        profile.style.justifyContent="center";
        profile.style.alignItems="center";
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

let API_URL = "http://localhost:9999/products/";
const username = localStorage.getItem('username');
const password = localStorage.getItem('password');
const token = "Basic " + btoa(username + ":" + password);

function getProduct(){
    const urlParams = new URLSearchParams(window.location.search);
    const productId = urlParams.get('id');

    if (productId) {

        fetch(API_URL + `${productId}`,{
            method: 'GET',
            headers : {
                'Authorization': token
            }
        })
        .then(response => response.json())
        .then(data => {
            console.log(data);
            
           let productImg = document.querySelector('.product-image img');
           productImg.src = data.imgUrl;

           productModel = document.querySelector('.right-side h2');
           productModel.textContent = data.model;

           let stars = '';
           for (let index = 0; index < data.rating; index++) {
                stars+='<i class="fa-solid fa-star"></i>';
           }
           document.getElementById('rating').innerHTML = stars;

           document.getElementById('price').textContent = data.price + "$";
           document.getElementById('brand').textContent = data.brand;
        })
    }
}

getProduct();

document.querySelector('.card-shopping').addEventListener('click',() => {
    window.location.href = "cart.html";
})

document.getElementById('add-to-cart').addEventListener('click',(e)=>{
    const urlParams=new URLSearchParams(window.location.search);
    const productId=urlParams.get('id');
    
    const cart={
        productId:productId
    }

    fetch(`http://localhost:9999/cards/add`,{
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': token
        },
        body: JSON.stringify(cart)
    })
    .then(data => {
        alert("Product add to cart successfully");
    })
})