package ro.mihaistangaciu.demospringcloud.functions;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;
import ro.mihaistangaciu.demospringcloud.weather.Schema;
import ro.mihaistangaciu.demospringcloud.weather.WeatherResponse;

import java.util.function.Function;

public class Weather implements Function<String, WeatherResponse> {

    @Value("apiId")
    private String apiId;

    @Override
    public WeatherResponse apply(String s) {
        final String uri = "http://api.openweathermap.org/data/2.5/weather?q="+ s +"&appid="+ apiId +"&lang=ro&units=metric";

        RestTemplate restTemplate = new RestTemplate();
        Schema schema;
        try{
        schema =  restTemplate.getForObject(uri, Schema.class);
        }catch(Exception e){
            return null;
        }
        return transformRawData(schema);
    }

    private WeatherResponse transformRawData(Schema data){
        return new WeatherResponse(data.getWeather().get(0).getDescription(),
                data.getMain().getTemp(),
                data.getMain().getTempMin(),
                data.getMain().getTempMax(),
                data.getWeather().get(0).getIcon());
    }
}
