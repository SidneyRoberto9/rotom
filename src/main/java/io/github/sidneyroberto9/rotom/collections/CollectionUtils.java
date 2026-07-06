package io.github.sidneyroberto9.rotom.collections;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * Utility class for common collection operations.
 */
public class CollectionUtils {

    /**
     * Removes duplicate elements from the given list, preserving the order of first occurrence.
     *
     * @param list list to deduplicate
     * @param <T>  element type
     * @return new list without duplicates, in the original order
     * @throws NullPointerException if {@code list} is null
     */
    public <T> List<T> removeDuplicates(List<T> list) {
        return new ArrayList<>(new LinkedHashSet<>(list));
    }
}
