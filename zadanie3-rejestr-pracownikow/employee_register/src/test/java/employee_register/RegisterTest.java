package employee_register;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.Test;

public class RegisterTest {
    private Register register;

    @Before
    public void setUp() {
        register = new Register();
    }

    @Test
    public void addEmployee() {
        Address address = new Address("Main St", 1, 1, "New York");
        Employee office = new OfficeEmployee("1", "John", "Doe", 30, 5, address, 120);

        register.addEmployee(office);
        List<Employee> allEmployees = register.getEmployeesSorted();
        assertEquals("Rejestr zawiera 1 pracownika po dodaniu", 1, allEmployees.size());
    }

    @Test
    public void removeEmployee() {
        Address address = new Address("Main St", 1, 1, "New York");
        Employee office = new OfficeEmployee("1", "John", "Doe", 30, 5, address, 120);

        register.addEmployee(office);
        register.removeEmployee(office.getID());
        List<Employee> allEmployees = register.getEmployeesSorted();
        assertEquals("Rejestr nie zawiera żadnych pracowników po usunięciu", 0, allEmployees.size());
    }

    @Test
    public void removeNonExistentEmployee() {
        boolean removed = register.removeEmployee("999");
        assertFalse("Nie można usunąć nie istniejącego pracownika", removed);
    }

    @Test
    public void addEmployees() {
        Address address1 = new Address("Mickiewicza", 10, 1, "Warszawa");
        Employee office = new OfficeEmployee("1", "Jan", "Kowalski", 30, 5, address1, 120);

        Address address2 = new Address("Słoneczna", 5, 2, "Kraków");
        Employee physical = new PhysicalEmployee("2", "Anna", "Nowak", 40, 10, address2, 80);

        Address address3 = new Address("Długa", 3, 5, "Gdańsk");
        Employee trader = new TraderEmployee("3", "Paweł", "Wiśniewski", 35, 7, address3, TraderEmployee.Effectiveness.HIGH, 20);

        register.addEmployees(Arrays.asList(office, physical, trader));
        List<Employee> allEmployees = register.getEmployeesSorted();
        assertEquals("Rejestr zawiera 3 pracowników po dodaniu", 3, allEmployees.size());
    }

    @Test
    public void sortEmployees() {
        Address address1 = new Address("Słoneczna", 5, 2, "Kraków");
        Employee physical = new PhysicalEmployee("1", "Anna", "Nowak", 40, 10, address1, 80);

        Address address2 = new Address("Mickiewicza", 10, 1, "Warszawa");
        Employee office = new OfficeEmployee("2", "Jan", "Kowalski", 30, 5, address2, 120);

        Address address3 = new Address("Długa", 3, 5, "Gdańsk");
        Employee trader = new TraderEmployee("3", "Paweł", "Wiśniewski", 35, 7, address3, TraderEmployee.Effectiveness.HIGH, 20);

        register.addEmployees(Arrays.asList(physical, office, trader));
        List<Employee> allEmployees = register.getEmployeesSorted();
        List<Employee> expectedOrder = Arrays.asList(physical, trader, office);
        assertEquals("Kolejność: Physical, Trader, Office", expectedOrder, allEmployees);
    }



    @Test
    public void findEmployeesByCity() {
        Address address = new Address("Main St", 1, 1, "New York");
        Employee office = new OfficeEmployee("1", "John", "Doe", 30, 5, address, 120);

        register.addEmployee(office);
        List<Employee> employeesInNY = register.getEmployeesByCity("New York");
        assertEquals("Znaleziony 1 pracownik w New York", 1, employeesInNY.size());
    }

    @Test
    public void findEmployeesByCityNoMatch() {
        List<Employee> employeesInChicago = register.getEmployeesByCity("Chicago");
        assertEquals("Nie ma pracowników w Chicago", 0, employeesInChicago.size());
    }

    @Test
    public void calculationOfficeEmployee() {
        Address address = new Address("Main St", 1, 1, "New York");
        Employee office = new OfficeEmployee("1", "John", "Doe", 30, 5, address, 120);

        double value = office.calculateValue();
        assertEquals("5 * 120 = 600", 600.0, value, 0.001);
    }

    @Test
    public void calculationPhysicalEmployee() {
        Address address = new Address("Park Ave", 5, 10, "Los Angeles");
        Employee physical = new PhysicalEmployee("2", "Jane", "Smith", 25, 3, address, 80);

        double value = physical.calculateValue();
        assertEquals("3 * 80 / 25 = 9.6", 9.6, value, 0.001);
    }

    @Test
    public void calculationTraderHighEffectiveness() {
        Address address = new Address("Main St", 1, 1, "New York");
        Employee trader = new TraderEmployee("3", "Alice", "Brown", 35, 10, address, TraderEmployee.Effectiveness.HIGH, 10.0);

        double value = trader.calculateValue();
        assertEquals("10 * 120 = 1200", 1200.0, value, 0.001);
    }
}