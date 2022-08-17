package com.luxoft.model;

import lombok.Data;

import java.util.Set;

/**
 * This model include all output details for result
 */
@Data
public class OutPutDetails {

    private Integer sameLengthWordCount;
    private Integer countOfVowel;
    private Set<String> vowels;
    private Integer average;
    private Integer wordLength;

}
