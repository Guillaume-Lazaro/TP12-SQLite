package fr.codevallee.formation.tp12;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ModifyUserActivity extends AppCompatActivity {

    private EditText editTextNom;
    private EditText editTextPrenom;
    private EditText editTextAge;
    private EditText editTextMetier;

    private User userCourant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_user);

        Intent intent = getIntent();
        int id = intent.getIntExtra("id",0);
        String nom = intent.getStringExtra("nom");
        String prenom = intent.getStringExtra("prenom");
        int age = intent.getIntExtra("age",0);
        String metier = intent.getStringExtra("metier");

        this.userCourant = new User(id,nom,prenom,age,metier);

        //Interface:
        Button buttonModifier = (Button) findViewById(R.id.button_modifier);
        Button buttonSupprimer = (Button) findViewById(R.id.button_supprimer);
        editTextNom = (EditText) findViewById(R.id.et_nom);
        editTextPrenom = (EditText) findViewById(R.id.et_prenom);
        editTextAge = (EditText) findViewById(R.id.et_age);
        editTextMetier = (EditText) findViewById(R.id.et_metier);

        //Remplissage des champs:
        editTextNom.setText(userCourant.getNom());
        editTextPrenom.setText(userCourant.getPrenom());
        editTextAge.setText(""+userCourant.getAge());
        editTextMetier.setText(userCourant.getMetier());

        buttonModifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(champRemplis()) {
                    modifierUser();
                    Intent intent = new Intent(ModifyUserActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });

        buttonSupprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(champRemplis()) {
                    supprimerUser();
                    Intent intent = new Intent(ModifyUserActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    public void modifierUser() {
        //Récupération du contenu des champs et création de l'objet newUser
        String nom,prenom,metier;
        int age,id;
        id = userCourant.getId();
        nom = editTextNom.getText().toString();
        prenom = editTextPrenom.getText().toString();
        age = Integer.valueOf(editTextAge.getText().toString());
        metier = editTextMetier.getText().toString();
        User newUser = new User(id,nom,prenom,age,metier);

        //Récupération de la base de données et insertion de l'objet newUser:
        LibraryDataSource libraryDataSource = new LibraryDataSource(this);
        UserDAO userDAO = new UserDAO(libraryDataSource);

        //userDAO.create(newUser);
        userDAO.update(newUser);
    }

    public void supprimerUser() {
        //Récupération de la base de données et insertion de l'objet newUser:
        LibraryDataSource libraryDataSource = new LibraryDataSource(this);
        UserDAO userDAO = new UserDAO(libraryDataSource);

        //userDAO.delete(userCourant);
        userDAO.delete(userCourant.getId() );
    }

    public boolean champRemplis() {
        if (editTextNom.getText().toString().equals(""))
            return false;
        else if (editTextPrenom.getText().toString().equals(""))
            return false;
        else if (editTextAge.getText().toString().equals(""))
            return false;
        else if (editTextMetier.getText().toString().equals(""))
            return false;
        else
            return true;
    }
}
