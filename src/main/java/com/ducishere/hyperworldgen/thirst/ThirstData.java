package toughasnails.thirst;

import net.minecraft.nbt.CompoundTag;
import toughasnails.api.thirst.IThirst;
import toughasnails.init.ModConfig;
import com.ducishere.hyperworldgen.seasons.SeasonManager;
import com.ducishere.hyperworldgen.farmerdelight.FDCompat;

public class ThirstData implements IThirst
{
    public static final int DEFAULT_THIRST = 20;
    public static final float DEFAULT_HYDRATION = 2.0F;

    private int thirstLevel = DEFAULT_THIRST;
    private float hydrationLevel = DEFAULT_HYDRATION;
    private float exhaustionLevel;
    private int tickTimer;
    private int lastThirst = -99999999;
    private boolean lastHydrationZero = true;

    // --- Save/Load ---
    public void addAdditionalSaveData(ValueOutput output) {
        if(ModConfig.thirst.enableThirst){
            output.putInt("thirstLevel", this.getThirst());
            output.putInt("thirstTickTimer", this.getTickTimer());
            output.putFloat("thirstHydrationLevel", this.getHydration());
            output.putFloat("thirstExhaustionLevel", this.getExhaustion());
        } else {
            output.putInt("thirstLevel", DEFAULT_THIRST);
            output.putInt("thirstTickTimer", 0);
            output.putFloat("thirstHydrationLevel", DEFAULT_HYDRATION);
            output.putFloat("thirstExhaustionLevel", 0.0F);
        }
    }

    public void readAdditionalSaveData(ValueInput input) {
        if(ModConfig.thirst.enableThirst){
            this.setThirst(input.getInt("thirstLevel").orElse(DEFAULT_THIRST));
            this.setTickTimer(input.getInt("thirstTickTimer").orElse(0));
            this.setHydration(input.getFloatOr("thirstHydrationLevel", DEFAULT_HYDRATION));
            this.setExhaustion(input.getFloatOr("thirstExhaustionLevel", 0.0F));
        } else {
            this.setThirst(DEFAULT_THIRST);
            this.setTickTimer(0);
            this.setHydration(DEFAULT_HYDRATION);
            this.setExhaustion(0.0F);
        }
    }

    // --- Getters/Setters ---
    @Override public int getThirst() { return thirstLevel; }
    @Override public int getLastThirst() { return lastThirst; }
    @Override public int getTickTimer() { return tickTimer; }
    @Override public float getHydration() { return hydrationLevel; }
    @Override public boolean getLastHydrationZero() { return lastHydrationZero; }
    @Override public float getExhaustion() { return exhaustionLevel; }

    @Override public void setThirst(int level) { this.thirstLevel = level; }
    @Override public void addThirst(int thirst) { this.thirstLevel = Math.min(this.thirstLevel + thirst, 20); }
    @Override public void setLastThirst(int thirst) { this.lastThirst = thirst; }
    @Override public void setTickTimer(int timer) { this.tickTimer = timer; }
    @Override public void addTicks(int ticks) { this.tickTimer += ticks; }
    @Override public void setHydration(float hydration) { this.hydrationLevel = hydration; }
    @Override public void setLastHydrationZero(boolean value) { this.lastHydrationZero = value; }
    @Override public void addHydration(float hydration) { this.hydrationLevel += hydration; }
    @Override public void setExhaustion(float exhaustion) { this.exhaustionLevel = exhaustion; }
    @Override public void addExhaustion(float exhaustion) { this.exhaustionLevel += exhaustion; }

    // --- Drink logic full compat ---
    @Override
    public void drink(int thirst, float hydrationModifier) {
        if (!this.isThirsty()) return;

        // seasonal modifier
        float seasonModifier = 1.0F;
        if(SeasonManager.isSummer()) seasonModifier = 0.8F;
        else if(SeasonManager.isWinter()) seasonModifier = 1.2F;

        // Farmer's Delight bonus
        float fdBonus = 1.0F;
        if(FDCompat.isFDDrink()) fdBonus = FDCompat.getHydrationBonus();

        this.thirstLevel = Math.min(thirst + this.thirstLevel, 20);
        this.hydrationLevel = Math.min(
            this.hydrationLevel + (float)thirst * hydrationModifier * 2.0F * seasonModifier * fdBonus,
            (float)this.thirstLevel
        );
    }

    @Override
    public boolean isThirsty() { return thirstLevel < 20; }
}
