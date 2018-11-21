package by.bsuir.ga.service;

import static java.lang.Math.log;
import static java.lang.Math.toIntExact;

import java.util.ArrayList;
import java.util.List;

import by.bsuir.ga.web.model.GeneticRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import by.bsuir.ga.algorithm.Algorithm;
import by.bsuir.ga.algorithm.FitnessCalc;
import by.bsuir.ga.algorithm.Gene;
import by.bsuir.ga.algorithm.Population;
import by.bsuir.ga.web.model.FunctionRequest;
import by.bsuir.ga.web.model.GeneticResponse;

@Service
public class GaService {

    private static final int STEP_SIZE = 10;


    private static final Logger logger = LogManager.getLogger();

    public GeneticResponse getPopulation(Population population, GeneticRequest geneticRequest) {
        GeneticResponse geneticResponse = new GeneticResponse();

        // Evolve our population until we reach an optimum solution

        logger.info(geneticRequest);
        for (int generationCount = 1; generationCount <= geneticRequest.getNumberOfGenerations(); generationCount++) {

            population = Algorithm.evolvePopulation(population, geneticRequest);
            logger.info("Generation: " + generationCount + " Fittest: " + population.getFittest().getFitness());

        }
        logger.info("Solution found!");
        logger.info("Generation: " + geneticRequest.getNumberOfGenerations() + " Fittest: " + population.getFittest().getFitness());
        logger.info("ChromosomeFitness:" + population.getFittest());
        logger.info("GeneVar:" + population.getFittest().getFittest());
        logger.info("GeneFitness:" + FitnessCalc.getGeneFitness(population.getFittest().getFittest()));


        geneticResponse.setPopulation(population);
        geneticResponse.setFittest(population.getFittest().getFittest());

        return geneticResponse;
    }

    public Gene[] getFunction(FunctionRequest functionRequest) {
        int pointsNumber = getPointsNumber(functionRequest);
        Gene[] genes = new Gene[pointsNumber];
        for (int i = 0; i < pointsNumber; i++) {
            Gene gene = new Gene();
            double[] vars = new double[functionRequest.getVarsNumber()];
            for (int j = 0; j < functionRequest.getVarsNumber(); j++) {
                double point = functionRequest.getMinRange()[j] + (double) i / STEP_SIZE;
                if (point > functionRequest.getMaxRange()[j]) {
                    vars[j] = functionRequest.getMaxRange()[j];
                } else {
                    vars[j] = point;
                }
            }
            gene.setVars(vars);
            genes[i] = gene;
        }
        return genes;
    }

    private int getPointsNumber(FunctionRequest functionRequest) {
        List<Long> numbers = new ArrayList<>();
        int pointsNumber = 0;
        for (int i = 0; i < functionRequest.getMinRange().length; i++) {
            long range = Math.round((functionRequest.getMaxRange()[i] - functionRequest.getMinRange()[i]));
            numbers.add(range);
        }
        pointsNumber = toIntExact(numbers.stream().max(Long::compareTo).get());

        return pointsNumber * STEP_SIZE;
    }
}
