package ru.otus.homework03.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.otus.homework03.exception.LanguageNotSupportException;
import ru.otus.homework03.exception.ReadCsvFileException;
import ru.otus.homework03.service.FileReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class ResourceFileReader implements FileReader {
    private final String fileQuestions;

    public ResourceFileReader(
            @Value("${questions.language}") final String language
            , @Value("${questions.file_name_en}") final String fileQuestionsEn
            , @Value("${questions.file_name_ru}") final String fileQuestionsRu) {
        if (language.equalsIgnoreCase("en")){
            this.fileQuestions = fileQuestionsEn;
        } else if (language.equalsIgnoreCase("ru")) {
            this.fileQuestions = fileQuestionsRu;
        } else {
            throw new LanguageNotSupportException("Language " + language + " not supported", new RuntimeException());
        }
    }

    @Override
    public List<String> getContentFile(){
        List<String> returnContextFile = new ArrayList<>();
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
