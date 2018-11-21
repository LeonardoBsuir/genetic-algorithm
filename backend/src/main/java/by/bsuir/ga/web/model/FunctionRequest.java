package by.bsuir.ga.web.model;

public class FunctionRequest {
    private double[] minRange;
    private double[] maxRange;
    private int varsNumber;

    public double[] getMinRange() {
        return minRange;
    }

    public void setMinRange(double[] minRange) {
        this.minRange = minRange;
    }

    public double[] getMaxRange() {
        return maxRange;
    }

    public void setMaxRange(double[] maxRange) {
        this.maxRange = maxRange;
    }

    public int getVarsNumber() {
        return varsNumber;
    }

    public void setVarsNumber(int varsNumber) {
        this.varsNumber = varsNumber;
    }
}
