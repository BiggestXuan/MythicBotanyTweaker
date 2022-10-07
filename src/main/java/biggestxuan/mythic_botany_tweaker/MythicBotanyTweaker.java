package biggestxuan.mythic_botany_tweaker;

import com.blamejared.crafttweaker.api.CraftTweakerConstants;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.common.Mod;

@Mod(MythicBotanyTweaker.ID)
public class MythicBotanyTweaker {
    public static final String ID = "mythic_botany_tweaker";
    public static final String NAME = "mods.mythicbotany.";

    public MythicBotanyTweaker() {}

    public static ResourceLocation rl(String name){
        return new ResourceLocation(CraftTweakerConstants.MOD_ID,name);
    }
}
