package com.raven.label.springboot.app.html.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.raven.label.springboot.app.entity.VideoFile;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Controller
public class LabelingHtmlController {
	
	@GetMapping("/labeling")
	public String get(Model model) {
		LocalDate defaultDate = LocalDate.of(2023, 2, 24);;
		
		VideoFile videoFilePlaceholder = new VideoFile();
		
		model.addAttribute("defaultDate", defaultDate);
		model.addAttribute("videoFilePlaceholder", videoFilePlaceholder);

		return "labeling";
	}
}
