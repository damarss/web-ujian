<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Buat Soal - Aplikasi CAT</title>
        <link rel="stylesheet" href="../style.css"/>
        <link rel="icon" type="image/x-icon" href="../favicon.ico">
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
    <body>
        <main>
        <form class="form-buat-soal"  action="/buat-soal" method="post">
            ${buatSoal}
            <button class="btn" type="submit" style="max-width: 500px; height: 50px;">Ajukan/Buat Soal</button>
        </form>
        </main>
        
        <script src="./script.js"></script>
    </body>
</html>
