import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
public class Employee {

    @NonNull
    private String surname, name;
    private String address, title;
    @NonNull
    private Double salary;
    @NonNull
    private Role role;

}
