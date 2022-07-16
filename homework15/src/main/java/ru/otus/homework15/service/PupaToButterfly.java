package ru.otus.homework15.service;

import org.springframework.stereotype.Service;
import ru.otus.homework15.model.Butterfly;
import ru.otus.homework15.model.Pupa;

@Service
public class PupaToButterfly {
    public Butterfly transform(Pupa pupa) {
        System.out.println("Transform pupa to butterfly");
        return new Butterfly(pupa.getName());
    }
}
