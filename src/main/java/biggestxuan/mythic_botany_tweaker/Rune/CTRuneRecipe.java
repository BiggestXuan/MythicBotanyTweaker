package biggestxuan.mythic_botany_tweaker.Rune;

import biggestxuan.mythic_botany_tweaker.MythicBotanyTweaker;
import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.action.recipe.ActionAddRecipe;
import com.blamejared.crafttweaker.api.action.recipe.ActionRemoveRecipeByName;
import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.api.recipe.handler.IRecipeHandler;
import com.blamejared.crafttweaker.api.recipe.manager.base.IRecipeManager;
import mythicbotany.ModRecipes;
import mythicbotany.rune.RuneRitualRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeType;
import org.openzen.zencode.java.ZenCodeType;

import java.util.ArrayList;
import java.util.List;

@ZenRegister
@IRecipeHandler.For(RuneRitualRecipe.class)
@ZenCodeType.Name(MythicBotanyTweaker.NAME+"RuneRitualRecipe")
public class CTRuneRecipe implements IRecipeManager<RuneRitualRecipe>, IRecipeHandler<RuneRitualRecipe> {
    @ZenCodeType.Method
    public void addRecipe(String name, IIngredient centerRunes, CTRunePosition[] runes, int mana, int ticks, IIngredient[] inputs, IItemStack[] outputs){
        name = fixRecipeName(name);
        ResourceLocation id = MythicBotanyTweaker.rl(name);
        RuneRitualRecipe recipe = new RuneRitualRecipe(id,centerRunes.asVanillaIngredient(),getPosition(runes),mana,ticks,getList(inputs),getList(outputs),null,null);
        CraftTweakerAPI.apply(new ActionAddRecipe<>(this, recipe, ""));
    }

    @ZenCodeType.Method
    public void removeRecipe(String name){
        String[] n = name.split(":");
        ResourceLocation rl = new ResourceLocation(n[0],n[1]);
        CraftTweakerAPI.apply(new ActionRemoveRecipeByName<>(this,rl));
    }

    @Override
    public String dumpToCommandString(IRecipeManager manager, RuneRitualRecipe recipe) {
        return manager.getCommandString() + "MythicBotany:rune_ritual" + recipe.getOutputs() + "[" + recipe.getInputs() + "]";
    }

    @Override
    public RecipeType<RuneRitualRecipe> getRecipeType() {
        return ModRecipes.RUNE_RITUAL;
    }

    private static List<RuneRitualRecipe.RunePosition> getPosition(CTRunePosition[] runes){
        List<RuneRitualRecipe.RunePosition> out = new ArrayList<>();
        for(CTRunePosition c:runes){
            out.add(c.get());
        }
        return out;
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
}
