import serial

ser = serial.serial_for_url('rfc2217://localhost:4000', baudrate=9600)
ser.write(b'hello\0')
ser.close()
