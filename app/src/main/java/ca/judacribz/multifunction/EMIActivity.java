package ca.judacribz.multifunction;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Locale;

public class EMIActivity extends AppCompatActivity {

    private static final int DEFAULT_LOAN_AMOUNT = 1000000;
    private static final int DEFAULT_INTEREST = 1;
    private static final int DEFAULT_LOAN_PERIOD = 12;

    SeekBar sbLoanAmount, sbInterestRate, sbLoanPeriod;
    TextView tvEMI, tvLoanAmount, tvInterestRate, tvLoanPeriod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emi);

        sbLoanAmount = findViewById(R.id.sbLoanAmount);
        sbInterestRate = findViewById(R.id.sbInterestRate);
        sbLoanPeriod = findViewById(R.id.sbLoanPeriod);

        tvEMI = findViewById(R.id.tvEMI);
        tvLoanAmount = findViewById(R.id.tvLoanAmount);
        tvInterestRate = findViewById(R.id.tvInterestRate);
        tvLoanPeriod = findViewById(R.id.tvLoanPeriod);

        sbLoanAmount.setOnSeekBarChangeListener(setOnSeekBarListener(tvLoanAmount, 0));
        sbInterestRate.setOnSeekBarChangeListener(setOnSeekBarListener(tvInterestRate, 8));
        sbLoanPeriod.setOnSeekBarChangeListener(setOnSeekBarListener(tvLoanPeriod, 1));

        setDefaults();
    }

    private void setDefaults() {
        sbLoanAmount.setProgress(DEFAULT_LOAN_AMOUNT);
        sbInterestRate.setProgress(DEFAULT_INTEREST);
        sbLoanPeriod.setProgress(DEFAULT_LOAN_PERIOD);
    }

    private SeekBar.OnSeekBarChangeListener setOnSeekBarListener(final TextView tvDisplay,
                                                                 final int offset) {
        return new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvDisplay.setText(String.valueOf(progress + offset));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        };

    }


    public void calculateEMI(View view) {
        double
            loanAmount = Double.valueOf(tvLoanAmount.getText().toString()),
            interestRate = Double.valueOf(tvInterestRate.getText().toString()),
            loanPeriod = Double.valueOf(tvLoanPeriod.getText().toString());

        double
            r = interestRate / 1200,
            r1 = Math.pow(r + 1, loanPeriod),
                monthlyPayment = (r + (r / (r1 - 1))) * loanAmount,
                totalPayment = monthlyPayment * loanPeriod;

        tvEMI.setText(String.format(
                Locale.ENGLISH,
                getResources().getString(R.string.emi_output),
                monthlyPayment,
                totalPayment
        ));
    }
}
