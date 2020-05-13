# Game of Life
## The Game
The Game of Life is a cellular automaton game invented by Cambridge mathematician John Conway.

It consists of a collection of cells which, based on a few mathematical rules, can live, die or multiply. Depending on the initial conditions, the cells form various patterns throughout the course of the game.

## The Rules
For a space that is populated:
* Each cell with one or no neighbor dies, as if by solitude.
* Each cell with four or more neighbors dies, as if by overpopulation.
* Each cell with two or three neighbors survives.

For a space that is empty or unpopulated:
* Each cell with three neighbors becomes populated.

## The Controls
When you open the application you will see a window like picture below.

![startview](https://github.com/NarawishS/pa4-NarawishS/blob/master/src/image/configure.jpg?raw=true)

1. choose the resolution
2. choose cell size
3. check loop if you want the game to loop around border

the application should look like this after you click Play!

![mainview](https://github.com/NarawishS/pa4-NarawishS/blob/master/src/image/main.jpg?raw=true)

to draw you can press and drag on board to set cell status.

after you make change to the board(load, draw, erase, random, clear) the day count will be reset.

```
Button
1. draw:    change mode of your mouse to set cell to alive
2. erase:   change mode of your mouse to set cell to dead
3. clear:   clear all cell on board
4. random:  random all cell on board
5. speed:   change speed of the game(fast-slow)
6. start:   run the game
7. stop:    pause the game
8. step:    run the game one frame
```

you can save or load file in menu(save file does not save day count and can't load file from different size and resolution)

## How to run
you can open with jar file.

if you can't then
1. run this program in your ide
2. If you are using JDK 8-10 then JavaFX is included. You don't need to add any libraries.
3. If you are using Java JDK 11, then install JavaFX 11 from https://gluonhq.com/products/javafx.
4. set up your ide to run JavaFX.
[see more](https://openjfx.io/openjfx-docs/)

run in command > `java -jar Game_of_life-NarawishS.jar` if you are using JDK 8

For Java 11 you need to specify the module path for JavaFX

run in command > `java --module-path /path/to/javafx11/lib/ --add-modules javafx.controls -jar lib.Game_of_life-NarawishS.jar`

## UML of application
![appUML](https://github.com/NarawishS/pa4-NarawishS/blob/master/src/image/uml.png?raw=true)

## External lib
[JDK](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)

[JavaFX](https://openjfx.io/)