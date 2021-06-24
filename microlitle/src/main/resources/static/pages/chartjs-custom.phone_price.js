$(function () {

    var polarData = [
        {
            value: p5000,
            color: "#ff6384",
            highlight: "#F0A015",
            label: "5000+"
        },
        {
            value:p4000_5000,
            color: "#30392e",
            highlight: "#eeA2EB",
            label: "4000-5000"
        },
        {
            value:p3000_4000,
            color: "#564488",
            highlight: "#4433bb",
            label: "3000-4000"
        },
        {
            value:p2000_3000,
            color: "#6059ee",
            highlight: "#36A2EB",
            label: "2000-3000"
        },
        {
            value:p1000_2000,
            color: "#03A9F3",
            highlight: "#43b8b8",
            label: "1000-2000"
        }
    ];

    var polarOptions = {
        scaleShowLabelBackdrop: true,
        scaleBackdropColor: "rgba(255,255,255,0.75)",
        scaleBeginAtZero: true,
        scaleBackdropPaddingY: 1,
        scaleBackdropPaddingX: 1,
        scaleShowLine: true,
        segmentShowStroke: true,
        segmentStrokeColor: "#fff",
        segmentStrokeWidth: 2,
        animationSteps: 100,
        animationEasing: "easeOutBounce",
        animateRotate: true,
        animateScale: false,
        responsive: true,
    };

    var ctx = document.getElementById("polarChart").getContext("2d");
    var myNewChart = new Chart(ctx).PolarArea(polarData, polarOptions);

    
});