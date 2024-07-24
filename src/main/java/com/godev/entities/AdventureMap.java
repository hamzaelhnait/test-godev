package com.godev.entities;




public class AdventureMap {
  private char[][] coordinatesComponents;

  public int getRows(){return coordinatesComponents.length;}

  public int getColumns(){return coordinatesComponents.length>0 ? coordinatesComponents[0].length : 0;}

  public char[][] getCoordinatesComponents() {return coordinatesComponents;}

  public void setCoordinatesComponents(char[][] coordinatesComponents) {this.coordinatesComponents = coordinatesComponents;}

  public static Builder builder(){return new Builder();}

  private AdventureMap(Builder builder){this.coordinatesComponents=builder.coordinatesComponents;}

  public static class Builder{
        private char[][] coordinatesComponents;
        public Builder coordinatesComponents(char[][] coordinatesComponents){
          this.coordinatesComponents = coordinatesComponents;
          return this;
        }

        public AdventureMap build(){return new AdventureMap(this);}

  }

}
