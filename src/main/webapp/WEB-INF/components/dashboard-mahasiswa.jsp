<h1>Dashboard Mahasiswa</h1>
<div class="dashboard-wrapper">
    <div id="piechart"></div>
    <div id="piechart2"></div>
</div>

<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>

<script type="text/javascript">
    var ujian;
    var kuis;
    var rekap;

    function loadUjian() {
        const url = "https://web-ujian.herokuapp.com/get-ujian";
        fetch(url)
            .then((response) => {
                return response.json();
            })
            .then((myJSON) => {
                ujian = myJSON;
                loadPiechartUjian();
            })
    }

    function loadPiechartUjian() {
        const url = "https://web-ujian.herokuapp.com/get-rekap-mahasiswa";
        fetch(url)
            .then((response) => {
                return response.json();
            })
            .then((myJSON) => {
                rekap = myJSON;

                let ujianTerselesaikan = 0;
                for (i in rekap) {
                    if (rekap[i].id_ujian != null) {
                        ujianTerselesaikan++;
                    }
                }

                // Load google charts
                google.charts.load('current', {'packages':['corechart']});
                google.charts.setOnLoadCallback(drawChart);

                // Draw the chart and set the chart values
                function drawChart() {
                    var data = google.visualization.arrayToDataTable([
                        ['Status', 'Frekuensi'],
                        ['Sudah Dikerjakan', ujianTerselesaikan],
                        ['Belum Dikerjakan', ujian.length - ujianTerselesaikan]
                    ]);

                    // Optional; add a title and set the width and height of the chart
                    var options = {'title':'Ujian Terselesaikan', titleTextStyle: {fontSize: 24}, 'width':450, 'height':450, backgroundColor: {fill: 'transparent'}};

                    // Display the chart inside the <div> element with id="piechart"
                    var chart = new google.visualization.PieChart(document.getElementById('piechart'));
                    chart.draw(data, options);
                }
            })
    }
    loadUjian();

    function loadKuis() {
        const url = "https://web-ujian.herokuapp.com/get-kuis";
        fetch(url)
            .then((response) => {
                return response.json();
            })
            .then((myJSON) => {
                kuis = myJSON;
                loadPieChartKuis();
            })
    }

    function loadPieChartKuis() {
        const url = "https://web-ujian.herokuapp.com/get-rekap-mahasiswa";
        fetch(url)
            .then((response) => {
                return response.json();
            })
            .then((myJSON) => {
                rekap = myJSON;

                let kuisTerselesaikan = 0;
                for (i in rekap) {
                    if (rekap[i].id_kuis != null) {
                        kuisTerselesaikan++;
                    }
                }

                // Load google charts
                google.charts.load('current', {'packages':['corechart']});
                google.charts.setOnLoadCallback(drawChart2);

                // Draw the chart and set the chart values
                function drawChart2() {
                    var data = google.visualization.arrayToDataTable([
                        ['Status', 'Frekuensi'],
                        ['Sudah Dikerjakan', kuisTerselesaikan],
                        ['Belum Dikerjakan', kuis.length - kuisTerselesaikan]
                    ]);

                    // Optional; add a title and set the width and height of the chart
                    var options = {'title':'Kuis Terselesaikan', titleTextStyle: {fontSize: 24},'width':450, 'height':450, backgroundColor: {fill: 'transparent'}};

                    // Display the chart inside the <div> element with id="piechart"
                    var chart = new google.visualization.PieChart(document.getElementById('piechart2'));
                    chart.draw(data, options);
                }
            })
    }
    loadKuis();
</script>