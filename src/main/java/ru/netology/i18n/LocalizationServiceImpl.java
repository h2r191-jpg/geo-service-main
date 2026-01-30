package ru.netology.i18n;

import ru.netology.entity.Country;

import java.util.Objects;

public class LocalizationServiceImpl implements LocalizationService {
    // метод изменеН по предложению IDEA
    public String locale(Country country) {
        if (Objects.requireNonNull(country) == Country.RUSSIA) {
            return "Добро пожаловать";
        }
        return "Welcome";
    }
}
