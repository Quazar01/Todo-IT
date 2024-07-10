package se.lexicon.DAO;

import se.lexicon.Model.AppUser;

import java.util.List;

public interface AppUserDAO {
    AppUser persist(AppUser appUser);
    AppUser findByUserName(String userName);
    List<AppUser> findAll();
    void remove(String username);
}
