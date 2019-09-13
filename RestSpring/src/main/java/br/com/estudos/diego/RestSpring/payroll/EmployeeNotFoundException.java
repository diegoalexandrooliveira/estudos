package br.com.estudos.diego.RestSpring.payroll;

@SuppressWarnings("serial")
public class EmployeeNotFoundException extends RuntimeException {

	public EmployeeNotFoundException(Long id) {
		super("Não foi possível localizar o empregado " + id);
	}

}
