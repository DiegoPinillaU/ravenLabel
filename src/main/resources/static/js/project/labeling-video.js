
const player = videojs('my-video', {
    playbackRates: [0.50, 1, 2, 4, 8, 16], // Array of playback rates
    
});

let currentScale = 1;
let currentTranslationX = 0;
let currentTranslationY = 0;
let snapRatio = 0.05;

let movementAllowed = true;
//let isPlaying = false;


// COMPONENTES UI
const video = document.getElementById('my-video_html5_api');
const videoContainer = document.getElementById('video-container');
const videoMinimapContainer = document.getElementById('video-minimap-container');
const currentZoomBoxIndicator = document.querySelector('.current-zoom-box-indicator');
const bodyTag = document.querySelector('body');
//const playbackTimeElem = document.getElementById("playback-time");
//const durationTimeElem = document.getElementById("duration-time");
//const videoSlider = document.getElementById("video-slider");
//const videoSpinner = document.querySelector(".spinner-border");
//videoSlider.value = 0;
//videoSlider.disable
//
//var player = videojs('my-video', {
//    playbackRates: [0.5, 0.75, 1, 1.25, 1.5, 2], // Array of playback rates
//});
//
//const nf = new Intl.NumberFormat("en-US", {
//	style: "decimal",
//	minimumIntegerDigits: 2,
//	maximumFractionDigits: 0
//});
//
let containerBounds = video.getBoundingClientRect();

function fitVideoZoomPan(event) {
	event.preventDefault();
	if (event.type == 'wheel'){
		currentScale += event.deltaY * -0.01;
		currentScale = Math.min(Math.max(1, currentScale), 4);
	}
	if (movementAllowed) {
		var relX = (event.x - containerBounds.left) / containerBounds.width;
		var relY = (event.y - containerBounds.top) / containerBounds.height;
		// console.log(currentTranslationX, currentTranslationY, relX, relY);

		relX = relX > 1 - snapRatio ? 1 : relX < snapRatio ? 0 : relX;
		relY = relY > 1 - snapRatio ? 1 : relY < snapRatio ? 0 : relY;
		currentTranslationX = - 100 * relX * (currentScale - 1) / currentScale;
		currentTranslationY = - 100 * relY * (currentScale - 1) / currentScale;
	}
	setTransformToVideo(currentScale, currentTranslationX, currentTranslationY);
	setZoomBoxIndicator(currentScale, relX, relY);
}


function setTransformToVideo(curScale, curTX, curTY) {
	video.style.transform = `scale(${curScale}) translate(${curTX}%, ${curTY}%)`;
}

function setZoomBoxIndicator(curScale, relX, relY) {
	currentZoomBoxIndicator.style.width = `${100 / curScale}%`;
	currentZoomBoxIndicator.style.height = `${100 / curScale}%`;
	currentZoomBoxIndicator.style.transform = `translate(${relX * (curScale - 1) * 100}%, ${relY * (curScale - 1) * 100}%)`;
}

function bodyResized() {
	containerBounds = videoContainer.getBoundingClientRect();
}

bodyTag.onresize = bodyResized;

videoContainer.addEventListener('wheel', fitVideoZoomPan);
videoContainer.addEventListener('mousemove', fitVideoZoomPan);

//videoContainer.addEventListener('mousedown', (event) => {
//	movementAllowed = !movementAllowed;
//});
//
let minimapVideo = document.createElement("video");
minimapVideo.muted = true;

function captureAndPlay(){
	if (video.captureStream) {
		minimapVideo.srcObject = video.captureStream();
		minimapVideo.play();
	} else if (video.mozcaptureStream){
		minimapVideo.srcObject = video.mozcaptureStream();
		minimapVideo.play();
	}
}
captureAndPlay();
minimapVideo.muted = true;
videoMinimapContainer.appendChild(minimapVideo);


video.oncanplay = () => {
	console.log("can play");
	captureAndPlay();
}
//
//function showLoadingSpinner(){
//	videoSpinner.style.opacity = '100%';
//}
//
//function hideLoadingSpinner(){
//	videoSpinner.style.opacity = '0%';
//}
//
//video.onwaiting = showLoadingSpinner;
//video.onloadstart = showLoadingSpinner;
//video.onprogress = showLoadingSpinner;
////video.ontimeupdate = showLoadingSpinner;
//video.onplaying = hideLoadingSpinner;
//video.oncanplay = hideLoadingSpinner;
//
//video.addEventListener('timeupdate', () => {
//	hideLoadingSpinner();
//	var min = nf.format(video.currentTime / 60);
//	var seg = nf.format(video.currentTime % 60);
//	
//	playbackTimeElem.innerHTML = `${min}:${seg}`;
//	videoSlider.value = video.currentTime;
//});
//
//video.addEventListener('loadeddata', () => {
//	hideLoadingSpinner();
//	videoSlider.max = video.duration;
//	videoSlider.value = video.currentTime;
//	var min = nf.format(video.duration / 60);
//	var seg = nf.format(video.duration % 60);
//	
//	durationTimeElem.innerHTML = `${min}:${seg}`;
//})
//
//let videoPause = (event) => {
//	console.log(event);
//	if ((event.type == 'click' && event.pointerId != -1) || (event.type == 'keypress' && event.key == ' ')){
//		if (isPlaying){
//			video.pause();
//			minimapVideo.pause();
//			playPauseBtn.innerHTML = "▶️";
//		}
//		else{
//			video.play();
//			captureAndPlay();
//			playPauseBtn.innerHTML = "⏸️";
//		}
//		isPlaying = !isPlaying;
//	}
//}
//
//videoSlider.oninput = (evt) => {
//	video.currentTime = evt.target.value;
//}
//
//playPauseBtn.addEventListener("click", videoPause);
//document.addEventListener("keypress", videoPause);