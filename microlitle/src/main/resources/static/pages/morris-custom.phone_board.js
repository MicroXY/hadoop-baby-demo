$( document ).ready(function() {
	
	  Morris.Area({
        element: 'morris1',
        data: [{
                    period: '华为',
                    huawei: huawei,
                    vivo: 0,
                    oppo: 0,
                    apple: 0,
                    xiaomi: 0
                }, {
                    period: 'VIVO',
                    huawei: 0,
                    vivo: 0,
                    oppo: 0,
                    apple: 0,
                    xiaomi: 0
                }, {
                    period: 'OPPO',
                    huawei: 0,
                    vivo: 0,
                    oppo: oppo,
                    apple: 0,
                    xiaomi: 0
                }, {
                    period: 'APPLE',
                    huawei: 0,
                    vivo: 0,
                    oppo: 0,
                    apple: apple,
                    xiaomi: 0
                }, {
                    period: '小米',
                    huawei: 0,
                    vivo: 0,
                    oppo: 0,
                    apple: 0,
                    xiaomi: 0
                }
                ],
                lineColors: ['#F9C851', '#01c0c8', '#D5EEE9','#5EEE90','#a1c0c8'],
                xkey: 'period',
                ykeys: ['huawei', 'vivo', 'oppo','apple','xiaomi'],
                labels: ['huawei', 'vivo', 'oppo','apple','xiaomi'],
                pointSize: 0,
                lineWidth: 0,
                resize:true,
                fillOpacity: 0.9,
                behaveLikeLine: true,
                gridLineColor: '#e0e0e0',
                hideHover: 'auto'
        
    });
	
});