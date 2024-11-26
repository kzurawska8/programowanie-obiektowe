package employee_register;

public class OfficeEmployee extends Employee {
    private int iq;

    public OfficeEmployee(String id, String firstName, String lastName, int age, int experience, Address address, int iq) {
        super(id, firstName, lastName, age, experience, address);
        this.iq = iq;
    }

    @Override
    public double calculateValue() {
        return getExperience() * iq;
    }

    @Override
    public String toString() {
        return super.toString() + ", IQ: " + iq;
    }
}