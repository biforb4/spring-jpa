package pl.infoshare.jpa.movie.model;

import java.util.UUID;

public interface MovieSummary {
    UUID getId();
    String getTitle();
    Genre getGenre();
    String getCover();
}
