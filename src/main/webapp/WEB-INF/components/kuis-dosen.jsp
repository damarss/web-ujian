<h1>Kuis</h1>
<a class="btn" href="pembuatan-kuis">+ Buat Kuis</a>
<table id="tbl-kuis">
    <tr>
        <th>No</th>
        <th>Mata Kuliah</th>
        <th>Nama Kuis</th>
        <th>Batas Pelaksanaan</th>
        <th>Action</th>
    </tr>
</table>

<script>
    var kuis = document.getElementById("tbl-kuis");
    var dataKuis = null;

    fetch("web-ujian.herokuapp.com/get-kuis")
        .then(async (response) => {
            return response.json();
        })
        .then((myJson) => {
            dataKuis = myJson
            console.log(dataKuis)
            let no = 0;
            for (i in dataKuis) {
                let date = new Date(dataKuis[i]['batasWaktu']);

                let batasWaktu = date.toLocaleDateString('id-id', {weekday:'long', day:'2-digit',
                    month:'long', year:'numeric', hour:'2-digit', minute:'2-digit'})

                kuis.innerHTML += "<tr>" +
                    "<td>" + ++no + "</td>" +
                    "<td>" + dataKuis[i]['id_matkul']['mataKuliah'] + "</td>" +
                    "<td>" + dataKuis[i]['namaKuis'] + "</td>" +
                    "<td>" + batasWaktu + "</td>" +
                    "<td><div class='action-wrapper'><a class='btn edit' href='edit-pembuatan-kuis?id_kuis=" + dataKuis[i]['id'] + "'>Edit</a><a class='btn cons' href='hapus-kuis?id_kuis=" + dataKuis[i]['id'] + "'>Hapus</a></div></td>" +
                    "</tr>"
            }
        })
        .catch((ex) => {
            console.log(ex);
        })

</script>