package test.com.seller_app;

public class User {

    // string variable for
    // storing employee name.
    private String name;

    // string variable for storing
    // employee contact number
    private String quantity;

    // string variable for storing
    // employee address.
    private String address;

    // an empty constructor is
    // required when using
    // Firebase Realtime Database.

    // created getter and setter methods
    // for all our variables.
    public String getName() {
        return name;
    }

    public void setName(String employeeName) {
        this.name = employeeName;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String employeeContactNumber) {
        this.quantity = employeeContactNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String employeeAddress) {
        this.address = employeeAddress;
    }
}
