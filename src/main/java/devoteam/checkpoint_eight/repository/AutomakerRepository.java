package devoteam.checkpoint_eight.repository;

import devoteam.checkpoint_eight.domain.Automaker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface  AutomakerRepository extends JpaRepository<Automaker, Integer> {

    public Optional<Automaker> findByName(String name);
}
