#define INPUT_COUNT 8
#define OUTPUT_COUNT 4
#define STATE_COUNT (1 << INPUT_COUNT)

const byte ledCount = OUTPUT_COUNT;
const byte ledPins[] = {10, 11, 12, 13};
const byte switchCount = INPUT_COUNT;
const byte switchPins[] = {2, 3, 4, 5, 6, 7, 8, 9};

unsigned circuit[STATE_COUNT] = {1};

char buffer[200];

void setup() {
  Serial.begin(9600);
  // initialize the LED pins and switches
  for (byte i = 0; i < switchCount; i++) {
    pinMode(switchPins[i], INPUT_PULLUP);
  }
  for (byte i = 0; i < ledCount; i++) {
    pinMode(ledPins[i], OUTPUT);
  }
}

void loop() {
  unsigned circuitState = 0;
  for (byte i = 0; i < switchCount; i++) {
    unsigned s = digitalRead(switchPins[i]) << i;
    circuitState |= s;
  }
  sprintf(buffer, "%u\n", circuitState);
  Serial.print(buffer);
  delay(1000);

}
