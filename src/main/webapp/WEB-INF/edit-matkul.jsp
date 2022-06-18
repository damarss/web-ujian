<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Edit Mata Kuliah - Aplikasi CAT</title>
    <link rel="stylesheet" href="../style.css"/>
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
    <form class="form-buat" action="/edit-matkul/${id}" method="post">
        <h1>Edit Mata Kuliah</h1>
        <input type="text" value="<%=request.getAttribute("mataKuliah") %>" name="mata-kuliah" id="mata-kuliah" placeholder="Mata Kuliah" required/>
        <div class="action-wrapper">
            <button class="btn" type="submit">Edit</button>
            <a class="btn cons" href="/kelola">Batal</a>
        </div>
    </form>
</main>

<script src="./script.js"></script>
</body>
</html>
