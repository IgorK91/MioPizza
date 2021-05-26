package ru.korelyakov.miopizza;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import ru.korelyakov.miopizza.product.OrderTools;
import ru.korelyakov.miopizza.product.Product;
import ru.korelyakov.miopizza.ui.menu.MenuFragment;

public class MainActivity3 extends AppCompatActivity {

    TextView order, coast, structure, nameMain3, numberMain3, count, adress;
    SharedPreferences sPref;
    final String SAVED_TEXT = "saved_text";

    ArrayList<String> List = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        order = findViewById(R.id.order);
        coast = findViewById(R.id.coast);
        count = findViewById(R.id.count);
        structure = findViewById(R.id.structure);
        nameMain3 = findViewById(R.id.nameMain3);
        numberMain3= findViewById(R.id.numberMain3);
        adress = findViewById(R.id.adress);

        order.setText(OrderTools.Corzina.getItems7().toString());
        coast.setText(OrderTools.Corzina.getItems2().toString());
        count.setText(OrderTools.Corzina.getItems3().toString());
        nameMain3.setText(OrderTools.Corzina.getItems4());
        numberMain3.setText(OrderTools.Corzina.getItems5());
    }

    private void saveText() {
        sPref = getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString(SAVED_TEXT, order.getText().toString());
        ed.commit();
    }


    private void loadText() {
        sPref = getSharedPreferences("MyPref", MODE_PRIVATE);
        String savedText = sPref.getString(SAVED_TEXT, "");
        order.setText(savedText);
    }

    @Override
    public void onBackPressed() {
        saveText();
             super.onBackPressed();
             finish();
    }

    public void Order(View v) {

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        dateFormat.format(date);

        List.add(date.toString());
        List.add(adress.getText().toString());
        List.add(nameMain3.getText().toString());
        List.add(numberMain3.getText().toString());
        List.add(order.getText().toString());
        List.add(count.getText().toString());
        List.add(coast.getText().toString());

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference().child("Заказы").push();
        myRef.setValue(List);
        ShowOver();
    }

    public void ShowOver() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity3.this);
        builder.setTitle("Заказ получен!")
                .setMessage("Мы свяжемся с Вами в течении 10 минут для подтверждения заказа.")
                .setCancelable(false)
                .setPositiveButton("Меню",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                OrderTools.Corzina.cleanCart();
                                Intent intent = new Intent(MainActivity3.this, MainActivity.class);
                                intent.putExtra("name", nameMain3.getText().toString());
                                intent.putExtra("number", numberMain3.getText().toString());
                                startActivity(intent);
                                finish();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
//g
