package ru.ifmo.se.lab4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ifmo.se.lab4.domain.HistoryEntry;
import ru.ifmo.se.lab4.domain.Point;
import ru.ifmo.se.lab4.domain.User;
import ru.ifmo.se.lab4.repository.HistoryEntryRepository;
import ru.ifmo.se.lab4.repository.UserRepository;

import java.util.List;

@Service
public class GraphService {

    private final UserRepository userRepository;

    private final HistoryEntryRepository historyEntryRepository;

    @Autowired
    public GraphService(UserRepository userRepository, HistoryEntryRepository historyEntryRepository) {
        this.userRepository = userRepository;
        this.historyEntryRepository = historyEntryRepository;
    }

    public HistoryEntry addPoint(HistoryEntry historyEntry, String username) {
        User owner = userRepository.findByUsername(username);
        historyEntry.setOwner(owner);
        historyEntry.setInArea(isInArea(historyEntry.getPoint(), historyEntry.getR()));

        return historyEntryRepository.save(historyEntry);
    }

    public List<HistoryEntry> getHistory(String username) {
        User owner = userRepository.findByUsername(username);
        return historyEntryRepository.findAllByOwner(owner);
    }

    public void clearHistory(String username) {
        User owner = userRepository.findByUsername(username);
        historyEntryRepository.removeAllByOwner(owner);
    }

    private boolean isInArea(Point point, double r) {
        double x = point.getX();
        double y = point.getY();

        if (x <= 0 && y <= 0 && sqr(r / 2) >= sqr(x) + sqr(y)) {
            return true;
        }

        if (x >= 0 && y <= 0 && x <= r / 2 && Math.abs(y) <= r) {
            return true;
        }

        return (x >= 0 && y >= 0 && y <= ((-x / 2) + r / 2));
    }

    private double sqr(double v) {
        return v * v;
    }
}
