package ru.otus.homework03.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.homework03.service.InputDevice;

import java.io.InputStream;

@Service
public class InputDeviceConsole implements InputDevice {

    @Override
    public InputStream getInputStream() {
        return System.in;
    }
}
