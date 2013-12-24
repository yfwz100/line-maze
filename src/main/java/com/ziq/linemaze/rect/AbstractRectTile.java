package com.ziq.linemaze.rect;

import com.ziq.linemaze.MazeTile;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * 2D Rectangle Tile.
 *
 * @author ziq
 */
public abstract class AbstractRectTile implements MazeTile, Serializable {

    private List<Integer> position = Arrays.asList(0, 0);
    private List<Integer> exits = new ArrayList<Integer>(Collections.nCopies(8, 0));
    private List<Boolean> accessed = new ArrayList<Boolean>(Collections.nCopies(8, false));
    private int rotation = 0;

    @Override
    public void rotate() {
        rotation = (rotation + 90) % 360;
    }

    private Integer tr(int i) {
        return (i - rotation / 45 + exits.size()) % exits.size();
    }

    private Integer out(int i) {
        return (i + rotation / 45) % exits.size();
    }

    @Override
    public Integer getExit(Integer entrance) {
        return out(exits.get(tr(entrance)));
    }

    @Override
    public Integer access(Integer entrance) {
        accessed.set(exits.get(tr(entrance)), true);
        accessed.set(tr(entrance), true);
        return getExit(entrance);
    }

    @Override
    public Boolean isAccessed(Integer entrance) {
        return accessed.get(tr(entrance));
    }

    @Override
    public Iterator<Path> getPathIterator() {
        return new Iterator<Path>() {

            private boolean[] bits = new boolean[8];
            private int current = 0;

            @Override
            public boolean hasNext() {
                return current < 8 && !bits[current];
            }

            @Override
            public Path next() {
                try {
                    bits[current] = true;
                    bits[exits.get(current)] = true;
                    return new Path(Arrays.asList(current, exits.get(current)), accessed.get(current));
                } finally {
                    while ((++current) < 8 && bits[current]) ;
                }

            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override
    public List<Integer> getPosition() {
        return position;
    }

    public void setPosition(List<Integer> position) {
        this.position = position;
    }

    public List<Integer> getExits() {
        return exits;
    }

    public void setExits(List<Integer> exits) {
        this.exits = exits;
    }

    public List<Boolean> getAccessed() {
        return accessed;
    }

    public void setAccessed(List<Boolean> accessed) {
        this.accessed = accessed;
    }

    @Override
    public int getRotation() {
        return rotation;
    }

    public void setRotation(int rotation) {
        this.rotation = rotation;
    }

}
