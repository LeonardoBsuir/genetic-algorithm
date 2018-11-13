package by.bsuir.ga.service;

import by.bsuir.ga.algorithm.Algorithm;
import by.bsuir.ga.algorithm.FitnessCalc;
import by.bsuir.ga.algorithm.Population;
import by.bsuir.ga.web.model.GeneticResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class GaService {

    private static final int NUMBER_OF_GENERATIONS = 50;

    private static final Logger logger = LogManager.getLogger();

    public GeneticResponse getPopulation(Population population) {
        GeneticResponse geneticResponse = new GeneticResponse();

        // Evolve our population until we reach an optimum solution

        for (int generationCount = 1; generationCount <= NUMBER_OF_GENERATIONS; generationCount++) {

            population = Algorithm.evolvePopulation(population);
            logger.info("Generation: " + generationCount + " Fittest: " + population.getFittest().getFitness());

        }
        logger.info("Solution found!");
        logger.info("Generation: " + NUMBER_OF_GENERATIONS + " Fittest: " + population.getFittest().getFitness());
        logger.info("ChromosomeFitness:" + population.getFittest());
        logger.info("GeneVar:" + population.getFittest().getFittest());
        logger.info("GeneFitness:" + FitnessCalc.getGeneFitness(population.getFittest().getFittest()));

        geneticResponse.setPopulation(population);
        geneticResponse.setFittest(population.getFittest().getFittest());

        return geneticResponse;
    }
}
