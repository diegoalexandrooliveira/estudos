package behavioral.strategy;

public class AmexStrategy implements ValidationStrategy {
    @Override
    public boolean isValid(CreditCard creditCard) {
        if (creditCard.getNumber().equals("123")) {
            return true;
        }
        return false;
    }
}
