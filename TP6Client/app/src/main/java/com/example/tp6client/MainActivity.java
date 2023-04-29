package com.example.tp6client;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lvAffichage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Récupérer les views déclarées dans l'interface (ListView + Bouton)
        lvAffichage = (ListView) findViewById(R.id.listView);
        Button bout = (Button) findViewById(R.id.button);

// Appeler la méthode getDataFromBDD (pour récupérer les données de la BDD) puis la méthode displayGotData (pour afficher les données récupérées dans un ListView) lors du clic sur le bouton

        bout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Cursor cur = getDataFromBDD();
                    displayGotData(cur);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
    // Intérroger la BDD de l'application initiale (application1) en passant par son Content
    //Provider (il faut appeler la méthode query à partir du contentResolver en spécifiant l’URI de la table). L’URI doit spécifier l’authority, le nom de la BDD ainsi que le nom de la table.
    private Cursor getDataFromBDD() {
        //TODO MY SOLUTION
        Uri uri = Uri.parse("content://com.tp6.provider/MaBDD/Planning");
        String[] projection = {"_id", "Nom","Priorite","Deadline","Etat"};
        Cursor curseur = getContentResolver().query(
                uri, projection, null, null, null);
        return curseur;
        //TODO MY SOLUTION END
    }
    // Afficher les données sur ListView. On s’intéresse uniquement à l’identifiant et au nom de chaque tâche
    private void displayGotData(Cursor cur) {
        ArrayList<String> lignesLues = new ArrayList<>(cur.getCount());
        if (cur.moveToFirst()) {
            while (!cur.isAfterLast()) {
                int id = cur.getColumnIndex("_id");
                int nom = cur.getColumnIndex("Nom");
                lignesLues.add(cur.getString(id) + "-" + cur.getString(nom) + "\n");
                cur.moveToNext();
            }
            cur.close(); // fermer le cursor
            //Afficher le résultat sur un ListView
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,
                    android.R.layout.simple_list_item_1, lignesLues);
            lvAffichage.setAdapter(adapter);
        } else {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,
                    android.R.layout.simple_list_item_1, new String[]{"La BDD des tâches est vide"});
            lvAffichage.setAdapter(adapter);
        }
    }
}