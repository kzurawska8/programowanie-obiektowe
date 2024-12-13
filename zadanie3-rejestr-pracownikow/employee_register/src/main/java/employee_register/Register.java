package employee_register;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Register {
    private final List<Employee> employees = new ArrayList<>();

    public void addEmployee(Employee employee) {
        employees.add(Objects.requireNonNull(employee, "Pracownik nie może być pusty"));
    }

    public void addEmployees(List<Employee> employeesToAdd) {
        employeesToAdd.forEach(this::addEmployee);
    }

    public boolean removeEmployee(String id) {
        return employees.removeIf(employee -> employee.getID().equals(id));
    }

    public List<Employee> getEmployeesSorted() {
        EmployeeComparator comparator = new EmployeeComparator();
        return employees.stream()
                .sorted((e1, e2) -> comparator.compare(e1, e2))
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

class EmployeeComparator {
    public int compare(Employee e1, Employee e2) {
        if (e1.getExperience() != e2.getExperience()) {
            return e2.getExperience() - e1.getExperience();
        } else if (e1.getAge() != e2.getAge()) {
            return e1.getAge() - e2.getAge();
        } else {
            return e1.getLastName().compareTo(e2.getLastName());
        }
    }
}
}