package biggestxuan.mythic_botany_tweaker.Infuser;

import biggestxuan.mythic_botany_tweaker.MythicBotanyTweaker;
import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.action.recipe.ActionAddRecipe;
import com.blamejared.crafttweaker.api.action.recipe.ActionRemoveRecipeByOutput;
import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.api.item.MCItemStackMutable;
import com.blamejared.crafttweaker.api.recipe.handler.IRecipeHandler;
import com.blamejared.crafttweaker.api.recipe.manager.base.IRecipeManager;
import mythicbotany.ModRecipes;
import mythicbotany.infuser.IInfuserRecipe;
import mythicbotany.infuser.InfuserRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeType;
import org.openzen.zencode.java.ZenCodeType;

import java.util.ArrayList;
import java.util.List;

@ZenRegister
@IRecipeHandler.For(IInfuserRecipe.class)
@ZenCodeType.Name(MythicBotanyTweaker.NAME+"Infuser")
public class CTInfuserRecipe implements IRecipeManager<IInfuserRecipe> , IRecipeHandler<IInfuserRecipe> {
    @ZenCodeType.Method
    public void addRecipe(String name, IItemStack output, IIngredient[] inputs, int mana, int fromColor, int toColor){
        name = fixRecipeName(name);
        InfuserRecipe recipe = new InfuserRecipe(MythicBotanyTweaker.rl(name),output.getInternal(),mana,fromColor,toColor,getIngredient(inputs));
        CraftTweakerAPI.apply(new ActionAddRecipe<>(this, recipe, ""));
    }

    @ZenCodeType.Method
    public void removeRecipe(IItemStack output){
        CraftTweakerAPI.apply(new ActionRemoveRecipeByOutput<>(this,output){
            @Override
            public void apply(){
                List<ResourceLocation> remove = new ArrayList<>();
                for(ResourceLocation location : getManager().getRecipes().keySet()){
                    IInfuserRecipe r = getManager().getRecipes().get(location);
                    if(r instanceof InfuserRecipe ir){
                        ItemStack stack = ir.m_8043_();
                        if(output.matches(new MCItemStackMutable(stack))){
                            remove.add(location);
                        }
                    }
                }
                remove.forEach(getManager().getRecipes()::remove);
            }
        });
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

    @Override
    public RecipeType<IInfuserRecipe> getRecipeType() {
        return ModRecipes.INFUSER;
    }

    @Override
    public String dumpToCommandString(IRecipeManager manager, IInfuserRecipe recipe) {
        return manager.getCommandString() + "MythicBotany:infusion" + recipe.getResultItem() + "[" + recipe.getIngredients() + "]";
    }
}
