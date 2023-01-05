package app.zeri.organizer.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectModel {
    String projectName;
    String projectDescription;
    String companyName;
}
