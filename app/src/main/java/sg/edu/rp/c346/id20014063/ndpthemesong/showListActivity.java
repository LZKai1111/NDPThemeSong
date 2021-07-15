package sg.edu.rp.c346.id20014063.ndpthemesong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class showListActivity extends AppCompatActivity {
    ListView lv;
    Button btnFilter;
    ArrayList<Song> al;
    ArrayAdapter<Song> aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list);

        btnFilter = findViewById(R.id.btnfilter5star);
        lv = findViewById(R.id.lv);

        al = new ArrayList<Song>();
        aa = new ArrayAdapter<>(showListActivity.this, android.R.layout.simple_list_item_1, al);
        lv.setAdapter(aa);

        DBHelper dbh = new DBHelper(showListActivity.this);
        al.addAll(dbh.getAllSongs());
        aa.notifyDataSetChanged();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Song item = aa.getItem(position);

                Intent i = new Intent(showListActivity.this, EditActivity.class);
                i.putExtra("data", item);
                startActivity(i);
            }
        });
    }
}