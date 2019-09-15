import console.ConsoleService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainHW01 {
    public static void main(String[] args) {
        System.out.println("Program started");

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");

        ConsoleService console = context.getBean(ConsoleService.class);

        String name = console.askName();
        String surname = console.askSurname();
        int result = console.askQuestions();
        System.out.println("Dear " + surname + " " + name + ". " + "Your result is: " + result + "%");
    }
}
