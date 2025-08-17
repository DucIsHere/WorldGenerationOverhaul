package toughasnails.network;

import glitchcore.network.CustomPacket;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.tags.FluidTags;
import toughasnails.api.potion.TANEffects;
import toughasnails.api.thirst.IThirst;
import toughasnails.api.thirst.ThirstHelper;
import toughasnails.init.ModConfig;
import toughasnails.init.ModTags;

public class DrinkInWorldPacket implements CustomPacket<DrinkInWorldPacket>
{
    private final BlockPos pos;

    public DrinkInWorldPacket(BlockPos pos)
    {
        this.pos = pos;
    }

    // Empty ctor cho decode
    public DrinkInWorldPacket()
    {
        this.pos = BlockPos.ZERO;
    }

    @Override
    public void encode(FriendlyByteBuf buf)
    {
        buf.writeBlockPos(this.pos);
    }

    @Override
    public DrinkInWorldPacket decode(FriendlyByteBuf buf)
    {
        return new DrinkInWorldPacket(buf.readBlockPos());
    }

    @Override
    public void handle(DrinkInWorldPacket packet, Context context)
    {
        context.getPlayer().ifPresent(player -> {
            Level level = player.level();
            IThirst thirst = ThirstHelper.getThirst(player);

            // Double-check server side
            if (level.mayInteract(player, packet.pos) && level.getFluidState(packet.pos).is(FluidTags.WATER))
            {
                thirst.drink(
                    ModConfig.thirst.handDrinkingThirst,
                    (float) ModConfig.thirst.handDrinkingHydration
                );

                // Biome safe-get (Holder<Biome> trong 1.20.1 vẫn ok)
                Holder<Biome> biomeHolder = level.getBiome(packet.pos);
                Biome biome = biomeHolder.value();

                // Poison chance check
                if (level.random.nextFloat() < ModTags.Biomes.getBiomeWaterType(biomeHolder).getPoisonChance())
                {
                    player.addEffect(new MobEffectInstance(TANEffects.THIRST, 600));
                }
            }
        });
    }
}
