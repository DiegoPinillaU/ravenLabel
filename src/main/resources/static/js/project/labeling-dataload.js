var playlist = document.querySelector("table tbody");
var videoPlayer = document.getElementById("my-video");

function loadVideoCallback(){
	videoPlayer.setAttribute("src", `play/${this.value}`);
}

function populatePlaylist(jsonData){
//	videos = jsonData._embedded.videoFiles;
//	console.log(videos, videos.length);
//	playlist.innerHTML = "";
//	
//	for(var i = 0; i < videos.length; i++){
//		row = document.createElement("tr");
//		row.onclick = loadVideoCallback;
//		row.value = i;
//		cols = [videos[i].serverPath, videos[i].revisado, "placeholder", "placeholder"];
//		for(var j = 0; j < 2; j++){
//			col = document.createElement("td")
//			col.innerHTML = cols[j];
//			console.log(cols[j]);
//			row.appendChild(col);
//		}
//		playlist.appendChild(row);
//	}
}


fetch("api/videoFiles")
	.then(response => response.text())
	.then(data => populatePlaylist(JSON.parse(data)));
	