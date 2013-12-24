package com.ziq.linemaze.rect;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * The regular rectangle tile.
 *
 * @author ziq
 */
public class LineRectTile extends AbstractRectTile {

    /**
     * Construct a new {@link LineRectTile} object.
     */
    public LineRectTile() {
        List<Integer> numbers = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7);
        Collections.shuffle(numbers);
        for (int i = 0; i < numbers.size() / 2; i++) {
            getExits().set(numbers.get(i * 2), numbers.get(i * 2 + 1));
            getExits().set(numbers.get(i * 2 + 1), numbers.get(i * 2));
        }
    }
}
