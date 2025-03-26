const addProductBtn = document.querySelector('.sub-main button');

addProductBtn.addEventListener('click', () => {
    window.location.href = "newProduct.html";
})

document.getElementById('shop').addEventListener('click',()=>{
    window.location.href="shop.html";
})

let API_URL = "http://localhost:9999/products/";
const username = localStorage.getItem('username');
const password = localStorage.getItem('password');
const token = "Basic " + btoa(username + ":" + password);

function loadOnProducts() {
    fetch(API_URL + 'getAll', {
        method: 'GET',
        headers: {
            'Authorization': token
        }
    })
        .then(response => response.json())
        .then(data => {
            console.log(data);

            let tableContent = "";
            data.forEach(element => {
                tableContent += `
                <tr><td>${element.id}</td>
                <td>${element.brand}</td>
                <td>${element.model}</td>
                <td>${element.category}</td>
                <td>
                    <img src="${element.imgUrl}" alt="${element.model}" style="width: 100px; height: 80px; object-fit: cover;">
                </td>
                <td>${element.price} $</td>
                <td>${element.rating}/5</td>
                <td>
                    <button type="button" class="btn btn-primary edit-btn" data-id="${element.id}">Edit</button>
                    <button type="button" class="btn btn-danger delete-btn" data-id=${element.id}>Delete</button>
                </td></tr>
            `
            });
            document.getElementById('products-tbody').innerHTML += tableContent;
        })
}

loadOnProducts();


document.addEventListener('click', (e) => {
    if (e.target.classList.contains('edit-btn')) {
        const productId = e.target.getAttribute('data-id');
        console.log(productId);
        
        window.location.href = `newProduct.html?id=${productId}`;
    }
});

function deleteProduct() {
    document.addEventListener('click', (e) => {
        if (e.target.classList.contains('delete-btn')) {
            const productId = e.target.getAttribute('data-id');
            if (confirm("Silmek istediyinize eminsiniz?")) {
                fetch(API_URL + productId, {
                    method: 'DELETE',
                    headers: {
                        'Authorization': token
                    }
                })
                .then(data => {
                    alert("Product deleted successfully");
                    e.target.closest('tr').remove();
                })
            }
        }
    });
}

deleteProduct();

document.querySelector('.card-shopping').addEventListener('click',() => {
    window.location.href = "cart.html";
})