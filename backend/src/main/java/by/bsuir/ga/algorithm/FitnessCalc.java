package by.bsuir.ga.algorithm;

public class FitnessCalc {

    // Calculate chromosomes fitness by comparing it to our candidate solution
    public static double getChromosomeFitness(Chromosome chromosome) {
        int fitness = 0;
        for (int i = 0; i < chromosome.getGenes().length; i++) {
            fitness += getGeneFitness(chromosome.getGenes()[i]);
        }
        return fitness;
    }

    // Calculate genes fitness
    public static double getGeneFitness(Gene gene) {
        double x = gene.getVars()[0];
        double f = (x + 1.3) * Math.sin(0.5 * Math.PI * x + 1);
        return f;
    }

}
