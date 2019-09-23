import testing.TestingService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainHW01 {
    public static void main(String[] args) {
        System.out.println("Program started");

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");

        TestingService testingService = context.getBean(TestingService.class);

        testingService.start();
    }
}
