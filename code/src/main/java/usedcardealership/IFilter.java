/**
 * Filter interface
 * 
 * @author Talon Dunbar - 2131651
 * @version 11/16/2024
 */

package usedcardealership;

public interface IFilter<T> {

    /**
     * Determines if given Object matches the filter's criteria.
     * 
     * @param item the Generic Object to check
     * @return true if matches, false otherwise
     */
    boolean filter(T item);
}