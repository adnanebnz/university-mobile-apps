package com.example.tp6;

import android.content.res.TypedArray;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    TypedArray etatVals;
    TypedArray prioVals;
    private String etatAvanc;
    private Editable deadl;
    private Editable taskName;
    private String prio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Supprimer (Réinitialise) la BDD à chaque lancement de l'application
        this.deleteDatabase("MaBDD");
        // Récupére les view déclarées dans l'interface (fichier layout xml)
        Button addBout = (Button) findViewById(R.id.button2);
        Button viewBout = (Button) findViewById(R.id.button4);
        Button delBout = (Button) findViewById(R.id.button3);
        EditText nomEditText = (EditText) findViewById(R.id.editTextTextPersonName);
        EditText dateEditText = (EditText) findViewById(R.id.editTextDate2);
        ListView affichageLV = (ListView) findViewById(R.id.listView);
        Spinner prioSpinn = (Spinner) findViewById(R.id.spinner);
        Spinner etatSpinn = (Spinner) findViewById(R.id.spinner2);
        // Récupérer les ressources String-Array de type TypesArray qui sont déclarées dans le fichier strings.xml
        etatVals = getResources().obtainTypedArray(R.array.etat);
        prioVals = getResources().obtainTypedArray(R.array.priorite);
        // Créer une instance du gestionnaire de la BDD (classe à partir de laquelle on peut manipuler la BDD)
        GestionBDD control = new GestionBDD(MainActivity.this);
        // Ajouter les Listeners aux boutons
        // Bouton ajouter une nouvelle tâche
        addBout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Dans un premier temps il faut vérifier si tous les champs ont bien éte remplis
                // Puis récupérer les valeurs saisies dans des variables (taskName,etatAvanc,deadl,prio )
                if (!(nomEditText.getText().toString()).equals("") &&
                        !(dateEditText.getText().toString()).equals("")) {
                    // Récupérer le nom de la tâche
                    taskName = nomEditText.getText();
                    // Récupérer la date d'échéance de la tâche
                    deadl = dateEditText.getText();
                    // Récupérer l'état d'avancement
                    etatAvanc = etatVals.getString(etatSpinn.getSelectedItemPosition());
                    etatSpinn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            etatAvanc = etatVals.getString(position);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                    // Récupérer la priorité
                    prio = prioVals.getString(prioSpinn.getSelectedItemPosition());
                    prioSpinn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            etatAvanc = prioVals.getString(position);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                    // Deuxième étape appeler la méthode insert (il est nécessaire de convertir deadl en un objet Date):
                    try {
                        Date deadDateFormat = new SimpleDateFormat("dd/mm/yyyy").parse(deadl.toString());
                        long id = control.insererTache(taskName, prio, deadDateFormat,
                                etatAvanc);
                        Toast.makeText(MainActivity.this, "L'id de la tâche est = " + id, Toast.LENGTH_LONG).show();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                // Si il manque des informations concernant la tâche à ajouter dans la BDD
                else {
                    Toast.makeText(MainActivity.this, "Veuillez saisir les champs manquants", Toast.LENGTH_SHORT).show();
                }
            }
        });
        // Bouton Afficher les tâches disponibles dans la BDD
        viewBout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    // Appelet la méthode obtenirTache ensuite récupérer les lignes du cursseur retourné
                    Cursor cur = control.obtenirTache();
                    String[] lignesLues = new String[cur.getCount()];
                    if (cur.getCount() > 0) {
                        for (int i = 0; i < cur.getCount(); i++) {
                            cur.moveToNext();
                            int id = cur.getInt(0);
                            String nom = cur.getString(1);
                            String prio = cur.getString(2);
                            String etat = cur.getString(3);
                            String dead = cur.getString(4);
                            lignesLues[i] = id + "---" + nom + "---" + prio + "---" + etat + "---" + dead + "\n";
                        }
                        cur.close(); // fermer le cursor
                        // Afficher les lignes récupérées dans une ListView.
                        ArrayAdapter<String> adapter = new
                                ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, lignesLues);
                        affichageLV.setAdapter(adapter);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        // Bouton supprimer les tâches réalisées depuis la BDD
        delBout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                control.supprimerTache("Done");
            }
        });
    }
}