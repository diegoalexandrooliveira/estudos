package structural.adapter;

import java.util.List;

public class AdapterDemo {

    public static void main(String[] args) {
        List<Employee> employees = new EmployeeClient().getEmployees();


        System.out.println(employees);
    }
}
