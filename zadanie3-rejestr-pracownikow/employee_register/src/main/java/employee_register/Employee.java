package employee_register;

public abstract class Employee {
    private final String id;
    private String firstName;
    private String lastName;
    private int age;
    private int experience;
    private Address address;

    public Employee(String id, String firstName, String lastName, int age, int experience, Address address) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.experience = experience;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public int getExperience() {
        return experience;
    }

    public int getAge() {
        return age;
    }

    public String getLastName() {
        return lastName;
    }

    public Address getAddress() {
        return address;
    }

    public abstract double calculateValue();

    @Override
    public String toString() {
        return String.format("ID: %s, %s %s, Age: %d, Experience: %d, Address: %s", 
            id, firstName, lastName, age, experience, address);
    }
}