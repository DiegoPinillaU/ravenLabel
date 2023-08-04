/**
 * 
 */

const plotList = document.querySelectorAll(".plots");

let layout = {
      showlegend: false, // Hide the legend
      margin: { t: 0, r: 0, b: 0, l: 0 }, // Set all margins to 0
      yaxis: {
        title: 'Amplitud'
      },
      height: 120 // Set the fixed height in pixels
    };

function getRandomDataPoints(count, minY, maxY, constantX) {
  const data = [];
  for (let i = 0; i < count; i++) {
    const randomY = Math.random() * (maxY - minY) + minY;
    data.push({ x: i, y: randomY });
  }
  return data;
}


for (var i = 0; i < plotList.length; i++){
	
		// Generate 50 random Y-axis values between 0 and 100 while keeping X constant at 5
	var randomData = getRandomDataPoints(50, 0, 100, 5);
	
	// Scatter plot data
	var data = [
	  {
	    y: randomData.map(point => point.y),
	    type: 'scatter'
	  }
	];
	var pltContainer = plotList[i];
	var referenceSignalId = pltContainer.dataset.id;

	Plotly.newPlot('plot' + i, data, layout);
	
	(function (currentIndex, id) {
        fetch("/getReferenceSignalFile/" + id)
            .then(response => response.text())
            .then(csvData => {
                // Process the CSV data as needed
                var list = csvData.split("\n");
                var values = [];

                for (j in list) {
                    values.push(parseFloat(list[j].slice(1, -1)));
                }
                console.log(values);

                Plotly.restyle('plot' + currentIndex, {
                    y: [values]
                });
            })
            .catch(error => console.error('Error fetching CSV:', error));
    })(i, referenceSignalId);
}