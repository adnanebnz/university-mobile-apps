package com.example.tp6;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.Editable;
import android.util.Log;

import java.util.Date;

public class GestionBDD {
    BDDHelper instHelper;
    private Context contexte;

    // Constructeur
    public GestionBDD(Context context) {
        this.contexte = context;
        instHelper = new BDDHelper(contexte);
    }

    ////////////////////////////INSERT////////////////////////////////
    public long insererTache(Editable nom, String prio, Date date, String etat) {

        // Récupérer la base de données SQLite puis appeller sa méthode insert
        SQLiteDatabase bdd = instHelper.getWritableDatabase();
        ContentValues ligne = new ContentValues();
        // Attribuer les valeurs (nom, prio, date, etat) à « ligne »
        //TODO MY SOLUTION HERE
        ligne.put(BDDHelper.NomTask, nom.toString());
        ligne.put(BDDHelper.PrioTask, prio);
        ligne.put(BDDHelper.DeadTask, date.toString());
        ligne.put(BDDHelper.EtatAvancementTask, etat);
        //TODO END OF SOLUITON

        long id = bdd.insert(BDDHelper.NOM_TABLE, null, ligne);
        Log.i("*****", "L'id de la tâche insérée est = " + Integer.toString((int) id));
        return id;
    }

    ////////////////////////////DELETE////////////////////////////////
    // Supprimer les tâches dont leur état d'avancement = condition (DONE)
    public int supprimerTache(String condition) {
        // Récupérer la base de données SQLite puis appeller sa méthode delete
        SQLiteDatabase bdd = instHelper.getWritableDatabase();
        String[] tasksupp = {condition};
        int ret = bdd.delete(BDDHelper.NOM_TABLE, BDDHelper.EtatAvancementTask + "=?",
                tasksupp);
        Log.i("*****", "Nombre de tâches supprimées = " + Integer.toString((int) ret));
        return ret;
    }

    //////////////////////////SELECT////////////////////////////////
    public Cursor obtenirTache() {
        // Récupérer la base de données SQLite puis appeller sa méthode query

        //TODO MY SOLUTION HERE
        SQLiteDatabase bdd = instHelper.getWritableDatabase();
        String[] colonnes = {BDDHelper.NomTask, BDDHelper.PrioTask, BDDHelper.DeadTask, BDDHelper.EtatAvancementTask};
        Cursor curseur = bdd.query(BDDHelper.NOM_TABLE, colonnes, null, null, null, null, null);
        return curseur;
        //TODO END OF SOLUITON


    }
} 