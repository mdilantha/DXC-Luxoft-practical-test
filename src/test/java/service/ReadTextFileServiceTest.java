package service;

import com.luxoft.exception.ProcessException;
import com.luxoft.service.ReadTextFileService;
import com.luxoft.service.impl.ReadTextFileServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ReadTextFileServiceTest {


    ReadTextFileService readTextFileService= new ReadTextFileServiceImpl();

    @Test
    public void readFileSuccessfully() {

        var result = readTextFileService.getWordByReadingFile("src/test/resources/input.txt");
        Assertions.assertThat(result).isNotNull().contains("Platon made bamboo boats.");

    }

    @Test(expected = ProcessException.class)
    public void errorThrowWhenReadingTheFile() {
        readTextFileService.getWordByReadingFile("src/test/resources/input1.txt");
    }

}
