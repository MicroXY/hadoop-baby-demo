$(document).ready(function () {
	var dataSet = [
		{ label: "男", data: man, color: "#3FB7EE" },
		{ label: "女", data: female, color: "#727CB6" },
	];

	$.plot('#flot3', dataSet, {
		series: {
			pie: {
				show: true,
				combine: {
				color: '#3B8DD5',
				threshold: 0.1
				}
			}
		},
		legend: {
		show: false
		}
	});
});