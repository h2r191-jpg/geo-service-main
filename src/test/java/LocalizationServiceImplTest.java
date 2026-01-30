
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocalizationServiceImplTest {
    // создаем объект
    private final LocalizationServiceImpl locService = new LocalizationServiceImpl();

    @Test
	@DisplayName("Тест локализации для России")
    void givenRussiaCountryWhenLocaleThenReturnsRussianMessage() {
        assertEquals("Добро пожаловать", locService.locale(Country.RUSSIA));

    }

    @Test
	@DisplayName("Тест для других локализаций")
    void givenGermanyCountryWhenLocaleThenReturnsEnglishMessage() {
        assertEquals("Welcome", locService.locale(Country.GERMANY));
    }


}
