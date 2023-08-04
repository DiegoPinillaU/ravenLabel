package com.raven.label.springboot.app;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.raven.label.springboot.app.entity.EventoClasificado;
import com.raven.label.springboot.app.entity.VideoFile;
import com.raven.label.springboot.app.service.IVideoFileService;

@Component
public class PostgreSQLDummyDataInitializer implements CommandLineRunner{
	
	@Autowired
	private IVideoFileService videoFileService;

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
//		for (int i = 0; i < 100; i++) {
//			System.out.printf("Adding videofile %d\n", i);
//			VideoFile exampleVF = new VideoFile();
//			exampleVF.setChannel("ch1");
//			exampleVF.setId(i);
//			exampleVF.setRevisado(i % 2);
//			exampleVF.setEventosClasificados(new ArrayList<EventoClasificado>());
//			exampleVF.setServerPath("C:\\Users\\Diego\\Downloads\\test_videos\\video" + Integer.toString(i) + ".mp4");
//			videoFileService.save(exampleVF);
//		}
		
		
	}
	
	
	
	
}
