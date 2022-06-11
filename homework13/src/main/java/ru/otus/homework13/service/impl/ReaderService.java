package ru.otus.homework13.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.otus.homework13.model.Reader;
import ru.otus.homework13.repository.ReaderRepository;

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

        throw new UsernameNotFoundException("Reader not found");
    }
}
