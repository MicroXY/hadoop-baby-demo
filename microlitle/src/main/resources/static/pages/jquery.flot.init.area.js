$(document).ready(function () {

        var data = [[0, yixian], [1, xinyixian], [2, erxian], [3,sanxian ], [4, sixian], [5, wuxianyixia]];
        var dataset = [{
            data: data,
            color: "#03A9F3"
        }];
        var ticks = [[0, "一线城市"], [1, "新一线城市"], [2, "二线城市"], [3, "三线城市"], [4, "四线城市"], [5, "五线及以下城市"]];

        var options = {
            series: {
                lines: {
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
        $.plot($("#flot2"), dataset, options);

});