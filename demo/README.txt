 Our game is a little glitchy because of some version error in javafx. So if the game glitches out, please restart it, it works most of the time.

Implementation details:
    We have a class for every object we could find in the game : Block, Stick, Character, Cherry, Score, Background music. The scores class implements the singleton design pattern and the background music is a factory for everty sound there is in the game.
We have a driver class for the game : GameController. It does everything and handles the logic of the game. Similarly, we have controllers for the gameOver screen and the homeScreen which have the implementation of the logic of the respective screens. We have implemented the game with multithreading, for the bonus. The methods used, like growStick, which require a smooth animation have been done using multithreading.

For playing the game, go to the StickHero class and run it.

The JUnit tests are also included, which we're using for checking if the character is supposed to die or move forward. The methods in the tests check if the stick has landed in the correct place, with respect to the block or not. I've hardcoded the values no, but it can obviously be changed as per the requirement in the test, and if obviously being dynamically determined in the game.

git link : https://github.com/syam22528/Ap-project/tree/main