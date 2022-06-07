package ru.otus.homework12.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.otus.homework12.exception.NotFoundException;
import ru.otus.homework12.model.Reader;
import ru.otus.homework12.repository.ReaderRepository;

@Service
public class ReaderService implements UserDetailsService {

    private final ReaderRepository readerRepository;

    @Autowired
    public ReaderService(ReaderRepository readerRepository) {
        this.readerRepository = readerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Reader reader = readerRepository.findByLogin(username);
        if (reader != null) {
            return reader;
        }

        throw new NotFoundException();
    }
}
