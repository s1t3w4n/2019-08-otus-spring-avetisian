import app.Attempt;
import app.AttemptImpl;
import console.Console;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@PropertySource("classpath:application.properties")
@Configuration
@ComponentScan
public class MainHW02 {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainHW02.class);

        //Attempt attempt = context.getBean(AttemptImpl.class);
        Console console = context.getBean(Console.class);
        console.print("Hello world...");
        String s = console.read();
        System.out.println(s);
        //attempt.start();
    }

    @Bean
    PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
