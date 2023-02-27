# Coding challenge for Kata09_Checkout
http://codekata.com/kata/kata09-back-to-the-checkout/

### Author: Tamir Mayblat (tamirmayb@gmail.com)

### This solution uses ```Easy Rules``` which is a java based rule engine which takes a JSON file and converts it into applicable set of rules and provides Rule abstraction and matches the rules with actions that can be applied on our RegistryItems

### Current configuration:
```
  Item   Unit      Special
         Price     Price
  --------------------------
    A     50       3 for 130
    B     30       2 for 45
    C     20
    D     15
```

#### Prerequisite
* JDK 1.8
* Maven 3.x

#### How to run
* Clone this repository
* Build the project and run the provided test by:
```
mvn clean package

```