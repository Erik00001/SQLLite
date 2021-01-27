package com.example.sqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.SQLException;

public class MainActivity extends AppCompatActivity {
    EditText etName,etCell,etID;
    Button checkBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etName=(EditText)findViewById(R.id.etName);
        etCell=(EditText)findViewById(R.id.editTextPhone);
        etID=(EditText)findViewById(R.id.etID);
        etID.setVisibility(View.GONE);
        checkBox=(CheckBox)findViewById(R.id.checkBox);
    }
    public void checkBoxClicked(View v){
        if(((CheckBox)v).isChecked()){
            etID.setVisibility(View.VISIBLE);
        }
        else
            etID.setVisibility(View.GONE);
            etID.setText(null);
    }

    public void btnSubmit(View v){
        String name=etName.getText().toString().trim();
        String cell=etCell.getText().toString().trim();

        ContactsDB db=new ContactsDB(this);
        db.open();
        db.createEntry(name,cell);
        db.close();
        Toast.makeText(this,"Successfully saved!",Toast.LENGTH_SHORT).show();
        etCell.setText("");
        etName.setText("");
    }

    public void btnShowData(View v){
        startActivity(new Intent(this,Data.class));
    }

    public void btnEditData(View v){
        String name=etName.getText().toString().trim();
        String cell=etCell.getText().toString().trim();
        String id=etID.getText().toString().trim();
        ContactsDB db=new ContactsDB(this);
        db.open();
        db.updateEntry(id,name,cell);
        db.close();
        Toast.makeText(this,"Successfully edited!",Toast.LENGTH_SHORT).show();
        etCell.setText("");
        etName.setText("");
        etID.setText("");

    }

    public void btnDeleteData(View v){
        String id=etID.getText().toString().trim();
        ContactsDB db=new ContactsDB(this);
        db.open();
        db.deleteEntry(id);
        db.close();
        Toast.makeText(this,"Successfully deleted!",Toast.LENGTH_SHORT).show();
        etID.setText("");

    }
}