# Prospace Java Challenge

## Description

This program demonstrated how to convert the Roman numerics to Galaxy numerics.

## Development Tools

- JDK 1.8
- Maven 3.8.1
- IntelliJ IDEA

## Design

This program is just a console program, we can run by clicking run button in IntelliJ IDEA or using maven `mvn exec:java -Dexec.mainClass="com.ichromanrd.prospace.Application"`.

There are predefined contents loaded (which can also be customized), located in `src/main/resources`. The files are:
- `galacticromans.txt`, which is a translation between roman numeric and galactical numeric.
- `minerals.txt`, which is a information about credits of the mineral in galactical currency system.

These files are loaded once.

The idea is to have a gateway as a portal for every inquiry. Basically there are only 2 questions:
- How much, refers to question about how much number do the galactical numeric have, and
- How many, refers to question about how many credits does the mineral have based on certain galactical numeric

Each question has its own handler. There are 2 handlers for those 2 question types:
- MineralQuestionHandler, uses MineralCalculator to calculate the mineral's credit
- GalacticNumberQuestionHandler, uses RomanGalacticConverter to get the actual number of the galactic numeric

![Design](https://i.ibb.co/MSr9JWL/prospace-roman-galactic.png)

<hr/>

There are 2 exceptions in the application:
- InvalidFormatException, which is an exception for an invalid format of galactical numbers
- SystemErrorException, which is an exception for fatal condition, it's occurred when users input unknown words for the galactical numbers

<hr/>

## Testing

![testing](https://i.ibb.co/59DvLJc/test.png)