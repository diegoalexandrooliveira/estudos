package metodos;

public class Casa extends Construcao {

  private int numero;

  public Casa(int numero) {
    this.numero = numero;
  }

  @Override
  void pintar() {
    System.out.println(String.format("Pintando a casa %s", numero));
  }

  @Override
  public String toString() {
    return "Casa{" +
      "numero=" + numero +
      '}';
  }
}
