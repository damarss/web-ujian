<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Buat User - Aplikasi CAT</title>
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
            <form class="form-buat" action="/buat-user" method="post">
                <h1>Buat User</h1>
                <div id="errMsg">${errMsg}</div>
                <input type="text" name="username" id="username" placeholder="Username / NIM" value="${username}" required/>
                <input type="text" name="password" id="password" placeholder="Password" value="${password}" required/>
                <input type="text" name="nama" id="nama" placeholder="Nama Lengkap" value="${nama}" required/>
                <select name="role" required>
                	<option disabled selected value="">-- Role --</option>
                	<option value="2">Dosen</option>
                	<option value="3">Mahasiswa</option>
                </select>
                <div class="action-wrapper">
                	<button class="btn" type="submit">Buat User</button>
                	<a class="btn cons" href="/kelola">Batal</a>
                </div>
            </form>
        </main>
        
        <script src="./script.js"></script>
    </body>
</html>
