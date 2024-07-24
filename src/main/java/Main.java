import com.godev.exceptions.InputNotValidException;
import com.godev.service.AdventureImpl;
import com.godev.service.IAdventure;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        try {
            String pathInput;
            String pathMap;
            int indexFlag=indexFlag(args);
            if (indexFlag!=-1) {
                pathInput = Paths.get(args[indexFlag + 1]).normalize().toString();
                pathMap = Paths.get(args[indexFlag + 2]).normalize().toString();
            } else {
                pathInput = Paths.get("src", "main", "resources", "input.txt").toAbsolutePath().normalize().toString();
                pathMap = Paths.get("src", "main", "resources", "carte.txt").toAbsolutePath().normalize().toString();
            }
            IAdventure iAdventure = AdventureImpl.create(pathMap, pathInput);
            for (int i = 0; i < AdventureImpl.getInputContent().size() / 2; i++) {
                    LOGGER.info(String.format("[inputNumero=%d , resultat=%s]" ,i+1 , iAdventure.executeAdventure() ));
            }

        } catch (IOException | InputNotValidException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            //e.printStackTrace();
        }
    }
    public static int indexFlag(String... args) {
        List<String> argsList = Arrays.asList(args);
        int indexOfFileFlag = argsList.indexOf("-f");
        if( indexOfFileFlag + 2 >= argsList.size() ){
            return indexOfFileFlag;
        }
        return indexOfFileFlag;
    }



}
