// function saveUser(event){
//     event.preventDefault();

//     let userName=document.getElementById("user-name").value;
//     let userSurname=document.getElementById("user-surname").value;
//     let userEmail=document.getElementById("user-email").value;
//     let userUsername=document.getElementById("user-username").value;
//     let userPassword=document.getElementById("user-password").value;

//     let userObject={
//         name:userName,
//         surname:userSurname,
//         username:userUsername,
//         password:userPassword,
//         email:userEmail
//     }

//     let request=JSON.stringify(userObject);

//     let xml=new XMLHttpRequest();
//     xml.open('POST','http://localhost:8888/users',true);
//     xml.setRequestHeader('Content-type','application/json');
//     xml.send(request);
// }


function saveUser(event) {
    event.preventDefault();

    let userName = document.getElementById("user-name").value;
    let userSurname = document.getElementById("user-surname").value;
    let userEmail = document.getElementById("user-email").value;
    let userUsername = document.getElementById("user-username").value;
    let userPassword = document.getElementById("user-password").value;

    let userObject = {
        name: userName,
        surname: userSurname,
        username: userUsername,
        password: userPassword,
        email: userEmail
    };

    fetch('http://localhost:8888/users/user', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(userObject)
    })
        .then(response => {
            if (response.status === 400) {
                return response.json().then(data => {
                    alert(data.message);
                });
            } else {
                alert("Qeydiyyat ugurla tamamlandi");

                userName.value = "";
                userSurname.value = "";
                userEmail.value = "";
                userUsername.value = "";
                userPassword.value = "";
            }
        })
}
