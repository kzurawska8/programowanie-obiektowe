package employee_register;

public class PhysicalEmployee extends Employee {
    private int strength;

    public PhysicalEmployee(String id, String firstName, String lastName, int age, int experience, Address address, int strength) {
        super(id, firstName, lastName, age, experience, address);
        if (strength <= 0) throw new IllegalArgumentException("Siła musi być większa od 0");
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