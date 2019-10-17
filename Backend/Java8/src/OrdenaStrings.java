import java.util.ArrayList;
import java.util.List;

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
                Integer.compare(string1.length(), string2.length())*-1
        );

        palavras.forEach(s ->
                System.out.println(s)
        );

    }
}
