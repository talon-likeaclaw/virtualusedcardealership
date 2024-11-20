/**
 * Compare interface
 * 
 * @author Juan Badel Sebastian - 2338127
 * @version 11/18/2024
 */
package usedcardealership.comparators;

/**
 * Compares two Objects, used for sorting.
 * 
 * @param obj1 Object that gets compared to
 * @param obj2 Object to compare with
 * @return int
 */
public interface ICompare<T>{
    int compareTo(T obj1, T obj2);
}

