package com.raven.label.springboot.app.html.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DownloadManagerController {

	@GetMapping("/download-manager")
	public String downloadManager() {
		return "download-manager";
	}
	
}
