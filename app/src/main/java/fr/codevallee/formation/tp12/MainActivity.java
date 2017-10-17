package fr.codevallee.formation.tp12;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listViewUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Création de la base et du DAO :
        UserDataSource userDataSource = new UserDataSource(this);
        UserDAO userDAO = new UserDAO(userDataSource);

        //Interface:
        listViewUsers = (ListView) findViewById(R.id.lv_users);
        Button buttonAdd = (Button) findViewById(R.id.button_add_user);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddUserActivity.class);
                startActivity(intent);
            }
        });

        //Remplissage:
        final ArrayList<User> listUser = (ArrayList<User>) userDAO.readAll();
        ArrayList<String> listName = new ArrayList<>();

        for (int i=0 ; i<listUser.size() ; i++) {
            listName.add(i, listUser.get(i).getNom()+" "+listUser.get(i).getPrenom());
        }
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listName); //Ancienne solution

        UserAdapter userAdapter = new UserAdapter(this, listUser);
        listViewUsers.setAdapter(userAdapter);

        //Ajout d'un clickListener pour accéder à l'écran de modification
        listViewUsers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                User userSelected = listUser.get(position);
                Intent intent = new Intent(MainActivity.this, ModifyUserActivity.class);
                intent.putExtra("id", userSelected.getId());
                intent.putExtra("nom", userSelected.getNom());
                intent.putExtra("prenom", userSelected.getPrenom());
                intent.putExtra("age", userSelected.getAge());
                intent.putExtra("metier", userSelected.getMetier());

                startActivity(intent);
            }
        });
    }
}