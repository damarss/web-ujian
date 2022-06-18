<h1>Ujian</h1>
<table id="tbl-ujian">
    <tr>
        <th>No</th>
        <th>Mata Kuliah</th>
        <th>Jenis Ujian</th>
        <th>Tanggal Ujian</th>
        <th>Status</th>
        <th>Action</th>
    </tr>
</table>
<div id="jadwal">
    <form id="form-jadwal" class="form-buat" action="/atur-jadwal/">
        <input type="datetime-local" name="tanggal-ujian" id="tanggal-ujian" required>
        <div class="action-wrapper">
            <button type="reset" onclick="hideJadwal()" class="btn cons">Batal</button>
            <button class="btn" type="submit">Atur Jadwal</button>
        </div>
    </form>
</div>

<script>
    var jadwal = document.getElementById("jadwal");

    function showJadwal(id) {
        jadwal.style.display = "flex";
        document.querySelector("body").style.overflow = "hidden";
        document.getElementById("form-jadwal").action += id;
    }

    function hideJadwal() {
        jadwal.style.display = "none";
        document.querySelector("body").style.overflow = "auto";
        document.getElementById("form-jadwal").action = "atur-jadwal/";
    }
</script>

<script>
    var ujian = document.getElementById("tbl-ujian");
    var dataUjian = null;

    fetch("https://web-ujian.herokuapp.com/get-ujian")
        .then(async (response) => {
            return response.json();
        })
        .then((myJson) => {
            dataUjian = myJson
            console.log(dataUjian)
            let no = 0;
            let date;
            let tanggalUjian
            for (i in dataUjian) {
                if (dataUjian[i]['tanggalPelaksanaan'] != null) {
                    date = new Date(dataUjian[i]['tanggalPelaksanaan']);
                    tanggalUjian = date.toLocaleDateString('id-id', {weekday:'long', day:'2-digit',
                        month:'long', year:'numeric', hour:'2-digit', minute:'2-digit'})
                } else {
                    tanggalUjian = "Belum diatur";
                }

                ujian.innerHTML += "<tr>" +
                    "<td>" + ++no + "</td>" +
                    "<td>" + dataUjian[i]['id_matkul']['mataKuliah'] + "</td>" +
                    "<td>" + dataUjian[i]['jenisUjian'] + "</td>" +
                    "<td>" + tanggalUjian + "</td>" +
                    "<td><span class='" + dataUjian[i]['status'] + "'>" + dataUjian[i]['status'] + "</td>" +
                    "<td><div class='action-wrapper'><a class='btn edit' href='lihat-soal/" + dataUjian[i].id + "'>Lihat</a><a class='btn' onclick='showJadwal(" + dataUjian[i].id + ")' href='#atur-jadwal'>Atur Jadwal</a></td>" +
                    "</tr>"
            }
        })

</script>