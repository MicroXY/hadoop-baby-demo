$(function () {

    var lineData = {
        labels: ["携程/美团", "去哪儿/饿了么"],
        datasets: [
            {
                label: "Example dataset",
                fillColor: "#03A9F3",
                strokeColor: "#FFAA00",
                pointColor: "#00ACAC",
                pointStrokeColor: "#fff",
                pointHighlightFill: "#fff",
                pointHighlightStroke: "#36a2eb",
                data: [aibiy, tujia ]
            },
            {
                label: "Example dataset",
                fillColor: "#00ACAC",
                strokeColor: "#03A9F3",
                pointColor: "#00ACAC",
                pointStrokeColor: "#fff",
                pointHighlightFill: "#fff",
                pointHighlightStroke: "#ff6384",
                data: [meituan, eleme]
            }
        ]
    };

    var lineOptions = {
        scaleShowGridLines: true,
        scaleGridLineColor: "rgba(0,0,0,.05)",
        scaleGridLineWidth: 1,
        bezierCurve: true,
        bezierCurveTension: 0.4,
        pointDot: true,
        pointDotRadius: 4,
        pointDotStrokeWidth: 1,
        pointHitDetectionRadius: 20,
        datasetStroke: true,
        datasetStrokeWidth: 2,
        datasetFill: false,
        responsive: true,
    };
    var ctx = document.getElementById("lineChart").getContext("2d");
    var myNewChart = new Chart(ctx).Line(lineData, lineOptions);

});