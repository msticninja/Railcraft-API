/*
 * ******************************************************************************
 *  Copyright 2011-2015 CovertJaguar
 *
 *  This work (the API) is licensed under the "MIT" License, see LICENSE.md for details.
 * ***************************************************************************
 */

package mods.railcraft.api.core;

import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;

/**
 * If you want your block to connect (or not connect) to posts, implement this
 * interface.
 * <p/>
 * The result takes priority over any other rules.
 *
 * @author CovertJaguar <http://www.railcraft.info>
 */
public interface IPostConnection {

    enum ConnectStyle {

        NONE,
        SINGLE_THICK,
        TWO_THIN,
    }

    /**
     * Return the ConnectStyle that should be used if the block at this location
     * connects to a post.
     *
     * @param world The World
     * @param pos Block Position
     * @param side  Side to connect to
     * @return true if connect
     */
    ConnectStyle connectsToPost(IBlockAccess world, BlockPos pos, EnumFacing side);

}
