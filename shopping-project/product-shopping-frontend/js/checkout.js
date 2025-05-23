let API_URL = "http://localhost:9999/orders/";
const username = localStorage.getItem('username');
const password = localStorage.getItem('password');
const token = "Basic " + btoa(username + ":" + password);

document.querySelector('.btn-danger').addEventListener('click', () => {
    const firstName = document.getElementById('firstName').value;
    const lastName = document.getElementById('lastName').value;
    const countries = document.getElementById('state').options[document.querySelector('.left-side select').selectedIndex].text;
    const city = document.getElementById('city').value;
    const address = document.getElementById('address').value;
    const zip = document.getElementById('zipCode').value;
    const tel = document.getElementById('phone').value;
    const email = document.getElementById('email').value;
    const cart_number = document.getElementById('cardNumber').value;
    const expiration_month = document.getElementById('expiryMonth').value;
    const expiration_year = document.getElementById('expiryYear').value;
    const security_code = document.getElementById('securityCode').value;

    const cartIds = JSON.parse(localStorage.getItem('cartIds'));

    const promises = cartIds.map(cartId => {
        const order = {
            cartId: cartId,
            firstName: firstName,
            lastName: lastName,
            state: countries,
            city: city,
            address: address,
            zipCode: zip,
            phone: tel,
            email: email,
            cardNumber: cart_number,
            expiryMonth: expiration_month,
            expiryYear: expiration_year,
            securityCode: security_code
        };

        return fetch(API_URL + 'add', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': token
            },
            body: JSON.stringify(order)
        });
    });

    Promise.all(promises)
        .then(responses => {
            responses.forEach(response => {
                if (response.status === 400) {
                    response.json().then(data => {
                        if (data.message) {
                            Swal.fire({
                                title: data.message,
                                icon: 'error',
                                width: '300px',
                                position: 'bottom-end',
                                toast: true, 
                                showConfirmButton: false,
                                timer: 3000, 
                                timerProgressBar: true, 
                                background: '#f8d7da',
                                color: '#721c24',
                            });
                        }

                        document.querySelectorAll('.error-message').forEach(msg => msg.remove());
                        document.querySelectorAll('input, select').forEach(input => {
                            input.style.borderColor = '';
                        });
                        if (data.validations) {
                            data.validations.forEach(error => {
                                const field = error.field;
                                const message = error.message;

                                const inputField = document.getElementById(field);

                                if (inputField) {
                                    inputField.style.borderColor = '#ced4da';
                                    const errorMessage = document.createElement('div');
                                    errorMessage.classList.add('error-message');
                                    errorMessage.style.color = 'red';
                                    errorMessage.innerText = message;
                                    errorMessage.style.fontSize = "12px";

                                    inputField.parentElement.appendChild(errorMessage);
                                }
                            });
                        }
                        // document.querySelectorAll('input, select').forEach(input => {
                        //     if (input.style.borderColor !== '#ced4da') {
                        //         input.style.borderColor = 'green';  // Set border to green if no validation error
                        //     }
                        // });
                    });
                } else if (response.status === 200) {

                    document.querySelectorAll('.error-message').forEach(msg => msg.remove());
                    document.querySelectorAll('input, select').forEach(input => {
                        input.style.borderColor = '#28a745';  
                    });

                    document.getElementById('firstName').value = "";
                    document.getElementById('lastName').value = "";
                    document.getElementById('state').selectedIndex = 0; 
                    document.getElementById('city').value = "";
                    document.getElementById('address').value = "";
                    document.getElementById('zipCode').value = "";
                    document.getElementById('phone').value = "";
                    document.getElementById('email').value = "";
                    document.getElementById('cardNumber').value = "";
                    document.getElementById('expiryMonth').value = "";
                    document.getElementById('expiryYear').value = "";
                    document.getElementById('securityCode').value = "";

                    localStorage.removeItem('cartIds');

                    document.querySelectorAll('input, select').forEach(input => {
                        input.style.borderColor = 'green'; 
                    });

                    Swal.fire({
                        title: "Order Successful!",
                        icon: "success",
                        width: '300px', 
                        position: 'bottom-end',
                        toast: true, 
                        showConfirmButton: false, 
                        timer: 3000,
                        timerProgressBar: true,
                        background: '#d4edda',
                        color: '#155724', 
                    });
                }
            });
        })
});

function getSubTotal(){
    fetch('http://localhost:9999/cards/getCards', {
        method: 'GET',
        headers: {
            'Authorization': token
        }
    })
    .then(response => response.json())
    .then(data => {

        const cartIds = JSON.parse(localStorage.getItem('cartIds'));
        let total = 0;
        cartIds.forEach(cartId => {
            let item = data.find(cart => cart.id === cartId);
            if (item) {
                total+=item.subTotal;
            }
        });
        document.getElementById('sub-total').textContent = total + "$";
        document.getElementById('total').textContent = total + "$";
    })
}

getSubTotal();