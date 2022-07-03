package ru.otus.homework13.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.otus.homework13.model.Reader;
import ru.otus.homework13.repository.ReaderRepository;
import ru.otus.homework13.service.ReaderCurrent;

@Service
public class ReaderCurrentImpl implements ReaderCurrent {
    private final ReaderRepository readerRepository;

    @Autowired
    public ReaderCurrentImpl(ReaderRepository readerRepository) {
        this.readerRepository = readerRepository;
    }

    @Override
    public String getCurrentReader() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Reader reader = readerRepository.findByLogin(auth.getName());
        return reader.getFullname();
    }
}
