package biggestxuan.mythic_botany_tweaker.Rune;

import biggestxuan.mythic_botany_tweaker.MythicBotanyTweaker;
import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import mythicbotany.rune.RuneRitualRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@ZenCodeType.Name(MythicBotanyTweaker.NAME+"CTRunePosition")
public class CTRunePosition {
    private final Ingredient ingredient;
    private final int x;
    private final int y;
    private final boolean consume;

    @ZenCodeType.Constructor
    public CTRunePosition(IIngredient ingredient, int x, int y, boolean consume){
        this.ingredient = ingredient.asVanillaIngredient();
        this.x = x;
        this.y = y;
        this.consume = consume;
    }

    public RuneRitualRecipe.RunePosition get(){
        return new RuneRitualRecipe.RunePosition(this.ingredient,this.x,this.y,this.consume);
    }

    @ZenCodeType.Method
    public static CTRunePosition getPosition(IIngredient ingredient,int x,int y,boolean consume){
        return new CTRunePosition(ingredient,x,y,consume);
    }
}
