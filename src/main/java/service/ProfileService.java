package service;

import entity.Profile;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import repository.ProfileRepository;

import java.sql.SQLException;

@RequiredArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;
    public Profile getById(Long id) throws SQLException {
        return profileRepository.get(id);
    }
    public Profile getByLoginPassword(String login, String password) throws SQLException {
        return profileRepository.get(login,password);
    }
    @SneakyThrows
    public boolean checkExisting(Profile profile) throws SQLException {
        return profileRepository.check(profile);
    }
}
