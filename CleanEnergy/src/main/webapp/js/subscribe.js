function subscribe() {
    var email = document.getElementById("email");
    if (email.value === '') {
        alert("Please fill out the field.");
    } else {
        alert("Subscribed succefully.");
        email.value = '';
    }
}