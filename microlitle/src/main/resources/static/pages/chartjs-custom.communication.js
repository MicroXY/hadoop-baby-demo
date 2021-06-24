$(function () {


    var barData = {
        labels: ["QQ(2018/2019)", "微信(2018/2019)", "微博(2018/2019)","微博国际版(2018/2019)","百度贴吧(2018/2019)"],
        datasets: [
            {

                label: "2018",
                fillColor: "#ffb014",
                strokeColor: "#ffb014",
                pointColor: "#36a2eb",
                pointStrokeColor: "#36a2eb",
                pointHighlightFill: "#fff",
                pointHighlightStroke: "rgba(220,220,220,1)",
                data: [QQ2018, wechat2018, weibo2018,weboguoji2018,baidutieba2018]
            },
            {
                label: "2019",
                fillColor: "#3374dd",
                strokeColor: "#FB6D9D",
                pointColor: "#3b8bba",
                pointStrokeColor: "#ff6384",
                pointHighlightFill: "#fff",
                pointHighlightStroke: "rgba(60,141,188,1)",
                data: [QQ2019,wechat2019,weibo2019,weboguoji2019,baidutieba2019]
            }
        ]
    };

    var barChartOptions = {
        //Boolean - If we should show the scale at all
        showScale: true,
        //Boolean - Whether grid lines are shown across the chart
        scaleShowGridLines: false,
        //String - Colour of the grid lines
        scaleGridLineColor: "rgba(0,0,0,.05)",
        //Number - Width of the grid lines
        scaleGridLineWidth: 1,
        //Boolean - Whether to show horizontal lines (except X axis)
        scaleShowHorizontalLines: true,
        //Boolean - Whether to show vertical lines (except Y axis)
        scaleShowVerticalLines: true,
        //Boolean - Whether the line is curved between points
        bezierCurve: true,
        //Number - Tension of the bezier curve between points
        bezierCurveTension: 0.3,
        //Boolean - Whether to show a dot for each point
        pointDot: false,
        //Number - Radius of each point dot in pixels
        pointDotRadius: 4,
        //Number - Pixel width of point dot stroke
        pointDotStrokeWidth: 1,
        //Number - amount extra to add to the radius to cater for hit detection outside the drawn point
        pointHitDetectionRadius: 20,
        //Boolean - Whether to show a stroke for datasets
        datasetStroke: true,
        //Number - Pixel width of dataset stroke
        datasetStrokeWidth: 2,
        //Boolean - Whether to fill the dataset with a color
        datasetFill: true,
        //String - A legend template
        legendTemplate: "<ul class=\"<%=name.toLowerCase()%>-legend\"><% for (var i=0; i<datasets.length; i++){%><li><span style=\"background-color:<%=datasets[i].lineColor%>\"></span><%if(datasets[i].label){%><%=datasets[i].label%><%}%></li><%}%></ul>",
        //Boolean - whether to maintain the starting aspect ratio or not when responsive, if set to false, will take up entire container
        maintainAspectRatio: true,
        //Boolean - whether to make the chart responsive to window resizing
        responsive: true
    }


    var ctx = document.getElementById("barChart").getContext("2d");
    var barChart = new Chart(ctx);

    var barChartData = barData;
    barChartData.datasets[1].fillColor = "#3374dd";
    barChartData.datasets[1].strokeColor = "#3374dd";
    barChartData.datasets[1].pointColor = "#ff6384";
    barChartOptions.datasetFill = false;
    barChart.Bar(barChartData, barChartOptions);

 

});