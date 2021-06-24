$( document ).ready(function() {
	
	  
	
	
	
    Morris.Bar({
        element: 'morris2',
        data: [
            { year: '超级课程表', a: chaojikcb2018, b: chaojikcb2019 },
            { year: '百词斩', a:baicizhan2018, b: baicizhan2019 },
            { year: '知到', a: zhidao2018, b: zhidao2019 },
            { year: '网易有道词典', a:wangyiyoudao2018, b: wangyiyoudao2019 }
        ],
        xkey: 'year',
        ykeys: ['a', 'b'],
        labels: ['2018', '2019'],
        barRatio: 0.4,
        hideHover: 'auto',
        barColors: ['#03A9F3','#FFAA00'],
        resize: true
    });
    
    
});