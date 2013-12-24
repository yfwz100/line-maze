package com.ziq.linemaze;

import java.util.Iterator;
import java.util.List;

/**
 * The tile of the game.
 *
 * @author ziq
 */
public interface MazeTile {

    /**
     * Get the position of the tile.
     *
     * @return the next position.
     */
    public List<Integer> getPosition();

    /**
     * Rotate once.
     */
    public void rotate();

    /**
     * Get the rotation.
     */
    public int getRotation();

    /**
     * Access the entrance and the corresponding exit.
     *
     * @param entrance the entrance.
     */
    public Integer access(Integer entrance);

    /**
     * Get if the entrance path is accessed.
     *
     * @param entrance the entrance of the path.
     * @return true if accessed.
     */
    public Boolean isAccessed(Integer entrance);

    /**
     * Iterate the pathNodes.
     *
     * @return the iterator with path.
     */
    public Iterator<Path> getPathIterator();

    /**
     * Get the exit of the path with given entrance code.
     *
     * @param entrance the entrance of the path.
     * @return true if exit
     */
    public Integer getExit(Integer entrance);

    /**
     * The path object to iterate in {@link MazeTile}
     */
    public static class Path {

        private List<Integer> exits;
        private boolean selected;

        public Path() {
        }

        public Path(List<Integer> exits, boolean selected) {
            this.exits = exits;
            this.selected = selected;
        }

        public List<Integer> getExits() {
            return exits;
        }

        public void setExits(List<Integer> exits) {
            this.exits = exits;
        }

        public boolean isSelected() {
            return selected;
        }

        public void setSelected(boolean selected) {
            this.selected = selected;
        }

        @Override
        public String toString() {
            return "Path{" +
                    "exits=" + exits +
                    ", selected=" + selected +
                    '}';
        }
    }
}
