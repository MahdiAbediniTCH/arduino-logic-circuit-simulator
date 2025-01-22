
const byte ledCount = 4;
const byte ledPins[] = {10, 11, 12, 13};
const byte switchCount = 8;
const byte switchPins[] = {2, 3, 4, 5, 6, 7, 8, 9};

void setup() {
  // initialize the LED pins and switches
  for (byte i = 0; i < ledCount; i++) {
    pinMode(ledPins[i], OUTPUT);
  }
  for (byte i = 0; i < switchCount; i++) {
    pinMode(switchPins[i], INPUT_PULLUP);
  }
}

void loop() {
  for (byte i = 0; i < ledCount; i++) {
    int state = digitalRead(switchPins[i]);
    digitalWrite(ledPins[i], state);
  }
}
