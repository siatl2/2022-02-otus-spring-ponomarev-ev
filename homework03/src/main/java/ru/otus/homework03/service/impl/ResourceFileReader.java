package ru.otus.homework03.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.otus.homework03.exception.ReadCsvFileException;
import ru.otus.homework03.service.FileReader;
import ru.otus.homework03.service.QuestionFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class ResourceFileReader implements FileReader {
    private final QuestionFile questionFile;

    @Autowired
    public ResourceFileReader(QuestionFile questionFile) {
        this.questionFile = questionFile;
    }

    @Override
    public List<String> getContentFile(){
        List<String> returnContextFile = new ArrayList<>();
        String fileQuestions = questionFile.getFileQuestions();
        try(
                InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(fileQuestions);
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferReader = new BufferedReader(inputStreamReader);
        ){
            String line = bufferReader.readLine();
            while(line!=null)
            {
                returnContextFile.add(line);
                line = bufferReader.readLine();
            }
        } catch (IOException e){
            throw new ReadCsvFileException("Error reading csv-file",e);
        }
        return returnContextFile;
    }
}
