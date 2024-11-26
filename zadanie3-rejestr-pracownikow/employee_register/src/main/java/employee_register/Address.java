package employee_register;

public class Address {
    private String street;
    private int buildingNumber;
    private int apartmentNumber;
    private String city;

    public Address(String street, int buildingNumber, int apartmentNumber, String city) {
        this.street = street;
        this.buildingNumber = buildingNumber;
        this.apartmentNumber = apartmentNumber;
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return street + " " + buildingNumber + "/" + apartmentNumber + ", " + city;
    }
}