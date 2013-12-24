package com.ziq.linemaze.rect;

import com.ziq.linemaze.MazeModel;
import com.ziq.linemaze.MazeTile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 2D Rectangle Maze strategy.
 *
 * @author ziq
 */
public class RectMazeModel extends MazeModel {

    protected final static int[][] NEXT_POS = {
            {0, -1}, {0, -1}, {1, 0}, {1, 0}, {0, 1}, {0, 1}, {-1, 0}, {-1, 0}
    };
    protected final static int[] NEXT_EXIT = {
            5, 4, 7, 6, 1, 0, 3, 2
    };

    /**
     * The exit code of the current strategy.
     */
    private Integer exit;

    @Override
    protected void initTiles() {
        exit = 7;
        getTiles().clear();
        for (int i = 1; i <= 8; i++) {
            // top
            addTile(createBlockRectTile(i, 0, 3));
            // bottom
            addTile(createBlockRectTile(i, 9, 1));
            // left
            addTile(createBlockRectTile(0, i, 2));
            // right
            addTile(createBlockRectTile(9, i, 0));
        }

        StartRectTile startRectTile = new StartRectTile();
        startRectTile.setPosition(new ArrayList<Integer>(Arrays.asList(5, 5)));
        addTile(startRectTile);

        LineRectTile rectTile = new LineRectTile();
        rectTile.setPosition(new ArrayList<Integer>(Arrays.asList(4, 5)));
        addTile(rectTile);
    }

    private BlockRectTile createBlockRectTile(int x, int y, int direction) {
        BlockRectTile tile = new BlockRectTile();
        tile.setPosition(new ArrayList<Integer>(Arrays.asList(x, y)));
        tile.setDirection(direction);
        return tile;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Integer> nextPosition(MazeTile tile) {
        // converted from exit using game strategy
        Integer enter = NEXT_EXIT[exit];
        // point to another exit
        exit = tile.access(enter);
        if (exit != null) {
            return Arrays.asList(tile.getPosition().get(0) + NEXT_POS[exit][0], tile.getPosition().get(1) + NEXT_POS[exit][1]);
        } else {
            return null;
        }

    }

    @Override
    public MazeTile createTile(List<Integer> position) {
        LineRectTile tile = new LineRectTile();
        tile.setPosition(position);
        return tile;
    }

    public Integer getExit() {
        return exit;
    }

    public void setExit(Integer exit) {
        this.exit = exit;
    }

}
