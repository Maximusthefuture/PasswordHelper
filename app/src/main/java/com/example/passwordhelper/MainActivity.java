package com.example.passwordhelper;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private TextView mResultTextView;
    private EditText mSourceTextView;
    private PasswordsHelper helper;
    private View mButtonCopy;
    private ImageView mQuality;
    private TextView mQualityTextView;
    private SeekBar mPasswordLengthBar;
    private TextView mPasswordLength;
    //Базовый класс
    private CompoundButton checkUppercase;
    private CompoundButton checkNumbers;
    private CompoundButton checkSpecialSymbols;
    private Button generatePasswordButton;
    private TextView generatedPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helper = new PasswordsHelper();

        mResultTextView = findViewById(R.id.result_password);
        mSourceTextView = findViewById(R.id.source_text);
        mButtonCopy = findViewById(R.id.buttonCopy);
        mButtonCopy.setEnabled(false);
        mQuality = findViewById(R.id.quality);
        checkUppercase = findViewById(R.id.check_uppercase);
        mQualityTextView = findViewById(R.id.quality_tv);
        mPasswordLengthBar = findViewById(R.id.password_length);
        mPasswordLength = findViewById(R.id.tv_length);
        checkUppercase = findViewById(R.id.check_uppercase);
        checkNumbers = findViewById(R.id.check_numbers);
        checkSpecialSymbols = findViewById(R.id.check_special_symbols);
        generatePasswordButton = findViewById(R.id.generate_password_button);
        generatedPassword = findViewById(R.id.password);


        checkUppercase.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    helper.generatePassword(6, true, false, false);
                } else {
                    helper.generatePassword(6, false, false, false);
                }

            }
        });

        checkNumbers.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    helper.generatePassword(6, false, true, false);
                } else {
                    helper.generatePassword(0, false, false, false);
                }
            }
        });

        checkSpecialSymbols.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                helper.generateSpecialSymbols();
                if (isChecked) {
                    helper.generatePassword(6, false, false, true);
                }
            }
        });


        mPasswordLengthBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int i = 0;
                int step = 2;
                //?
                if (progress % step == 1) {
                    i++;
                }
                    int total = i + progress;

                String symbols = getResources().getQuantityString(
                        R.plurals.symbols_quantity, i, i, progress, total,
                        getResources().getQuantityString(R.plurals.symbols_more, progress, progress),
                        getResources().getQuantityString(R.plurals.symbols_more, total, total));
                    mPasswordLength.setText(symbols);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mButtonCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager manager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);

                manager.setPrimaryClip(ClipData.newPlainText(MainActivity.this.getString(R.string.clipboard_title), mResultTextView.getText()));

                Toast.makeText(MainActivity.this, R.string.copy_text, Toast.LENGTH_SHORT).show();
            }


        });

        mSourceTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mResultTextView.setText(helper.convertWithHashMap(charSequence));
                mButtonCopy.setEnabled(charSequence.length() > 0);
                int quality = helper.getQuality(charSequence);
                mQuality.setImageLevel(quality * 1000);
                mQualityTextView.setText(getResources().getStringArray(R.array.qualities)[quality]);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        generatePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkUppercase.isChecked()) {
                    generatedPassword.setText(helper.generatePassword(6, true, false, false));
                } else if (checkUppercase.isChecked() && checkNumbers.isChecked()) {
                    generatedPassword.setText(helper.generatePassword(6, true, true, false));
                } else if (checkNumbers.isChecked()) {
                    generatedPassword.setText(helper.generatePassword(6, false, true, false));

                } else if (checkSpecialSymbols.isChecked()) {
                    generatedPassword.setText(helper.generatePassword(6, false, false, true));

                } else {
                    generatedPassword.setText(helper.generatePassword(6, false, false, false));
                }


            }
        });

    }
}
