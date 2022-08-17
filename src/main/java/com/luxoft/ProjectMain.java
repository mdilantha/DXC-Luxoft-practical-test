package com.luxoft;

import com.luxoft.service.ReadTextFileService;
import com.luxoft.service.WordManipulationService;
import com.luxoft.service.WriteTextFileService;
import com.luxoft.service.impl.ReadTextFileServiceImpl;
import com.luxoft.service.impl.WordManipulationServiceImpl;
import com.luxoft.service.impl.WriteTextFileServiceImpl;
import lombok.extern.slf4j.Slf4j;

/**
 * starting point of the project
 */
@Slf4j
public class ProjectMain {
    public static void main(String[] args) {

        log.info("Application started");
        ReadTextFileService readTextFile = new ReadTextFileServiceImpl();
        var lineInTextFile = readTextFile.getWordByReadingFile(args[0]);

        WriteTextFileService writeTextFileService = new WriteTextFileServiceImpl();

        WordManipulationService wordManipulationService = new WordManipulationServiceImpl(writeTextFileService);
        var wordList = wordManipulationService.getWordsInSentence(lineInTextFile);
        wordManipulationService.getVowelCountInListOfWord(wordList, args[1]);
        log.info("Task has been completed");
    }
}