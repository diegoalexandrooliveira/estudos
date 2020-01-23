package behavioral.strategy;

public class StrategyDemo {


    public static void main(String[] args) {
        CreditCard creditCard = new CreditCard(new AmexStrategy());
    }
}
