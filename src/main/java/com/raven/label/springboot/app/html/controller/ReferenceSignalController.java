package com.raven.label.springboot.app.html.controller;

import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import com.raven.label.springboot.app.dao.IReferenceSignalFileDao;
import com.raven.label.springboot.app.entity.ReferenceSignalFile;

@RestController
@RequestMapping("/getReferenceSignalFile")
public class ReferenceSignalController {
	
	@Autowired
	private IReferenceSignalFileDao referenceSignalFiles;
	
	private Optional<ReferenceSignalFile> refSignal;
	
	
	@GetMapping(value = "/{id}", produces = "text/csv")
	public ResponseEntity<String> getReferenceSignalFile(@PathVariable("id") String id) {
		try {
			List<String[]> csvData = null;
			try {
				this.refSignal = referenceSignalFiles.findById(Integer.valueOf(id));
			} catch (NumberFormatException e) {
				return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
			}
			try {
				csvData = readCsvFile(refSignal.get().getServerPath()); // Read CSV data from the file				
			} catch (NoSuchElementException e) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

            // Process the data as needed
            // In this example, we will simply return the same CSV data as the response

            StringWriter writer = new StringWriter();
            CSVWriter csvWriter = new CSVWriter(writer);

            // Write the CSV data to the response
            csvWriter.writeAll(csvData);
            csvWriter.close();

            // Set the CSV content in the response body
            String csvContent = writer.toString();

            // Set response headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.TEXT_PLAIN);
//            headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=students.csv");			
			
			return new ResponseEntity<>(csvContent, headers, HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<>("Error occurred while processing the CSV file.", HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
    private List<String[]> readCsvFile(String fileName) throws IOException {
        List<String[]> csvData = new ArrayList<>();

        try (CSVReader csvReader = new CSVReader(new FileReader(fileName))) {
            String[] nextLine;
            while ((nextLine = csvReader.readNext()) != null) {
                csvData.add(nextLine);
            }
        }catch (CsvValidationException e) {
        	e.printStackTrace();
        	return null;
        }

        return csvData;
    }
}
