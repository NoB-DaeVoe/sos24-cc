package fi.arcada.codechallenge;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.renderscript.Sampler;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView outputText;
    EditText inputText;
    TextView calculate;

    RecyclerView recyclerView;

    SharedPreferences prefs;
    SharedPreferences.Editor prefsEditor;

    // Data
    String[] cityNames = { "Helsingfors", "Esbo", "Tammerfors", "Vanda", "Uleåborg", "Åbo", "Jyväskylä", "Kuopio", "Lahtis", "Björneborg", "Kouvola", "Joensuu", "Villmanstrand", "Tavastehus", "Vasa", "Seinäjoki", "Rovaniemi", "S:t Michel", "Salo", "Kotka", "Borgå", "Karleby", "Hyvinge", "Lojo", "Träskända", "Raumo", "Kervo", "Kajana", "S:t Karins", "Nokia", "Ylöjärvi", "Kangasala", "Nyslott", "Riihimäki", "Raseborg", "Imatra", "Reso", "Brahestad", "Sastamala", "Torneå", "Idensalmi", "Valkeakoski", "Kurikka", "Kemi", "Varkaus", "Jämsä", "Fredrikshamn", "Nådendal", "Jakobstad", "Heinola", "Äänekoski", "Pieksämäki", "Forssa", "Ackas", "Orimattila", "Loimaa", "Nystad", "Ylivieska", "Kauhava", "Kuusamo", "Pargas", "Lovisa", "Lappo", "Kauhajoki", "Ulvsby", "Kankaanpää", "Kalajoki", "Mariehamn", "Alavo", "Pemar", "Lieksa", "Grankulla", "Nivala", "Kides", "Vittis", "Mänttä-Vilppula", "Närpes", "Keuru", "Nurmes", "Alajärvi", "Saarijärvi", "Orivesi", "Högfors", "Somero", "Letala", "Hangö", "Kuhmo", "Kiuruvesi", "Pudasjärvi", "Nykarleby", "Kemijärvi", "Oulainen", "Kumo", "Suonenjoki", "Ikalis", "Haapajärvi", "Harjavalta", "Haapavesi", "Outokumpu", "Virdois", "Kristinestad", "Parkano", "Viitasaari", "Etseri", "Kannus", "Pyhäjärvi", "Kaskö" };
    double[] founded = { 1550, 1972, 1779, 1974, 1605, 1200, 1837, 1782, 1905, 1558, 1960, 1848, 1649, 1639, 1606, 1960, 1960, 1838, 1960, 1878, 1346, 1620, 1960, 1969, 1967, 1442, 1970, 1651, 1993, 1977, 2004, 2018, 1639, 1960, 2009, 1971, 1974, 1649, 2009, 1621, 1891, 1963, 1977, 1869, 1962, 1977, 1653, 1443, 1652, 1839, 1973, 1962, 1964, 2007, 1992, 1969, 1617, 1971, 1986, 2000, 1977, 1745, 1977, 2001, 2000, 1972, 2002, 1861, 1977, 1997, 1973, 1972, 1992, 1992, 1977, 2009, 1993, 1986, 1974, 1986, 1986, 1986, 1977, 1993, 1986, 1874, 1986, 1993, 2004, 1620, 1973, 1977, 1977, 1977, 1977, 1977, 1977, 1996, 1977, 1977, 1649, 1977, 1996, 1986, 1986, 1993, 1785 };
    double[] population = { 658457, 297132, 244223, 239206, 209551, 195137, 144473, 121543, 120027, 83482, 80454, 77261, 72634, 67971, 67615, 64736, 64180, 52122, 51400, 51241, 51149, 47909, 46880, 45988, 45226, 38959, 37232, 36493, 35497, 34884, 33533, 32622, 32547, 28521, 27484, 25655, 24810, 24260, 23998, 21333, 20958, 20695, 20197, 19982, 19973, 19767, 19702, 19579, 19097, 18344, 18318, 17253, 16573, 16467, 15808, 15628, 15463, 15357, 15312, 15165, 15086, 14643, 14203, 12890, 12669, 12662, 12412, 11742, 11197, 11041, 10543, 10396, 10396, 9877, 9870, 9563, 9562, 9443, 9423, 9311, 9117, 8978, 8717, 8563, 8456, 7979, 7928, 7759, 7702, 7497, 7105, 7102, 6951, 6891, 6877, 6802, 6785, 6613, 6506, 6465, 6380, 6286, 6070, 5484, 5390, 4964, 1289 };

    ArrayList<DataItem> dataItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        outputText = findViewById(R.id.outText);
        inputText = findViewById(R.id.inText);
        calculate = findViewById(R.id.calc);
        recyclerView = findViewById(R.id.recyclerView);

        // SharedPreference
        prefs = PreferenceManager.getDefaultSharedPreferences(this);

        // Öppna editorn
        prefsEditor = prefs.edit();

        // Läs värde från SharedPreferences
        int appStartCount = prefs.getInt("counter", 0);
        String greeting = prefs.getString("greeting", "Hello!");

        // Lägg till värde till SharedPreferences och öka med 1
        prefsEditor.putInt("counter", appStartCount + 1);
        prefsEditor.putString("greeting", "Morjens!");

        // Spara ändringen
        prefsEditor.apply();

        // Visa värdet
        calculate.setText(String.format("Times you have opened app: %s\nHälsning: %s",
                appStartCount,
                greeting
        ));

        // Vi fyller vår ArrayList med värdena från testdata-arrayen
        for (int i = 0 ; i < population.length ; i++) {
            dataItems.add(new DataItem(cityNames[i], population[i]));
        }

        // RecycleView
        DataItemViewAdapter adapter = new DataItemViewAdapter(this, dataItems);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    // OK knappen
    public void buttonHandler(View view) {
        String text = inputText.getText().toString();
        outputText.setText(text);
    }

    @SuppressLint("DefaultLocale")
    public void calculate(View view) {
        ArrayList<Double> values = new ArrayList<>();
        for (DataItem item : dataItems) {
            values.add(item.getValue());
        }
        calculate.setText(String.format("Population:\nMedelvärde: %.2f\nMedian: %.2f\nStandaravvikelse: %.2f\nTypvärde: %.2f\nLQ: %.2f\nUQ: %.2f\nIQR: %.2f",
                Statistics.calcMean(values),
                Statistics.calcMedian(values),
                Statistics.calcStdev(values),
                Statistics.calcMode(values),
                Statistics.calcLQ(values),
                Statistics.calcUQ(values),
                Statistics.calcIQR(values)
        ));
    }

    public void openSettings(View view) {
        Intent intent = new Intent (this, SettingsActivity.class);
        startActivity(intent);
    }
}
