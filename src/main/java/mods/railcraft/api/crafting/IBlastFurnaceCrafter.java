/*------------------------------------------------------------------------------
 Copyright (c) CovertJaguar, 2011-2018

 This work (the API) is licensed under the "MIT" License,
 see LICENSE.md for details.
 -----------------------------------------------------------------------------*/

package mods.railcraft.api.crafting;

import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public interface IBlastFurnaceCrafter {
    /**
     * The default number of ticks it takes to turn an ingot of iron into an ingot of steel.
     */
    int SMELT_TIME = 1280;

    /**
     * Add a fuel source. It uses the standard Furnace cookTime for the heat value.
     *
     * @param input An object that can be converted into an Ingredient. This includes,
     *              but is not limited to Ingredients, ItemStacks, Items, Blocks, and OreTag Strings.
     */
    default IFuelBuilder newFuel(Object input) {
        return new IFuelBuilder() {};
    }

    /**
     * Begins the definition of a Blast Furnace recipe.
     *
     * @param input An object that can be converted into an Ingredient. This includes,
     *              but is not limited to Ingredients, ItemStacks, Items, Blocks, and OreTag Strings.
     */
    default IRecipeBuilder newRecipe(Object input) {
        return new IRecipeBuilder() {};
    }

    /**
     * You can remove fuels from this list, but do not add them, it will throw an UnsupportedOperationException.
     */
    default List<@NotNull ISimpleRecipe> getFuels() {
        return Collections.emptyList();
    }

    /**
     * You can remove recipes from this list, but do not add them, it will throw an UnsupportedOperationException.
     */
    default List<@NotNull IRecipe> getRecipes() {
        return Collections.emptyList();
    }

    default int getCookTime(ItemStack stack) {
        return 0;
    }

    default Optional<IRecipe> getRecipe(ItemStack stack) {
        return Optional.empty();
    }

    /**
     * Represents a blast furnace recipe.
     */
    interface IRecipe extends ISimpleRecipe {

        /**
         * Gets the output for this recipe.
         *
         * @return The output, safe to modify
         */
        ItemStack getOutput();

        /**
         * Gets the slag output for this recipe.
         */
        int getSlagOutput();
    }

    interface IRecipeBuilder extends ISimpleRecipeBuilder<IRecipeBuilder> {
        default IRecipeBuilder slagOutput(int num) {
            return this;
        }

        default IRecipeBuilder output(ItemStack output) {
            return this;
        }

        /**
         * Finalize and commit the recipe.
         */
        default void register() {}
    }

    interface IFuelBuilder extends ISimpleRecipeBuilder<IFuelBuilder> {

        /**
         * Takes an ItemStack and looks up the standard Furnace heatValue for it.
         */
        default IFuelBuilder time(ItemStack fuel) {
            return this;
        }

        /**
         * Finalize and commit the recipe.
         */
        default void register() {}
    }
}