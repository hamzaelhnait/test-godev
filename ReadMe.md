
### Structure du Projet

               └── src/
               ├── main/
               │ ├── java/
               │ │ ├── org/
               │ │ │ └── godev/
               │ │ │ ├── entities/
               │ │ │ │ ├── Hero.java
               │ │ │ │ ├── AdventureMap.java
               │ │ │ │ └── Position.java
               │ │ │ ├── exceptions/
               │ │ │ │ └── InputNotValidException.java
               │ │ │ ├── service/
               │ │ │ │ ├── IAdventure.java
               │ │ │ │ └── AdventureImpl.java
               │ │ │ └── utils/
               │ │ │ ├── ConstantUtils.java
               │ │ │ └── Helpers.java
               │ │ └── Main.java
               │ └── ressources/
               │ ├── input.txt
               │ └── carte.txt
               └── test/
               ├── java/
               │ └── AdventureImplTest.java
               └── ressources/
               ├── carte.txt
               ├── input1.txt
               ├── input2.txt
               ├── empty.txt
               ├── input_multiple.txt
               └── non_valid_data.txt
### Description

- **`AdventureMap.java`** : Définit la carte de l'aventure, y compris sa représentation en grille et ses dimensions.
- **`Hero.java`** : Représente le héros, y compris sa position sur la carte et les mouvements qu'il effectuera.
- **`Position.java`** : Définit la position du héros sur la grille.
- **`InputNotValidException.java`** : Exception personnalisée pour les données d'entrée non valides.
- **`AdventureImpl.java`** : Implemente la logique du jeu, y compris la lecture des fichiers de carte et d'entrée, l'exécution des mouvements et la validation de la position du héros.
- **`IAdventure.java`** : Interface définissant les méthodes pour l'exécution de l'aventure.
- **`ConstantUtils.java`** : Contient les constantes utilisées dans le jeu telles que les directions de mouvement.
- **`Helpers.java`** : Fournit des méthodes utilitaires pour lire les fichiers et valider les données.
- **`AdventureImplTest.java`** : Tests unitaires pour `AdventureImpl` utilisant JUnit.
### Design pattern utilisé
 1.Builder Pattern

### Comment Exécuter

1. **Compiler et Construire le Projet**

   Utilisez Maven pour compiler et construire le projet. Exécutez la commande suivante depuis le répertoire racine du projet :

   ```bash
   mvn clean compile
   ``` 
2. **Exécution**
  Pour éxecuter le projet java 
  ```bash
   java -cp <classpath> Main -f "<input-file>" "<map-file>"
  ``` 
### Exemple
   ```bash
      java -cp target/classes Main  
   ``` 
   ```bash
    java -cp target/classes Main -f "chemin/input.txt" "chemin/carte.txt"
   ``` 



  
   

