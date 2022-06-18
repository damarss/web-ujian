<h1>Dashboard Operator</h1>
<div id="piechart2"></div>

<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>

<script type="text/javascript">
    // Load google charts
    google.charts.load('current', {'packages':['corechart']});
    google.charts.setOnLoadCallback(drawChart);

    // Draw the chart and set the chart values
    function drawChart() {
        fetch("http://localhost:8080/get-user")
            .then((response) => {
                return response.json();
            })
            .then((myJSON) => {
                var data = google.visualization.arrayToDataTable([
                    ['Role', 'Jumlah'],
                    ['Dosen', myJSON.dosen],
                    ['Mahasiswa', myJSON.mahasiswa]
                ]);

                var options = {'title':'Komposisi User', titleTextStyle: {fontSize: 24}, 'width':450, 'height':400, backgroundColor: {fill: 'transparent'}};
                // Optional; add a title and set the width and height of the chart

                // Display the chart inside the <div> element with id="piechart"
                var chart = new google.visualization.PieChart(document.getElementById('piechart2'));
                chart.draw(data, options);
            })
    }
</script>