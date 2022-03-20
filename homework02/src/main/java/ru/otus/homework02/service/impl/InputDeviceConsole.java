package ru.otus.homework02.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.homework02.service.InputDevice;

import java.io.InputStream;

@Service
public class InputDeviceConsole implements InputDevice {

    @Override
    public InputStream getInputStream() {
        return System.in;
    }
}
