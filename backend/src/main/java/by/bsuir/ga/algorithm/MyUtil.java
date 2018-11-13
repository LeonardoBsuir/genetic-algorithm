package by.bsuir.ga.algorithm;

import java.util.Random;

public class MyUtil {

    private static final int NUMBER_OF_VARS = 1;
    private static double rangeMin[] = {0};
    private static double rangeMax[] = {5};

    public static Gene generateGene() {
        Gene gene = new Gene();
        double[] vars = new double[NUMBER_OF_VARS];
        for (int j = 0; j < NUMBER_OF_VARS; j++) {
            Random r = new Random();
            vars[j] = rangeMin[j] + (rangeMax[j] - rangeMin[j]) * r.nextDouble();
        }
        gene.setVars(vars);
        return gene;
    }

}
