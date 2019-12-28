package structural.adapter;

public class EmployeeAdapterLdap implements Employee {
    private EmployeeLdap employeeLdap;

    public EmployeeAdapterLdap(EmployeeLdap employeeLdap) {
        this.employeeLdap = employeeLdap;
    }

    @Override
    public String getId() {
        return this.employeeLdap.getIdentifier();
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + getId() + '\'' +
                ", name='" + getName() + '\'' +
                '}';
    }

    @Override
    public String getName() {
        return this.employeeLdap.getFirstName();
    }

}
