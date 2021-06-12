package pl.infoshare.jpa.movie.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Value;

import javax.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
@Getter
public class Director {
    @JsonProperty("name")
    private String firstName;
    @JsonProperty("surname")
    private String lastName;
    @JsonProperty("birthDate")
    private LocalDate dob;

    public Director(String firstName, String lastName, LocalDate dob) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
    }

    protected Director() {

    }
}
