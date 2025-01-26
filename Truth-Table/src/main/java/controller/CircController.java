package controller;

import javafx.scene.control.Tab;
import model.Circ;
import model.Table;
import model.VarLed;

import java.util.ArrayList;
import java.util.Objects;

public class CircController {
    public void evaluateVarLed(VarLed varLed) {
        varLed.setEvaluated(true);
        if (varLed.getOutputIn() == -1) {
            if (varLed.IS_LED)
                for (int i = 0; i < Circ.STATE_NUM; i++)
                    varLed.setStatus(Circ.getDefaultLed(), i);
            else
                for (int i = 0; i < Circ.STATE_NUM; i++)
                    varLed.setStatus(Circ.getDefaultVariable(), i);
        } else {
            Table table = Table.getFromAllTables(varLed.getOutputIn());
            int[] pseudoStatus = table.getOutPutStatus(varLed);
            ArrayList<Integer> varNums = table.getVarNumbers();
            ArrayList<Integer> switchNums = table.getSwitchNumbers();
            for(int i : varNums) {
                evaluateVarLed(Objects.requireNonNull(Circ.getVar(i)));
            }
            for(int i =0; i < Circ.STATE_NUM; i ++) {
                //TODO : see which element in the array should I check and fill the status for varLed
            }
        }
    }

    public void prepareLEDs() {
        for (int i = 0; i < Circ.LEDNUM; i++)
            evaluateVarLed(Objects.requireNonNull(Circ.getLed(i)));
    }

    public void writeInFile() {
        prepareLEDs();
        //TODO : open the data.bin and write the proper data
    }
}
