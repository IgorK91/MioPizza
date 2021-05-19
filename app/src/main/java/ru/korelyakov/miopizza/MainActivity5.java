package ru.korelyakov.miopizza;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ru.korelyakov.miopizza.product.OrderTools;

public class MainActivity5 extends AppCompatActivity {
   public static EditText name, number;
    Button run;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        name = findViewById(R.id.name);
        number = findViewById(R.id.number);
        run = findViewById(R.id.run);

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
            startActivity(intent);
        }
    }
}