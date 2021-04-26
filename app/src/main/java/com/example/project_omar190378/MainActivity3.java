package com.example.project_omar190378;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class MainActivity3 extends AppCompatActivity {

    EditText name1, name2, email, id, field, newValue;
    Button add, delete, update, viewbttn, menu;
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    String fname, lname, mail, num;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        newValue = (EditText) findViewById(R.id.editTextTextPersonName2);
        field = (EditText) findViewById(R.id.editTextTextPersonName);
        name1 = (EditText) findViewById(R.id.fnameinput);
        name2 = (EditText) findViewById(R.id.lnameinput);
        email = (EditText) findViewById(R.id.mailinp);
        id = (EditText) findViewById(R.id.idinput);
        add = (Button) findViewById(R.id.add);
        delete = (Button) findViewById(R.id.button2);
        update = (Button) findViewById(R.id.fbupdate);
        viewbttn = (Button) findViewById(R.id.ViewFB);
        menu = (Button) findViewById(R.id.mainact);

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity3.this, MainActivity.class));
            }
        });


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rootNode = FirebaseDatabase.getInstance(); //call the root node and include all of them
                reference = rootNode.getReference();


                fname = name1.getText().toString();
                lname = name2.getText().toString();
                mail = email.getText().toString();
                num = id.getText().toString();

                if (TextUtils.isEmpty(fname) || TextUtils.isEmpty(lname) || TextUtils.isEmpty(mail) || TextUtils.isEmpty(num)) {
                    Toast.makeText(MainActivity3.this, "please enter all user's info", Toast.LENGTH_LONG).show();

                } else {
                    writeNewUser(fname, lname, mail, num);
                    name1.getText().clear();
                    name2.getText().clear();
                    email.getText().clear();
                    Toast.makeText(MainActivity3.this, "User Added to the FB DB", Toast.LENGTH_LONG).show();

                }
            }


            private void writeNewUser(String fname, String lname, String mail, String num) {
                User user = new User(fname, lname, mail, num);
                reference.child("users").child(num).setValue(user);
            }
        });


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                num = id.getText().toString();
                if (TextUtils.isEmpty(id.getText().toString())) {

                    Toast.makeText(MainActivity3.this, "Please Enter ID number", Toast.LENGTH_LONG).show();
                } else
                    deleteUser(num);

            }


            private void deleteUser(String num) {
                DatabaseReference dltuser = FirebaseDatabase.getInstance().getReference("users").child(num);

                dltuser.removeValue();
                Toast.makeText(MainActivity3.this, "User Deleted from the system", Toast.LENGTH_LONG).show();


            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idmain = id.getText().toString();
                String f = field.getText().toString();
                String newval = newValue.getText().toString();

                try{
                    if(TextUtils.isEmpty(idmain)
                    || TextUtils.isEmpty(newval) || TextUtils.isEmpty(f))
                        Toast.makeText(MainActivity3.this, "Please make sure you entered ID, field and updated value correctly",
                                Toast.LENGTH_LONG).show();
                    else {
                        rootNode = FirebaseDatabase.getInstance();
                        reference = rootNode.getReference("users");
                        reference.child(idmain).child(f).setValue(newval);
                    }
                }

                catch(Exception e){
                    Toast.makeText(MainActivity3.this, "Something went wrong check user's ID", Toast.LENGTH_LONG).show();
                }

            }
        });


        viewbttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity3.this, displayUsers.class));
            }
        });


    }


    }
        @IgnoreExtraProperties
         class User {
            public String fname;
            public String lname;
            public String mail;
            public String num;

            public User() {
                // constructor
            }

            public User(String fname, String lname, String mail, String num) {
                this.fname = fname;
                this.lname = lname;
                this.mail = mail;
                this.num = num;
            }
        }
