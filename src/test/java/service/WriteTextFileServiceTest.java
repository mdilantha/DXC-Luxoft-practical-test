package service;

import com.luxoft.model.OutPutDetails;
import com.luxoft.service.impl.WriteTextFileServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.Set;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Files.class)
@PowerMockIgnore("jdk.internal.reflect.*")
public class WriteTextFileServiceTest {

    @InjectMocks
    WriteTextFileServiceImpl writeTextFileService;

    @Test
    public void writeInputInFileSuccessfully() throws IOException {

        PowerMockito.mockStatic(Files.class);
        PowerMockito.when(Files.write(Mockito.any(),Mockito.eq("".getBytes()),Mockito.any()))
                .thenReturn(Path.of("output.txt"));
        writeTextFileService.writeWordIntoTheFile(Collections.singletonList(createObject()), "output.txt");
    }

    private OutPutDetails createObject() {
        OutPutDetails outPutDetails = new OutPutDetails();
        outPutDetails.setCountOfVowel(4);
        outPutDetails.setSameLengthWordCount(2);
        outPutDetails.setWordLength(6);
        outPutDetails.setVowels(Set.of("a", "i", "u"));
        return outPutDetails;
    }
}
