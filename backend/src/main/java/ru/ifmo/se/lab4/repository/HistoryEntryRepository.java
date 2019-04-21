package ru.ifmo.se.lab4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.ifmo.se.lab4.domain.HistoryEntry;
import ru.ifmo.se.lab4.domain.User;

import java.util.List;

public interface HistoryEntryRepository extends JpaRepository<HistoryEntry, Long> {

    List<HistoryEntry> findAllByOwner(User owner);

    @Transactional
    void removeAllByOwner(User owner);

}
