package employee_register;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class RegisterTest {
    private Register register;

    @Before
    public void setUp() {
        register = new Register();
    }

    @Test
    public void testAddEmployee() {
        Address address = new Address("Main St", 1, 1, "New York");
        Employee emp = new OfficeEmployee("1", "John", "Doe", 30, 5, address, 120);

        register.addEmployee(emp);
        List<Employee> allEmployees = register.getEmployeesSorted();
        assertEquals("Register should contain 1 employee after adding.", 1, allEmployees.size());
    }

    @Test
    public void testRemoveEmployeeById() {
        Address address = new Address("Main St", 1, 1, "New York");
        Employee emp = new OfficeEmployee("1", "John", "Doe", 30, 5, address, 120);

        register.addEmployee(emp);
        boolean removed = register.removeEmployeeById("1");
        assertTrue("Employee should be removed successfully.", removed);
    }

    @Test
    public void testRemoveNonExistentEmployee() {
        boolean removed = register.removeEmployeeById("999");
        assertFalse("Non-existent employee should not be removable.", removed);
    }

    @Test
    public void testGetEmployeesByCitySingleMatch() {
        Address address = new Address("Main St", 1, 1, "New York");
        Employee emp = new OfficeEmployee("1", "John", "Doe", 30, 5, address, 120);

        register.addEmployee(emp);
        List<Employee> employeesInNY = register.getEmployeesByCity("New York");
        assertEquals("Should find 1 employee in New York.", 1, employeesInNY.size());
    }

    @Test
    public void testGetEmployeesByCityNoMatch() {
        List<Employee> employeesInChicago = register.getEmployeesByCity("Chicago");
        assertEquals("Should find no employees in Chicago.", 0, employeesInChicago.size());
    }

    @Test
    public void testEmployeeValueCalculationOfficeEmployee() {
        Address address = new Address("Main St", 1, 1, "New York");
        Employee emp = new OfficeEmployee("1", "John", "Doe", 30, 5, address, 120);

        double value = emp.calculateValue();
        assertEquals("Value should be correctly calculated for OfficeEmployee.", 600.0, value, 0.001);
    }

    @Test
    public void testEmployeeValueCalculationPhysicalEmployee() {
        Address address = new Address("Park Ave", 5, 10, "Los Angeles");
        Employee emp = new PhysicalEmployee("2", "Jane", "Smith", 25, 3, address, 80);

        double value = emp.calculateValue();
        assertEquals("Value should be correctly calculated for PhysicalEmployee.", 9.6, value, 0.001);
    }

    @Test
    public void testEmployeeValueCalculationTraderHighEffectiveness() {
        Address address = new Address("Main St", 1, 1, "New York");
        Employee emp = new TraderEmployee("3", "Alice", "Brown", 35, 10, address, TraderEmployee.Effectiveness.HIGH, 10.0);

        double value = emp.calculateValue();
        assertEquals("Value should be correctly calculated for Trader with HIGH effectiveness.", 1200.0, value, 0.001);
    }
}