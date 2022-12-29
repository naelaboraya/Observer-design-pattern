# Observer design pattern

Implementing Observer design pattern in java with UndoableStringBuilder.

## Overview
The observer design pattern is a behavioral design pattern that allows an object (observer) to receive updates from another object (subject) when the subject's state changes. The observer subscribes to the subject and is notified whenever the subject's state changes. The observer can then perform some action based on this notification. This allows for a separation of concerns, as the subject does not need to know how its state is being used or what action is being taken as a result of the state change. The observer design pattern is commonly used in applications to implement event handling, logging, and other types of reactive programming.

This project is a Java implementation of the Observer design pattern using the UndoableStringBuilder as the observable state. The main package, observer, contains the following classes:

* UndoableStringBuilder: This class represents the observable state that is being observed.
* GroupAdmin: This class implements the Sender interface (observable) and is responsible for maintaining a list of observers (ConcreteMembers) and notifying them of   changes to the UndoableStringBuilder.
* ConcreteMember: This class implements the Member interface , it represents the observer , and the object of type ConcreteMember should be updated when a change happen to the GroupAdmin object that it observes.

### Data structures used in the project

* Hashmap<Member,String> : to store the observers in GroupAdmin class , the hashmap key is the Member that observes the GroupAdmin and the value is the observer's name , we used a hashmap instead of other data structures like ArrayList for example because the operations on Hashmap are faster and most of them take O(1) time.
* LinkedList<String> : in UndoableStringBuilder class , in order to save the history of the string states and to enable undo operation , we stored the states of the string in a LinkedList , we used a LinkedList because it is more efficient in terms of memory than other data structures like stack for example. 

```
private Map<Member,String> observers;
```

```
private LinkedList<String> history;
```
### Testing

There is another package in the project that is named test , that includes the class Tests , which tests the functions for differenet classes , it also checks the memory usage by some objects of types GrouAdmin and ConcreteMember.
  
The memory usage test is performed using JvmUtilities class which has 3 static methods : 
  1. objectFoorprint - Shows all the references that the object indludes and the size of each object.
  2. objectTotalSize - Shows the total size of the object.
  3. jvmInfo - Shows the process ID of the JVM and the total amount of memory available to the program.
  
  #### screenshots of testing objects sizes in the meory :
  
  ![tst1](https://user-images.githubusercontent.com/94143804/209946276-7c9cbcfe-3d64-427a-8662-07b81717dba1.jpg)
  ![tst2B](https://user-images.githubusercontent.com/94143804/209948114-c7989e13-4d6a-453b-bcf8-2d34327ed17c.jpg)
  ![tst3B](https://user-images.githubusercontent.com/94143804/209948133-7b0e35b9-b7a9-4441-b440-29bd1af7bb80.jpg)



### Installation and setup

To use this project, you will need to have the latest version of Java and a Java IDE (such as Eclipse or IntelliJ) installed on your machine.

1. Clone or download this repository to your local machine.
2. Import the project into your IDE as a Maven project. 
3. Run the tests in test -> Tests to ensure that everything is working properly.



## Built With

* [Intellij IDE](https://www.jetbrains.com/idea/promo/?source=google&medium=cpc&campaign=9730674410&term=intellij&content=602143185271&gclid=EAIaIQobChMI-o2gudOe_AIVxYXVCh35PgUWEAAYASAAEgIe7vD_BwE) - The IDE used
* [Maven](https://maven.apache.org/) - Dependency Management


## Contributing

If you want to contribute to this project, please follow these guidelines:
1. Fork this repository and make your changes in a separate branch.
2. Test your changes thoroughly to ensure that they do not break any existing functionality.
3. Create a pull request, explaining the changes you have made and why they are necessary.


## Authors

*  [Nael Aboraya](https://github.com/naelaboraya)
*  [Marwan Hresh](https://github.com/marwanhresh)



