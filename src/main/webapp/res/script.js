/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


function revealPassword(btn) {
    var pass = document.getElementById('password');
    if (pass.type == 'password') {
        pass.type = 'text';
        btn.setAttribute('src', './assets/unreveal.png');
        btn.setAttribute('alt', 'unreveal');
    } else {
        pass.type = 'password';
        btn.setAttribute('src', './assets/reveal.png');
        btn.setAttribute('alt', 'reveal');
    }
}

function showErrMsg() {
	errMsg = document.getElementById('errMsg');
	
	if (errMsg.innerHTML == "") {
		errMsg.style.display = "none";
	} else {
		errMsg.style.display = "block";
	}
}