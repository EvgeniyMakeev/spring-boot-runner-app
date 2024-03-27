package dev.makeev.springbootrunnerapp.run;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class RunRepository {

    private final List<Run> runs = new ArrayList<>();

    List<Run> findAll() {
        return runs;
    }

    @PostConstruct
    private void init() {
        runs.add(new Run(
                1,
                "Fun run",
                LocalDateTime.now(),
                LocalDateTime.now().plusHours(1),
                7,
                Location.OUTDOOR));
        runs.add(new Run(
                2,
                "Indoor run",
                LocalDateTime.now(),
                LocalDateTime.now().plusHours(2),
                13,
                Location.INDOOR));

    }

    Optional<Run> findById(Integer id) {
        for (Run run : runs) {
            if (run.id().equals(id)) {
                return Optional.of(run);
            }
        }
        return Optional.empty();
    }

    void create(Run run) {
        runs.add(run);
    }

    void update(Run run, Integer id) {
        Optional<Run> existingRun = findById(id);
        existingRun.ifPresent(value -> runs.set(runs.indexOf(value), run));
    }

    void delete(Integer id) {
        runs.removeIf(run -> run.id().equals(id));
    }
}
