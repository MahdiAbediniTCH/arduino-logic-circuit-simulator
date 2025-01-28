#define INPUT_COUNT 8
#define OUTPUT_COUNT 4
#define STATE_COUNT (1 << INPUT_COUNT)

const byte ledCount = OUTPUT_COUNT;
const byte ledPins[] = {10, 11, 12, 13};
const byte switchCount = INPUT_COUNT;
const byte switchPins[] = {2, 3, 4, 5, 6, 7, 8, 9};

byte circuit[STATE_COUNT] = {0b1101}; // sample circuit: 00000000 -> 1101

char buffer[1024];

byte hexToByte(char hex) {
  byte val = hex - '0';
  if (val > 9) 
    val = hex - 'a' + 10;
  if (val < 0 || val > 15)
    val = 0b01010101; // error indicator
  return val;
}
const uint8_t digitTable[] = {
  0b11000000,
  0b11111001,
  0b10100100,
  0b10110000,
  0b10011001,
  0b10010010,
  0b10000010,
  0b11111000,
  0b10000000,
  0b10010000,
};
const int LATCH_PIN = A1;  // 74HC595 pin 12
const int DATA_PIN = A0;  // 74HC595pin 14
const int CLOCK_PIN = A2;  // 74HC595 pin 11

void displayNumber(int number) {
  int high = number % 100 / 10;
  int low = number % 10;
  sendNumber(high ? digitTable[high] : 0xff, digitTable[low]);
}

void sendNumber(uint8_t high, uint8_t low) {
  digitalWrite(LATCH_PIN, LOW);
  shiftOut(DATA_PIN, CLOCK_PIN, MSBFIRST, low);
  shiftOut(DATA_PIN, CLOCK_PIN, MSBFIRST, high);
  digitalWrite(LATCH_PIN, HIGH);
}

void setup() {
  Serial.begin(9600);
  Serial.setTimeout(50);
  // initialize the LED pins and switches
  for (byte i = 0; i < switchCount; i++) {
    pinMode(switchPins[i], INPUT_PULLUP);
  }
  for (byte i = 0; i < ledCount; i++) {
    pinMode(ledPins[i], OUTPUT);
  }
  pinMode(LATCH_PIN, OUTPUT);
  pinMode(CLOCK_PIN, OUTPUT);
  pinMode(DATA_PIN, OUTPUT);
}


void loop()
{


  unsigned inputState = getInputState();
  unsigned outputState = getOutputState(inputState);
  displayNumber(outputState);
  for (byte i = 0; i < ledCount; i++) {
    bool s = (outputState >> i) & 1;
    setOutput(i, s);
  }
  if (Serial.readBytes(buffer, STATE_COUNT)) {
    for (unsigned i = 0; i < STATE_COUNT; i++) {
      circuit[i] = hexToByte(buffer[i]);
    }
  }

}

unsigned getInputState() {
  unsigned state = 0;
  for (byte i = 0; i < switchCount; i++) {
    unsigned s = (!digitalRead(switchPins[i])) << i; // inverting input so it looks better
    state |= s;
  }
  return state;
}
unsigned getOutputState(unsigned inputState) {
  return circuit[inputState];
}
void setOutput(byte index, bool value) {
  digitalWrite(ledPins[index], value);
}