package com.luxoft.service;

import java.util.List;

public interface WordManipulationService {

    List<String> getWordsInSentence(List<String> sentences);

    void getVowelCountInListOfWord(List<String> wordList, String outputPath);
}
