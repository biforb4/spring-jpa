package pl.infoshare.jpa.movie.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.infoshare.jpa.movie.model.Genre;
import pl.infoshare.jpa.movie.model.Movie;
import pl.infoshare.jpa.movie.repository.MovieRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController()
public class Controller {
    private final MovieRepository repository;

    public Controller(MovieRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/api/movies")
    public void createMovie(@RequestBody Movie movie) {
        repository.save(movie);
    }

    @GetMapping("/api/movies")
    public Page<Movie> getMovies(Pageable pageable, @RequestParam Optional<String> title) {

        if(title.isPresent()) {
            return repository.findAllByTitleContaining(title.get(), pageable);
        }
        return repository.findAll(pageable);

    }

    @GetMapping("/api/popular-movies")
    public List<Movie> getPopularMovies() {
        return repository.findMostPopular();
    }

    @PutMapping("/api/movies/{id}")
    @Transactional
    public void updateMovie(@PathVariable UUID id, @RequestBody Movie updated) {
        var movie = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        movie.update(updated);
        repository.save(movie);
    }

    @GetMapping("/api/movies/{id}")
    public Movie getMovie(@PathVariable UUID id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/api/movies/{id}")
    @Transactional
    public void deleteMovie(@PathVariable UUID id) {
        repository.deleteById(id);
    }

    @DeleteMapping("/api/movies")
    @Transactional
    public void deleteMovie(@RequestParam Genre genre) {
        repository.deleteAllByGenre(genre);
    }


}
