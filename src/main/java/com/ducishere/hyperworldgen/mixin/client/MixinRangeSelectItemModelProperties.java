package toughasnails.client;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.util.Identifier;
import toughasnails.client.item.TemperatureProperty;

public class ToughAsNailsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // Register model predicate "temperature"
        ModelPredicateProviderRegistry.register(new Identifier("temperature"), 
            (stack, world, entity, seed) -> TemperatureProperty.getValue(stack, world, entity));
    }
}
