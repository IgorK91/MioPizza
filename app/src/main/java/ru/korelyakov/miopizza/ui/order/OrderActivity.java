package ru.korelyakov.miopizza.ui.order;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import ru.korelyakov.miopizza.R;
import ru.korelyakov.miopizza.product.Cart;
import ru.korelyakov.miopizza.product.OrderTools;
import ru.korelyakov.miopizza.product.Product;
import ru.korelyakov.miopizza.ui.main.MainActivity;

public class OrderActivity extends AppCompatActivity {

    TextView order, coast, nameMain3, numberMain3, count, adress;
    SharedPreferences sPref;
    final String SAVED_TEXT = "saved_text";
    ArrayList<String> List = new ArrayList<String>();
    private int currentApiVersion;
    public String test;
    public String test2;
    public String test3;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

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

        coast = findViewById(R.id.coast);
        nameMain3 = findViewById(R.id.nameMain3);
        numberMain3= findViewById(R.id.numberMain3);
        adress = findViewById(R.id.adress);

        //order.setText(OrderTools.Cart.getCartNameList().toString());
        //coast.setText(OrderTools.Cart.getCoastList().toString());
        //count.setText(OrderTools.Cart.getCountList().toString());
        nameMain3.setText(OrderTools.Cart.getName());
        numberMain3.setText(OrderTools.Cart.getNumber());
        test = OrderTools.Cart.getAllNames();
        test2 = OrderTools.Cart.getAllCount();
        test3 = String.valueOf(OrderTools.Cart.getTotalPrice());
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
        // List.add(order.getText().toString());
        //  List.add(count.getText().toString());
        //  List.add(coast.getText().toString());

        List.add(test);
        List.add(test2);
        List.add(test3);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference().child("Заказы").child(List.get(0));
        myRef.child("time").setValue(List.get(0));
        myRef.child("address").setValue(List.get(1));
        myRef.child("name").setValue(List.get(2));
        myRef.child("number").setValue(List.get(3));
        myRef.child("title").setValue(List.get(4));
        myRef.child("quantity").setValue(List.get(5));
        myRef.child("coast").setValue(List.get(6));


        Task<Void> myRef2 = database.getReference().child("Клиенты").child(List.get(3)).setValue("0");

        ShowOver();
    }

    public void ShowOver() {
        AlertDialog.Builder builder = new AlertDialog.Builder(OrderActivity.this);
        builder.setTitle("Заказ получен!")
                .setMessage("Мы свяжемся с Вами в течении 10 минут для подтверждения заказа.")
                .setCancelable(false)
                .setPositiveButton("Меню",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                OrderTools.Cart.cleanCart();
                                Intent intent = new Intent(OrderActivity.this, MainActivity.class);
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
//
