package com.luxoft.service.impl;

import com.luxoft.exception.ProcessException;
import com.luxoft.service.ReadTextFileService;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 *  this class handle all file read task
 */
@Slf4j
public class ReadTextFileServiceImpl implements ReadTextFileService {

    /**
     *  The method read all text inside the file
     * @param filepath
     * @return
     */
    @Override
    public List<String> getWordByReadingFile(String filepath) {

        List<String> lines;
        try {
            Path path = Paths.get(filepath);
            lines = Files.readAllLines(path);
            log.info("Data reading has been completed");
        } catch (IOException e) {
            log.error("Error has been occurred while reading the data in file");
            throw new ProcessException(e.getMessage());
        }

        return lines;
    }
}
