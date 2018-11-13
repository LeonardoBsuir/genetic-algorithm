package by.bsuir.ga.algorithm;

public class Population {

    private Chromosome[] chromosomes;

    /*
     * Constructors
     */
    // Create a population
    public Population(int populationSize, boolean initialise) {
        chromosomes = new Chromosome[populationSize];
        // Initialise population
        if (initialise) {
            // Loop and create chromosomes
            for (int i = 0; i < size(); i++) {
                Chromosome newchromosome = new Chromosome();
                newchromosome.generatechromosome();
                savechromosome(i, newchromosome);
            }
        }
    }

    /* Getters */
    public Chromosome getchromosome(int index) {
        return chromosomes[index];
    }

    public Chromosome getFittest() {
        Chromosome fittest = chromosomes[0];
        // Loop through chromosomes to find fittest
        for (int i = 0; i < size(); i++) {
            if (fittest.getFitness() <= getchromosome(i).getFitness()) {
                fittest = getchromosome(i);
            }
        }
        return fittest;
    }

    /* Public methods */
    // Get population size
    public int size() {
        return chromosomes.length;
    }

    // Save chromosome
    public void savechromosome(int index, Chromosome indiv) {
        chromosomes[index] = indiv;
    }
}
