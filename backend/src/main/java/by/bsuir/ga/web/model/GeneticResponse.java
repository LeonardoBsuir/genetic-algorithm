package by.bsuir.ga.web.model;

import by.bsuir.ga.algorithm.Gene;
import by.bsuir.ga.algorithm.Population;

public class GeneticResponse {
    private Population population;
    private Gene fittest;

    public Population getPopulation() {
        return population;
    }

    public void setPopulation(Population population) {
        this.population = population;
    }

    public Gene getFittest() {
        return fittest;
    }

    public void setFittest(Gene fittest) {
        this.fittest = fittest;
    }
}
