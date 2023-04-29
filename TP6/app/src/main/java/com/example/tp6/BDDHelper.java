package com.example.tp6;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class BDDHelper extends SQLiteOpenHelper {
    public static final String NOM_TABLE = "Planning";
    public static final String NOM_BDD = "MaBDD";
    public static final int Version_BDD = 1;
    public static final String NomTask = "Nom";
    public static final String PrioTask = "Priorite";
    public static final String DeadTask = "Deadline";
    public static final String EtatAvancementTask = "Etat";
    public static final String CREATE_TABLE;

    static {
        CREATE_TABLE = "CREATE TABLE " + NOM_TABLE + " (_id INTEGER PRIMARY KEY, "
                + NomTask + " VARCHAR(255), " + PrioTask + " VARCHAR(255), " + EtatAvancementTask +
                " VARCHAR(255) , " + DeadTask + " Date ); ";
    }

    public BDDHelper(Context context) {
        super(context, NOM_BDD, null, Version_BDD);
    }

    @Override
    public void onCreate(SQLiteDatabase bdd) {
        bdd.execSQL(CREATE_TABLE);
        Log.i("*****", "Chemin de la BDD = " + bdd.getPath());
    }

    // Lorsque la table est mise à jour, il faut supprimer l'ancienne table puis recréer une nouvelle Ceci évitera d'avoir différentes tables ayant le même nom.

    @Override
    public void onUpgrade(SQLiteDatabase bdd, int oldVersion, int newVersion) {
        bdd.execSQL("DROP TABLE IF EXISTS " + NOM_TABLE);
        onCreate(bdd);
    }
} 