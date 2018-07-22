# Intergalactic ForeignExchange

Helps currency conversion of Earthly metals to foreign unit w.r.t Roman Numerals

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

```
Java 8
maven 3
Junit 4
```
### Design and Assumption
* **App** class holds the entry point of application which takes input from file and throws exception in case of any glitch in the system.
* **Converter** class executes all conversions as per input passed and has a **IntergalacticPlanet** dependency to perform calculation.
* **IntergalacticPlanet** is one of the planet in the galaxy that user has reached and performing a forexchange by the conversion rules and currency provided by the planet. This class holds the currency conversion  logic and hides the implementation from outer world . It also holds mappings for earthly metals and its curreny mapping values.
* In case if the user ventures to another planet he will have to provide implementation for that planet same like **IntergalacticPlanet**.
* **IntergalacticUnitMapper** interface has to be implemented by new planet implementation to provide the RomanNumeral conversion value. 
### Installing

Unzip the file to any directory. And open the project in your favourite IDE. Do the following 

```
mvn clean package
$ java -jar intergalactic-1.0.jar /path/to/input/file/input.txt /path/to/output/file/output.txt
```

if running on IDE
```
Run the App.java with 2 program arguments 
i.e input-file.txt and output.txt present in the root directory  
```

End with an example of getting some data out of the system or using it for a little demo

## Running the tests

Tests are included to check the Converter.java logic on calculating / validating inputs from user

```
mvn clean test
```
## Built With

* [Maven](https://maven.apache.org/) - Dependency Management
