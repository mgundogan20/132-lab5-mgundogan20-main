### Collaboration Rules
This lab  is an individual work. Everyone should submit their own work. You can use any material from the course, or any other resource on Java programming language. You can discuss lecture related topics, the problem at the concept level with other students, but not the solution. You can reuse (copy-paste) code from the lecture notes, textbook, or code given in the lab, but not from others (internet sources, other students etc.)

### Honor Code - by submitting lab work I accept:
- This code is my own work. 
- I did not consult to any program written other students. 
- I did not search for the solution of the problem in the internet.
- I did not copy-paste code written by others.
- This code is my own work.

### IMPORTANT

* DON'T COPY PASTE code from anybody or any other resource other than allowed ones. The code should be written by you. The code similarity tools will find such codes and disciplinary action will be taken.

* You should solve the problem the way we described. By not following instructions, you may lose points.

* Check Blackboard LAB5 for possible corrections.

* Print out in the same format, points will be taken off, if you don't print in the same format.

### LATE SUBMISSION 

Each Lab has its late submission rule. Lab5 late submission is :
* Regular Submission: Noon 12pm March 26, at the end of the lab
* Late Submission: March 26
   * 12:05: -2 points
   * 12:10: -5 points
   * 23:59: -50 points
   * No bonus for late submissions, no partial point for the bonus.

# Object-Oriented Knight's Tour
In this Laboratory Assignment, we are going to revisit the Knight's Tour problem. As you have done in Lab 3, you will implement the single tour and 1000 tours, different strategies for the knight such as random, heuristics moves. However this time, you are expected to implement the same application with an object-oriented approach. The purpose of the lab is to demonstrate the advantages of OOP: code reuse (using inheritance), polymorphism  - adding a new type (knight or tour) without changing the rest of the code.

The program is organized in three packages **lab5**, **knighttour**, and **knighttour.knight**.  The classes in each package  (and their very brief descriptions) are:

* lab5
   * Lab5Main : this class is the driver class that contains the main method.
* knighttour.knight 
  * Knight : this class represents a knight which  moves in a fixed order (see lab3). 
  * RandomKnight:  This class represents a knight that  moves in random order (see lab3). It is a subclass of Knight.
  * HeuristicKnight: This class represents a knight that moves using the heuristic algorithm as described in lab3. It is a subclass of Knight.
* knighttour:
  * KnightTour: this class represents a single tour. It has a board, a knight. It performs one single tour of  the knight on the board.
  * FixedTour: this class is responsible for making 1000 tours. It is a subclass of KnightTour.
  * Board: this class represents the board. It is responsible to record the moves that knight performs.

Now, we will explain each class in detail, especially, their public methods (services that they provide to other classes). The program is a collection of classes interacting with each other using their public methods. For example, Knight class will call Board class's isValid(row,column) method to check if it can move to a position etc. It is the Board class that knows if a square is available for a move or not. Similarly, KnightTour will call makeMove() method of Knight to move the knight to a next position etc.

The prelab source code comes with the main class Lab5Main. Lab5Main is in the package **lab5**.  

First, you are asked to create the packages **knighttour** and **knighttour.knight**. 

---

## PreLab Lab5Main
---
Make necassary changes in Lab5Main.java so that it can access classes in **knighttour** and **knighttour.knight** packages.

---

## Board Class
---
#### Board Class Private Instance Variables: 
Board class is in the knightour package. Board Class has the following instance variables. Instance variable of this class is not visible to other classes.

1. ```int[][] chessBoard``` 
2. ```int moveCount``` keeps the current move count.
3. ```int size``` size of the board.
 
 
#### Board Class Public Methods: 
You will define two constructors for this class, their prototype should look like this: 
```java
1. public Board () 
2. public Board (int size) 
```

+ The first constructor should create an 8x8 chessboard using the second constructor.
+ Do not forget to initialize private variables in constructors.

Additionally, implement the following public methods:

```java
int getMoveCount() // getter for moveCount
int getSize() // getter for size
boolean isValid(int row, int column)
```

+ isValid returns false if the move defined by the row and column variables is off the chessboard or the knight has already visited that position, otherwise true.

```java
void clear()
```

+ When it is called, the method should re-inialize the board and set the moveCount variable to zero.
 
```java
void addMove(int row, int column)
```

+ It increments moveCount, then the given position in the chessboard is  marked with the value of moveCount.

```java
boolean isFull()
```

+ This method returns true if the board is full, meaning the number of maximum moves is reached. 

```java
void printBoard()
```

+ Write a method to print the current status of board. An example output is shown below: (can use code from lab3 soln)

```java
    0    1    2    3    4    5    6    7

0    0    6   19   26    9    4   15   12
1   20   27    8    5   14   11   42    3
2    7   18   25   10   41   16   13   32
3   28   21   40   17   24   31    2   43
4   39   36   23   30    1   44   33   52
5   22   29   38   45   34   51    0   49
6   37   46   35    0    0   48   53    0
7    0    0    0   47   54    0   50    0

```


## Knight Class 
---

Inside the **knighttour** package, create a package called **knight**  (if you did not create before) which will include the class implementations of knights that use different strategies to move on the board.

#### Knight Class Protected Instance Variables: 

**Note:** *protected* modifier is used to specify the members that can be accessed within its package or by its subclasses. As we will use these members in its subclasses, their modifier should be defined as **protected**.

Every instance of the Knight Class should have the following protected instance variables:
 
1. ```int currentRow```
2. ```int currentColumn```
3. ```Board board```

In addition, Knight Class needs the following protected static variables which you should remember from the Lab 3:

1. ```static final int[] horizontal = {2, 1, -1, -2, -2, -1, 1, 2}```
2. ```static final int[] vertical = {-1, -2, -2, -1, 1, 2, 2, 1}```
3. ```static final Random random```

#### Knight Class Public Methods:
+ You will define one constructor for this class, its prototype should look like this: 
```java
public Knight(Board board)
```
+ When the Knight instance is created, it initializes its board instance variable with the provided board parameter. The currentRow and currentColumn variables should be assigned to random values generated by Random. Then, this initial move should be added to the board using the appropriate method you implemented in Board class.

```java
void reset()
```

+ This method should reset the knight by choosing a new initial position and by adding that position as a move to the board.

```java
boolean makeMove()
```

+ This method should try to find a possible move using the horizontal and vertical arrays. It checks it in a fixed order starting from 0 to 7 - see lab3). It should call the board object, if the move is valid (using isValid of Board) or not. If the move is valid, it should add the move to the board and return true. If there is no possible move, it returns false. 

## RandomKnight Class
---
Create the RandomKnight Class in the **knight** package. This class should inherit from the Knight Class. It does not  have any additional instance fields. 

#### RandomKnight Public Methods: 
Define the constructor for this class, which should only call the constructor of its super class with the given parameters.

```java
public RandomKnight(Board board)
```

Unlike the Knight, RandomKnight will start looking for a valid move starting from a random position (see lab3). Override the following Knight Class method accordingly:

```java
public boolean makeMove()
```

## HeuristicKnight Class
---
Create the HeuristicKnight Class in the **knight** package. This class should inherit from the Knight Class.

#### HeuristicKnight Class Private Instance Variables: 
HeuristicKnight Class has the following private instance variable (access) that will be used by the heuristic algorithmm  when choosing moves (see lab3).
 
```java
 int[][] access = 
	  {{2, 3, 4, 4, 4, 4, 3, 2},
	   {3, 4, 6, 6, 6, 6, 4, 3},
	   {4, 6, 8, 8, 8, 8, 6, 4},
	   {4, 6, 8, 8, 8, 8, 6, 4},
	   {4, 6, 8, 8, 8, 8, 6, 4},
	   {4, 6, 8, 8, 8, 8, 6, 4},
	   {3, 4, 6, 6, 6, 6, 4, 3},
	   {2, 3, 4, 4, 4, 4, 3, 2}};
```
#### HeuristicKnight Public Methods: 
Define the constructor for this class, which should only call the constructor of its super class with the given parameters.

```java
public HeuristicKnight(Board board)
```

In addition override the following methods:

```java
1. public void reset()
2. public boolean makeMove() // see lab3 for heuristic alg
```

+ **Note:** Since the HeuristicKnight will be changing the access matrix while making moves, the access matrix should be assigned to its initial values when the reset method is called.


## KnightTour Class
---
Create the KnightTour Class in **knighttour** package. 

#### KnightTour Class Protected Instance Variables: 

1. ```Board board``` 
2. ```Knight knight``` 
  
#### KnightTour Class Public Methods: 
You will define two constructors for this class, their prototype should look like this: 
```java
1. public KnightTour () 
2. public KnightTour (int boardSize, String knightType) 
```

+ The first constructor should create a KnightTour with 8x8 chessboard and a "regular" knight (that is, an instance of Knight)
+ The second constructor should create either Knight, HeuristicKnight or RandomKnight according to the argument knightType. KnightType string can be "regular", "heuristic", or "random"

Additionally, implement the following methods:

```java
public void tour()
```

+ Remember that KnightTour performs a single tour. This method is a wrapper that is called in Lab5Main file.  It will call the protected method ```singleTour``` (exlained below) to the single tour.

```java
public void printResult() 
```

+ This method will print the same results as in Lab3. An example output is given below:

```java
The tour ended with 54 moves.
This was not a full tour.
    0    1    2    3    4    5    6    7

0    0    6   19   26    9    4   15   12
1   20   27    8    5   14   11   42    3
2    7   18   25   10   41   16   13   32
3   28   21   40   17   24   31    2   43
4   39   36   23   30    1   44   33   52
5   22   29   38   45   34   51    0   49
6   37   46   35    0    0   48   53    0
7    0    0    0   47   54    0   50    0
```

```java
protected int singleTour()
```

+ In this method, Knight should move on the board until a single tour is completed (see lab3). It interacts with the knight object to make a move (remember knight has the makeMove method). ```singleTour``` returns the number of moves (remember, the board knows if the board is full or not, and the  current move number etc, so ```singleTour``` should interact with the board to get this information).

## FixedKnightTour Class
---
Create the FixedKnightTour Class in **knighttour** package. This class should inherit the KnightTour class.

#### FixedKnightTour Class Private Instance Variables: 

1. ```private int[] moveTotals```  // see lab3 
2. ```private static final int NUMBER_OF_TOURS = 1000```
  
#### FixedKnightTour Class Public Methods: 

```java
public FixedKnightTour(int boardSize, String knightType)
```

+ The constructor should call the constructor of super class, and also initialize the moveTotals array.

Override the following methods for FixedKnightTour:

```java
1. public void tour()
2. public void printResult()
```
+ ```tour```  method can calls a private helper method called ```fixedTour```.  

```java
private void fixedTour() // helper method
```

+  fixedTour() performs NUMBER_OF_TOURS  tours. It should reuse ```singleTour()``` of its superclass. IMPORTANT: Make sure you clear the board and reset the knight after each tour.
  

A sample output for printResult is given below:

```java
# tours having # moves

0              1        
0              2        
0              3        
0              4        
0              5        
0              6        
0              7        
13             8        
0              9        
15             10       
0              11       
0              12       
0              13       
0              14       
0              15       
0              16       
0              17       
0              18       
0              19       
0              20       
0              21       
0              22       
0              23       
0              24       
0              25       
27             26       
25             27       
33             28       
14             29       
0              30       
11             31       
60             32       
34             33       
56             34       
123            35       
71             36       
29             37       
0              38       
17             39       
53             40       
41             41       
76             42       
30             43       
30             44       
22             45       
30             46       
17             47       
32             48       
17             49       
0              50       
0              51       
0              52       
0              53       
100            54       
24             55       
0              56       
0              57       
0              58       
0              59       
0              60       
0              61       
0              62       
0              63       
0              64    
Total number of tours: 1000    
```



## Lab5Main Class
---

You can run your implementation using the provided Lab5Main Class. This class takes command line arguments as described below: 

1. java Lab5Main 8 fixed heuristic     (an example command line)

2. go to run configurations, put the arguments in the arguments tab  (eclipse)

The three arguments are
1. First argument:  integer  (boardsize n)
2. Second argument:  string (type of tour - single, fixed)
3. Third arguments: string (type of knight - regular, random or heuristic)

If no argument is provided, the program will run in default mode which is defined as: 8x8 board, single tour, regular knight.
