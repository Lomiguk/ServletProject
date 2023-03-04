package repository;

import entity.Dinosaur;
import lombok.AllArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
public class DinosaurRepository {
    private static String SELECT_BY_ID_QUERY =
            "SELECT id, name FROM dinosaur WHERE id = ?;";
    private static String SELECT_ALL_QUERY =
            "SELECT id, name FROM dinosaur;";
    private ConnectionFactory connectionFactory;

    public Dinosaur get(Long id) throws SQLException {
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_QUERY);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return toDinosaurEntity(resultSet);
            }
            return null;
        }
    }

    public Collection<Dinosaur> getAll() throws SQLException {
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_QUERY);
            ResultSet resultSet = statement.executeQuery();
            Set<Dinosaur> dinosaurSet = new HashSet<>();
            while (resultSet.next()) {
                dinosaurSet.add(toDinosaurEntity(resultSet));
            }
            return dinosaurSet;
        }
    }

    private Dinosaur toDinosaurEntity(ResultSet resultSet) throws SQLException {
        return Dinosaur.builder()
                .id(resultSet.getLong("id"))
                .name(resultSet.getString("name"))
                .build();
    }
}
