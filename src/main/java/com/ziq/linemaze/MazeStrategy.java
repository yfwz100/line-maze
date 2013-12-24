package com.ziq.linemaze;

import java.io.Serializable;
import java.util.List;

/**
 * The maze strategy.
 *
 * @author ziq
 */
public interface MazeStrategy {

    /**
     * Initialize the tiles.
     * To avoid null pointer exception, we didn't use the return value and use output parameter instead..
     *
     * @param model the model of the maze.
     */
    public void initTiles(MazeModel model);

    /**
     * Get the connected position and exit with the given position and exit.
     *
     * @param current the current {@link MazeTile}.
     * @return the new location.
     */
    public List<Integer> nextPosition(MazeTile current);

    /**
     * Get tile by the given position.
     *
     * @param position the position to find the tile.
     * @return the {@link MazeTile}.
     */
    public MazeTile createTile(List<Integer> position);
}
