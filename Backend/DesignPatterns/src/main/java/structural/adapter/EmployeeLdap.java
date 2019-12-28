package structural.adapter;

public class EmployeeLdap {


    private String identifier;

    private String firstName;

    public EmployeeLdap(String identifier, String firstName) {
        this.identifier = identifier;
        this.firstName = firstName;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
