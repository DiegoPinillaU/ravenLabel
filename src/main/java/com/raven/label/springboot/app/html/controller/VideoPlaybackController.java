package com.raven.label.springboot.app.html.controller;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.raven.label.springboot.app.entity.VideoFile;
import com.raven.label.springboot.app.service.IVideoFileService;

/*
 * Modificado de https://www.codeproject.com/Articles/5341970/Streaming-Media-Files-in-Spring-Boot-Web-Applicati
 */

@RestController
public class VideoPlaybackController {
	
	@Autowired
	private IVideoFileService videoFileService; // para acceder a la ruta del video guardada en la BD
	
	private VideoFile currentVideo;
	
	private ArrayList<Long> parseRange(String rangeReader){
		System.out.println("rangeReader value:");
		System.out.println(rangeReader);
		
		ArrayList<Long> values = new ArrayList<Long>();

		if (rangeReader == null) {
			values.add(null);
			values.add(null);
			return values;
		}
		
		String result[] = rangeReader.split("/");
		result = result[0].split("-");
		
		Long startRange = Long.parseLong(result[0].substring(6));
		Long endRange = result.length > 1 ? Long.parseLong(result[1]) : null;
		
		values.add(startRange);
		values.add(endRange);
		
		return values;
	}
	
	@GetMapping("/play/{vid_id}")
	@ResponseBody
	public ResponseEntity<StreamingResponseBody> play(
			@PathVariable("vid_id") String vid_id,
			@RequestHeader(value = "Range", required = false) String rangeReader){
		
		byte[] buffer = new byte[40000000];
		final HttpHeaders responseHeaders = new HttpHeaders();
		StreamingResponseBody responseStream;
		
		try {
			this.currentVideo = videoFileService.findById(Integer.valueOf(vid_id));		
			if (this.currentVideo == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (NumberFormatException e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
		Path videoFilePath = Paths.get(this.currentVideo.getServerPath());
		Long videoFileSize = (long) 0;
		try {
			videoFileSize = Files.size(videoFilePath);
		} catch (IOException e){
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		ArrayList<Long> ranges = this.parseRange(rangeReader);
		Long requestedStartRange = ranges.get(0);
		Long requestedEndRange = ranges.get(1);
		Long outputStartRange = ranges.get(0) == null ? 0 : requestedStartRange;
		Long outputEndRange = ranges.get(1) == null ? outputStartRange + buffer.length - 1: requestedEndRange;
		outputEndRange = outputEndRange >= videoFileSize ? videoFileSize - 1 : outputEndRange;
		Long contentLength = outputEndRange - outputStartRange + 1;
		
		if (outputStartRange > videoFileSize) {
			return new ResponseEntity<>(HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE);
		}
		
		responseHeaders.add("Content-Type", "video/mp4");
		responseHeaders.add("Content-Length", Long.toString(contentLength));
		if (rangeReader != null) responseHeaders.add("Accept-Ranges", "bytes");
		if (rangeReader != null) {
			responseHeaders.add("Content-Range", "bytes " + outputStartRange + "-" + outputEndRange + "/" + videoFileSize);
		}
		
		final long finalPos = outputEndRange;
		responseStream = os -> {
			RandomAccessFile file = new RandomAccessFile(this.currentVideo.getServerPath(), "r");
			try {
				Long pos = outputStartRange;
				file.seek(pos);
				while (pos < finalPos) {
					file.read(buffer);
					os.write(buffer);
					pos += buffer.length;
				}
				os.flush();
			} catch (IOException e) {
				
			}
		};
		return new ResponseEntity<StreamingResponseBody>(responseStream, responseHeaders, rangeReader == null ? HttpStatus.OK : HttpStatus.PARTIAL_CONTENT);
	}
	
}
