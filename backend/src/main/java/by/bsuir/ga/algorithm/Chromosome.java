package by.bsuir.ga.algorithm;

public class Chromosome {

    private static int defaultChromosomeLength = 10;
    private Gene[] genes = new Gene[defaultChromosomeLength];


    // Cache
    private double fitness = 0;

    // Create a random chromosome
    public void generatechromosome() {
        for (int i = 0; i < size(); i++) {
            genes[i] = MyUtil.generateGene();
        }
    }

    /* Getters and setters */
    // Use this if you want to create chromosomes with different gene lengths
    public static void setDefaultGeneLength(int length) {
        defaultChromosomeLength = length;
    }

    public Gene[] getGenes() {
        return genes;
    }

    public void setGenes(Gene[] genes) {
        this.genes = genes;
        fitness = 0;
    }

    public Gene getGene(int index) {
        return genes[index];
    }

    public void setGene(int index, Gene value) {
        genes[index] = value;
        fitness = 0;
    }

    /* Public methods */
    public int size() {
        return genes.length;
    }

    public double getFitness() {
        if (fitness == 0) {
            fitness = FitnessCalc.getChromosomeFitness(this);
        }
        return fitness;
    }

    @Override
    public String toString() {
        StringBuilder geneString = new StringBuilder();
        for (int i = 0; i < size(); i++) {
            geneString.append(genes[i]).append(" ");
        }
        return geneString.toString();
    }

    public Gene getFittest() {
        Gene fittest = genes[0];
        // Loop through chromosomes to find fittest
        for (int i = 0; i < size(); i++) {
            if (fittest.getFitness() <= getGene(i).getFitness()) {
                fittest = getGene(i);
            }
        }
        return fittest;
    }


}
