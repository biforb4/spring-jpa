package pl.infoshare.jpa.movie.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
public class Movie {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(updatable = false, nullable = false)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID id;
    private String title;
    private String description;
    private Director director;
    @Enumerated(EnumType.STRING)
    private Genre genre;
    private Duration duration;
    private LocalDate releaseDate;
    private BigDecimal score;
    @JsonProperty("thumbnail")
    private String cover;
    @LastModifiedDate
    private LocalDate lastModified;

    public Movie(String title, String description, Director director, Genre genre, Duration duration, LocalDate releaseDate, BigDecimal score, String cover) {
        this.title = title;
        this.description = description;
        this.director = director;
        this.genre = genre;
        this.duration = duration;
        this.releaseDate = releaseDate;
        this.score = score;
        this.cover = cover;
    }

    protected Movie() {

    }

    public void update(Movie updated) {
        this.title = updated.getTitle();
        this.description = updated.getDescription();
        this.director = updated.getDirector();
        this.genre = updated.getGenre();
        this.duration = updated.getDuration();
        this.releaseDate = updated.getReleaseDate();
        this.score = updated.getScore();
        this.cover = updated.getCover();
    }
}
