  
$(document).ready(function() {
   var sparklineChart = function() { 
	
        $('#sparkline6').sparkline([4, 7, 8, 5, 8, 9, 4, 10, 12, 7, 4, 6, 11, 12, 7, 12], {
            type: 'bar',
            height: '500',
            width: '100%',
            resize: true,
            align: 'center',
            barWidth: '10',
            barSpacing: '3',
            barColor: '#03A9F3'
        });
    
        $('#sparkline6').sparkline([4, 7, 8, 5, 8, 9, 4, 10, 12, 7, 4, 6, 11, 12, 7, 12],{
            type: 'line',
            height: '500',
            width: '100%',
            lineColor: '#FB6D9D',
            fillColor: 'transparent',
            resize: true,
            composite: true,
            highlightLineColor: 'rgba(0,0,0,.1)',
            highlightSpotColor: '#03A9F3'
        });
        
   
   
   
   }
    var sparkResize;
 
        $(window).resize(function(e) {
            clearTimeout(sparkResize);
            sparkResize = setTimeout(sparklineChart, 500);
        });
        sparklineChart();

});