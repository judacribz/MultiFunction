package ca.judacribz.multifunction;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

import ca.judacribz.multifunction.models.Person;

import static ca.judacribz.multifunction.PersonsActivity.EXTRA_PERSONS;

public class DisplayPersonsActivity extends AppCompatActivity {


    TextView tvPersonsOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_persons);

        tvPersonsOut = findViewById(R.id.tvPersonsOut);
        setPersonInfo();
    }

    private void setPersonInfo() {
        ArrayList<Person> persons = getIntent().getParcelableArrayListExtra(EXTRA_PERSONS);
        StringBuilder sb = new StringBuilder();
        for (Person person : persons) {
            sb.append(persons.indexOf(person) + 1);
            sb.append(".) ");
            sb.append(person.toString());
            sb.append("\n");
        }

        tvPersonsOut.setText(sb.toString());

    }
}
