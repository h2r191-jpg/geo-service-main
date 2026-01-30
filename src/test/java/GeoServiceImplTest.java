
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.internal.stubbing.answers.Returns;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;

import static org.junit.jupiter.api.Assertions.*;

public class GeoServiceImplTest {
	// создаем объект
	private final GeoServiceImpl geoService = new GeoServiceImpl();

	@Test
	@DisplayName("Тест метода определения локации по ip")
	void givenRussianIpWhenByIpThenReturnsMoscow() {
		Location moscowLocation = geoService.byIp("172.0.32.11");
		assertAll(
				() -> assertEquals("Moscow", moscowLocation.getCity()),
				() -> assertEquals(Country.RUSSIA, moscowLocation.getCountry()),
				() -> assertEquals("Lenina", moscowLocation.getStreet()),
				() -> assertEquals(15, moscowLocation.getBuiling())
		);
	}

	@Test
	@DisplayName("Тест для некорректного/ нулевого адеса")
	void givenUnknownIpWhenByIpThenReturnsNull() {
		Location unknownLocation = geoService.byIp(" ");
		assertNull(unknownLocation);
	}

}
