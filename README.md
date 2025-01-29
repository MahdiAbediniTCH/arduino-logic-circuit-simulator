# Arduino logic circuit simulator and truth table interface
Computer Structure and Language final project

Main project No.1: Logical circuit simulator on an Arduino/ESP32 chip using JavaFX as a graphical interface for setting truth tables

Links:
  [GitHub](https://github.com/MahdiAbediniTCH/arduino-logic-circuit-simulator)
  [Latex](https://latex.sharif.edu/read/gkvnnwtsfzqh)
## Dependencies
1. PlatformIO 6.1.16+
2. Wokwi simulator VSCode extension
3. pySerial on python 3.12.0+
4. JDK 21
  * Maven dependencies are specified in pom.xml

## Build the project

This is a [PlatformIO](https://platformio.org) project. To build it, [install PlatformIO](https://docs.platformio.org/en/latest/core/installation/index.html), and then run the following command in ```sim/```:

```
pio run
```

## Simulate the project

To simulate this project, install [Wokwi for VS Code](https://marketplace.visualstudio.com/items?itemName=wokwi.wokwi-vscode). Open the project directory in Visual Studio Code, press **F1** and select "Wokwi: Start Simulator".

## Run user interface
In order to use GUI to upload a circuit onto the board, compile and run ```Truth-Table/src/main/java/org/group24/truthtable/Main.java```.
