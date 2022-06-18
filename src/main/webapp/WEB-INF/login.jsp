<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login - Aplikasi CAT</title>
        <link rel="stylesheet" href="style.css"/>
        <link rel="icon" type="image/x-icon" href="favicon.ico">
        <style>
            body {
                grid-template-areas: "main";
                grid-template-columns: 1fr;
            }
            
            .sidebar {
                display: none;
            }
            
            main {
                display: flex;
                justify-content: center;
                align-items: center;
            }
        </style>
    </head>
    <body onload="showErrMsg()">
        <main>
            <form class="form-login" action="/login" method="post">
                <h1>LOGIN</h1>
                <div id="errMsg">${error}</div>
                <div class="form-wrapper">
                    <input type="text" name="username" placeholder="Username / NIM"/>
                    <input type="password" name="password" id="password" placeholder="Password"/>
                    <img class="reveal-btn" onclick="revealPassword(this)" src="./assets/reveal.png" alt="reveal password"/>
                    <button class="btn login" type="submit">Login</button>
                </div>
            </form>
        </main>
        
        <script src="./script.js"></script>
    </body>
</html>
