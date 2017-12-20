package ipsis.woot.farming;

import ipsis.Woot;
import ipsis.woot.configuration.EnumConfigKey;
import ipsis.woot.oss.LogHelper;
import ipsis.woot.power.storage.IPowerStation;

public class SimpleRecipeProgressTracker implements IRecipeProgressTracker {

    private IPowerStation powerStation;
    private PowerRecipe powerRecipe;
    private int consumedPower = 0;

    /**
     * IRecipeProgressTracker
     */
    public void tick() {

        int consumed = powerStation.consume(powerRecipe.getPowerPerTick());
        if (Woot.wootConfiguration.getBoolean(EnumConfigKey.STRICT_POWER)) {
            if (consumed >= powerRecipe.getPowerPerTick())
                consumedPower += consumed;
        } else {
            consumedPower += consumed;
        }
    }

    public boolean isComplete() {

        return consumedPower >= powerRecipe.getTotalPower();
    }

    public void reset() {

        consumedPower = 0;
    }

    public void setPowerRecipe(PowerRecipe powerRecipe) {

        this.powerRecipe = powerRecipe;
        consumedPower = 0;
    }

    public void setPowerStation(IPowerStation powerStation) {

        this.powerStation = powerStation;
    }

    @Override
    public int getConsumedPower() {

        return consumedPower;
    }

    @Override
    public void setConsumedPower(int power) {

        this.consumedPower = power;
    }


}
