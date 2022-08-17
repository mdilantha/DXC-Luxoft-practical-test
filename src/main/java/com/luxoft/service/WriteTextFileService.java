package com.luxoft.service;

import com.luxoft.model.OutPutDetails;

import java.util.List;

public interface WriteTextFileService {

    void writeWordIntoTheFile(List<OutPutDetails> outPutDetailsList, String param);
}
