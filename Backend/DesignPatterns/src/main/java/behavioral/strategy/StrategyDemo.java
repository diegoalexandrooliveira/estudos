package behavioral.strategy;

public class StrategyDemo {


    public static void main(String[] args) {
        CreditCard creditCard = new CreditCard(new AmexStrategy());

        creditCard.setCcv("400");
        creditCard.setNumber("123");

        System.out.println(creditCard.isValid());
    }
}
