package com.example.passwordhelper;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private TextView mResultTextView;
    private EditText mSourceTextView;
    private PasswordsHelper helper;
    private View mButtonCopy;
    private ImageView mQuality;
    private TextView mQualityTextView;
    //Базовый класс
    private CompoundButton checkUppercase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helper = new PasswordsHelper(getResources().getStringArray(R.array.russian), getResources().getStringArray(R.array.english));
//        helper = new PasswordsHelper();

        mResultTextView = findViewById(R.id.result_password);
        mSourceTextView = findViewById(R.id.source_text);
        mButtonCopy = findViewById(R.id.buttonCopy);
        mButtonCopy.setEnabled(false);
        mQuality = findViewById(R.id.quality);
        checkUppercase = findViewById(R.id.check_uppercase);

        //Написать все буквы через HashMap<String, String>
//        Дописать все буквы


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

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }
}
