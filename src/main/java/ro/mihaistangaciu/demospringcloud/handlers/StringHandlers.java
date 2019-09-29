package ro.mihaistangaciu.demospringcloud.handlers;

import org.springframework.cloud.function.adapter.aws.SpringBootRequestHandler;
import ro.mihaistangaciu.demospringcloud.weather.WeatherResponse;

public class StringHandlers extends SpringBootRequestHandler<String, WeatherResponse> {
}
