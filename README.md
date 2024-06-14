# BlackJack Game in Java

This is a simple BlackJack game implemented in Java using Swing for the graphical user interface. The game includes basic functionality for a player and a dealer, with features such as drawing cards, checking for BlackJack, and determining the winner.

## Table of Contents

- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [Game Rules](#game-rules)
- [Credits](#credits)

## Features

- Graphical user interface using Java Swing.
- Basic BlackJack rules implemented.
- Player can draw cards ("Hit") or end their turn ("Stay").
- Dealer draws cards automatically after the player stays.
- Checks for BlackJack and handles Ace values dynamically.
- Displays game results and updates the game panel in real-time.

## Installation

To run the BlackJack game, ensure you have Java installed on your system. Follow these steps:

1. Clone the repository or download the source code.

    ```sh
    git clone https://github.com/yourusername/blackjack.git
    ```

2. Navigate to the project directory.

    ```sh
    cd blackjack
    ```

3. Compile the Java source files.

    ```sh
    javac BlackJack.java
    ```

4. Run the application.

    ```sh
    java BlackJack
    ```

## Usage

Once the application is running, you can interact with the game using the following controls:

- **Hit**: Click the "Hit" button to draw a card.
- **Stay**: Click the "Stay" button to end your turn and let the dealer draw cards.

The game panel will display the cards for both the player and the dealer, as well as the current scores. The result of the game will be shown once the player stays and the dealer finishes drawing cards.

## Game Rules

- The game starts with the player and the dealer each being dealt two cards.
- The player can draw additional cards by clicking "Hit".
- The player can end their turn by clicking "Stay".
- The dealer draws cards until their hand value is at least 17.
- The value of Aces can be either 1 or 11, depending on the total hand value.
- The game checks for BlackJack (an Ace and a 10-value card) at the start for both the player and the dealer.
- The winner is determined based on the final hand values, with the following outcomes:
  - Player wins if their hand value is higher than the dealer's, without exceeding 21.
  - Dealer wins if their hand value is higher than the player's, without exceeding 21.
  - A tie occurs if both hand values are the same.
  - If either the player or the dealer exceeds 21, they lose.

## Screenshots
*Game Start screen:*
![Game Start](screenshots/game_start.png)


*End of the game screen:*
![Game Over](screenshots/game_over.png)


## Contributing

Contributions are welcome! If you have any improvements or new features to add, please follow these steps:

1. Fork the repository.
2. Create a new branch (`git checkout -b feature/your-feature`).
3. Make your changes.
4. Commit your changes (`git commit -m 'Add some feature'`).
5. Push to the branch (`git push origin feature/your-feature`).
6. Open a Pull Request.
