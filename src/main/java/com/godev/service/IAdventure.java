package com.godev.service;



import java.io.IOException;

public interface IAdventure {
    String  executeAdventure() throws IOException;
    void moveHero(char direction);

}
