import java.util.List;


public class Department {

    private String name;
    private List<Employee> employees;
    private Double annualBudget;

    public Department() {
    }


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmployees() {
        return this.employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public Double getAnnualBudget() {
        return this.annualBudget;
    }

    public void setAnnualBudget(Double annualBudget) {
        this.annualBudget = annualBudget;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Department)) return false;
        final Department other = (Department) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$employees = this.getEmployees();
        final Object other$employees = other.getEmployees();
        if (this$employees == null ? other$employees != null : !this$employees.equals(other$employees)) return false;
        final Object this$annualBudget = this.getAnnualBudget();
        final Object other$annualBudget = other.getAnnualBudget();
        if (this$annualBudget == null ? other$annualBudget != null : !this$annualBudget.equals(other$annualBudget))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Department;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $employees = this.getEmployees();
        result = result * PRIME + ($employees == null ? 43 : $employees.hashCode());
        final Object $annualBudget = this.getAnnualBudget();
        result = result * PRIME + ($annualBudget == null ? 43 : $annualBudget.hashCode());
        return result;
    }

    public String toString() {
        return "Department(name=" + this.getName() + ", employees=" + this.getEmployees() + ", annualBudget=" + this.getAnnualBudget() + ")";
    }
}

