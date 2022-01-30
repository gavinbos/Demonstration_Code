//TIC-TAC-TOE [game]
// ...But with pointers

#include <iostream>
#include <string>
#include <sstream>
#include <ctime>
#include <cstdlib>

using namespace std;

const int SIZE = 3;

void displayBoard(string** board);
// generates the computers turn, using various forms of logic and set
// rules to placement to make the best move. However a probability of
// random placement was added to make the game fair to the player
void generateComputerMove(string** board, int &row, int &col);
bool userFirst();
bool currentPlayerWon(string** board, string symbol);

int main() {
  //construct board
  string** board = new string*[3];
  for (int i = 0; i < 3; i++) {
    board[i] = new string[3];
  }
  //sets up an pointer array of arrays. each index in the array
  //is an array itself, which results in the columns being made.

  int position_id = 1;
  for (int i=0; i< SIZE; i++) {
    for (int j=0; j < SIZE; j++) {
        board[i][j] = to_string(position_id);
      /*  stringstream ss;
        ss << position_id;
        board[i][j] = ss.str();
        */position_id++;
    }
  }

  //Initial game output
  cout << "Welcome to Tic-Tac-Toe" << endl;

  bool userTurn = userFirst();
  if (userTurn == true) {
    cout << "Player goes first!" << endl;
  } else {
    cout << "Computer goes first!" << endl;
  }

  //Loop for turn taking in game
  int positionsRemaining = SIZE * SIZE;
  bool userWon = false;
  bool computerWon = false;

  while ((positionsRemaining > 0)
        && (!userWon) && (!computerWon)) {
      displayBoard(board);

      //User's turn
      if (userTurn) {
        bool validMove = false;
        while (!validMove) {
          int position_id;
          cout << "Enter a position: ";
          cin >> position_id;
          if ((position_id <= (SIZE*SIZE))
              && (position_id > 0)) {
            int row = (position_id-1)/SIZE;
            int col = (position_id-1)%SIZE;
            //cout << "row = " << row << " col = " << col << endl;
            if ((board[row][col] != "X")
                && (board[row][col] != "O")) {
                  board[row][col] = "X";
                  validMove = true;
            } else {
              cout << "Position already used. Try again." << endl;
            }

          } else {
            cout << "Position invalid. Try again." << endl;
          }
        }
        positionsRemaining--;
        userWon = currentPlayerWon(board, "X");
        userTurn = false;
      }

      //Computer's turn
      else {
        //The row and col are both passed as
        //call-by-reference
        int row, col;
        generateComputerMove(board, row, col);
        
        positionsRemaining--;
        computerWon = currentPlayerWon(board, "O");
        userTurn = true;
      }
  }

  //Display game result
  displayBoard(board);
  if (userWon) {
    cout << "Congratulations! You have won!" << endl;
  } else if (computerWon) {
    cout << "The computer has won. Try again." << endl;
  } else {
    cout << "Out of moves. Try again." << endl;
  }
  return 0;
}

void displayBoard(string** b) {
  cout << "Tic-tac-toe board:" << endl << endl;
  for (int i=0; i < SIZE; i++) {
    for (int j=0; j < SIZE; j++) {
        cout << b[i][j] << "\t";
    }
    cout << endl;
  }
  cout << endl;
}

bool userFirst() {
  //set seed for random number generation
  srand(time(NULL));

  //generate a random number
  //0 for computer
  //1 for user
  int num = rand()%2;
  if (num == 0) {
    return false;
  }
  return true;
}

//Return true if player/computer with symbol (X or O) has won
bool currentPlayerWon(string** b, string symbol) {
  //Horizontal case
  //Loop through each row
  for (int i=0; i < SIZE; i++) {
    bool rowWinDetected = true;
    //Check all positions in row and see if they are the same symbol
    for (int j = 0; j < SIZE; j++) {
      if (b[i][j] != symbol) {
        rowWinDetected = false;
      }
    }
    if (rowWinDetected) {
      return true;
    }
  }

  //Vertical case
  //Loop through each column
  for (int i=0; i < SIZE; i++) {
    bool colWinDetected = true;
    //Check all positions in column and see if they are the same symbol
    for (int j = 0; j < SIZE; j++) {
      if (b[j][i] != symbol) {
        colWinDetected = false;
      }
    }
    if (colWinDetected) {
      return true;
    }
  }

  //Diagonal case #1
  bool diagonal1WinDetected = true;
  for (int i=0; i < SIZE; i++) {
    if (b[i][i] != symbol) {
      diagonal1WinDetected = false;
    }
  }
  if (diagonal1WinDetected) {
    return true;
  }

  //Diagonal case #2
  bool diagonal2WinDetected = true;
  for (int i=0; i < SIZE; i++) {
    if (b[(SIZE-1)-i][i] != symbol) {
      diagonal2WinDetected = false;
    }
  }
  if (diagonal2WinDetected) {
    return true;
  }

  //otherwise win not diagonal2WinDetected
  return false;

}

void generateComputerMove(string** board, int &row, int &col) {
	int position[4] = {0, 0, 0, 0};
	int index = 0;
	int num = 1;
	int pos_1 = 0;
	int pos_2 = 0;
	int O_position = 0;
	
	for (int i = 0; i < SIZE; i++) {
		for (int j = 0; j < SIZE; j++){
			if (board[i][j] == "X") {
				position[index] = num;
				index += 1;
			}
			num += 1;
		}
	}
	// creates an array of the positions of the players pieces
	
	for (int i = 3; i >= 0; i--) {
		if (position[i] != 0 && i != 0) {
			pos_1 = position[i];
			pos_2 = position[i-1];
			break;
		}
		
		else if ((position[i] != 0) && (i == 0)) {
			pos_1 = position[i];
		}
	}
	// defines a position 1 and 2, where 2 is the second last move
	// by the player, and 1 is the last. There is only two as the game
	// is won by getting three in a row, so using the last two 
	// placements my algorithm will try to predict the next move
	
	if ((pos_1 == 0) && (pos_2 == 0)) {
		row = rand()%SIZE;
		col = rand()%SIZE;
		board[row][col] = "O";

	}
	// if there is 0 player pieces, the computer will randomly
	// generate a position
	
	else if ((pos_1 != 0) && (pos_2 == 0)) {
		
		int num = 0;
		
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++){
				if (board[i][j] == "O") {
					O_position = num + 1;
				}
				num += 1;
			}
		}
		// will find the position of the previous randomly
		// generated piece (if it exists)
		
		if (O_position != 0) {
			int choice = rand()%2;
			
			if ((O_position == 1) || (O_position == 3)) {
				if (board[(O_position + 2)/SIZE][(O_position - 1)%SIZE] != "X") {
					board[(O_position + 2)/SIZE][(O_position - 1)%SIZE] = "O";
				}
				
				else if ((O_position == 1) && (choice == 1)) {
					board[0][1] = "O";
				}
				
				else if ((O_position == 3) && (choice == 1)) {
					board[0][1] = "O";
				}
				
				else if (choice == 0) {
					board[1][1] == "O";
				}
			}
			// sets rules for if the random piece is in either
			// of the upper corners, it will always try to
			// place below first, then either diagonal or
			// beside which is randomly chosen
			
			if ((O_position == 7) || (O_position == 9)) {
				int choice = rand()%2;
				if (board[(O_position - 4)/SIZE][(O_position - 4)%SIZE] != "X") {
					board[(O_position - 4)/SIZE][(O_position - 4)%SIZE] = "O";
				}
				
				else if ((O_position == 7) && (choice == 1)){
					board[2][1] = "O";
				}
				
				else if ((O_position == 9) && (choice == 1)) {
					board[2][1] = "O";
				}
				
				else if (choice == 0) {
					board[1][1] == "O";
				}
			}
			// sets rules for if the random piece is in either
			// of the lower corners, it will always try to
			// place above first, then either diagonal or
			// beside which is randomly chosen
			
			if ((O_position == 4) || (O_position == 6)) {
				if (board[1][1] != "X") {
					board[1][1] = "O";
				}
				
				else if (O_position == 4) {
					int choice = rand()%2;
					if (choice == 0) {
						board[0][0] = "O";
					}
					if(choice == 1) {
						board[2][0] = "O";
					}
				}
				
				else if (O_position == 6) {
					int choice = rand()%2;
					if (choice == 0) {
						board[0][2] = "O";
					}
					if(choice == 1) {
						board[2][2] = "O";
					}
				}
				
			}
			// sets rules for side positions
			
			if ((O_position == 2) || (O_position == 8)) {
				if (board[1][1] != "X") {
					board[1][1] = "O";
				}
				
				else if (O_position == 2) {
					int choice = rand()%2;
					if (choice == 0) {
						board[0][0] = "O";
					}
					if(choice == 1) {
						board[0][2] = "O";
					}
				}
				
				else if (O_position == 8) {
					int choice = rand()%2;
					if (choice == 0) {
						board[2][0] = "O";
					}
					if(choice == 1) {
						board[2][2] = "O";
					}
				}
				
			}
			// sets rules for top and bottom positions
			
			if (O_position == 5) {
				
				int choice = rand()%10;
				
				while(choice = 5) {
					choice = rand()%10;
				}
				
				if (board[choice-1/SIZE][choice-1%SIZE] != "X") {
					board[choice-1/SIZE][choice-1%SIZE] = "O";
				}
				
			}
			// sets rules for center position	
		}
		
		if (O_position == 0) {
			row = rand()%SIZE;
			col = rand()%SIZE;
			
			while (board[row][col] == "X") {
				row = rand()%SIZE;
				col = rand()%SIZE;
			}
			
			board[row][col] = "O";
		}
		// this is of course the case where there is no 
		// prior computer piece, so the placement does not
		// matter
	}
	
	else {
		int chance = rand()%5;
		
		if (chance == 0) {
			row = rand()%SIZE;
			col = rand()%SIZE;
			while((board[row][col] == "X") || (board[row][col] == "O")) {
				row = rand()%SIZE;
				col = rand()%SIZE;
			}
			board[row][col] = "O";
		}
		// in order to make the game fair, there is a 1/5 			// probability that the computer will randomly place
		// rather than strategically
		
		else {
			if (pos_2 <= 3) {
				if (pos_2 + 3 == pos_1) {
					if (board[(pos_2 + 5)/SIZE][(pos_2 - 1)%SIZE] != "O") {
						board[(pos_2 + 5)/SIZE][(pos_2 - 1)%SIZE] = "O";
					}
				}
				
				if (pos_2 + 6 == pos_1) {
					if (board[(pos_2 + 2)/SIZE][(pos_2 + 2)%SIZE] != "O") {
						board[(pos_2 + 2)/SIZE][(pos_2 + 2)%SIZE] = "O";	
					}
				}	
			
				if (pos_2 + 1 == pos_1) {
					if (board[(pos_2 + 1)/SIZE][(pos_2 + 1)%SIZE] != "O") {
						board[(pos_2 + 1)/SIZE][(pos_2 + 1)%SIZE] = "O";
					}
				}
				
				if (pos_2 + 2 == pos_1) {
					if (board[pos_2/SIZE][pos_2%SIZE] != "O")
					board[pos_2/SIZE][pos_2%SIZE] = "O";
				}
			}
			// these are the logic rules for placement, if
			// the players second last piece is in the first 
			// row
			
			else if ((pos_2 > 3) && (pos_2 <=6)) {
				if (pos_2 + 3 == pos_1) {
					if (board[(pos_2 - 4)/SIZE][(pos_2 - 4)%SIZE] != "O") {
						board[(pos_2 - 4)/SIZE][(pos_2 - 4)%SIZE] = "O";
					}
				}	
			
				if (pos_2 + 1 == pos_1) {
					if (board[(pos_2 + 1)/SIZE][(pos_2 + 1)%SIZE] != "O") {
						board[(pos_2 + 1)/SIZE][(pos_2 + 1)%SIZE] = "O";
					}
				}
				
				if (pos_2 + 2 == pos_1) {
					if (board[pos_2/SIZE][pos_2%SIZE] != "O") {
					board[pos_2/SIZE][(pos_2-1)%SIZE] = "O";
					}
				}
			}
			// logic rules for the other rows
			
			else {
				row = rand()%SIZE;
				col = rand()%SIZE;
				while((board[row][col] == "X") || (board[row][col] == "O")) {
					row = rand()%SIZE;
					col = rand()%SIZE;
				}
				board[row][col] = "O";
			}
			// if none apply randomly place
		
		}
	}
}
