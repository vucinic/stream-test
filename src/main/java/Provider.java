import java.util.ArrayList;
import java.util.List;

public class Provider {
    public static List<Department> provide() {
        Department d1 = new Department();

        d1.setAnnualBudget(200000.0);
        d1.setName("Sales");

        var emplo1 = new ArrayList<Employee>();

        var vermi = new Employee("Vermi", "Massimo", 45000.0, Role.MANAGER);

        emplo1.add(new Employee("Rossi", "Mario", 15000.0, Role.BASIC));
        emplo1.add(new Employee("Verdi", "Mario", 45000.0, Role.MANAGER));
        emplo1.add(new Employee("Bianchi", "Mario", 25000.0, Role.BASIC));
        emplo1.add(new Employee("Rosa", "Mario", 25000.0, Role.BASIC));
        emplo1.add(new Employee("Neri", "Mario", 95000.0, Role.DIRECTOR));
        emplo1.add(vermi);

        d1.setEmployees(emplo1);

        Department d2 = new Department();

        d2.setAnnualBudget(1000000.0);
        d2.setName("Marketing");

        var emplo2 = new ArrayList<Employee>();

        emplo2.add(new Employee("Rotti", "Massimo", 15000.0, Role.BASIC));
        emplo2.add(vermi);
        emplo2.add(new Employee("Biant", "Massimo", 25000.0, Role.BASIC));
        emplo2.add(new Employee("Rost", "Massimo", 25000.0, Role.BASIC));
        emplo2.add(new Employee("Neroni", "Massimo", 95000.0, Role.DIRECTOR));

        d2.setEmployees(emplo2);


        Department d3 = new Department();

        d3.setAnnualBudget(50000.0);
        d3.setName("Production");

        var emplo3 = new ArrayList<Employee>();

        emplo3.add(new Employee("Ramm", "Luis", 30000.0, Role.BASIC));
        emplo3.add(new Employee("Vern", "Luis", 45000.0, Role.MANAGER));
        emplo3.add(new Employee("Bawar", "Luis", 21000.0, Role.BASIC));
        emplo3.add(new Employee("Rutt", "Luis", 15000.0, Role.BASIC));
        emplo3.add(new Employee("Neigen", "Luis", 100000.0, Role.DIRECTOR));

        d3.setEmployees(emplo3);

        return List.of(d1, d2, d3);
    }

}
