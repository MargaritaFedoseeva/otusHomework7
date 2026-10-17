package ru.otus.emulator;

import lombok.AllArgsConstructor;
import lombok.Getter;
@AllArgsConstructor
@Getter
public enum Emulator {
    ANDROID_12(80);

    private final int port;
}
