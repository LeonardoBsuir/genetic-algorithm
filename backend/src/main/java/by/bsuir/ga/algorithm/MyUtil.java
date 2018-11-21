package by.bsuir.ga.algorithm;

import java.util.Random;

import by.bsuir.ga.web.model.FunctionRequest;

public class MyUtil {

    public static Gene generateGene(FunctionRequest functionRequest) {
        Gene gene = new Gene();
        double[] vars = new double[functionRequest.getVarsNumber()];
        for (int j = 0; j < functionRequest.getVarsNumber(); j++) {
            Random r = new Random();
            vars[j] = functionRequest.getMinRange()[j]
                    + (functionRequest.getMaxRange()[j]
                    - functionRequest.getMinRange()[j])
                    * r.nextDouble();
        }
        gene.setVars(vars);
        return gene;
    }

}
