package com.angrycrayfish.csvreader.service;

import com.angrycrayfish.csvreader.mapper.EventProcessor;
import com.angrycrayfish.csvreader.repository.EventRepository;
import com.opencsv.CSVReader;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Service
public class CsvReaderService {

    private EventRepository eventRepository;
    private EventProcessor eventProcessor;
    List<String> filenames = new LinkedList<>();
    public static final Character COMMA_DELIMITER = '\t';

    CsvReaderService(@NonNull EventRepository eventRepository,
                     @NonNull EventProcessor eventProcessor){
        this.eventRepository = eventRepository;
        this.eventProcessor = eventProcessor;
    }

    @PostConstruct
    private void importCsvFiles(){

        final File folder = new File("D://Development//csvreader//src//main//resources//csvs");
        listFilesForFolder(folder);

        extractEventsFromCsv();
    }

    private void extractEventsFromCsv() {
        List<List<String>> records = new ArrayList<List<String>>();

        for(String fileName : filenames) {
            try (CSVReader csvReader = new CSVReader(new FileReader("D://Development//csvreader//src//main//resources//csvs//" + fileName), COMMA_DELIMITER);) {
                String[] values = null;
                while ((values = csvReader.readNext()) != null) {
                    records.add(Arrays.asList(values));
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        persistEventsToDatabase(records);

    }

    private void persistEventsToDatabase(List<List<String>> records) {
        int counter = 1;
        for(List<String> entry : records){
            System.out.println("Processing entry " + counter);
            counter++;
            eventProcessor.mapEntryToEvent(entry);
        }
    }

    public void listFilesForFolder(final File folder) {
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
                if(fileEntry.getName().contains(".csv"))
                    filenames.add(fileEntry.getName());
            }
        }
    }

}
