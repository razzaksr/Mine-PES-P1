package prototype.phase.pes_one;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Professor {
    @NotNull(message = "Id shouldn't be null")
    @Min(value = 1, message = "Invalid ID")
    private int id;
    @NotNull(message = "Name shouldn't be null")
    @Pattern(regexp = "^[a-zA-Z ]{3,}$", message = "Invalid Name")
    private String name;
    @NotNull(message = "Department shouldn't be null")
    @Pattern(regexp = "^[a-zA-Z ]{3,}$", message = "Invalid Department")
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