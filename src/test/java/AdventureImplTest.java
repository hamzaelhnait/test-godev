import com.godev.entities.Hero;
import com.godev.entities.Position;
import com.godev.exceptions.InputNotValidException;
import com.godev.service.AdventureImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AdventureImplTest {

    private AdventureImpl adventure;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private AdventureImpl initializeAdventure(String mapFile, String inputFile) throws IOException {
        Path mapFilePath = Paths.get("src", "test", "resources", mapFile).toAbsolutePath();
        Path inputDataFilePath = Paths.get("src", "test", "resources", inputFile).toAbsolutePath();
        AdventureImpl adventure = AdventureImpl.create(mapFilePath.toString(), inputDataFilePath.toString());
        return adventure;
    }

    @Test
    void testExecuteAdventureFirstInput() throws IOException {
        adventure = initializeAdventure("carte.txt", "input1.txt");
        String result = adventure.executeAdventure();
        assertEquals("(9,2)", result);
    }

    @Test
    void testExecuteAdventureSecondInput() throws IOException {
        adventure = initializeAdventure("carte.txt", "input2.txt");
        String result = adventure.executeAdventure();
        assertEquals("(5,7)", result);
    }

    @Test
    void testMoveHeroValidMove() throws IOException {
        adventure = initializeAdventure("carte.txt", "input1.txt");
        adventure.setHero(Hero.builder().position(new Position(3, 0)).moves("S").build());
        adventure.moveHero('S');
        assertEquals("(3,1)", adventure.getHero().getCurrentPosition().toString());
    }

    @Test
    void testMoveHeroInvalidMove() throws IOException {
        adventure = initializeAdventure("carte.txt", "input1.txt");
        adventure.setHero(Hero.builder().position(new Position(3, 0)).moves("N").build());
        adventure.moveHero('N');
        assertEquals("(3,0)", adventure.getHero().getCurrentPosition().toString());
    }

    @Test
    void testExecuteMultipleAdventures() throws IOException {
        adventure = initializeAdventure("carte.txt", "input_multiple.txt");
        String result1 = adventure.executeAdventure();
        assertEquals("(9,2)", result1);
        String result2 = adventure.executeAdventure();
        assertEquals("(5,7)", result2);
        String result3 = adventure.executeAdventure();
        assertEquals("(6,9)", result3);
    }
    @Test
    void testInvalidInput() throws IOException{
        assertThrows(InputNotValidException.class, () ->initializeAdventure("carte.txt", "empty.txt"));
        assertThrows(InputNotValidException.class, () ->initializeAdventure("carte.txt", "non_valid_data.txt"));
    }
}
