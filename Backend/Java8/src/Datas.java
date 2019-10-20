import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Datas {

    public static void main(String[] args) {

        LocalDate hoje = LocalDate.now();

        System.out.println(hoje);

        LocalDate outroDia = LocalDate.of(2025, Month.JULY, 29);

        int anos = outroDia.getYear() - hoje.getYear();

        System.out.println(anos);

        Period periodo = Period.between(hoje,outroDia);

        System.out.println(periodo.getUnits());

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println(dateTimeFormatter.format(outroDia));

        LocalDateTime now = LocalDateTime.now();

        DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm");

        System.out.println(now.format(dateTimeFormatter1));
    }
}
