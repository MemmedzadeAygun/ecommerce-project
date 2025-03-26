const username = localStorage.getItem('username');
const password = localStorage.getItem('password');
const token = "Basic " + btoa(username + ":" + password);

document.getElementById('send').addEventListener('click',() => {

    const name = document.getElementById('name').value;
    const email = document.getElementById('email').value;
    const phone = document.getElementById('phone').value;
    const message = document.getElementById('message').value;

    const contact = {
        name:name,
        email: email,
        phone: phone,
        message: message
    }

    fetch("http://localhost:9999/contact", {
        method: 'POST',
        headers: {
            "Content-Type": "application/json",
            "Authorization": token
        },
        body: JSON.stringify(contact)
    })
    .then(response => response.text())
    .then(data => {
        alert(data);
        document.getElementById('name').value = "";
        document.getElementById('email').value = "";
        document.getElementById('phone').value = "";
        document.getElementById('message').value = "";
    })
})