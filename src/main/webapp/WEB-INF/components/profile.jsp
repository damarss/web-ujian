<%-- 
    Document   : profile
    Created on : May 15, 2022, 2:26:27 PM
    Author     : Damars
--%>

<div class="profile-card">
    <img class="profile-pict" src="./assets/avatar.png" alt="profile pict">
    <p>Selamat Datang,<br><b id="user">${user.getNamaLengkap()}</b></p>
    <a class="btn logout" href="logout"><img src="./assets/keluar.png" alt="keluar"/> Keluar</a>
</div>