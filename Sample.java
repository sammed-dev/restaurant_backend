import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Sample {
    
    public static void main(String[] args) {
        System.out.println(LocalDate.now());
        System.out.println(LocalDateTime.now());
        System.out.println(LocalDateTime.now().toLocalDate() == LocalDate.now());
        System.out.println(LocalDateTime.now().toLocalDate().equals(LocalDate.now()));
        System.out.println("-----------");
        System.out.println(LocalDateTime.now().getHour() == 11);
    }
}
