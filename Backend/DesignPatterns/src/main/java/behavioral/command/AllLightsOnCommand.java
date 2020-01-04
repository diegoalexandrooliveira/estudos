package behavioral.command;

import java.util.List;

public class AllLightsOnCommand implements Command {

    private List<Light> allLights;

    public AllLightsOnCommand(List<Light> allLights) {
        this.allLights = allLights;
    }

    @Override
    public void execute() {
        this.allLights.stream().filter(light -> !light.isOn()).forEach(Light::toggle);
    }
}
