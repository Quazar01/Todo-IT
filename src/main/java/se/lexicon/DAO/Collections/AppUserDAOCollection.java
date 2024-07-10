package se.lexicon.DAO.Collections;

import se.lexicon.DAO.*;
import se.lexicon.DAO.IdSequencer.AppUserSequencer;
import se.lexicon.Model.AppUser;

import java.util.ArrayList;
import java.util.List;

public class AppUserDAOCollection implements AppUserDAO {
    private final List<AppUser> appUserList;

    // Constructor
    public AppUserDAOCollection() {
        appUserList = new ArrayList<>();
    }

    @Override
    public AppUser persist(AppUser appUser) {
        // Set an id for the appUser
        appUser.setId(AppUserSequencer.nextId());
        // Add the appUser to the list
        appUserList.add(appUser);
        return appUser;
    }

    @Override
    public AppUser findByUserName(String userName) {
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

        for (AppUser appUser : appUserList) {
            if (appUser.getUsername().equalsIgnoreCase(username)) {
                appUserList.remove(appUser);
                break;
            }
        }
    }
}
