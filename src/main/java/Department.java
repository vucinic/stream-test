import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class Department {

    private String name;
    private List<Employee> employees;
    private Double annualBudget;


}

