package fr.codevallee.formation.tp12;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guillaumelazaro on 16/10/2017.
 */

public class UserDAO {

    private UserDataSource lib;

    private static final String COL_ID = "id";
    private static final String COL_NOM = "nom";
    private static final String COL_PRENOM = "prenom";
    private static final String COL_AGE = "age";
    private static final String COL_METIER = "metier";
    private static final String TABLE_NAME = "users";

    public UserDAO(UserDataSource userDataSource) {
        this.lib = userDataSource;
    }

    public User create(User user) {
        ContentValues values = new ContentValues();
        values.put(COL_NOM, user.getNom());
        values.put(COL_PRENOM, user.getPrenom());
        values.put(COL_AGE, user.getAge());
        values.put(COL_METIER, user.getMetier());

        //Requete insert
        int id = (int) lib.getDB().insert(TABLE_NAME, null, values);
        user.setId(id);

        return user;
    }

    public User update(User user) {
        ContentValues values = new ContentValues();
        values.put(COL_NOM, user.getNom());
        values.put(COL_PRENOM, user.getPrenom());
        values.put(COL_AGE, user.getAge());
        values.put(COL_METIER, user.getMetier());

        //Création de la clause where
        String clause = COL_ID + " = ?";
        String[] clauseArgs = new String[]{String.valueOf(user.getId())};

        //On update la table
        lib.getDB().update(TABLE_NAME, values, clause, clauseArgs);

        return user;
    }

    public void delete(User user) {
        //Création de la clause where
        String clause = COL_ID + " = ?";
        String[] clauseArgs = new String[]{String.valueOf(user.getId())};

        //On update la table
        lib.getDB().delete(TABLE_NAME, clause, clauseArgs);
    }

    public void delete(int id) {
        //Création de la clause where
        String clause = COL_ID + " = ?";
        String[] clauseArgs = new String[]{String.valueOf(id)};

        //On update la table
        lib.getDB().delete(TABLE_NAME, clause, clauseArgs);
    }

    public User read(User user) { //TODO fix cette methode
        //Définition des colonnes utilisés:
        String[] allColumns = new String[]{COL_ID, COL_NOM, COL_PRENOM, COL_AGE, COL_METIER};

        //Création de la clause where pour la requéte SELECT
        String clause = COL_ID + " = ?";
        String[] clauseArgs = new String[]{String.valueOf(user.getId())};

        //Cursor cursor = lib.getDB().query(TABLE_NAME, allColumns, "ID = ?", clauseArgs, null, null, null); //ERREUR
        Cursor cursor = lib.getDB().query(TABLE_NAME, allColumns, clause, clauseArgs, null, null, null);

        //On lit le cursor récupéré:
        cursor.moveToFirst();
        user.setNom(cursor.getString(1));
        user.setPrenom(cursor.getString(2));
        user.setAge(cursor.getInt(3));
        user.setMetier(cursor.getString(4));
        cursor.close();

        return user;
    }

    public List<User> readAll() {
        //Définition des colonnes utilisés:
        String[] allColumns = new String[]{COL_ID, COL_NOM, COL_PRENOM, COL_AGE, COL_METIER};

        //Création de la clause where pour la requéte SELECT
        Cursor cursor = lib.getDB().query(TABLE_NAME, allColumns, null, null, null, null, null);

        // iterate on cursor and retreive result
        List<User> users = new ArrayList<User>();
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            users.add(new User(cursor.getInt(0), cursor.getString(1),
                    cursor.getString(2), cursor.getInt(3), cursor.getString(4)));
            cursor.moveToNext();
        }
        cursor.close();
        return users;
    }
}
