package model;

public class VarLed {
    private boolean isEvaluated = false;
    public final int NUMBER;
    private int OutputIn = -1;
    public final boolean IS_LED;

    public VarLed(boolean IS_LED, int NUMBER) {
        this.IS_LED = IS_LED;
        this.NUMBER = NUMBER;
    }

    // if outputIn == -1 , then our VarLed is not output in any table, else VarLed is equal to the table number that
    // our VarLed is output in
    private int[] status = new int[Circ.STATE_NUM];

    public boolean isEvaluated() {
        return isEvaluated;
    }

    public void setEvaluated(boolean evaluated) {
        isEvaluated = evaluated;
    }

    public int getOutputIn() {
        return OutputIn;
    }

    public void setOutputIn(int outputIn) {
        OutputIn = outputIn;
    }

    public void setStatus(int stat, int place) {
        this.status[place] = stat;
    }

    public int getStatus(int place) {
        return this.status[place];
    }
}
