let signupName;
let signupEmail;
let signupPassword;
let signupRepeatPassword;

function signup() {
  signupName = document.getElementById("name").value;
  signupEmail = document.getElementById("email").value;
  signupPassword = document.getElementById("password").value;
  signupRepeatPassword = document.getElementById("repeatPassword").value;

  if (
    signupName === "" ||
    signupEmail === "" ||
    signupPassword === "" ||
    signupRepeatPassword === ""
  ) {
    alert("Please fill in all fields");
  } else if (signupPassword !== signupRepeatPassword) {
    alert("Passwords do not match");
  } else {
    alert("Sign up successful");
    window.location.href = "login.html";
  }
}

function login() {
  var logEmail = document.getElementById("loginEmail").value;
  var logPassword = document.getElementById("loginPassword").value;

  // console.log(logEmail, logPassword);
  if (logEmail === "" || logPassword === "") {
    alert("Please fill in all fields");
  }
  if (logEmail !== signupEmail || logPassword !== signupPassword) {
    alert("Login successful");
    window.location.href = "plans.html";
  }

  // if (logEmail === "" || logPassword === "") {
  //   alert("Please fill in all fields");
  // } else if (signupEmail !== logEmail || signupPassword !== logPassword) {
  //   alert("Invalid email or password");
  // } else {
  //   alert("Login successful");
  //   window.location.href = "plans.html";
  // }
}
function package1() {
  alert("You successfully purchased Basic package");
}

function package2() {
  alert("You successfully purchased Premium package");
}

function package3() {
  alert("You successfully purchased Family package");
}
