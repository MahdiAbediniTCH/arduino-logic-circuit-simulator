package model;

import java.util.ArrayList;
import java.util.HashMap;

public class Table {
    private static ArrayList<Table> allTables = new ArrayList<>();
    private ArrayList<Integer> switchNumbers = new ArrayList<>();
    private ArrayList<Integer> varNumbers = new ArrayList<>();
    private ArrayList<VarLed> outputs = new ArrayList<>();
    private HashMap<VarLed, int[]> outputStats = new HashMap<>();
    private int statNumber;

    /*
    conventions about table to work properly:
    columns of the table related to inputs, should have first all switches in ascending order and then all variables
    in ascending order.
     */
    private Table(ArrayList<Integer> switchNumbers, ArrayList<Integer> varNumbers, ArrayList<VarLed> outputs) {
        this.switchNumbers = switchNumbers;
        this.varNumbers = varNumbers;
        this.outputs = outputs;
        statNumber = (int) Math.pow(2, getNumberOfInputs());
        for (VarLed varLed : outputs) {
            int[] array = new int[statNumber];
            outputStats.put(varLed, array);
            varLed.setOutputIn(allTables.size());
        }
    }

    public static Table getFromAllTables(int i) {
        return allTables.get(i);
    }

    public boolean createTable(ArrayList<Integer> switchNumbers, ArrayList<Integer> varNumbers, ArrayList<VarLed> outputs) {
        for (VarLed varLed : outputs)
            if (varLed.getOutputIn() != -1)
                return false;
        //the false above, shows that the construction wasn't successful
        Table table = new Table(switchNumbers, varNumbers, outputs);
        allTables.add(table);
        return true;
    }

    public int getNumberOfInputs() {
        return switchNumbers.size() + varNumbers.size();
    }

    public int[] getOutPutStatus(VarLed varLed) {
        return outputStats.get(varLed);
    }

    public ArrayList<Integer> getSwitchNumbers() {
        return switchNumbers;
    }

    public ArrayList<Integer> getVarNumbers() {
        return varNumbers;
    }

    public void changeState(VarLed varLed, int place) {
        if (outputStats.get(varLed) == null) {
            System.out.println("this varled is not an output for this table!");
            return;
        }
        outputStats.get(varLed)[place] = 1 - outputStats.get(varLed)[place];
    }
}
