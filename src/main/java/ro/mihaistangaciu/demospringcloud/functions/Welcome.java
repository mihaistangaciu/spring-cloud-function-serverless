package ro.mihaistangaciu.demospringcloud.functions;

import java.util.function.Function;

public class Welcome implements Function<String, String> {

    @Override
    public String apply(String s) {
        return "Welcome to Spring Cloud Functions " + s + "! ";
    }
}
