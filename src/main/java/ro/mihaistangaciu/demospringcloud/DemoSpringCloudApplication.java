package ro.mihaistangaciu.demospringcloud;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.cloud.function.context.FunctionRegistration;
import org.springframework.cloud.function.context.FunctionType;
import org.springframework.cloud.function.context.FunctionalSpringApplication;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.support.GenericApplicationContext;
import ro.mihaistangaciu.demospringcloud.functions.Weather;
import ro.mihaistangaciu.demospringcloud.weather.WeatherResponse;

import java.util.function.Function;

@SpringBootConfiguration
public class DemoSpringCloudApplication implements ApplicationContextInitializer<GenericApplicationContext> {

	public static void main(String[] args) {
		FunctionalSpringApplication.run(DemoSpringCloudApplication.class, args);
	}

	public Function<String, WeatherResponse> retrieveWeather() {
		return value -> new Weather().apply(value);
	}

	@Override
	public void initialize(GenericApplicationContext context) {
		context.registerBean("weather", FunctionRegistration.class,
				() -> new FunctionRegistration<>(retrieveWeather())
						.type(FunctionType.from(String.class).to(WeatherResponse.class)));
	}
}
