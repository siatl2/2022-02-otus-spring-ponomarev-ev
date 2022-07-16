package ru.otus.homework15.service;

import org.springframework.stereotype.Service;
import ru.otus.homework15.model.Larva;
import ru.otus.homework15.model.Pupa;

@Service
public class LarvaToPupa {
    public Pupa transform(Larva larva) {
        System.out.println("Transform larva to pupa");
        return new Pupa(larva.getName());
    }
}
