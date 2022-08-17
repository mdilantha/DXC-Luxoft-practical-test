package service;

import com.luxoft.model.OutPutDetails;
import com.luxoft.service.WriteTextFileService;
import com.luxoft.service.impl.WordManipulationServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class WordManipulationServiceTest {

    @InjectMocks
    private WordManipulationServiceImpl wordManipulationService;

    @Mock
    private WriteTextFileService writeTextFileService;


    @Test
    public void getWordsInSentenceSuccessfully() {

        var result = wordManipulationService.getWordsInSentence(List.of("Platon made bamboo boats."));
        Assertions.assertThat(result).isNotNull().contains("Platon", "made", "bamboo", "boats");

    }

    @Test
    public void getVowelCountSuccessfullyInWord() {

        doNothing().when(writeTextFileService).writeWordIntoTheFile(Collections.singletonList(new OutPutDetails()), "path");
        wordManipulationService.getVowelCountInListOfWord(Arrays.asList("Platon", "made", "bamboo", "boats"),
                "path");
        Mockito.verify(writeTextFileService, times(1))
                .writeWordIntoTheFile(anyList(), anyString());
    }

}
