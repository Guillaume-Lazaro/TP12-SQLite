package fr.codevallee.formation.tp12;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddUserActivity extends AppCompatActivity {

    private EditText editTextNom;
    private EditText editTextPrenom;
    private EditText editTextAge;
    private EditText editTextMetier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        //Interface:
        Button buttonOK = (Button) findViewById(R.id.button_ok);
        editTextNom = (EditText) findViewById(R.id.et_nom);
        editTextPrenom = (EditText) findViewById(R.id.et_prenom);
        editTextAge = (EditText) findViewById(R.id.et_age);
        editTextMetier = (EditText) findViewById(R.id.et_metier);

        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(champRemplis()) {
                    //On crée l'user et on sort de l'activity
                    createUser();
                    Intent intent = new Intent(AddUserActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    public void createUser() {
        //Récupération du contenu des champs et création de l'objet newUser
        String nom,prenom,metier;
        int age;
        nom = editTextNom.getText().toString();
        prenom = editTextPrenom.getText().toString();
        age = Integer.valueOf(editTextAge.getText().toString());
        metier = editTextMetier.getText().toString();
        User newUser = new User(nom,prenom,age,metier);

        //Récupération de la base de données et insertion de l'objet newUser:
        UserDataSource userDataSource = new UserDataSource(this);
        UserDAO userDAO = new UserDAO(userDataSource);
        userDAO.create(newUser);
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