<h1>Ujian</h1>
<a class="btn" href="pengajuan-soal">+ Ajukan Soal</a>
<table id="tbl-ujian">
    <tr>
        <th>No</th>
        <th>Mata Kuliah</th>
        <th>Jenis Ujian</th>
        <th>Status</th>
        <th>Action</th>
    </tr>
</table>

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
            for (i in dataUjian) {
                ujian.innerHTML += "<tr>" +
                        "<td>" + ++no + "</td>" +
                        "<td>" + dataUjian[i]['id_matkul']['mataKuliah'] + "</td>" +
                        "<td>" + dataUjian[i]['jenisUjian'] + "</td>" +
                        "<td><span class='" + dataUjian[i]['status'] + "'>" + dataUjian[i]['status'] + "</td>" +
                    "<td><div class='action-wrapper'><a class='btn edit' href='edit-pengajuan-soal?id_ujian=" + dataUjian[i]['id'] + "'>Edit</a><a class='btn cons' href='hapus-ujian?id_ujian=" + dataUjian[i].id + "'>Hapus</a></div></td>" +
                    "</tr>"
            }
        })

</script>