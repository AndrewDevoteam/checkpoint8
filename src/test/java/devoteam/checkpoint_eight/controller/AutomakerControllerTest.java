package devoteam.checkpoint_eight.controller;
import devoteam.checkpoint_eight.domain.Automaker;
import devoteam.checkpoint_eight.service.AutomakerService;
import devoteam.checkpoint_eight.util.AutomakerCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
@ExtendWith(SpringExtension.class)
class AutomakerControllerTest {
    @InjectMocks
    private AutomakerController automakerController;
    @Mock
    private AutomakerService automakerServiceMock;

    @BeforeEach
    public void setUp(){
        List<Automaker> automakers = List.of(AutomakerCreator.createValidAutomaker());
        BDDMockito.when(automakerServiceMock.listAll())
                .thenReturn(automakers);

        BDDMockito.when(automakerServiceMock.findByID(ArgumentMatchers.anyInt()))
                .thenReturn(AutomakerCreator.createValidAutomaker());

        BDDMockito.when(automakerServiceMock.findByName(ArgumentMatchers.anyString()))
                .thenReturn(AutomakerCreator.createValidAutomaker());

        BDDMockito.when(automakerServiceMock.save(AutomakerCreator.createAutomakerToBeSaved()))
                .thenReturn(AutomakerCreator.createValidAutomaker());

        BDDMockito.doNothing().when(automakerServiceMock).delete(ArgumentMatchers.anyInt());

        BDDMockito.when(automakerServiceMock.save(AutomakerCreator.createValidAutomaker()))
                .thenReturn(AutomakerCreator.createValidUpdatedAutomaker());
    }
    @Test
    @DisplayName("listAll returns a list of animes when successfull")
    public void ListAll_ReturnListOfAnimeInsidePageObject_WhenSuccessfull(){
        String expectedName = AutomakerCreator.createValidAutomaker().getName();

        List<Automaker> automakerList = automakerController.listAll().getBody();

        Assertions.assertThat(automakerList).isNotNull();
        Assertions.assertThat(automakerList).isNotEmpty();
        Assertions.assertThat(automakerList.get(0).getName()).isEqualTo(expectedName);

    }
    @Test
    @DisplayName("findById returns an Automaker when successfull")
    public void findById_ReturnsAnime_WhenSuccessfull(){
        Integer expectedId = AutomakerCreator.createValidAutomaker().getId();

        Automaker automaker = automakerController.findById(1).getBody();

        Assertions.assertThat(automaker).isNotNull();
        Assertions.assertThat(automaker.getId()).isNotNull();
        Assertions.assertThat(automaker.getId()).isEqualTo(expectedId);

    }
    @Test
    @DisplayName("findByName returns an Automaker when successfull")
    public void findByName_ReturnsListOfAnime_WhenSuccessfull(){
        String expectedName = AutomakerCreator.createValidAutomaker().getName();

        Automaker automaker = automakerController.findByName("DBZ").getBody();

        Assertions.assertThat(automaker).isNotNull();
        Assertions.assertThat(automaker.getName()).isNotEmpty();
        Assertions.assertThat(automaker.getName()).isEqualTo(expectedName);

    }

    @Test
    @DisplayName("save creates an Automaker when successfull")
    public void save_CreatesAnAnime_WhenSuccessfull(){
        Integer expectedId = AutomakerCreator.createValidAutomaker().getId();

        Automaker automakerToBeSaved = AutomakerCreator.createAutomakerToBeSaved();

        Automaker automaker = automakerController.save(automakerToBeSaved).getBody();

        Assertions.assertThat(automaker).isNotNull();
        Assertions.assertThat(automaker.getId()).isNotNull();
        Assertions.assertThat(automaker.getId()).isEqualTo(expectedId);

    }
    @Test
    @DisplayName("delete removes an Automaker when successfull")
    public void delete_RemovesAnAnime_WhenSuccessfull(){


        ResponseEntity<Void> responseEntity = automakerController.delete(1);
        Assertions.assertThat(responseEntity).isNotNull();
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        Assertions.assertThat(responseEntity.getBody()).isNull();

    }

    @Test
    @DisplayName("update save updated Automaker when successfull")
    public void update_SaveUpdatedAnime_WhenSuccessfull(){

        ResponseEntity<Void> responseEntity = automakerController.update(AutomakerCreator.createValidAutomaker());

        Assertions.assertThat(responseEntity).isNotNull();
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        Assertions.assertThat(responseEntity.getBody()).isNull();

    }

}