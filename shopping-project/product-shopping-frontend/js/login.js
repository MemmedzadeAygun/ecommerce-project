let API_URL = "http://localhost:9999";
let usernameInput=document.getElementById('username');
let passwordInput=document.getElementById('password');

function onLogin(event){
    event.preventDefault();
    let username=usernameInput.value;
    let password=passwordInput.value;

    let inputs = [usernameInput, passwordInput];

    let isValid = true;

    inputs.forEach(input => {
        if (input.value.trim() === '') {
            input.style.border = '1px solid red';
            isValid = false;
        } else {
            input.style.border = '1px solid green';
        }
    });

    if (!isValid) {
        alert('Zəhmət olmasa, bütün sahələri doldurun!');
        return;
    }

    let authHeader = "Basic " + btoa(username + ":" + password);

   fetch(API_URL + "/users/login",{
        method:"GET",
        headers:{
            "Authorization":authHeader
        }
    })
    .then(response=>{
        if (response.status===200) {
            localStorage.setItem('username',username);
            localStorage.setItem('password',password);
            alert("Login successfully!");
            usernameInput.value="";
            passwordInput.value="";
            window.location.href="index.html";
        }else if (response.status===401) {
            return response.text().then(text=>{
              alert("Username ve ya parol sehvdir!!!");  
            });
        }
    })
}


function getUserProfile(){
    let username=localStorage.getItem('username');
    let password=localStorage.getItem('password');

    console.log(username);
    console.log(password);
    

    fetch(API_URL + "/users/me", {
        method: "GET",
        headers: {
            "Authorization": "Basic " + btoa(username + ":" + password)
        }
    })
    .then(response=>{
        if (response.ok) {
            return response.json();
        }
    })
    .then(userDetails=>{
        console.log("User details",userDetails);
        displayUserProfile(userDetails);
        
    })
}

getUserProfile();

function displayUserProfile(userDetails){
    document.getElementById('user-name').textContent=userDetails.name;
    document.getElementById('user-surname').textContent=userDetails.surname;
    document.getElementById('user-email').textContent=userDetails.email;
    document.getElementById('user-username').textContent=userDetails.username;
}