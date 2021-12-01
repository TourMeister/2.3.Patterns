package ru.netology.domain;

import com.github.javafaker.PhoneNumber;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor

public class UserInfo {
    private final String name;
    private final PhoneNumber phone;
    private final String card;
    private final LocalDate cardExpire;
}
