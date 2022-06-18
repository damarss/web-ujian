<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Kuis - Aplikasi CAT</title>
        <link rel="stylesheet" href="style.css"/>
        <link rel="icon" type="image/x-icon" href="favicon.ico">
    </head>
    <body>
        <jsp:include page="./components/sidebar.jsp" />
        <main>
            <div class="main-container">			    
			    <jsp:include page="${main}" />
			</div>
        </main>
        
        <script>
            document.getElementById('kuis').classList.add('active');
        </script>
    </body>
</html>
