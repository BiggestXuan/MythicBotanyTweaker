package biggestxuan.mythic_botany_tweaker.Infuser;

import biggestxuan.mythic_botany_tweaker.MythicBotanyTweaker;
import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import com.blamejared.crafttweaker.api.item.IIngredient;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.api.managers.IRecipeManager;
import com.blamejared.crafttweaker.impl.actions.recipes.ActionAddRecipe;
import com.blamejared.crafttweaker.impl.actions.recipes.ActionRemoveRecipeByOutput;
import com.blamejared.crafttweaker.impl.item.MCItemStackMutable;
import mythicbotany.ModRecipes;
import mythicbotany.infuser.IInfuserRecipe;
import mythicbotany.infuser.InfuserRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import org.openzen.zencode.java.ZenCodeType;

import java.util.ArrayList;
import java.util.List;

@ZenRegister
@ZenCodeType.Name(MythicBotanyTweaker.NAME+"InfuserRecipe")
public class CTInfuserRecipe implements IRecipeManager {
    @ZenCodeType.Method
    public void addRecipe(String name, IItemStack output, IIngredient[] inputs, int mana, int fromColor, int toColor){
        InfuserRecipe recipe = new InfuserRecipe(MythicBotanyTweaker.rl(name),output.getInternal(),mana,fromColor,toColor,getIngredient(inputs));
        CraftTweakerAPI.apply(new ActionAddRecipe(this,recipe,""));
    }

    @ZenCodeType.Method
    public void removeRecipe(IItemStack output){
        CraftTweakerAPI.apply(new ActionRemoveRecipeByOutput(this,output){
            @Override
            public void apply(){
                List<ResourceLocation> toRemove = new ArrayList<>();
                for(ResourceLocation location : getManager().getRecipes().keySet()) {
                    IRecipe<?> recipe = getManager().getRecipes().get(location);
                    if(recipe instanceof InfuserRecipe) {
                        InfuserRecipe recipeIR = (InfuserRecipe) recipe;
                        ItemStack recipeOutput = recipeIR.func_77571_b();
                        if(output.matches(new MCItemStackMutable(recipeOutput))) {
                            toRemove.add(location);
                        }
                    }
                }
                toRemove.forEach(getManager().getRecipes()::remove);
            }
        });
    }

    @Override
    public IRecipeType<IInfuserRecipe> getRecipeType() {
        return ModRecipes.INFUSER;
    }

    private Ingredient[] getIngredient(IIngredient[] inputs){
        List<Ingredient> cache = new ArrayList<>();
        for(IIngredient ii :inputs){
            cache.add(ii.asVanillaIngredient());
        }
        Ingredient[] out = new Ingredient[cache.size()];
        for (int i = 0; i < cache.size(); i++) {
            out[i] = cache.get(i);
        }
        return out;
    }
}
