<%-- 
    Document   : index
    Created on : May 15, 2022, 9:03:54 AM
    Author     : Damars
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Kelola - Aplikasi CAT</title>
        <link rel="stylesheet" href="style.css"/>
        <link rel="icon" type="image/x-icon" href="favicon.ico">
    </head>
    <body>
        <jsp:include page="./components/sidebar.jsp" />
        <main>
       	 	<div class="main-container">
			    <h1>Kelola User</h1>
			    <a class="btn" href="buat-user">+ Buat User</a>
			    <table>
			    	<tr>
			    		<th>No</th>
			    		<th>Username</th>
			    		<th>Nama Lengkap</th>
			    		<th>Role</th>
			    		<th>Action</th>
			    	</tr>
			    	${userList}
			    </table>

                <h1>Kelola Mata Kuliah</h1>
                <a class="btn" style="max-width: 180px" href="tambah-matkul">+ Tambah Matkul</a>
                <table>
                    <tr>
                        <th>No</th>
                        <th>Mata Kuliah</th>
                        <th>Action</th>
                    </tr>
                    ${matkulList}
                </table>
			</div>
        </main>
        
        <script>
            document.getElementById('kelola').classList.add('active');
        </script>
        <script src="./script.js"></script>
    </body>
</html>
