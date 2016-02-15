package io.farragolabs.bugbuster.database;

public class DBFactory {

    public static Database database;

    public static synchronized Database get()
    {
        if(database == null)
        {
             database = new Database();
        }
        return database;
    }
}
