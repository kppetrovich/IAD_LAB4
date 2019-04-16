package ru.ifmo.se.lab4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ifmo.se.lab4.domain.HistoryEntry;
import ru.ifmo.se.lab4.domain.Point;
import ru.ifmo.se.lab4.dto.PointDto;
import ru.ifmo.se.lab4.service.GraphService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/graph")
public class GraphController {

    private final GraphService graphService;

    @Autowired
    public GraphController(GraphService graphService) {
        this.graphService = graphService;
    }

    @PostMapping("/add-point")
    public HistoryEntry addPoint(@RequestBody PointDto pointDto, Principal principal) {
        Point point = new Point(pointDto.getX(), pointDto.getY());
        HistoryEntry historyEntry = new HistoryEntry(point, pointDto.getR());
        return graphService.addPoint(historyEntry, principal.getName());
    }

    @PostMapping("/history")
    public List<HistoryEntry> getHistory(Principal principal) {
        return graphService.getHistory(principal.getName());
    }

}
