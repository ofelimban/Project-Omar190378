package com.example.project_omar190378;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    DatabaseHelper myDB;
    EditText id, fn, ln, mail;
    Button add, dlt, upd, view, act1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        id = (EditText) findViewById(R.id.idinput);
        fn = (EditText) findViewById(R.id.fnameinput);
        ln = (EditText) findViewById(R.id.lnameinput);
        mail = (EditText) findViewById(R.id.mailinp);

        add = (Button) findViewById(R.id.add);
        dlt = (Button) findViewById(R.id.button2);
        view = (Button) findViewById(R.id.ViewFB);
        upd = (Button) findViewById(R.id.button4);
        act1 = (Button) findViewById(R.id.mainact);

        myDB = new DatabaseHelper(this);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try{
                if(myDB.addData(Integer.parseInt(id.getText().toString()),fn.getText().toString(), ln.getText().toString(),
                        mail.getText().toString())==false)
                    Toast.makeText(MainActivity2.this, "Input already exist please try again", Toast.LENGTH_LONG).show();

                    else
                        Toast.makeText(MainActivity2.this, "user added", Toast.LENGTH_LONG).show();
                }

                catch(Exception e){
                    Toast.makeText(MainActivity2.this, "please enter all inputs", Toast.LENGTH_LONG).show();

                }
        }});

        dlt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int result=myDB.dltRow(id.getText().toString());

                if(result>=1) {
                    Toast.makeText(MainActivity2.this,"User: " +id.getText().toString() + " susscessfully deleted", Toast.LENGTH_LONG).show();
                    id.getText().clear(); fn.getText().clear(); ln.getText().clear();
                    mail.getText().clear();
                }
                else
                    Toast.makeText(MainActivity2.this,"No user deleted Please enter ID correctly",Toast.LENGTH_LONG).show();

            }
        });

        upd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try{
                if(TextUtils.isEmpty(id.getText().toString()) || TextUtils.isEmpty(mail.getText().toString()) ){
                    Toast.makeText(MainActivity2.this, "Please enter ID and updated email", Toast.LENGTH_LONG).show();
                }
                myDB.update(Integer.parseInt(id.getText().toString()), mail.getText().toString());
                Toast.makeText(MainActivity2.this, "User: "+id.getText().toString()+
                        " updated his/her email", Toast.LENGTH_SHORT).show();
                id.getText().clear();
                mail.getText().clear();
            }
            catch(Exception ex){

            }}
        });

        view.setOnClickListener(new View.OnClickListener() {


            Cursor cur;
            StringBuffer buffer=new StringBuffer();

            @Override
            public void onClick(View v) {
                if (fn.getText().toString().equals("")) {
                    cur = myDB.getListContents();
                } else if (!(fn.getText().toString().equals(""))) {
                    cur = myDB.getSpecificResult(fn.getText().toString());
                }

                if (cur.getCount() == 0)
                    Toast.makeText(MainActivity2.this, "No users found !", Toast.LENGTH_LONG).show();
                else {

                    while (cur.moveToNext()) {
                        for (int i = 0; i < cur.getColumnCount(); i++) {
                            buffer.append(cur.getColumnName(i) + ": " + cur.getString(i) + "\n");
                        }
                        buffer.append("\n");

                    }

                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity2.this);
                    builder.setCancelable(true);
                    builder.setTitle("Users: ");
                    builder.setMessage(buffer.toString());
                    builder.show();

                    buffer.delete(0, buffer.capacity());
                }
            }
        });

        act1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity2.this, MainActivity.class));
            }
        });



    }
}