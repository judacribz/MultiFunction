package ca.judacribz.multifunction;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import ca.judacribz.multifunction.models.Person;

public class PersonsActivity extends AppCompatActivity {

    public static final String EXTRA_PERSONS = "ca.judacribz.multifunction.EXTRA_PERSONS";
    EditText etFirstName, etLastName, etAge;
    EditText[] form;
    Button btnClearInfo, btnDisplayPersons;


    ArrayList<Person> persons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persons);

        btnDisplayPersons = findViewById(R.id.btnDisplayPersons);
        btnClearInfo = findViewById(R.id.btnClearInfo);
        btnClearInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearInfo();
            }
        });

        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etAge = findViewById(R.id.etAge);
        etFirstName.requestFocus();

        form = new EditText[3];
        form[0] = etFirstName;
        form[1] = etLastName;
        form[2] = etAge;

        persons = new ArrayList<>();

    }

    public void clearInfo() {
        for (EditText et : form) {
            et.setText("");
        }

        etFirstName.requestFocus();
    }

    public boolean checkEmpty() {
        boolean allFilled = true;
        for (EditText et : form) {
            if (et.getText().toString().trim().isEmpty()) {
                et.setError(getResources().getString(R.string.required));
                allFilled = false;
            }
        }
        return allFilled;
    }

    public void enterInfo(View view) {
        if (checkEmpty()) {
            persons.add(new Person(
                    etFirstName.getText().toString().trim(),
                    etLastName.getText().toString().trim(),
                    Integer.valueOf(etAge.getText().toString().trim())
            ));

            btnDisplayPersons.setText(String.format(
                    getResources().getString(R.string.display_persons),
                    persons.size()
            ));

            if (btnDisplayPersons.getVisibility() == View.GONE) {
                btnDisplayPersons.setVisibility(View.VISIBLE);
            }

            clearInfo();
        }
    }

    public void displayPersons(View view) {
        Intent intent = new Intent(this, DisplayPersonsActivity.class);
        intent.putParcelableArrayListExtra(EXTRA_PERSONS, persons);
        startActivity(intent);
    }
}
