import serial

with open("data.bin", 'rb') as data, \
    serial.serial_for_url('rfc2217://localhost:4000', baudrate=9600) as ser:
    ser.write(data.read())
