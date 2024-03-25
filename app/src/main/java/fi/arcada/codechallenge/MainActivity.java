package fi.arcada.codechallenge;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import static fi.arcada.codechallenge.Statistics.calcMean;

public class MainActivity extends AppCompatActivity {

    TextView outputText;
    EditText inputText;
    TextView mean;

    double[] values = { 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89 };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        outputText = findViewById(R.id.outText);
        inputText = findViewById(R.id.inText);
        mean = findViewById(R.id.calc);
    }

    public void buttonHandler(View view) {
        String text = inputText.getText().toString();
        outputText.setText(text);
    }

    public void calculate(View view) {

        mean.setText(String.format("%.2f", calcMean(values)));
    }
}
