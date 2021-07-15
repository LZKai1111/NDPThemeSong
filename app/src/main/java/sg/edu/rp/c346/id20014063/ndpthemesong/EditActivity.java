package sg.edu.rp.c346.id20014063.ndpthemesong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {

    Button btnUpdate, btnDelete, btnCancel;
    EditText etTitle, etSingers, etYear;
    RadioGroup rgStars;
    Song data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        btnUpdate = findViewById(R.id.btnUpdate);
        btnCancel = findViewById(R.id.btnCancel);
        btnUpdate = findViewById(R.id.btnUpdate);
        etTitle = findViewById(R.id.etTitle);
        etSingers = findViewById(R.id.etSinger);
        etYear = findViewById(R.id.etYear);
        rgStars = findViewById(R.id.rgStars);

        Intent intent = getIntent();
        data = (Song) intent.getSerializableExtra("data");

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditActivity.this);
                dbh.deleteSong(data.getId());
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.setTitle(etTitle.getText().toString());
                data.setSingers(etSingers.getText().toString());
                data.setYears(Integer.parseInt(etYear.getText().toString()));
                data.setStars(getStars());
                DBHelper dbh = new DBHelper(EditActivity.this);
                dbh.updateSong(data);
                dbh.close();
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