package com.example.sqlitedbapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Database1 database1;
    EditText editText1,editText2,editText3,editText4;
    Button button1,button2,button3,button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database1=new Database1(MainActivity.this);

        editText1=(EditText)findViewById(R.id.editText1);
        editText2=(EditText)findViewById(R.id.editText2);
        editText3=(EditText)findViewById(R.id.editText3);
        editText4=(EditText)findViewById(R.id.editText4);
        button1=(Button)findViewById(R.id.button1);
        button2=(Button)findViewById(R.id.button2);
        button3=(Button)findViewById(R.id.button3);
        button4=(Button)findViewById(R.id.button4);

        insertdata();
        showdata();
        updatedata();
        deletedata();
    }

    public void insertdata(){
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean r=database1.insert(editText1.getText().toString(),editText2.getText().toString(),editText3.getText().toString(),editText4.getText().toString());
                if(r==true)
                    Toast.makeText(MainActivity.this, "Inserted Sucessfully!!!", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this, "Error while inserting data!!!", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void showdata(){
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cursor=database1.show();
                StringBuffer stringBuffer=new StringBuffer();
                if(cursor.getCount()<=0){
                    message("Studebt Data","Data not found!!!");
                }
                else{
                    while(cursor.moveToNext()){
                        stringBuffer.append("\nId: "+cursor.getString(0)+", Name: "+cursor.getString(1)
                                +", Surname: "+cursor.getString(2)+", Mark: "+cursor.getString(3)+"\n");
                    }
                    message("Student Data",stringBuffer.toString());
                }
            }
        });
    }

    public void message(String title,String message){
        AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
        builder.setMessage(message);
        builder.setTitle(title);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.show();
    }

    public void updatedata(){
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean flag=database1.updateDB(editText1.getText().toString(), editText2.getText().toString(),
                        editText3.getText().toString(), editText4.getText().toString());

                if(flag==true)
                {
                    Toast.makeText(MainActivity.this,"Data Updated!!!",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Error in updating data!!!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void deletedata(){
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean r=database1.deleteDB(editText1.getText().toString());
                if(r==true)
                    Toast.makeText(MainActivity.this, "Data deleted successfully!!!", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this, "Error while deleting data!!!", Toast.LENGTH_LONG).show();
            }
        });
    }
}