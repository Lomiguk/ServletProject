package repository;

import entity.Profile;
import lombok.AllArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@AllArgsConstructor
public class ProfileRepository {
    private final String SELECT_BY_ID_QUERY =
            "SELECT id, login, password FROM profile WHERE id=?;";
    private final String SELECT_BY_LOGIN_PASSWORD =
            "SELECT id, login, password FROM profile WHERE login=? AND password=?;";
    private final String CHECK_EXISTING_QUERY =
            "SELECT COUNT(*)>0 FROM profile WHERE login=? AND password=?;";
    private ConnectionFactory connectionFactory;
    public Profile get(Long id) throws SQLException {
        try(Connection connection = connectionFactory.getConnection()){
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_QUERY);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()){
                return toProfileEntity(resultSet);
            }
            return null;
        }
    }
    public Profile get(String login, String password) throws SQLException {
        try(Connection connection = connectionFactory.getConnection()){
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_LOGIN_PASSWORD);
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            return toProfileEntity(resultSet);
        }
    }
    public Boolean check(Profile profile) throws SQLException {
        try(Connection connection = connectionFactory.getConnection()){
            PreparedStatement statement = connection.prepareStatement(CHECK_EXISTING_QUERY);
            statement.setString(1, profile.getLogin());
            statement.setString(2, profile.getPassword());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                return resultSet.getBoolean(1);
            }
            return null;
        }
    }

    private Profile toProfileEntity(ResultSet resultSet) throws SQLException {
        return Profile.builder()
                .id(resultSet.getLong("id"))
                .login(resultSet.getString("login"))
                .password("password")
                .build();
    }
}
