package biggestxuan.mythic_botany_tweaker.Rune;

import biggestxuan.mythic_botany_tweaker.MythicBotanyTweaker;
import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import com.blamejared.crafttweaker.api.item.IIngredient;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.api.managers.IRecipeManager;
import com.blamejared.crafttweaker.impl.actions.recipes.ActionAddRecipe;
import com.blamejared.crafttweaker.impl.actions.recipes.ActionRemoveRecipeByName;
import mythicbotany.ModRecipes;
import mythicbotany.rune.RuneRitualRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import org.openzen.zencode.java.ZenCodeType;

import java.util.ArrayList;
import java.util.List;

@ZenRegister
@ZenCodeType.Name(MythicBotanyTweaker.NAME+"RuneRitualRecipe")
public class CTRuneRecipe implements IRecipeManager {

    @ZenCodeType.Method
    public void addRecipe(String name, IIngredient centerRunes,CTRunePosition[] runes, int mana, int ticks, IIngredient[] inputs, IItemStack[] outputs){
        ResourceLocation id = MythicBotanyTweaker.rl(name);
        RuneRitualRecipe recipe = new RuneRitualRecipe(id,centerRunes.asVanillaIngredient(),getPosition(runes),mana,ticks,getList(inputs),getList(outputs),null,null);
        CraftTweakerAPI.apply(new ActionAddRecipe(this,recipe,""));
    }

    @ZenCodeType.Method
    public void removeRecipe(String name){
        String[] n = name.split(":");
        ResourceLocation rl = new ResourceLocation(n[0],n[1]);
        CraftTweakerAPI.apply(new ActionRemoveRecipeByName(this,rl));
    }

    @Override
    public IRecipeType<RuneRitualRecipe> getRecipeType() {
        return ModRecipes.RUNE_RITUAL;
    }

    private static List<ItemStack> getList(IItemStack[] inputs){
        List<ItemStack> stack = new ArrayList<>();
        for(IItemStack i:inputs){
            stack.add(i.getInternal());
        }
        return stack;
    }

    private static List<Ingredient> getList(IIngredient[] inputs){
        List<Ingredient> stack = new ArrayList<>();
        for(IIngredient i:inputs){
            stack.add(i.asVanillaIngredient());
        }
        return stack;
    }

    private static List<RuneRitualRecipe.RunePosition> getPosition(CTRunePosition[] runes){
        List<RuneRitualRecipe.RunePosition> out = new ArrayList<>();
        for(CTRunePosition c:runes){
            out.add(c.get());
        }
        return out;
    }
}
