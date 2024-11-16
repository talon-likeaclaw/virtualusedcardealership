/**
 * Filter interface
 * 
 * @author Talon Dunbar - 2131651
 * @version 11/16/2024
 */

package usedcardealership;

import java.util.*;

public interface IFilter<T, Param> {

    /**
     * Filters a list of items based on a given parameter.
     * 
     * @param items the list of items to filter
     * @param param the parameter to filter the items by
     * @return a filtered list of items matching the parameter
     */
    List<T> filter(List<T> items, Param param);
}