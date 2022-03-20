package ru.otus.homework02.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.homework02.service.OutputDevice;

import java.io.PrintStream;

@Service
public class OutputDeviceConsole implements OutputDevice {
    @Override
    public PrintStream getPrintStream() {
        return System.out;
    }
}
