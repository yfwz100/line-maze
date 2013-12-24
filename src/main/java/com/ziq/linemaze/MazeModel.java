package com.ziq.linemaze;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The game logic framework.
 * <p>It encapsulates a tile maze game model framework. The model mainly contains the following public action:</p>
 * <ul>
 * <li>{@link #next()}: fix the current tile and move the cursor forward.</li>
 * <li>{@link #rotate()}: rotateTile the current tile.</li>
 * </ul>
 * <p>The main logic part is left for subclass to implement. </p>
 *
 * @author yfwz100
 */
public abstract class MazeModel implements Serializable {

    /**
     * The stateCallbacks of the model, used to introspect the internal of model.
     * The implementation is similar to a property change listener which listens the changes to the state property. However, it's different from the listeners in that it will notify any changes to the state instead of notifying the changes of state.
     *
     * @author yfwz100
     */
    public static interface StateCallback {

        /**
         * The state change stateCallbacks.
         *
         * @param state the state of model.
         */
        public void onState(int state);
    }

    /**
     * The state of rotating current tile.
     */
    public static final int STATE_ROTATE = 3;

    /**
     * The state of next step.
     */
    public static final int STATE_NEXT = 2;

    /**
     * The state of start.
     */
    public static final int STATE_START = 1;

    /**
     * The state of end.
     */
    public static final int STATE_END = 0;

    /**
     * The property 'state'.
     */
    public static final String PROPERTY_STATE = "state";

    /**
     * The property 'currentTile'.
     */
    public static final String PROPERTY_CURRENT_TILE = "currentTile";

    /**
     * The property 'pathNodes'.
     */
    public static final String PROPERTY_PATH_NODES = "pathNodes";

    /**
     * The property change notifier.
     */
    protected PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    /**
     * The inner map representation of tiles.
     * The key of the tiles is the position, usually presented as form (x,y).
     */
    private final Map<List, MazeTile> tiles = new HashMap<List, MazeTile>();

    /**
     * The state of the model.
     */
    private int state = 0;

    /**
     * The current control tile.
     */
    private MazeTile currentTile;

    /**
     * The pathNodes of the maze.
     */
    private int pathNodes = 0;

    /**
     * The stateCallbacks of the model.
     */
    private Set<StateCallback> stateCallbacks = new HashSet<StateCallback>();

    /**
     * Create the model with the strategy.
     */
    public MazeModel() {
    }

    /**
     * Get the tile at position (x, y).
     *
     * @param position the position vector.
     * @return the {@link MazeTile}
     */
    public MazeTile getTile(List position) {
        return tiles.get(position);
    }

    /**
     * Add tile to the game.
     *
     * @param tile the tile.
     */
    public void addTile(MazeTile tile) {
        tiles.put(tile.getPosition(), tile);
        setCurrentTile(tile);
    }

    /**
     * The next step method.
     */
    public void next() {
        // if the game state is not RUNNING, ignore.
        if (state <= STATE_END) return;

        int originState = state;
        setState(STATE_NEXT);

        // notify the current path node changes.
        setPathNodes(pathNodes + 1);

        List<Integer> pos = nextPosition(getCurrentTile());
        if (pos != null) {
            MazeTile tile = getTile(pos);

            if (tile != null) {
                // notify the current tile.
                setCurrentTile(tile);
                next();
            } else {
                addTile(createTile(pos));
            }

            if (originState == STATE_START) {
                setState(STATE_START);
            }
        } else {
            // notify the end of game.
            setState(STATE_END);
        }
    }

    /**
     * Get current tiles of the maze.
     *
     * @return the Map with position list as key and {@link MazeTile} as value.
     */
    public final Map<List, MazeTile> getTiles() {
        return tiles;
    }

    /**
     * Get the state of the model.
     *
     * @return the state of model.
     */
    public int getState() {
        return state;
    }

    /**
     * Set the state of the model.
     *
     * @param state the state of model.
     */
    public void setState(int state) {
        propertyChangeSupport.firePropertyChange(PROPERTY_STATE, this.state, this.state = state);
        notifyStateCallback(state);
    }

    /**
     * Get the current tile of the maze.
     *
     * @return the {@link MazeTile}
     */
    public MazeTile getCurrentTile() {
        return currentTile;
    }

    /**
     * Set the current tile of the maze.
     *
     * @param currentTile the {@link MazeTile}
     */
    public void setCurrentTile(MazeTile currentTile) {
        propertyChangeSupport.firePropertyChange(PROPERTY_CURRENT_TILE, this.currentTile, this.currentTile = currentTile);
    }

    public void rotate() {
        this.currentTile.rotate();
        notifyStateCallback(STATE_ROTATE);
    }

    /**
     * Get the number of path nodes.
     *
     * @return the number of path nodes currently collected.
     */
    public int getPathNodes() {
        return pathNodes;
    }

    /**
     * Set the number of path nodes.
     *
     * @param pathNodes the number of path nodes currently collected.
     */
    public void setPathNodes(int pathNodes) {
        propertyChangeSupport.firePropertyChange(PROPERTY_PATH_NODES, this.pathNodes, this.pathNodes = pathNodes);
    }

    /**
     * @see PropertyChangeSupport#addPropertyChangeListener(java.beans.PropertyChangeListener)
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    /**
     * @see PropertyChangeSupport#removePropertyChangeListener(String, java.beans.PropertyChangeListener)
     */
    public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(propertyName, listener);
    }

    /**
     * @see java.beans.PropertyChangeSupport#getPropertyChangeListeners()
     */
    public PropertyChangeListener[] getPropertyChangeListeners() {
        return propertyChangeSupport.getPropertyChangeListeners();
    }

    /**
     * @see PropertyChangeSupport#addPropertyChangeListener(String, java.beans.PropertyChangeListener)
     */
    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
    }

    /**
     * @see PropertyChangeSupport#getPropertyChangeListeners(String)
     */
    public PropertyChangeListener[] getPropertyChangeListeners(String propertyName) {
        return propertyChangeSupport.getPropertyChangeListeners(propertyName);
    }

    /**
     * @see PropertyChangeSupport#removePropertyChangeListener(String, java.beans.PropertyChangeListener)
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    /**
     * @see PropertyChangeSupport#hasListeners(String)
     */
    public boolean hasListeners(String propertyName) {
        return propertyChangeSupport.hasListeners(propertyName);
    }

    public void initModel() {
        this.initTiles();
        this.setState(STATE_START);
    }

    /**
     * Notify the state callback.
     *
     * @param state the state of model.
     */
    protected final void notifyStateCallback(int state) {
        for (StateCallback callback : stateCallbacks) {
            callback.onState(state);
        }
    }

    /**
     * Add a specific state callback.
     *
     * @param stateCallback the state callback.
     */
    public void addStateCallback(StateCallback stateCallback) {
        this.stateCallbacks.add(stateCallback);
    }

    /**
     * Remove a specific callback.
     *
     * @param stateCallback the state callback
     */
    public void removeStateCallback(StateCallback stateCallback) {
        this.stateCallbacks.remove(stateCallback);
    }

    /**
     * Get the next positon according to the current state.
     *
     * @return the position.
     */
    protected abstract List<Integer> nextPosition(MazeTile tile);

    /**
     * Initialize the maze.
     */
    protected abstract void initTiles();

    /**
     * Create tile according to the position.
     *
     * @param position the position.
     * @return the {@link MazeTile}.
     */
    protected abstract MazeTile createTile(List<Integer> position);
}
