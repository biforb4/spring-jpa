package pl.infoshare.jpa.movie.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import pl.infoshare.jpa.movie.model.Genre;
import pl.infoshare.jpa.movie.model.Movie;
import pl.infoshare.jpa.movie.model.MovieSummary;

import java.util.List;
import java.util.UUID;

@Component
public interface MovieRepository extends JpaRepository<Movie, UUID> {
    void deleteAllByGenre(Genre genre);

    @Query("select m FROM Movie m WHERE m.score > 7 AND m.releaseDate > '2010-01-01'")
    List<MovieSummary> findMostPopular();

    Page<MovieSummary> findAllByTitleContaining(String title, Pageable pageable);

    Page<MovieSummary> findAllProjectedBy(Pageable pageable);
}
