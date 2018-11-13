package by.bsuir.ga.web.controller;

import by.bsuir.ga.algorithm.Population;
import by.bsuir.ga.service.GaService;
import by.bsuir.ga.web.model.GeneticResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GeneticController {

    @Autowired
    GaService gaService;

    @RequestMapping("/getPopulation")
    public GeneticResponse getPopulation() {
        Population pop = null;
        return gaService.getPopulation(pop);
    }

}
