/**
 * Interface representing a generic data handler strategy for loading and saving items.
 *
 * @param <T> the type of items to handle
 * @author Talon Dunbar
 * @version 11/12/2024
 */

package usedcardealership.data.filehandling;

import java.util.*;

public interface IDataHandler<T> {
    /**
     * Loads a list of items from the data source.
     * 
     * @return a List of items loaded from the data source
     */
    List<T> load();

    /**
     * Saves a list of items to the data source.
     *
     * @param items a List of items to be saved to the data source
     */
    void save(List<T> items);
}
