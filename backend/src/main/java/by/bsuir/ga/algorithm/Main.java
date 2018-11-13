package by.bsuir.ga.algorithm;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    private static final int POPULATION_SIZE = 50;
    private static final int NUMBER_OF_GENERATIONS = 50;


    public static void main(String[] args) {

        // Create an initial population
        Population myPop = new Population(POPULATION_SIZE, true);

        // Evolve our population until we reach an optimum solution

        for (int generationCount = 1; generationCount <= NUMBER_OF_GENERATIONS; generationCount++) {


            logger.info("Generation: " + generationCount + " Fittest: " + myPop.getFittest().getFitness());
            myPop = Algorithm.evolvePopulation(myPop);
        }
        logger.info("Solution found!");
        logger.info("Generation: " + NUMBER_OF_GENERATIONS + " Fittest: " + myPop.getFittest().getFitness());
        logger.info("ChromosomeFitness:" + myPop.getFittest());
        logger.info("GeneVar:" + myPop.getFittest().getFittest());
        logger.info("GeneFitness:" + FitnessCalc.getGeneFitness(myPop.getFittest().getFittest()));
    }
}

