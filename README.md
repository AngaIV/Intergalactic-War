Group name: XP Merchants

Group members: Anga Peter, Liteta Tosi, Caiphus Mahlatji

Group mentor: Alden Boby

Project title: Intergalactic War

Project page: https://github.com/AngaIV/Intergalactic-War

Instructions for use (allowable actions): To run the game, open the "GAME" folder and open the following folders in the sequence provided "IntergalacticWar->RUN GAME HERE->dist", once open download and double tap on the "IntergalacticWar.jar" file. To run the project from the command line, go to the dist folder and type the following: java -jar "IntergalacticWar.jar". At the title screen, to play a new game press on the "0" key that's above the letter keys(NOT THE ONE ON THE NUMBERS ON THE SIDE OF THE KEYBOARD), or press "1" key to quit the game. To move player character use the arrow keys on the keyboard, to pause the game press the 'P' key and to shoot, press the 'S' key. You will find the game code inside the "src" folder. 

Tools used: Edited: Adobe Photoshop and Piskel were utilized for crafting and enhancing in-game characters and various visual elements.

Concepts used: The Loop concept serves as the base for the game, the game only runs as intended no-stop until a player completes the game or dies if the loop is implemented. 
              - Threads: The "Runnable" interface is implemented in the code, and a second thread is created to execute the game loop. This is how multithreading is used in the project.
              - Loops: The code utilizes for loops to iterate through lists of game objects, such as bullets, bots, and power-ups.
              - Event Handling: Event handling is achieved through the KeyListener implemented in the KeyHandler class. It listens for keyboard input and responds to user actions.
              - Arrays: Arrays are used to store and manipulate game-related data, such as the levels of bots to be created.
              - Comments: Comments are used throughout the code to provide explanations and document the functionality of various parts of the code.
              - Constants: Constants are defined using public static final variables to represent game states, screen dimensions, and other fixed values.
              - Mathematical Calculations: Mathematical calculations are used for collision detection, distance calculations, and other game physics calculations.
              - Inheritance: The GPanel class inherits from the JPanel class, and it overrides some of its methods. This is just one example of inheritance used in the project.
              - Probability: We implemented the power-up system using probability. Some power-ups are more likely to drop after destroying an enemy than others.

Assumptions: The game consists of only three levels, each comprising four waves. We expect the game to become progressively more challenging as you advance, eventually reaching a point where it becomes nearly impossible to complete. You have to gather and preserve your power-ups in order to increase your chances of beating the game.

Limitations: There are no levels beyond level 3, wave 4. The probability of the player taking damage from an enemy bullet while not firing is low.
