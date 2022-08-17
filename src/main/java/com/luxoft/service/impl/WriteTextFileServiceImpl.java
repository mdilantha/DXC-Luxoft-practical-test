package com.luxoft.service.impl;

import com.luxoft.exception.ProcessException;
import com.luxoft.model.OutPutDetails;
import com.luxoft.service.WriteTextFileService;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

@Slf4j
public class WriteTextFileServiceImpl implements WriteTextFileService {

    /**
     *
     * This method handles writing part of the file
     * @param outPutDetailsList
     * @param param
     */
    @Override
    public void writeWordIntoTheFile(List<OutPutDetails> outPutDetailsList, String param) {

        outPutDetailsList.forEach(outPutDetails -> {
            var outputString = convertObjectToDesiredOutput(outPutDetails);
            writeStringToFile(outputString, param);
        });
        log.info("Data writing to the file has been completed");
    }

    /**
     *  This method perform file writing part
     * @param outputString
     * @param param
     */
    private void writeStringToFile(String outputString, String param) {
        try {
            Files.write(Path.of(param), outputString.getBytes(), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
        } catch (IOException e) {
            log.error("Error has been occurred while writing data to the file", e);
            throw new ProcessException(e.getMessage());
        }
    }

    /**
     * This method return correct text format to input in the file
     * @param outPutDetails
     * @return String
     */
    private String convertObjectToDesiredOutput(OutPutDetails outPutDetails) {

        var modifiedString = String.join(", ", outPutDetails.getVowels());
        NumberFormat numberFormat = new DecimalFormat("####.#");

        var result = new StringBuilder().append("(").append("{").append(modifiedString).append(",")
                .append(" ").append(outPutDetails.getWordLength()).append(")").append(" ").append("->").append(" ")
                .append(numberFormat.format((double) outPutDetails.getCountOfVowel() / outPutDetails.getSameLengthWordCount()))
                .append(System.lineSeparator());
        return result.toString();
    }
}
