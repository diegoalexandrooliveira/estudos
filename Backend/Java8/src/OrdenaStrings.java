import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class OrdenaStrings {

    public static void main(String[] args) {


        List<String> palavras = new ArrayList<String>();

        palavras.add("teste 1");
        palavras.add("teste 1 teste 1");
        palavras.add("teste 1");

//        palavras.sort((string1, string2) -> {
//            if (string1.length() > string2.length())
//                return -1;
//            if (string1.length() < string2.length())
//                return 1;
//            return 0;
//        });

        palavras.sort((string1, string2) ->
                Integer.compare(string1.length(), string2.length()) * -1
        );

        palavras.sort(Comparator.comparing(String::length));


        new Thread(() -> System.out.println("Thread")).start();


        palavras.forEach(s ->
                System.out.println(s)
        );

        List<Curso> cursos = new ArrayList<>();

        cursos.add(new Curso("Java", 10));
        cursos.add(new Curso("Python", 20));
        cursos.add(new Curso("C#", 5));
        cursos.add(new Curso("Javascript", 30));

        cursos.sort(Comparator.comparing(Curso::getAlunos));

        cursos.stream()
                .filter(c -> c.getAlunos() > 10)
                .map(Curso::getAlunos)
                .forEach(System.out::println);

        cursos.stream()
                .filter(c -> c.getAlunos() > 100)
                .findFirst()
                .ifPresent(System.out::println);


    }
}
