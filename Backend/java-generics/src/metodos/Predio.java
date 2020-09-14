package metodos;

public class Predio extends Construcao {

  private int numero;

  public Predio(int numero) {
    this.numero = numero;
  }

  @Override
  void pintar() {
    System.out.println(String.format("Pintando o prédio %s", numero));
  }
}
