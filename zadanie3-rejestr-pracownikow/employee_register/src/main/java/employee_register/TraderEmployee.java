package employee_register;

public class TraderEmployee extends Employee {
    public enum Effectiveness {
        LOW(60), MEDIUM(90), HIGH(120);
        private final int value;
        Effectiveness(int value) { this.value = value; }
        public int getValue() { return value; }
    }

    private Effectiveness effectiveness;
    private double commission;

    public TraderEmployee(String id, String firstName, String lastName, int age, int experience, Address address, Effectiveness effectiveness, double commission) {
        super(id, firstName, lastName, age, experience, address);
        if (commission < 0 || commission > 100) throw new IllegalArgumentException("Musi być pomiędzy 0 a 100");
        this.effectiveness = effectiveness;
        this.commission = commission;
    }

    @Override
    public double calculateValue() {
        return getExperience() * effectiveness.getValue();
    }

    @Override
    public String toString() {
        return super.toString() + ", Efektywność: " + effectiveness + ", Skuteczność: " + commission + "%";
    }
}