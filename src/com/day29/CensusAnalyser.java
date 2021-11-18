package com.day29;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

public class CensusAnalyser {
    private static final String INDIA_CENSUS_CSV_FILE_PATH = "C:\\Users\\ashok\\IdeaProjects\\IndianCensusAnalyzer\\src\\test\\resources\\IndiaStateCensusData.csv";
    //method to load Indian State Census Information from csv file
    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
            CsvToBeanBuilder<IndiaCensusCSV> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
            csvToBeanBuilder.withType(IndiaCensusCSV.class);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            CsvToBean<IndiaCensusCSV> csvToBean = csvToBeanBuilder.build();
            Iterator<IndiaCensusCSV> censusCSVIterator = csvToBean.iterator();;
            int namOfEntries = 0;
            while (censusCSVIterator.hasNext()) {
                namOfEntries++;
                IndiaCensusCSV censusData = censusCSVIterator.next();
            }
           return namOfEntries;
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
        catch (Exception x) {
            throw new CensusAnalyserException(x.getMessage(),
                    CensusAnalyserException.ExceptionType.CSV_HEADER_PROBLEM);
        }
    }
}