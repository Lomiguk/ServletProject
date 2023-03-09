package service;

import entity.Dinosaur;
import lombok.RequiredArgsConstructor;
import repository.DinosaurRepository;

import java.sql.SQLException;
import java.util.Collection;

@RequiredArgsConstructor
public class DinosaurService {
    private final DinosaurRepository dinosaurRepository;
    public Dinosaur getById(Long id) throws SQLException {
        return dinosaurRepository.get(id);
    }

    public Collection<Dinosaur> getAll() throws SQLException {
        return dinosaurRepository.getAll();
    }

    public void add(Dinosaur dinosaur) throws SQLException {
        dinosaurRepository.add(dinosaur);
    }
}
