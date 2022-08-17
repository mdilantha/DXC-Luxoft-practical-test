package com.luxoft.service.impl;

import com.luxoft.model.OutPutDetails;
import com.luxoft.service.WordManipulationService;
import com.luxoft.service.WriteTextFileService;

import java.util.*;
import java.util.stream.Collectors;

/**
 * This class handle all the word manipulation task
 */
public class WordManipulationServiceImpl implements WordManipulationService {

    private final WriteTextFileService writeTextFileService;

    public WordManipulationServiceImpl(WriteTextFileService writeTextFileService) {
        this.writeTextFileService = writeTextFileService;
    }


    /**
     *
     * This method remove all pullstops and space in sentence and return the list of word
     * @param sentences
     * @return List<String>
     */
    @Override
    public List<String> getWordsInSentence(List<String> sentences) {
        return sentences.stream().map(sentence -> {
                    var sentenceWithoutPullStop = sentence.replace(".", "");
                    return sentenceWithoutPullStop.split(" ");
                }).flatMap(Collection -> Arrays.stream(Collection).sequential())
                .collect(Collectors.toList());
    }

    /**
     * This method extract the details from list of word
     *
     * @param wordList
     * @param outputPath
     */
    @Override
    public void getVowelCountInListOfWord(List<String> wordList, String outputPath) {

        // word group according to the length
        Map<Integer, List<String>> similarLengthWordMap = wordList.stream().collect(Collectors
                .groupingBy(String::length));

        // manipulate the word and get vowel count , word count, vowel list and assign them in OutPutDetails object
        var listOfOutputDetails = similarLengthWordMap.entrySet().stream().map(integerListEntry -> {

            List<List<String>> vowelListOfWords = new ArrayList<>();
            integerListEntry.getValue().forEach(s -> vowelListOfWords.add(getVowelCountInOneWord(s)));

            List<String> charactersList = vowelListOfWords.stream().flatMap(Collection::stream).collect(Collectors.toList());

            Set<String> uniqueVowelSet = new HashSet<>(charactersList);
            OutPutDetails outPutDetails = new OutPutDetails();
            outPutDetails.setSameLengthWordCount(integerListEntry.getValue().size());
            outPutDetails.setCountOfVowel(charactersList.size());
            outPutDetails.setVowels(uniqueVowelSet);
            outPutDetails.setWordLength(integerListEntry.getKey());
            return outPutDetails;
        }).collect(Collectors.toList());

        writeTextFileService.writeWordIntoTheFile(listOfOutputDetails, outputPath);
    }

    /**
     *
     *  find the list of vowel in the word
     * @param word
     * @return List<String>
     */
    private List<String> getVowelCountInOneWord(String word) {
        List<String> vowelLetters = new ArrayList<>();
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == 'a' || word.charAt(i) == 'e' ||
                    word.charAt(i) == 'i' || word.charAt(i) == 'o' ||
                    word.charAt(i) == 'u') {
                vowelLetters.add(String.valueOf(word.charAt(i)));
            }

        }
        return vowelLetters;
    }
}
