package behavioral.memento;

public class MementoDemo {


    public static void main(String[] args) {

        Caretaker caretaker = new Caretaker();

        Employee employee = new Employee();

        employee.setName("Diego");
        employee.setPhone("999999");

        System.out.println("Funcionário salvo antes " + employee);

        caretaker.save(employee);

        employee.setPhone("88888");

        caretaker.save(employee);

        System.out.println("Funcionário salvo depois " + employee);

        employee.setPhone("77777");

        caretaker.revert(employee);

        System.out.println("Reverteu o ultimo save " + employee);

        caretaker.revert(employee);

        System.out.println("Reverteu ao original " + employee);

    }
}
