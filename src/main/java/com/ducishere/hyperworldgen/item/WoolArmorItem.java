package toughasnails.item;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.DyedItemColor;

public class WoolArmorItem extends ArmorItem
{
    public WoolArmorItem(ArmorMaterial material, Type type, Properties properties)
    {
        super(material, type, properties);
    }

    @Override
    public void verifyComponentsAfterLoad(ItemStack stack)
    {
        if (!stack.has(DataComponents.DYED_COLOR))
        {
            // Mặc định trắng
            stack.set(DataComponents.DYED_COLOR, new DyedItemColor(0xFFFFFF, false));
        }
    }

    // Client-side: dùng tint của component để render
    @Override
    public int getColor(ItemStack stack)
    {
        DyedItemColor color = stack.get(DataComponents.DYED_COLOR);
        return color != null ? color.rgb() : 0xFFFFFF;
    }
}