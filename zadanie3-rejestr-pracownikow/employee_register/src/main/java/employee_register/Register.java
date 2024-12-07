package employee_register;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Register {
    private final List<Employee> employees = new ArrayList<>();

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void addEmployees(List<Employee> employeesToAdd) {
        employees.addAll(employeesToAdd);
    }

    public boolean removeEmployee(String id) {
        return employees.removeIf(employee -> employee.getID().equals(id));
    }

    public List<Employee> getEmployeesSorted() {
        return employees.stream()
                .sorted(Comparator.comparing(Employee::getExperience).reversed()
                        .thenComparing(Employee::getAge)
                        .thenComparing(Employee::getLastName))
                .collect(Collectors.toList());
    }

    public List<Employee> getEmployeesByCity(String city) {
        return employees.stream()
                .filter(employee -> employee.getAddress().getCity().equalsIgnoreCase(city))
                .collect(Collectors.toList());
    }

    public void printEmployeesWithValue() {
        employees.forEach(employee -> 
            System.out.printf("%s, Wartość: %.2f%n", employee, employee.calculateValue()));
    }
}