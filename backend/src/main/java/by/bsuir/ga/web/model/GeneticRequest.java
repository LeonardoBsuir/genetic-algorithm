package by.bsuir.ga.web.model;

/**
 * Created by eugene on 18.11.2018.
 */
public class GeneticRequest {

    private FunctionRequest functionRequest;
    private int numberOfGenerations;
    private double uniformRate;
    private double mutationRate;
    private int tournamentSize;
    private boolean elitism;
    private int populationSize;

    public FunctionRequest getFunctionRequest() {
        return functionRequest;
    }

    public void setFunctionRequest(FunctionRequest functionRequest) {
        this.functionRequest = functionRequest;
    }

    public int getNumberOfGenerations() {
        return numberOfGenerations;
    }

    public void setNumberOfGenerations(int numberOfGenerations) {
        this.numberOfGenerations = numberOfGenerations;
    }

    public double getUniformRate() {
        return uniformRate;
    }

    public void setUniformRate(double uniformRate) {
        this.uniformRate = uniformRate;
    }

    public double getMutationRate() {
        return mutationRate;
    }

    public void setMutationRate(double mutationRate) {
        this.mutationRate = mutationRate;
    }

    public int getTournamentSize() {
        return tournamentSize;
    }

    public void setTournamentSize(int tournamentSize) {
        this.tournamentSize = tournamentSize;
    }

    public boolean isElitism() {
        return elitism;
    }

    public void setElitism(boolean elitism) {
        this.elitism = elitism;
    }

    public int getPopulationSize() {
        return populationSize;
    }

    public void setPopulationSize(int populationSize) {
        this.populationSize = populationSize;
    }

    @Override
    public String toString() {
        return "GeneticRequest{" +
                "functionRequest=" + functionRequest +
                ", numberOfGenerations=" + numberOfGenerations +
                ", uniformRate=" + uniformRate +
                ", mutationRate=" + mutationRate +
                ", tournamentSize=" + tournamentSize +
                ", elitism=" + elitism +
                ", populationSize=" + populationSize +
                '}';
    }
}
