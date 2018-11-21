package by.bsuir.ga.web.controller;

import by.bsuir.ga.web.model.GeneticRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import by.bsuir.ga.algorithm.Gene;
import by.bsuir.ga.algorithm.Population;
import by.bsuir.ga.service.GaService;
import by.bsuir.ga.web.model.FunctionRequest;
import by.bsuir.ga.web.model.GeneticResponse;

@RestController
@RequestMapping("/api")
public class GeneticController {

    @Autowired
    GaService gaService;

    @RequestMapping(value = "/genetic", method = RequestMethod.POST)
    public GeneticResponse getGenetics(@RequestBody GeneticRequest geneticRequest) {
        Population pop = null;
        return gaService.getPopulation(pop, geneticRequest);
    }

    @RequestMapping(value = "/function", method = RequestMethod.POST)
    public Gene[] getFunction(@RequestBody FunctionRequest functionRequest) {
        return gaService.getFunction(functionRequest);
    }

}
