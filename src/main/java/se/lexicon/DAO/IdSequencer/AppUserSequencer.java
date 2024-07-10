package se.lexicon.DAO.IdSequencer;

public class AppUserSequencer {
    private static int currentId = 0;

    public static int nextId() {
        return ++currentId;
    }

    public static int getCurrentId() {
        return currentId;
    }

    public static void setCurrentId(int id) {
        currentId = id;
    }

    public static void reset() {
        currentId = 0;
    }
}
