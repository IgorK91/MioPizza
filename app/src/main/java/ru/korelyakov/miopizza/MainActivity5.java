package ru.korelyakov.miopizza;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ru.korelyakov.miopizza.product.OrderTools;

public class MainActivity5 extends AppCompatActivity {
   public static EditText name, number;
    Button run;
    SharedPreferences sPref, sPref2;
    public static String SAVED_NAME = "";
    public static String SAVED_NUMBER = "";
    String nameClear, numberClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        name = findViewById(R.id.name);
        number = findViewById(R.id.number);
        run = findViewById(R.id.run);

        Bundle arguments = getIntent().getExtras();

        if(arguments != null && !arguments.isEmpty()){
            nameClear = arguments.get("nameClear").toString();
            numberClear = arguments.get("numberClear").toString();
            name.setText(nameClear);
            number.setText(numberClear);
        }
        else{
            loadText();
            if (!name.getText().toString().equals(" ") && !number.getText().toString().equals(" ")) {
                CharSequence cc = name.getText();
                OrderTools.Corzina.addToCart4(cc.toString());
                CharSequence ss = number.getText();
                OrderTools.Corzina.addToCart5(ss.toString());
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
        else{
            CharSequence cc = name.getText();
            OrderTools.Corzina.addToCart4(cc.toString());
            CharSequence ss = number.getText();
            OrderTools.Corzina.addToCart5(ss.toString());
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
    }
}