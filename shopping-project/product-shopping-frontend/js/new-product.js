let API_URL = "http://localhost:9999/products/";
const username = localStorage.getItem('username');
const password = localStorage.getItem('password');
const token = "Basic " + btoa(username + ":" + password);

function createProduct() {
    document.getElementById('add-product').addEventListener('submit', (e) => {
        e.preventDefault();

        const brand = document.getElementById('brand').value;
        const model = document.getElementById('model').value;
        const category = document.getElementById('category').value;
        const description = document.getElementById('description').value;
        const price = parseFloat(document.getElementById('price').value);
        const rating = parseInt(document.getElementById('rating').value);
        const img_url = document.getElementById('img-url').value;

        const product = {
            brand: brand,
            model: model,
            category: category,
            description: description,
            price: price,
            rating: rating,
            imgUrl: img_url
        }

        const productId = localStorage.getItem('productId');

        if (productId) {
            updateProduct(productId, product);
        } else {
            fetch(API_URL + 'add', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': token
                },
                body: JSON.stringify(product)
            })
            .then(data => {
                console.log("Success:", data);
                alert('Product added successfully!');
                localStorage.removeItem('productId');
                window.location.href = "userProducts.html";
            })
        }
    })
}

function editProduct() {
    const urlParams = new URLSearchParams(window.location.search);
    const productId = urlParams.get('id');

    if (productId) {
        localStorage.setItem("productId", productId);

        fetch(API_URL + `${productId}`, {
            method: 'GET',
            headers: {
                'Authorization': token
            }
        })
        .then(response => response.json())
        .then(product => {
            document.getElementById('brand').value = product.brand;
            document.getElementById('model').value = product.model;
            document.getElementById('category').value = product.category;
            document.getElementById('description').value = product.description;
            document.getElementById('price').value = product.price;
            document.getElementById('rating').value = product.rating;
            document.getElementById('img-url').value = product.imgUrl;
        })
    }
}

function updateProduct(productId, product) {
    fetch(API_URL + 'update', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': token
        },
        body: JSON.stringify({
            id: productId,
            brand: product.brand,
            model: product.model,
            category: product.category,
            description: product.description,
            price: product.price,
            rating: product.rating,
            imgUrl: product.imgUrl
        })
    })
    .then(data => {
        console.log("Success:", data);
        alert('Product updated successfully!');
        localStorage.removeItem('productId');
        window.location.href = "userProducts.html";
    })
}


createProduct();
editProduct(); 

document.getElementById('shop').addEventListener('click',()=>{
    window.location.href="shop.html";
})