package ro.mihaistangaciu.demospringcloud;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.cloud.function.context.FunctionRegistration;
import org.springframework.cloud.function.context.FunctionType;
import org.springframework.cloud.function.context.FunctionalSpringApplication;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.support.GenericApplicationContext;

import java.util.function.Function;

@SpringBootConfiguration
public class DemoSpringCloudApplication implements ApplicationContextInitializer<GenericApplicationContext> {

	public static void main(String[] args) {
		FunctionalSpringApplication.run(DemoSpringCloudApplication.class, args);
	}

	public Function<String, String> reverseString() {
		return value -> new StringBuilder(value).reverse().toString();
	}

	@Override
	public void initialize(GenericApplicationContext context) {
		context.registerBean("demo", FunctionRegistration.class,
				() -> new FunctionRegistration<>(reverseString())
						.type(FunctionType.from(String.class).to(String.class)));
	}
}
