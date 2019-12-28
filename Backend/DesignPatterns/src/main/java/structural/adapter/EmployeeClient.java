package structural.adapter;

import java.util.ArrayList;
import java.util.List;

public class EmployeeClient {


    public List<Employee> getEmployees(){

        ArrayList<Employee> employees = new ArrayList<>();

        EmployeeDB diego = new EmployeeDB("1", "Diego");

        employees.add(diego);


        Employee joao = new EmployeeAdapterLdap(new EmployeeLdap("2", "João"));

        employees.add(joao);


        return employees;


    }
}
