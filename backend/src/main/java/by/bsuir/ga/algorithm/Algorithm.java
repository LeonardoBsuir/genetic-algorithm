package by.bsuir.ga.algorithm;

public class Algorithm {
    /* GA parameters */
    private static final double uniformRate = 0.5;
    private static final double mutationRate = 0.015;
    private static final int tournamentSize = 5;
    private static final boolean elitism = true;
    private static final int POPULATION_SIZE = 50;

    /* Public methods */

    // Evolve a population
    public static Population evolvePopulation(Population pop) {
        if (pop == null) {
            pop = new Population(POPULATION_SIZE, true);
        }
        Population newPopulation = new Population(pop.size(), false);

        // Keep our best chromosome
        if (elitism) {
            newPopulation.savechromosome(0, pop.getFittest());
        }

        // Crossover population
        int elitismOffset;
        if (elitism) {
            elitismOffset = 1;
        } else {
            elitismOffset = 0;
        }
        // Loop over the population size and create new chromosomes with
        // crossover
        for (int i = elitismOffset; i < pop.size(); i++) {
            Chromosome indiv1 = tournamentSelection(pop);
            Chromosome indiv2 = tournamentSelection(pop);
            Chromosome newIndiv = crossover(indiv1, indiv2);
            newPopulation.savechromosome(i, newIndiv);
        }

        // Mutate population
        for (int i = elitismOffset; i < newPopulation.size(); i++) {
            mutate(newPopulation.getchromosome(i));
        }

        return newPopulation;
    }

    // Crossover chromosomes
    private static Chromosome crossover(Chromosome indiv1, Chromosome indiv2) {
        Chromosome newSol = new Chromosome();
        // Loop through genes
        for (int i = 0; i < indiv1.size(); i++) {
            // Crossover
            if (Math.random() <= uniformRate) {
                newSol.setGene(i, indiv1.getGene(i));
            } else {
                newSol.setGene(i, indiv2.getGene(i));
            }
        }
        return newSol;
    }

    // Mutate an chromosome
    private static void mutate(Chromosome indiv) {
        // Loop through genes
        for (int i = 0; i < indiv.size(); i++) {
            if (Math.random() <= mutationRate) {
                // Create random gene
                Gene gene = MyUtil.generateGene();
                indiv.setGene(i, gene);
            }
        }
    }

    // Select chromosomes for crossover
    private static Chromosome tournamentSelection(Population pop) {
        // Create a tournament population
        Population tournament = new Population(tournamentSize, false);
        // For each place in the tournament get a random chromosome
        for (int i = 0; i < tournamentSize; i++) {
            int randomId = (int) (Math.random() * pop.size());
            tournament.savechromosome(i, pop.getchromosome(randomId));
        }
        // Get the fittest
        Chromosome fittest = tournament.getFittest();
        return fittest;
    }
}
