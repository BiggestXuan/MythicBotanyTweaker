package biggestxuan.mythic_botany_tweaker;

import com.blamejared.crafttweaker.CraftTweaker;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;

@Mod(MythicBotanyTweaker.ID)
public class MythicBotanyTweaker
{
    public static final String ID = "mythic_botany_tweaker";
    public static final String NAME = "mods.mythicbotany.";

    public MythicBotanyTweaker() {
    }

    public static ResourceLocation rl(String name){
        return new ResourceLocation(CraftTweaker.MODID,name);
    }
}
