package controller;

import model.Circ;
import model.Table;
import model.VarLed;

public class TableController {
    public static int[] convertToBinary(int input, int bitNum) {
        int[] output = new int[bitNum];
        for (int i = 0; i < bitNum; i++) {
            output[bitNum - 1 - i] = input % 2;
            input /= 2;
        }
        return output;
    }

    public static void removeTable(int number) {
        VarLed varLed;
        int outputIn;
        for(int i = 0; i < Circ.LEDNUM; i++) {
            varLed = Circ.getLed(i);
            outputIn = varLed.getOutputIn();
            if(outputIn == number)
                varLed.setOutputIn(-1);
            else if(outputIn > number)
                varLed.setOutputIn(outputIn - 1);
        }
        for(int i = 0; i < Circ.VARS; i++) {
            varLed = Circ.getVar(i);
            outputIn = varLed.getOutputIn();
            if(outputIn == number)
                varLed.setOutputIn(-1);
            else if(outputIn > number)
                varLed.setOutputIn(outputIn - 1);
        }
        Table.removeFromAllTables(number);
    }
}
