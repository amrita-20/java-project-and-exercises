package structural_pattern.facade_method;

public class Address {
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private String zip;

    public Address(String firstName, String lastName, String address, String city, String state, String zip) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }
}
