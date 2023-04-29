package com.example.tp6;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import java.util.List;

public class TasksContentProvider extends ContentProvider {
    private SQLiteDatabase bdd;

    @Override
    public boolean onCreate() {
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[]
            selectionArgs, String sortOrder) {

        // Récupérer les données à partir de l'URI sachant que uri.getPathSegments()).toString()= [MaBDD,Planning]
        String nomBDD = uri.getPathSegments().get(0);
        String nomTable = uri.getPathSegments().get(1);
        // Vérifier que la demande ne concerne pas une ligne particulière de la table c'est à dire une uri de type id_based (demander la ligne ayant un identifiant spécifié dans l'uri). Si c'est le cas, il faut récupérer les valeurs pour selection et selectionArgs à partir de l'URI passée en paramètre.

        String id = null;
        List<String> pathSeg = uri.getPathSegments();
        if (pathSeg.size() > 2) {
            id = uri.getLastPathSegment();
            if (selectionArgs == null) {
                selectionArgs = new String[]{id};
                selection = "_id= ?";
            } else {
                selection = selection + " AND _id=?";
                String[] tmp = new String[selectionArgs.length + 1];
                for (int i = 0; i < selectionArgs.length; i++) {
                    tmp[i] = selectionArgs[i];
                }
                tmp[tmp.length - 1] = id;
                selectionArgs = tmp;
            }
        }
        // Récupérer la base de données (en passant par son chemin) puis appeler la méthode query qui est locale à la BDD
        return null;    //????????
    }

    /////////////////////////////////////
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[]
            selectionArgs) {
        return 0;
    }
}