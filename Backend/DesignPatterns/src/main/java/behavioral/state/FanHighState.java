package behavioral.state;

public class FanHighState implements State {

    private Fan fan;

    public FanHighState(Fan fan) {
        this.fan = fan;
    }

    @Override
    public void handleRequest() {
        System.out.println("Turning off fan.");
        fan.setState(fan.getFanOffState());
    }

    @Override
    public String toString() {
        return "Fan is high.";
    }
}
