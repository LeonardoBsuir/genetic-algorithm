package by.bsuir.ga.algorithm;

import by.bsuir.ga.web.model.GeneticRequest;

public class Algorithm {
    /* Public methods */

    // Evolve a population
    public static Population evolvePopulation(Population pop, GeneticRequest geneticRequest) {
        if (pop == null) {
            pop = new Population(geneticRequest.getPopulationSize(), true, geneticRequest);
        }
        Population newPopulation = new Population(pop.size(), false, geneticRequest);

        // Keep our best chromosome
        if (geneticRequest.isElitism()) {
            newPopulation.savechromosome(0, pop.getFittest());
        }

        // Crossover population
        int elitismOffset;
        if (geneticRequest.isElitism()) {
            elitismOffset = 1;
        } else {
            elitismOffset = 0;
        }
        // Loop over the population size and create new chromosomes with
        // crossover
        for (int i = elitismOffset; i < pop.size(); i++) {
            Chromosome indiv1 = tournamentSelection(pop, geneticRequest);
            Chromosome indiv2 = tournamentSelection(pop, geneticRequest);
            Chromosome newIndiv = crossover(indiv1, indiv2, geneticRequest);
            newPopulation.savechromosome(i, newIndiv);
        }

        // Mutate population
        for (int i = elitismOffset; i < newPopulation.size(); i++) {
            mutate(newPopulation.getchromosome(i), geneticRequest);
        }

        return newPopulation;
    }

    // Crossover chromosomes
    private static Chromosome crossover(Chromosome indiv1, Chromosome indiv2, GeneticRequest geneticRequest) {
        Chromosome newSol = new Chromosome();
        // Loop through genes
        for (int i = 0; i < indiv1.size(); i++) {
            // Crossover
            if (Math.random() <= geneticRequest.getUniformRate()) {
                newSol.setGene(i, indiv1.getGene(i));
            } else {
                newSol.setGene(i, indiv2.getGene(i));
            }
        }
        return newSol;
    }

    // Mutate an chromosome
    private static void mutate(Chromosome indiv, GeneticRequest geneticRequest) {
        // Loop through genes
        for (int i = 0; i < indiv.size(); i++) {
            if (Math.random() <= geneticRequest.getMutationRate()) {
                // Create random gene
                Gene gene = MyUtil.generateGene(geneticRequest.getFunctionRequest());
                indiv.setGene(i, gene);
            }
        }
    }

    // Select chromosomes for crossover
    private static Chromosome tournamentSelection(Population pop, GeneticRequest geneticRequest) {
        // Create a tournament population
        Population tournament = new Population(geneticRequest.getTournamentSize(), false, geneticRequest);
        // For each place in the tournament get a random chromosome
        for (int i = 0; i < geneticRequest.getTournamentSize(); i++) {
            int randomId = (int) (Math.random() * pop.size());
            tournament.savechromosome(i, pop.getchromosome(randomId));
        }
        // Get the fittest
        Chromosome fittest = tournament.getFittest();
        return fittest;
    }
}
