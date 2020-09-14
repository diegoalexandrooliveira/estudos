package metodos;

import java.util.List;

public class Main {

  public static void main(String[] args) {

    Casa casa = new Casa(1);

    List<Casa> casas = criarLista(casa);

    System.out.println(casas);

    pintarConstrucao(casa);

  }

  static <T> List<T> criarLista(T construcao) {
    System.out.println(construcao.getClass());
    return List.of(construcao);
  }

  static <T extends Construcao> void pintarConstrucao(T construcao){
    construcao.pintar();
  }

  static void print(List<?> construcoes){
    construcoes.forEach(System.out::println);
  }

  static void pintarConstrucoes(List<? extends Construcao> construcoes){
    construcoes.forEach(Construcao::pintar);
  }
}
