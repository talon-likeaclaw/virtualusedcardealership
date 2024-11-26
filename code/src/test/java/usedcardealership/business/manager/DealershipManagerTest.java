/**
 * Test class for DealershipManager
 * 
 * @author Talon Dunbar
 * @version 11/11/2024
 */

package usedcardealership.business.manager;

import java.util.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class DealershipManagerTest {

    @Test
    public void testConstructorGetter() {
        // Arrange
        String expectedName = "Talon's Vehicle Emporium";
        double expectedBalance = 100000.00;

        // Act
        DealershipManager test = new DealershipManager(expectedName, expectedBalance, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        // Assert
        assertEquals(expectedName, test.getName());
        assertEquals(expectedBalance, test.getBalance(), 0.01);
    }

    @Test
    public void testUpdateAccountBalance_addsPositiveAmount() {
        // Arrange
        double initialBalance = 100000.00;
        double balanceChange = 10000.00;
        double expectedBalance = initialBalance + balanceChange;

        // Act
        DealershipManager test = new DealershipManager("Talon's Vehicle Emporium", initialBalance, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        test.updateAccountBalance(balanceChange);

        // Assert
        assertEquals(expectedBalance, test.getBalance(), 0.01);
    }

    @Test
    public void testUpdateAccountBalance_addsNegativeAmount() {
        // Arrange
        double initialBalance = 100000.00;
        double balanceChange = -10000.00;
        double expectedBalance = initialBalance + balanceChange;

        // Act
        DealershipManager test = new DealershipManager("Talon's Vehicle Emporium", initialBalance, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        test.updateAccountBalance(balanceChange);

        // Assert
        assertEquals(expectedBalance, test.getBalance(), 0.01);
    }

    @Test
    public void testUpdateAccountBalance_zeroChange() {
        // Arrange
        double initialBalance = 100000.00;
        double balanceChange = 0.0;
        double expectedBalance = initialBalance;

        // Act
        DealershipManager test = new DealershipManager("Talon's Vehicle Emporium", initialBalance, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        test.updateAccountBalance(balanceChange);

        // Assert
        assertEquals(expectedBalance, test.getBalance(), 0.01);
    }
}
