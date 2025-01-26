package model;

import java.util.ArrayList;

public class Circ {
    public static Circ wholeCirc = null;
    public static final int SWITCHES = 8;
    public static final int STATE_NUM = (int) Math.pow(2,SWITCHES);
    public static final int VARS = 4;
    public static final int LEDNUM = 4;
    private boolean defaultLed = false;
    private boolean defaultVariable = false;
    private static ArrayList<VarLed> leds;
    private static ArrayList<VarLed> variables;
    public static void initializeCirc(){
        if(wholeCirc != null)
            return;
        wholeCirc = new Circ();
    }
    private Circ() {
        for(int i = 0; i < LEDNUM; i++) {
            leds.add(new VarLed(true));
        }
        for(int i = 0; i < VARS; i++) {
            variables.add(new VarLed(false));
        }
    }

    public boolean isDefaultLed() {
        return defaultLed;
    }

    public void setDefaultLed(boolean defaultLed) {
        this.defaultLed = defaultLed;
    }

    public boolean isDefaultVariable() {
        return defaultVariable;
    }

    public void setDefaultVariable(boolean defaultVariable) {
        this.defaultVariable = defaultVariable;
    }
}
