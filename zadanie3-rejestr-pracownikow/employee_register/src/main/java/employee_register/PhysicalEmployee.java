package employee_register;

public class PhysicalEmployee extends Employee {
    private int strength;

    public PhysicalEmployee(String id, String firstName, String lastName, int age, int experience, Address address, int strength) {
        super(id, firstName, lastName, age, experience, address);
        this.strength = strength;
    }

    @Override
    public double calculateValue() {
        return getExperience() * strength / (double) getAge();
    }

    @Override
    public String toString() {
        return super.toString() + ", Siła: " + strength;
    }
}