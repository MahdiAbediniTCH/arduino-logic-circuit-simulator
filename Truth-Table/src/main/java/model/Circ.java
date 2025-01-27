package model;

import java.util.ArrayList;

public class Circ {
    public static final int SWITCHES = 8;
    public static final int STATE_NUM = (int) Math.pow(2, SWITCHES);
    public static final int VARS = 4;
    public static final int LEDNUM = 4;
    private static int defaultLed = 0;
    private static int defaultVariable = 0;
    private static ArrayList<VarLed> leds = new ArrayList<>();
    private static ArrayList<VarLed> variables = new ArrayList<>();

    static {
        for (int i = 0; i < LEDNUM; i++) {
            leds.add(new VarLed(true, i));
        }
        for (int i = 0; i < VARS; i++) {
            variables.add(new VarLed(false, i));
        }
    }

    public static int getDefaultLed() {
        return defaultLed;
    }

    public static void setDefaultLed(int defaultLed) {
        Circ.defaultLed = defaultLed;
    }

    public static int getDefaultVariable() {
        return defaultVariable;
    }

    public static void setDefaultVariable(int defaultVariable) {
        Circ.defaultVariable = defaultVariable;
    }

    public static VarLed getLed(int i) {
        if (i < 0 || i >= LEDNUM) {
            System.out.println("invalid number for led!");
            return null;
        }
        return leds.get(i);
    }

    public static VarLed getVar(int i) {
        if (i < 0 || i >= VARS) {
            System.out.println("invalid number for variable!");
            return null;
        }
        return variables.get(i);
    }
}
