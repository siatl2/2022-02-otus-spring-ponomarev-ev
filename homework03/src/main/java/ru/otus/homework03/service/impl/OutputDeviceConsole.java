package ru.otus.homework03.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.homework03.service.OutputDevice;

import java.io.PrintStream;

@Service
public class OutputDeviceConsole implements OutputDevice {
    @Override
    public PrintStream getPrintStream() {
        return System.out;
    }
}
