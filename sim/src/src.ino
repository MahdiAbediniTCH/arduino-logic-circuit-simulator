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
}


void loop()
{
  unsigned inputState = getInputState();
  unsigned outputState = getOutputState(inputState);
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
    unsigned s = digitalRead(switchPins[i]) << i;
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