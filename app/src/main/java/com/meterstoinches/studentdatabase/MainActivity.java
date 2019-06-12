package com.meterstoinches.studentdatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView t1;
    DatabaseHandler mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1 = findViewById(R.id.t1);
        t1.setMovementMethod(new ScrollingMovementMethod());
        mydb = new DatabaseHandler(this);
        mydb.getReadableDatabase();
        displayall();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.add_student){
            addstudent();
            update();
            return true;
        }
        if(id==R.id.add_many){
            addmany();
            update();
            return true;
        }
        if(id==R.id.delete_last){
            mydb.deleteLastRow();
            update();
            return true;
        }
        if(id==R.id.delete_database){
            mydb.deleteAll();
            update();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void addstudent(){
        mydb.addStudent("Davinder","1893729","davinder@gmail.com");
    }
    public void addmany(){
        mydb.addStudent("Rajvir","1893552","rajvir@gmail.com");
        mydb.addStudent("Vivek","1893553","vivek@gmail.com");
        mydb.addStudent("Akshil","1893554","akshil@gmail.com");
        mydb.addStudent("Jagdeep","1893550","jagdeep@gmail.com");
        mydb.addStudent("Harkirat","1893551","harkirat@gmail.com");
    }
    public void displayall(){
        List<Student> students = mydb.getAllStudents();
        if (students.size()==0){
            t1.setText("No records in the Database");
        }

        for (Student student : students){
            String log = "ID: "+student.getStudent_id()+" , Name: "+student.getName()
                    +" , Roll No: "+student.getRoll_number()+" , e-mail: "+student.getEmail_ID();
            t1.append(log+"\n"+"\n");
        }
    }
    public void update(){
        t1.setText("");
        displayall();
    }
}
