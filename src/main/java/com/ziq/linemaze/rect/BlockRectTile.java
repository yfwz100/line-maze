package com.ziq.linemaze.rect;

import com.ziq.linemaze.MazeTile;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

/**
 * The block tile.
 *
 * @author ziq
 */
public class BlockRectTile implements MazeTile, Serializable {

    private List<Integer> position;
    /**
     * The direction of the tile.
     */
    private Integer direction;

    @Override
    public void rotate() {
    }

    @Override
    public int getRotation() {
        return 0;
    }

    @Override
    public Integer access(Integer entrance) {
        return null;
    }

    @Override
    public Boolean isAccessed(Integer entrance) {
        return true;
    }

    @Override
    public Iterator<Path> getPathIterator() {
        return null;
    }

    @Override
    public Integer getExit(Integer entrance) {
        return null;
    }

    public List<Integer> getPosition() {
        return position;
    }

    public void setPosition(List<Integer> position) {
        this.position = position;
    }

    public Integer getDirection() {
        return direction;
    }

    public void setDirection(Integer direction) {
        this.direction = direction;
    }
}
