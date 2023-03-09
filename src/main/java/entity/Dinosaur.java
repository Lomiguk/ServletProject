package entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Dinosaur {
    private long id;
    private long typeOfDinosaurId;
    private String name;
    private LocalDate birthDate;
    private LocalDate deathDate;
    private long attractionId;
    private long employId;
}
