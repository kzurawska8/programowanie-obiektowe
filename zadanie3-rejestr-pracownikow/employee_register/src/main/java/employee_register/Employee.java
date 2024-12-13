package employee_register;

import java.util.Objects;

public abstract class Employee implements Valuable {
    private final String id;
    private String firstName;
    private String lastName;
    private int age;
    private int experience;
    private Address address;

    public Employee(String id, String firstName, String lastName, int age, int experience, Address address) {
        if (id == null || id.trim().isEmpty()) throw new IllegalArgumentException("ID nie może być puste lub null");
        if (firstName == null || firstName.trim().isEmpty()) throw new IllegalArgumentException("Imię nie może być puste lub null");
        if (lastName == null || lastName.trim().isEmpty()) throw new IllegalArgumentException("Nazwisko nie może być puste lub null");
        if (age <= 0) throw new IllegalArgumentException("Wiek musi wynosić więcej niż 0");
        if (experience < 0) throw new IllegalArgumentException("Doświadczenie nie może być ujemne");
        
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.experience = experience;
        this.address = Objects.requireNonNull(address, "Adres nie może być null");
    }

    public String getID() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public int getExperience() {
        return experience;
    }

    public Address getAddress() {
        return address;
    }

    @Override
    public abstract double calculateValue();
    
    @Override
    public String toString() {
        return String.format("ID: %s, %s %s, Wiek: %d, Doświadczenie: %d, Adres: %s", 
            id, firstName, lastName, age, experience, address);
    }
}