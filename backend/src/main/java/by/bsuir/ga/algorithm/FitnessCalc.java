package by.bsuir.ga.algorithm;

public class FitnessCalc {

    // Calculate chromosomes fitness by comparing it to our candidate solution
    public static double getChromosomeFitness(Chromosome chromosome) {
        double fitness = 0;
        for (int i = 0; i < chromosome.getGenes().length; i++) {
            fitness += getGeneFitness(chromosome.getGenes()[i]);
        }
        return fitness;
    }

    // Calculate genes fitness
    public static double getGeneFitness(Gene gene) {
        double f;
        switch (gene.getVars().length) {
            case 1: {
                f = getOneParamFunction(gene);
                break;
            }
            case 2: {
                f = getTwoParamsFunction(gene);
                break;
            }
            default:
                f = 0;
        }
        return f;
    }

    private static double getOneParamFunction(Gene gene) {
        double x = gene.getVars()[0];
        return (x + 1.3) * Math.sin(0.5 * Math.PI * x + 1);
    }

    private static double getTwoParamsFunction(Gene gene) {
        double x = gene.getVars()[0];
        double y = gene.getVars()[1];
        return -1 * (Math.pow((Math.pow(x, 2) + Math.pow(y, 2)), 0.25) * (Math.pow(Math.sin(50 * Math.pow((x * x + y * y), 0.1)), 2) + 1));
    }
}
