import org.junit.jupiter.api.DisplayName;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import org.junit.jupiter.api.Test;
import ru.netology.i18n.LocalizationService;
import ru.netology.sender.MessageSender;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;

import static ru.netology.sender.MessageSenderImpl.IP_ADDRESS_HEADER;

public class MessageSenderImplTest {

    @Test
    @DisplayName("Тест для Российского сегмента")
    void givenRussianIpWhenSendThenReturnsRussianMessage() {
        GeoService geoService = Mockito.mock(GeoService.class); //заглушку для GeoService
        LocalizationService localizationService = Mockito.mock(LocalizationService.class); // заглушку для LocalizationService

        // настройка поведения заглушек
        Mockito.when(geoService.byIp(anyString()))
                .thenReturn(new Location("", Country.RUSSIA, "", 0));
        Mockito.when(localizationService.locale(Country.RUSSIA))
                .thenReturn("Добро пожаловать");

        MessageSender sender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<>();
        headers.put(IP_ADDRESS_HEADER, "192.168.0.169");

        String result = sender.send(headers);

        assertEquals("Добро пожаловать", result);
    }

    @Test
    @DisplayName("Тест для Стороннего сегмента")
    void givenUsaIpWhenSendThenReturnsEnglishMessage() {
        GeoService geoService = Mockito.mock(GeoService.class); //заглушку для GeoService
        LocalizationService localizationService = Mockito.mock(LocalizationService.class); // заглушку для LocalizationService

        // настройка поведения заглушек
        Mockito.when(geoService.byIp(anyString()))
                .thenReturn(new Location("", Country.GERMANY, "", 0));
        Mockito.when(localizationService.locale(Country.GERMANY))
                .thenReturn("Welcome");

        MessageSender sender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<>();
        headers.put(IP_ADDRESS_HEADER, "62.208.64.0");

        String result = sender.send(headers);

        assertEquals("Welcome", result);
    }

    @Test
    @DisplayName("Тест на пустой IP")
    void givenEmptyIpWhenSendThenReturnsDefaultEnglishMessage() {
        GeoService geoService = Mockito.mock(GeoService.class); //заглушку для GeoService
        LocalizationService localizationService = Mockito.mock(LocalizationService.class); // заглушку для LocalizationService

        // настройка поведения заглушек
        Mockito.when(localizationService.locale(Country.USA))
                .thenReturn("Welcome");

        MessageSender sender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<>();
        headers.put(IP_ADDRESS_HEADER, ""); // пустое значение IP

        String result = sender.send(headers);

        assertEquals("Welcome", result);

    }

}



