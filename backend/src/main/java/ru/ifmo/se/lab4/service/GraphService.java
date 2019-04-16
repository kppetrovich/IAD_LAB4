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

    private boolean isInArea(Point point, double r) {
        // TODO
        return false;
    }
}
