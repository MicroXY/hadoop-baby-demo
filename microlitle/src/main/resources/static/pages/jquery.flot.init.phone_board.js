$(document).ready(function () {
    var data = [[0, huawei], [1, vivo], [2, oppo], [3, apple], [4, xiaomi]];
    var dataset = [{
        data: data,
        color: "#3B8DD5"
    }];
    var ticks = [[0, "华为"], [1, "VIVO"], [2, "OPPO"], [3, "APPLE"], [4, "小米"]];

    var options = {
        series: {
            bars: {
                show: true
            }
        },
        bars: {
            align: "center",
            barWidth: 0.5
        },
        xaxis: {
            ticks: ticks
        },
        legend: {
            show: false
        },
        grid: {
            color: "#AFAFAF",
            hoverable: true,
            borderWidth: 0,
            backgroundColor: '#FFF'
        },
        tooltip: true,
        tooltipOpts: {
            content: "X: %x, Y: %y",
            defaultTheme: false
        }
    };
    $.plot($("#flot1"), dataset, options);

});