package com.raven.label.springboot.app.form.controller;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.raven.label.springboot.app.entity.DateForm;
import com.raven.label.springboot.app.entity.ReferenceSignalFile;
import com.raven.label.springboot.app.entity.VideoFile;
import com.raven.label.springboot.app.service.IVideoFileService;

@Controller
public class LabelingFormController {

	@Autowired
	private IVideoFileService videoFileService;
	
	private List<VideoFile> filteredVideoFiles;
	private List<ReferenceSignalFile> currentReferenceSignalFiles;
	private VideoFile currentVideo;
	
	@PostMapping(value = "/buscarPorFecha")
	public String buscarVideoPorFecha(Model model, RedirectAttributes redirectAttributes, @ModelAttribute DateForm dateForm) throws ParseException {
		LocalDate selectedDate = dateForm.getDate();
        // Add your logic here to process the selected date (e.g., save to a database, perform some action, etc.)
        redirectAttributes.addFlashAttribute("selectedDate", selectedDate);
        int secondsToAdd = 86399;

        LocalDateTime startDate = selectedDate.atStartOfDay();
        LocalDateTime endDate = startDate.plus(secondsToAdd, ChronoUnit.SECONDS);

		
		filteredVideoFiles = videoFileService.findVideoFilesBetweenDates(startDate, endDate);
		
		redirectAttributes.addFlashAttribute("filteredVideoFiles", filteredVideoFiles); // guarda la lista en la vista de labeling
		
		return "redirect:/labeling";
	}

	@PostMapping(value = "/loadVideoSignal")
	public String loadVideoSignal(Integer videoId, Model model, RedirectAttributes redirectAttributes) throws ParseException {
		
		currentVideo = videoFileService.findById(videoId);
		currentReferenceSignalFiles = new ArrayList<ReferenceSignalFile>();
		
		for (Object item : currentVideo.getReferenceSignalFiles()) {
			currentReferenceSignalFiles.add((ReferenceSignalFile) item);
        }
		
		
		redirectAttributes.addFlashAttribute("filteredVideoFiles", filteredVideoFiles);
		redirectAttributes.addFlashAttribute("currentReferenceSignalFiles", currentReferenceSignalFiles);
		redirectAttributes.addFlashAttribute("videoId", videoId);
		return "redirect:/labeling";
	}

}
