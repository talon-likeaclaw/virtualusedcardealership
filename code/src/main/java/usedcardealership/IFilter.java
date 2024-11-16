/**
 * Filter interface
 * 
 * @author Talon Dunbar - 2131651
 * @version 11/16/2024
 */

package usedcardealership;

public interface IFilter<T> {

    /**
     * Determines if given item matches the filter's criteria.
     * 
     * @param items the iteam to check
     * @return true if matches, false otherwise
     */
    boolean filter(T item);
}