package com.godev.service;

import com.godev.entities.AdventureMap;
import com.godev.entities.Hero;
import com.godev.entities.Position;
import com.godev.exceptions.InputNotValidException;
import com.godev.utils.Helpers;

import java.io.IOException;
import java.util.List;

import static com.godev.utils.ConstantUtils.MAP_MOVEMENTS_COORDINATES;

public class AdventureImpl implements IAdventure{


    private Hero hero;
    private  AdventureMap adventureMap;
    private final String mapFile ;
    private final String inputDataFile ;
    private int currentIndexInput ; //variable utilisé si un fichier contient plusieurs input
    private static List<String> inputContent ;



    public AdventureImpl(String mapFile, String inputDataFile, List<String> data) {
        this.mapFile = mapFile;
        this.inputDataFile = inputDataFile;
        inputContent = data;
        currentIndexInput = 0 ;
    }
    public static AdventureImpl create(String mapFile, String inputDataFile) throws IOException {
        AdventureImpl adventure = new AdventureImpl(mapFile, inputDataFile,  Helpers.readDataInput(inputDataFile));
        adventure.initMap();
        return adventure;
    }

    @Override
    public String executeAdventure() throws IOException {
        initHero(); // pour chaque nouveaux input il faut initialiser si il y'a plusieur dans un meme fichier
        for(char direction : hero.getMoves().toCharArray()){
            moveHero(direction);
        }
        return hero.getCurrentPosition().toString();
    }

    @Override
    public void moveHero(char direction) {
         final int distanceOfMove = MAP_MOVEMENTS_COORDINATES.get(direction);
         if(!canHeroMove(direction)){
                return;
         }
        if(direction=='E' || direction =='O') hero.moveX(distanceOfMove);
        else hero.moveY(distanceOfMove);

    }

    public boolean canHeroMove(char direction){
        int x = hero.getCurrentPosition().x;
        int y = hero.getCurrentPosition().y;
        int dx=x;
        int dy=y;

        if(direction == 'E' || direction=='O')  {
            dx += MAP_MOVEMENTS_COORDINATES.get(direction);
        }
        else {
            dy+=MAP_MOVEMENTS_COORDINATES.get(direction);
        }
        boolean canSlide = dy<adventureMap.getRows() && dy>=0 && dx>=0 && dx< adventureMap.getColumns();
        if(!canSlide){
            return false;
        }


        return adventureMap.getCoordinatesComponents()[dy][dx]!='#';



    }

    private void initMap() throws IOException {
        char[][] maze = Helpers.fileToCharMatrix(mapFile);
        this.adventureMap =  AdventureMap.builder()
                .coordinatesComponents(maze).build();
    }


    private void initHero() throws IOException{
        List<Position> positions = Helpers.getPositions(inputContent);
        List<String> moves= Helpers.getMovementInput(inputContent);
        if(moves.size()!=positions.size()){
            throw new InputNotValidException("you should have the same number of  moves and positions");
        }
        if (!positions.isEmpty() && currentIndexInput<inputContent.size()/2) {
                this.hero = Hero.builder().
                        position(positions.get(currentIndexInput)).
                        moves(moves.get(currentIndexInput)).
                        build();
                currentIndexInput++;
        }


    }

    public static List<String> getInputContent(){
        return inputContent;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

}
