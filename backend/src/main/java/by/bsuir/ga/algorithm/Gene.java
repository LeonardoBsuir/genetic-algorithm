package by.bsuir.ga.algorithm;

import java.util.Arrays;

public class Gene {

    private double[] vars;

    public double[] getVars() {
        return vars;
    }

    public void setVars(double[] vars) {
        this.vars = vars;
    }

    public double getFitness() {
        return FitnessCalc.getGeneFitness(this);
    }

    @Override
    public String toString() {
        return "Gene{" +
                "vars=" + Arrays.toString(vars) +
                '}';
    }
}
