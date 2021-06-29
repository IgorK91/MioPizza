package ru.korelyakov.miopizza.ui.main;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.core.view.MenuItemCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import ru.korelyakov.miopizza.ui.confirm.ConfirmActivity;
import ru.korelyakov.miopizza.R;
import ru.korelyakov.miopizza.product.OrderTools;
import ru.korelyakov.miopizza.product.Product;
import ru.korelyakov.miopizza.product.ProductType;

import static ru.korelyakov.miopizza.product.OrderTools.*;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    TextView zakazcount, client, textCartItemCount;
    String nameMain, numberMain;
    private int currentApiVersion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
        nameMain = arguments.get("name").toString();
        numberMain = arguments.get("number").toString();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        View header = navigationView.getHeaderView(0);
        zakazcount = header.findViewById(R.id.zakazcount);
        client =  header.findViewById(R.id.client);
        client.setText(nameMain);


        DatabaseReference mRef3 = FirebaseDatabase.getInstance().getReference().child("Клиенты").child(numberMain);
        mRef3.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue(String.class) != null) {
                    zakazcount.setText("Количество заказов = " + dataSnapshot.getValue(String.class));
                }
                else{
                    zakazcount.setText("Количество заказов = 0"); 
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
            }
        });



        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);



        // пример создания продукта и добавления в список всех продуктов
        AllProducts.add(new Product("Pizza Mio", ProductType.Pizza, "Тесто, сыр моцарелла, соус томатный, куриное филе, " +
                "пеперони,бекон, ветчина", R.drawable.card1, 500,790));
        AllProducts.add(new Product("Polo Pesto", ProductType.Pizza, "Тесто, соус томатный,сыр моцарелла, куриное филе, грибы, песто соус ", R.drawable.card1,360,620));
        AllProducts.add(new Product("Болоньезе", ProductType.Pizza, "Тесто, соус томатный,сыр моцарелла, фарш домашний, лук красный, морковь", R.drawable.card2,370,630));
        AllProducts.add(new Product("Бони и Клайд", ProductType.Pizza, "Тесто, соус томатный,сыр моцарелла, купаты, пеперони," +
                "охотничьи колбаски, лук красный, холопение", R.drawable.card1,470,740));
        AllProducts.add(new Product("Грибная", ProductType.Pizza, "Тесто, соус цезарь, сыр моцарелла, грибы, томаты черри, песто соус", R.drawable.card1,360,620));
        AllProducts.add(new Product("Карбонара", ProductType.Pizza, "Тесто, соус томатный,сыр моцарелла, пармезан, бекон, яйцо", R.drawable.card1,380,640));
        AllProducts.add(new Product("Маргарита", ProductType.Pizza, "Тесто, соус томатный,сыр моцарелла, томаты черри", R.drawable.card1,300,500));
        AllProducts.add(new Product("Пеперони", ProductType.Pizza, "Тесто, соус томатный,сыр моцарелла, пеперони", R.drawable.card1,370,630));
        AllProducts.add(new Product("Рыбная", ProductType.Pizza, "Тесто, соус томатный,сыр моцарелла, лосось, томаты черри", R.drawable.card1,420,710));
        AllProducts.add(new Product("С ветчиной и грибами", ProductType.Pizza, "Тесто, соус томатный,сыр моцарелла, ветчина, грибы", R.drawable.card1,410,690));
        AllProducts.add(new Product("Четыре сыра", ProductType.Pizza, "Тесто, соус томатный,сыр моцарелла, эмменталь, пармезан, горгонзола, песто соус", R.drawable.card1,380,640));
        AllProducts.add(new Product("Пицца Странная", ProductType.Pizza, "Тесто, соус томатный,сыр моцарелла, ветчина, ананас", R.drawable.card1,380,640));
        AllProducts.add(new Product("Цезарь", ProductType.Pizza, "Тесто, соус томатный,сыр моцарелла, куриное филе, айсберг, " +
                "томаты черри, яйцо перепелиное, пармезан", R.drawable.card1,370,630));
        AllProducts.add(new Product("Овощная", ProductType.Pizza, "Тесто, соус томатный,сыр моцарелла,сладкий перец, зеленый огурец, кукуруза, томаты черри", R.drawable.card1,320,540));
        AllProducts.add(new Product("Маленький кальцоне с ветчиной и грибами", ProductType.Calzone, "Тесто, соус томатный,сыр моцарелла, ветчина, грибы", R.drawable.card1,170,170));
        AllProducts.add(new Product("Маленький кальцоне с грибами", ProductType.Calzone, "Тесто, соус томатный,сыр моцарелла, грибы", R.drawable.card1,120,120));
        AllProducts.add(new Product("Маленький кальцоне с ветчиной", ProductType.Calzone, "Тесто, соус томатный,сыр моцарелла, ветчина", R.drawable.card1,150,150));
        AllProducts.add(new Product("Кальцоне", ProductType.Calzone, "Тесто, соус томатный,сыр моцарелла, ветчина, грибы, яйцо", R.drawable.card1,350,350));
        AllProducts.add(new Product("Маково-черничный", ProductType.Dessert, "", R.drawable.card1,140,140));
        AllProducts.add(new Product("Медово-карамельный", ProductType.Dessert, "", R.drawable.card1,140,140));
        AllProducts.add(new Product("Творожно-кокосовое", ProductType.Dessert, "", R.drawable.card1,140,140));
        AllProducts.add(new Product("Кэррот кейк", ProductType.Dessert, "", R.drawable.card1,140,140));
        AllProducts.add(new Product("Вода Бон Аква", ProductType.Drink, "", R.drawable.card1,50,100));
        AllProducts.add(new Product("Вода Эвиан", ProductType.Drink, "", R.drawable.card1,240,240));
        AllProducts.add(new Product("Coca Cola", ProductType.Drink, "", R.drawable.card1,69,110));
        AllProducts.add(new Product("Сок Rioba", ProductType.Drink, "", R.drawable.card1,80,160));
        AllProducts.add(new Product("Паста с купатами", ProductType.Pasta, "", R.drawable.card1,260,260));
        AllProducts.add(new Product("Паста Болоньезе", ProductType.Pasta, "", R.drawable.card1,250,250));
        AllProducts.add(new Product("Паста Карбонара", ProductType.Pasta, "", R.drawable.card1,280,280));
        AllProducts.add(new Product("Паста al salmon", ProductType.Pasta, "", R.drawable.card1,290,290));
        AllProducts.add(new Product("Греческий", ProductType.Salad, "", R.drawable.card1,200,200));
        AllProducts.add(new Product("Цезарь с курицей", ProductType.Salad, "", R.drawable.card1,280,280));
        AllProducts.add(new Product("Капрезе", ProductType.Salad, "", R.drawable.card1,240,240));
        AllProducts.add(new Product("Томатный", ProductType.Soup, " ", R.drawable.card1,200,200));
        AllProducts.add(new Product("Сырный", ProductType.Soup, " ", R.drawable.card1,260,260));
        AllProducts.add(new Product("Рыбный", ProductType.Soup, " ", R.drawable.card1,280,280));
        AllProducts.add(new Product("Грибной", ProductType.Soup, " ", R.drawable.card1,250,250));
    }

    /*private static final Product[] mContactsCalzone = {"Кальцоне", "Маленький кальцоне с грибами",
            "Маленький кальцоне с ветчиной", "Маленький кальцоне с ветчиной и грибами"};

    private static final Product[] mContactsDeserts = {"Маково-черничный", "Медово-карамельный",
            "Творожно-кокосовое", "Кэррот кейк"};

    private static final Product[] mContactsDrinks = {"Вода Бон Аква", "Вода Эвиан",
            "Coca Cola", "Сок Rioba"};

    private static final Product[] mContactsPasta = {"Паста с купатами", "Паста Болоньезе",
            "Паста Карбонара", "Паста al salmon"};

    private static final Product[] mContactsPizza = {"Pizza Mio", "Polo Pesto", "Болоньезе", "Бони и Клайд",
            "Грибная", "Карбонара", "Маргарита", "Пеперони", "Рыбная", "С ветчиной и грибами",
            "Четыре сыра", "Пицца Странная", "Цезарь", "Овощная"};

    private static final Product[] mContactsSalad = {"Греческий", "Цезарь с курицей", "Капрезе"};

    private static final Product[] mContactsSoup = {"Томатный", "Сырный", "Рыбный", "Грибной"};*/

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (textCartItemCount != null)
            textCartItemCount.setText(String.format("%s", OrderTools.Cart.getSize()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        final MenuItem menuItem = menu.findItem(R.id.cart);
        View actionView = MenuItemCompat.getActionView(menuItem);
        textCartItemCount = actionView.findViewById(R.id.cart_badge);
        textCartItemCount.setText(String.format("%s", OrderTools.Cart.getSize()));
        setupBadge();
        actionView.setOnClickListener(v -> onOptionsItemSelected(menuItem));
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private void setupBadge() {
    }


    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case R.id.cart:
                //  Intent intent = new Intent(this, OrderActivity.class);
                Intent intent = new Intent(this, ConfirmActivity.class);
                startActivity(intent);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
//