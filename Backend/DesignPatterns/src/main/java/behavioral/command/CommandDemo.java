package behavioral.command;

import java.util.List;

public class CommandDemo {


    public static void main(String[] args) {
        Light bedroomLight = new Light();
        Light kitchenLight = new Light();
        Switch lightSwitch = new Switch();



        Command toggleCommand = new ToggleCommand(bedroomLight);
//
        lightSwitch.storeAndExecute(toggleCommand);
        lightSwitch.storeAndExecute(toggleCommand);
//        lightSwitch.storeAndExecute(toggleCommand);
//        lightSwitch.storeAndExecute(toggleCommand);

        List<Light> lights = List.of(bedroomLight, kitchenLight);

        AllLightsOnCommand allLightsOnCommand = new AllLightsOnCommand(lights);

        lightSwitch.storeAndExecute(allLightsOnCommand);

    }
}
