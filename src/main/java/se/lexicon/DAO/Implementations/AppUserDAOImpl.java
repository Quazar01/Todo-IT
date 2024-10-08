package se.lexicon.DAO.Implementations;

import se.lexicon.DAO.*;
import se.lexicon.DAO.IdSequencer.AppUserSequencer;
import se.lexicon.Model.AppUser;

import java.util.ArrayList;
import java.util.List;

public class AppUserDAOImpl implements AppUserDAO {
    private final List<AppUser> appUserList;

    // Constructor
    public AppUserDAOImpl() {
        appUserList = new ArrayList<>();
    }

    @Override
    public AppUser persist(AppUser appUser) {
        if (appUser == null) throw new IllegalArgumentException("AppUser should not be null");
        if (appUserList.contains(appUser)) throw new IllegalArgumentException("AppUser is already in the list");
        // Set an id for the appUser
        appUser.setId(AppUserSequencer.nextId());
        // Add the appUser to the list
        appUserList.add(appUser);
        return appUser;
    }

    @Override
    public AppUser findByUserName(String userName) {
        if (userName == null) throw new IllegalArgumentException("Username should not be null");
        for (AppUser appUser : appUserList) {
            if (appUser.getUsername().equalsIgnoreCase(userName)) {
                return appUser;
            }
        }
        return null;
    }

    @Override
    public List<AppUser> findAll() {
        return appUserList;
    }

    @Override
    public void remove(String username) {
        if (username == null || username.trim().isEmpty()) throw new IllegalArgumentException("Username should not be null");
        for (AppUser appUser : appUserList) {
            if (appUser.getUsername().equalsIgnoreCase(username)) {
                appUserList.remove(appUser);
                break;
            }
        }
    }
}
