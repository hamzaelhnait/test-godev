package com.godev.utils;

import com.godev.entities.Position;
import com.godev.exceptions.InputNotValidException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.IntStream;

import static com.godev.utils.ConstantUtils.MAP_MOVEMENTS_COORDINATES;

public class Helpers {
    public static char[][] fileToCharMatrix(String path) throws IOException {
       return Files.readAllLines(Path.of(path)).stream().map(String::toCharArray).toArray(char[][]::new);
    }
    public static List<Position> getPositions(List<String> inputFileContent){
           return IntStream
                   .range(0 , inputFileContent.size())
                   .filter(index->index%2==0)
                   .mapToObj(index -> getPositionFromIndex(inputFileContent.get(index)))
                   .toList();

    }
    public static List<String> getMovementInput(List<String> inputFileContent){
        return IntStream
                .range(0 , inputFileContent.size())
                .filter(index->index%2!=0)
                .mapToObj(inputFileContent::get)
                .toList();

    }

    public static List<String> readDataInput(String path) throws IOException{
        List<String> inputFileContent = Files
                .readAllLines(Path.of(path), StandardCharsets.UTF_8)
                .stream().
                filter(l->!l.isEmpty()).toList();
        validateDataInput(inputFileContent);
        return inputFileContent;
    }
    private static void validateDataInput(List<String> content){

        if(content.size()<2) throw new InputNotValidException("you should give position and movements");
        // si il y'a plusieurs input on valide toute les lignes

        for(int i=0 ; i<content.size() ; i++){
            if(i%2==0){
                validatePosition(content.get(i).trim().split(","));
                continue;
            }
            validateMovements(content.get(i).trim());
        }

    }
    private static void validatePosition(String[] pos){
        if(pos.length!=2){
            throw new InputNotValidException("invalid position [x,y]");}
        if(!pos[0].matches("[0-9]+") || !pos[1].matches("[0-9]+")){
            throw new InputNotValidException("position should be numbers with , :[x,y]");}
    }
    private static void validateMovements(String moves){
        for(int i=0 ; i<moves.length() ; i++){
            if(MAP_MOVEMENTS_COORDINATES.get(moves.charAt(i))==null){
                throw new InputNotValidException(String.format("invalid move %c only (N,S,E,O) are accepted" , moves.charAt(i)));}
        }
    }
    private static Position getPositionFromIndex(String pos){
        String[] splitedPosition = pos.split(",");
        return  new Position(Integer.parseInt(splitedPosition[0])
                , Integer.parseInt(splitedPosition[1]));

    }}

