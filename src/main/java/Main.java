import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

record EmployeeWithDeps(Employee employee, List<Department> departments) {
}


public class Main {

    public static void main(String[] args) {

        var company = Provider.provide();

        System.out.println("1 ---------------------");
        // 1 Print out all the Employees "Name Surname"
        company.stream()
                .flatMap(dep -> dep.getEmployees().stream())
                .distinct()
                .forEach(employee -> System.out.println(employee.getName() + " " + employee.getSurname()));

        System.out.println("2 ---------------------");
        // 2 Print out all the Manager "Name Surname"
        company.stream()
                .flatMap(dep -> dep.getEmployees().stream())
                .filter(e -> e.getRole() == Role.MANAGER)
                .forEach(s -> System.out.println(s.getName() + " " + s.getSurname()));


        System.out.println("3 ---------------------");
        // 3 Print out the total annual budget for all the departments together
        var total = company.stream()
                .collect(Collectors.summarizingDouble(Department::getAnnualBudget)).getSum();
        System.out.println("Total budget: " + total);

        {
            var totalA = company.stream()
                    .mapToDouble(Department::getAnnualBudget)
                    .reduce(0, (a, b) -> a + b);

            System.out.println("Total budget: " + totalA);
        }

        {
            var totalA = company.stream()
                    .mapToDouble(Department::getAnnualBudget)
                    .sum();

            System.out.println("Total budget: " + totalA);
        }


        System.out.println("4 ---------------------");
        // 4 Print out the total annual salary for all the employees from all departments
        var total2 = company.stream()
                .flatMap(dep -> dep.getEmployees().stream())
                .collect(Collectors.summarizingDouble(Employee::getSalary)).getSum();
        System.out.println("Total salary: " + total2);
//
//
//        // 5 Print out the name and the relative departments names of all the employees
//        // that are assigned to more than one department

        System.out.println("5 ---------------------");
        company.stream()
                .flatMap(department -> department.getEmployees().stream())
                .distinct()
                .map(e -> new EmployeeWithDeps(e, company.stream().filter(department -> department.getEmployees().contains(e)).toList()))
                .filter(employeeWithDeps -> employeeWithDeps.departments().size() > 1)
                .forEach(employeeWithDeps -> {
                    var name = employeeWithDeps.employee().getName();
                    var surname = employeeWithDeps.employee().getSurname();

                    var deps = employeeWithDeps.departments()
                            .stream()
                            .map(Department::getName)
                            .collect(Collectors.joining(" - "));

                    System.out.println(name + " " + surname + " " + deps);
                });


        System.out.println("6 ---------------------");
        // 6 Print out the Name and the salary of the employees (from any department)
        // whose salary is above the company average salary
        var average = company.stream()
                .map(Department::getEmployees)
                .flatMap(Collection::stream)
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(0);

        System.out.println("Average is: " + average);
        System.out.println("Rich employees are: ");

        company.stream()
                .flatMap(department -> department.getEmployees().stream())
                .distinct()
                .filter(employee -> employee.getSalary() > average)
                .forEach(s -> System.out.println(s.getName() + " " + s.getSurname() + " " + s.getSalary()));


        // For each department take only the first n employees that can be paid
        // using the annualBudget
        System.out.println("7 ---------------------");

//        Production
//        Paid employees:
//        Luis Ramm
//        Luis Rutt
        company.stream()
                .peek(department -> System.out.println(department.getName() + " " + department.getAnnualBudget()))
                .forEach(
                        department -> department.getEmployees()
                                .stream()
                                .sorted(Comparator.comparingDouble(Employee::getSalary))
                                .collect(Collector.of(
                                        () -> new ArrayList<Employee>(),
                                        (employees, employee) -> {
                                            if(employees.stream().mapToDouble(Employee::getSalary).sum() + employee.getSalary() < department.getAnnualBudget())
                                                employees.add(employee);
                                        },
                                        (employees, employees2) -> {
                                            employees.addAll(employees2); return employees;
                                        }
                                ))
                                .forEach(s -> System.out.println(s.getName() + " " + s.getSurname() + " " + s.getSalary()))
                );




        System.out.println("8 ---------------------");
        // Print out the names of the departments in which the sum of the employees' salary
        // is greater than the department annual budget
        company.stream()
                .filter(department -> department.getAnnualBudget() <
                        department.getEmployees().stream().mapToDouble(Employee::getSalary).sum())
                .forEach(department -> System.out.println(department.getName() + " " + department.getAnnualBudget() +
                        " < " + department.getEmployees().stream().mapToDouble(Employee::getSalary).sum()));

    }


}

