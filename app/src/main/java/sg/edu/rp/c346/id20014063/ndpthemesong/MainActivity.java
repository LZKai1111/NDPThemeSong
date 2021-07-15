package sg.edu.rp.c346.id20014063.ndpthemesong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnInsert, btnShowList;
    EditText etTitle, etSingers, etYear;
    RadioGroup rgStars;
    ArrayList<Song> al;
    ArrayAdapter<Song> aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnShowList = findViewById(R.id.btnShowList);
        etTitle = findViewById(R.id.etTitle);
        etSingers = findViewById(R.id.etSinger);
        etYear = findViewById(R.id.etYear);
        rgStars = findViewById(R.id.rgStars);



        al = new ArrayList<Song>();
        aa = new ArrayAdapter<Song>(this, android.R.layout.simple_list_item_1, al);



        btnShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, showListActivity.class);
                startActivity(i);
            }
        });

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etTitle.getText().toString();
                String singer = etSingers.getText().toString();
                int year = Integer.parseInt(etYear.getText().toString());
                int stars = getStars();
                DBHelper dbh = new DBHelper(MainActivity.this);
                long inserted_id = dbh.insertSong(title, singer, year, stars);

            if (inserted_id != -1){
                al.clear();
                al.addAll(dbh.getAllSongs());
                aa.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Insert successful", Toast.LENGTH_SHORT).show();
            }
            }
        });

    }

    private int getStars(){
        int stars = 0;
        if(rgStars.getCheckedRadioButtonId() == R.id.rb1){
            stars = 1;
        }
        else if(rgStars.getCheckedRadioButtonId() == R.id.rb2){
            stars = 2;
        }
        else if(rgStars.getCheckedRadioButtonId() == R.id.rb3){
            stars = 3;
        }
        else if(rgStars.getCheckedRadioButtonId() == R.id.rb4){
            stars = 4;
        }
        else if(rgStars.getCheckedRadioButtonId() == R.id.rb5){
            stars = 5;
        }
        return stars;
    }

}