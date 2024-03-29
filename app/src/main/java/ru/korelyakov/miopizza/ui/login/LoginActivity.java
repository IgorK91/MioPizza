package ru.korelyakov.miopizza.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.telephony.PhoneNumberUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.onesignal.OneSignal;

import ru.korelyakov.miopizza.ui.main.MainActivity;
import ru.korelyakov.miopizza.R;
import ru.korelyakov.miopizza.product.OrderTools;

public class LoginActivity extends AppCompatActivity {
    public static EditText name, number;
    Button run;
    SharedPreferences sPref, sPref2;
    public static String SAVED_NAME = "";
    public static String SAVED_NUMBER = "";
    String clear = "";
    private int currentApiVersion;
    private static final String ONESIGNAL_APP_ID = "b645081a-e832-4b85-8c5b-3a4831cf0bd8";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);

        // OneSignal Initialization
        OneSignal.initWithContext(this);
        OneSignal.setAppId(ONESIGNAL_APP_ID);

        name = findViewById(R.id.name);
        number = findViewById(R.id.number);
        //PhoneNumberUtils.formatNumber(number.getText().toString());
        number.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
        run = findViewById(R.id.run);

        currentApiVersion = android.os.Build.VERSION.SDK_INT;
        final int flags =  View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION  | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        if (currentApiVersion >= Build.VERSION_CODES.KITKAT) {
            getWindow().getDecorView().setSystemUiVisibility(flags);
            final View decorView = getWindow().getDecorView();
            decorView
                    .setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
                        @Override
                        public void onSystemUiVisibilityChange(int visibility) {
                            if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
                                decorView.setSystemUiVisibility(flags);
                            }
                        }
                    });
        }

        Bundle arguments = getIntent().getExtras();

        boolean isClear = false;
        if(arguments != null && !arguments.isEmpty()){
            Object isClearFromBundle = arguments.get("clearData");
            if (isClearFromBundle != null)
                isClear = (boolean)isClearFromBundle;
        }

        if (isClear) {
            name.setText(clear);
            number.setText(clear);
            saveText();
        }
        else{
            loadText();
            if (!name.getText().toString().isEmpty() && !number.getText().toString().isEmpty() &&  !name.getText().toString().equals(" ") && !number.getText().toString().equals(" ")) {
                CharSequence cc = name.getText();
                OrderTools.Cart.setName(cc.toString());
                CharSequence ss = number.getText();
                OrderTools.Cart.setNumber(ss.toString());
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("name", name.getText().toString());
                intent.putExtra("number", number.getText().toString());
                startActivity(intent);
            }
        }
    }

    public  void Run(View view)
    {
        if (name.length() == 0){
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Данные не заполнены", Toast.LENGTH_SHORT);
            toast.show();
        }
        if(number.length()!=15){
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Ошибка! Проверьте номер телефона", Toast.LENGTH_SHORT);
            toast.show();
        }
        else{
            CharSequence cc = name.getText();
            OrderTools.Cart.setName(cc.toString());
            CharSequence ss = number.getText();
            OrderTools.Cart.setNumber(ss.toString());
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("name",name.getText().toString());
            intent.putExtra("number",number.getText().toString());
            saveText();
            startActivity(intent);
        }
    }

    private void saveText() {
        sPref = getSharedPreferences("MyPref", MODE_PRIVATE);
        sPref2 = getSharedPreferences("MyPref2", MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        SharedPreferences.Editor ed2 = sPref2.edit();
        ed.putString(SAVED_NAME, name.getText().toString());
        ed2.putString(SAVED_NUMBER, number.getText().toString());
        ed.commit();
        ed2.commit();
    }


    private void loadText() {
        sPref = getSharedPreferences("MyPref", MODE_PRIVATE);
        sPref2 = getSharedPreferences("MyPref2", MODE_PRIVATE);
        String savedName = sPref.getString(SAVED_NAME, "");
        String savedNumber = sPref2.getString(SAVED_NUMBER, "");
        name.setText(savedName);
        number.setText(savedNumber);
        //cld
    }
}