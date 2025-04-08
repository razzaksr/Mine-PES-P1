package prototype.phase.pes_one;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class Professor {
    @NotNull(message = "{valid.null.id}")
    @Min(value = 1, message = "{valid.id}")
    private int id;
    @NotNull(message = "{valid.null.name}")
    @Pattern(regexp = "^[a-zA-Z ]{3,}$", message = "{valid.name}")
    private String name;
    @NotNull(message = "{valid.null.dept}")
    @Pattern(regexp = "^[a-zA-Z ]{3,}$", message = "{valid.dept}")
    private String department;

    public Professor(int id, String name, String department) {
        this.id = id;
        this.name = name;
        this.department = department;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getDepartment() { return department; }

    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setDepartment(String department) { this.department = department; }
}