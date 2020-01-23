package behavioral.memento;

import java.util.Stack;

// caretaker
public class Caretaker {

    private Stack<EmployeeMemento> employeeHistory = new Stack<>();


    public void save(Employee employee){
        employeeHistory.push(employee.save());
    }

    public void revert(Employee employee){
        employee.revert(employeeHistory.pop());
    }

}
