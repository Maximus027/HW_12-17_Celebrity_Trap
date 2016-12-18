package nyc.c4q.huilin.celebritytrap;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import nl.qbusict.cupboard.QueryResultIterable;
import nyc.c4q.huilin.celebritytrap.model.Celebrity;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;
import static nyc.c4q.huilin.celebritytrap.MyNotificationService.CELEB_IMG;
import static nyc.c4q.huilin.celebritytrap.MyNotificationService.CELEB_NAME;

public class MainActivity extends AppCompatActivity implements CelebrityAdapter.Listener, EditCelebrityDialogListener{

    private SQLiteDatabase db;
    private CelebrityAdapter celebAdapter;
    private RecyclerView celebCollection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scheduleAlarm();
        createDatabase();
        addContractedCeleb(getIntent());
        initiateRV();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void initiateRV() {
        celebAdapter = new CelebrityAdapter(selectAllCeleb(), this);

        celebCollection = (RecyclerView) findViewById(R.id.rv_celebrities);
        celebCollection.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));
        celebCollection.setAdapter(celebAdapter);
    }

    private void createDatabase() {
        CelebrityDatabaseHelper dbHelper = CelebrityDatabaseHelper.getInstance(getApplicationContext());
        db = dbHelper.getWritableDatabase();
    }

    private void addContractedCeleb(Intent intent) {
        if (intent.getExtras() != null) {
            Bundle extras = intent.getExtras();

            // FIXME got the information and created celebrity with it but no image

            String celebName = extras.getString(CELEB_NAME);
            String celebImgTxt = extras.getString(CELEB_IMG);

            addCeleb(new Celebrity(celebName, celebImgTxt));
        }

    }

    private void addCeleb(Celebrity celeb) {
        cupboard().withDatabase(db).put(celeb);
    }

    private List<Celebrity> selectAllCeleb() {
        List<Celebrity> celebs = new ArrayList<>();

        QueryResultIterable<Celebrity> itr = cupboard().withDatabase(db).query(Celebrity.class).query();
        for (Celebrity celeb : itr) {
            celebs.add(celeb);
        }
        itr.close();

        return celebs;
    }

    private void refreshCelebrityList() {
        celebAdapter.setData(selectAllCeleb());
    }

    // TODO implement dialog with edittext and grab input to update db

    @Override
    public void onCelebrityClicked(Celebrity celeb) {
        Toast.makeText(this, "Celebrity Status", Toast.LENGTH_SHORT).show();
        showEditDialog(celeb);

    }

    private void showEditDialog(Celebrity celeb) {
        FragmentManager fm = getSupportFragmentManager();
        EditCelebrityDialogFragment dialogFragment = EditCelebrityDialogFragment.newInstance("some title", celeb);
        dialogFragment.show(fm, "fragment_edit_name");


    }

    @Override
    public void onCelebrityLongClicked(Celebrity celeb) {
        cupboard().withDatabase(db).delete(celeb);
        Toast.makeText(this, "Blacklisted!", Toast.LENGTH_SHORT).show();
        refreshCelebrityList();

    }

    // this will become triggered by a tile

    public void scheduleAlarm() {
        Intent intent = new Intent(getApplicationContext(), MyAlarmReceiver.class);

        final PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),
                MyAlarmReceiver.REQUEST_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        long firstMillis = System.currentTimeMillis();

        AlarmManager alarm = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, firstMillis, AlarmManager.INTERVAL_FIFTEEN_MINUTES / 3, pendingIntent);
    }

    @Override
    public void onFinishEditDialog(String inputText, Celebrity celebClicked) {
        updateCeleb(inputText, celebClicked);
        Toast.makeText(this, "New stage name is " + inputText, Toast.LENGTH_SHORT).show();
        

    }

    private void updateCeleb(String inputText, Celebrity celebClicked) {
        ContentValues values = new ContentValues();
        values.put("name", inputText);
        Celebrity celebSelected = cupboard().withDatabase(db).get(celebClicked);
        celebSelected.setName(inputText);
        cupboard().withDatabase(db).put(celebSelected);
        refreshCelebrityList();
    }
}
