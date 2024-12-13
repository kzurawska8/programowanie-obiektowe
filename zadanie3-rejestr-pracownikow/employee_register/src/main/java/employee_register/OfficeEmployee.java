package employee_register;

public class OfficeEmployee extends Employee {
    private int iq;

    public OfficeEmployee(String id, String firstName, String lastName, int age, int experience, Address address, int iq) {
        super(id, firstName, lastName, age, experience, address);
        if (iq <= 0) throw new IllegalArgumentException("IQ musi być większe od 0");
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