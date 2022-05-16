package devoteam.checkpoint_eight.repository;

import devoteam.checkpoint_eight.domain.Automaker;
import devoteam.checkpoint_eight.util.AutomakerCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@DisplayName("Automaker Repository Test")
class AutomakerRepositoryTest {

    @Autowired
    private AutomakerRepository automakerRepository;

    @Test
    @DisplayName("Save creates Automaker when successfull")
    public void save_PersistAutomaker_WhenSuccessfull() {
        Automaker anime = AutomakerCreator.createAutomakerToBeSaved();
        Automaker savedAutomaker = this.automakerRepository.save(anime);

        Assertions.assertThat(savedAutomaker.getId()).isNotNull();
        Assertions.assertThat(savedAutomaker.getName()).isNotNull();
        Assertions.assertThat(savedAutomaker.getName()).isEqualTo(anime.getName());
    }

    @Test
    @DisplayName("Save updates Automaker when successfull")
    public void save_UpdateAutomaker_WhenSuccessfull() {
        Automaker anime = AutomakerCreator.createAutomakerToBeSaved();
        Automaker savedAutomaker = this.automakerRepository.save(anime);

        savedAutomaker.setName(("That time i got reincarnated as a slime"));

        Automaker updatedAutomaker = this.automakerRepository.save(savedAutomaker);

        Assertions.assertThat(savedAutomaker.getId()).isNotNull();
        Assertions.assertThat(savedAutomaker.getName()).isNotNull();
        Assertions.assertThat(savedAutomaker.getName()).isEqualTo(updatedAutomaker.getName());
    }

    @Test
    @DisplayName("Removes Automaker when successfull")
    public void delete_RemoveAutomaker_WhenSuccessfull() {
        Automaker anime = AutomakerCreator.createAutomakerToBeSaved();
        Automaker savedAutomaker = this.automakerRepository.save(anime);

        this.automakerRepository.delete(anime);
        Optional<Automaker> animeOptional = this.automakerRepository.findById(savedAutomaker.getId());

        savedAutomaker.setName(("That time i got reincarnated as a slime"));

        Assertions.assertThat(animeOptional.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("Find By name returns Automaker when successfull")
    public void findByName_ReturnAutomaker_WhenSuccessfull() {
        Automaker anime = AutomakerCreator.createAutomakerToBeSaved();
        Automaker savedAutomaker = this.automakerRepository.save(anime);

        String name = savedAutomaker.getName();

        Optional<Automaker> automakerOptional = this.automakerRepository.findByName(name);

        Assertions.assertThat(automakerOptional).isNotEmpty();
        Assertions.assertThat(automakerOptional).contains(savedAutomaker);
    }

    @Test
    @DisplayName("Find By name returns empty list when no anime is found")
    public void findByName_ReturnEmptyList_WhenAutomakerNotFound() {

        String name = "Fake-Name";

        Optional<Automaker> automakerOptional = this.automakerRepository.findByName(name);

        Assertions.assertThat(automakerOptional).isEmpty();
        ;
    }

    @Test
    @DisplayName("Save throw ConstraintViolationException when name is empty")
    public void save_ThrowConstraintViolationException_WhenNameIsEmpty() {
        Automaker anime = new Automaker();

        Assertions.assertThatThrownBy(() -> automakerRepository.save(anime))
                .isInstanceOf(ConstraintViolationException.class);
    }



}