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
        profile.style.display = "flex";
        profile.style.justifyContent = "center";
        profile.style.alignItems = "center";
        card_shopping.style.display = "block";
    }
}

checkUser();

let profileImg = document.querySelector('.profile img');

profileImg.addEventListener('click', () => {
    window.location.href = "profile.html";
})

let API_URL = "http://localhost:9999/products/";
const username = localStorage.getItem('username');
const password = localStorage.getItem('password');
const token = "Basic " + btoa(username + ":" + password);

function getAllCategory() {
    fetch(API_URL + 'getAllProduct', {
        method: 'GET',
        headers: {
            'Authorization': token
        }
    })
        .then(response => response.json())
        .then(data => {

            const uniqueCategory = new Set();
            data.forEach(element => {
                uniqueCategory.add(element.category);
            });
            console.log(uniqueCategory);


            uniqueCategory.forEach(category => {
                let p = document.createElement('p');
                p.textContent = category;
                p.style.cursor = "pointer";

                p.addEventListener('click', () => {
                    filterProductsByCategory(category);
                })

                document.querySelector('.shop').appendChild(p);
            });
        })
}

getAllCategory();

let allProducts = [];

function getAllProducts() {
    fetch(API_URL + 'getAllProduct', {
        method: 'GET',
        headers: {
            'Authorization': token
        }
    })
        .then(response => response.json())
        .then(data => {
            allProducts = data;
            renderProducts(allProducts);
            console.log(allProducts);

        });
}

function renderProducts(products) {
    const productsContainer = document.querySelector('.products');
    productsContainer.innerHTML = '';

    products.forEach(element => {
        const product = document.createElement('div');
        product.classList.add('product');
        product.setAttribute('data-id', element.id);


        const product_img = document.createElement('div');
        product_img.classList.add('product-img');

        const img = document.createElement('img');
        img.src = element.imgUrl;

        const product_detail = document.createElement('div');
        product_detail.classList.add('product-detail');

        const model = document.createElement('p');
        model.classList.add('model');
        model.textContent = element.model;

        const price = document.createElement('p');
        price.classList.add('price');
        price.textContent = element.price + '$';

        const rate = document.createElement('p');
        let stars = '';
        for (let i = 0; i < element.rating; i++) {
            stars += '<i class="fa-solid fa-star"></i>';
        }
        rate.innerHTML = stars;

        const btn = document.createElement('button');
        btn.textContent = "Add to Cart";
        btn.setAttribute('data-id', element.id);

        btn.addEventListener('click',(e)=>{
            e.stopPropagation();
            let productId=btn.getAttribute('data-id')
            console.log(productId);
            addToCart(productId);
        })

        product.appendChild(product_img);
        product_img.appendChild(img);
        product.appendChild(product_detail);
        product_detail.appendChild(model);
        product_detail.appendChild(price);
        product_detail.appendChild(rate);
        product.appendChild(btn);
        productsContainer.appendChild(product);
    });
}


document.querySelectorAll('.rate p').forEach((rateP, index) => {
    rateP.addEventListener('click', () => {
        const starCount = 5 - index;
        const filtered = allProducts.filter(product => product.rating === starCount);
        renderProducts(filtered);
    });
});

getAllProducts();

function filterProductsByCategory(category) {
    fetch(API_URL + 'getAllProduct', {
        method: 'GET',
        headers: {
            'Authorization': token
        }
    })
        .then(response => response.json())
        .then(data => {
            const products = document.querySelector('.products');

            products.innerHTML = '';

            const filtered = data.filter(product => product.category === category);

            filtered.forEach(element => {
                const product = document.createElement('div');
                product.classList.add('product');

                const product_img = document.createElement('div');
                product_img.classList.add('product-img');

                const img = document.createElement('img');
                img.src = element.imgUrl;

                const product_detail = document.createElement('div');
                product_detail.classList.add('product-detail');

                const model = document.createElement('p');
                model.classList.add('model');
                model.textContent = element.model;

                const price = document.createElement('p');
                price.classList.add('price');
                price.textContent = element.price + '$';

                const rate = document.createElement('p');
                let stars = '';
                for (let index = 0; index < element.rating; index++) {
                    stars += '<i class="fa-solid fa-star"></i>';
                }
                rate.innerHTML = stars;

                const btn = document.createElement('button');
                btn.textContent = "add to card";

                product.appendChild(product_img);
                product_img.appendChild(img);
                product.appendChild(product_detail);
                product_detail.appendChild(model);
                product_detail.appendChild(price);
                product_detail.appendChild(rate);
                product.appendChild(btn);
                products.appendChild(product);
            })
        })
}

document.querySelector('.right-side button').addEventListener('click', () => {
    getAllProducts();
})

function searchProducts() {
    let API_URL = "http://localhost:9999/products/searchProduct";
    let query = document.getElementById('searchInput').value;

    fetch(`${API_URL}?query=${query}`, {
        method: 'GET',
        headers: {
            'Authorization': token
        }
    })
        .then(response => response.json())
        .then(data => {
            renderProducts(data);
        })
}

searchProducts();

document.getElementById('sortSelect').addEventListener('change', () => {

    function sortProducts() {
        const sortValue = document.getElementById('sortSelect').value;
        let sortedProducts = [...allProducts];

        if (sortValue === 'priceAsc') {
            sortedProducts.sort((a, b) => parseFloat(a.price) - parseFloat(b.price));
        } else if (sortValue === 'priceDesc') {
            sortedProducts.sort((a, b) => parseFloat(b.price) - parseFloat(a.price));
        }

        renderProducts(sortedProducts);
    }

    sortProducts();
})

document.addEventListener('click', (e) => {
    // if (e.target.closest('button')) return;

    if (e.target.closest('.product')) {
        const productId = e.target.closest('.product').getAttribute('data-id');
        console.log(productId);
        window.location.href = `product.html?id=${productId}`;
    }

});

function addToCart(productId){

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
}

document.querySelector('.card-shopping').addEventListener('click',() => {
    window.location.href = "cart.html";
})