package ru.otus.homework01.service;

import org.springframework.core.io.FileSystemResource;
import ru.otus.homework01.exception.ReadCsvFileException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ReaderFile implements Reader {
    private FileSystemResource resource;
    public ReaderFile(FileSystemResource resource){
        this.resource = resource;
    }

    @Override
    public List<String> getContentFile(){
        List<String> returnContextFile = new ArrayList<>();
        try(
                InputStream inputStream = resource.getInputStream();
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
