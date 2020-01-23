package behavioral.strategy;

public class AmexStrategy implements ValidationStrategy {
    @Override
    public boolean isValid(CreditCard creditCard) {
        return false;
    }
}
