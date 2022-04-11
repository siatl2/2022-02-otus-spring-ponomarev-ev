package ru.otus.homework04.component.impl;

import org.springframework.stereotype.Component;
import ru.otus.homework04.component.InputDevice;

import java.io.InputStream;

@Component
public class InputDeviceConsole implements InputDevice {

    @Override
    public InputStream getInputStream() {
        return System.in;
    }
}
