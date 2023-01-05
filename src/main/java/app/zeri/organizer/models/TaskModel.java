package app.zeri.organizer.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TaskModel {
    String taskName;
    String taskDescription;
    String deadline;
    String projectName;
    String companyName;
}
