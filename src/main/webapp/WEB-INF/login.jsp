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

            .modal {
                display: flex;
                height: 75vh;
                width: 100vw;
                position: absolute;
                justify-content: center;
            }

            .modal-content {
                display: flex;
                flex-direction: column;
                justify-content: center;
                gap: 10px;
                align-items: center;
                background-color: #fff;
                padding: 75px;
                border-radius: 10px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.25);
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
                    <button class="btn login">Login</button>
                </div>
                <div class="modal">
                    <div class="modal-content">
                        <h3>Berikut ini akun yang dapat digunakan</h3>
                        <ul>
                            <li>Username: dosen | Password: dosen</li>
                            <li>Username: mahasiswa | password: mahasiswa</li>
                            <li>Username: mahasiswa2 | password: mahasiswa2</li>
                            <li>Username: mahasiswa3 | password: mahasiswa3</li>
                            <li>Username: mahasiswa4 | password: mahasiswa4</li>
                            <li>Username: mahasiswa5 | password: mahasiswa5</li>
                        </ul>
                        <button class="btn" type="reset" onclick="closeModal()">Tutup</button>
                    </div>
                </div>
            </form>
        </main>
        
        <script src="./script.js"></script>
        <script>
            function closeModal() {
                document.querySelector('.modal').style.display = 'none';
            }
        </script>
    </body>
</html>
