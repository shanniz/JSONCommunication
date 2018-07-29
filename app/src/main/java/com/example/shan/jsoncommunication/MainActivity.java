package com.example.shan.jsoncommunication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
//import com.google.firebase.database.FirebaseFirestore;


/////https://www.androidhive.info/2016/06/android-getting-started-firebase-simple-login-registration-auth/

public class MainActivity extends AppCompatActivity {


    private static final String NAME_KEY = "uname";
    private static final String EMAIL_KEY = "Email";
    private static final String PHONE_KEY = "Phone";

    //FirebaseFirestore db;
    FirebaseDatabase fdb;

    TextView tview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Write a message to the database
        fdb = FirebaseDatabase.getInstance();
        //auto sync database upon data Internet connectivity
        fdb.setPersistenceEnabled(true);

        setContentView(R.layout.activity_main);

        tview = (TextView) findViewById(R.id.response);
        //doFirebase();

    }

    public void orderActivity(View v){
        Intent intent = new Intent(MainActivity.this, NewOrder.class);
        startActivity(intent);
    }

    public void setOrder(View v){
        DatabaseReference myRef = fdb.getReference("Orders");
        Product pro = new Product("p20", "yoghurt", 20);
        myRef.child("order4").setValue(pro);
        //myRef.child("order4").push().setValue(pro);

        //myRef.child("order1").child("productId").setValue("p12");
        //myRef.child("order1").child("quantity").setValue(10);

        Map<String, String> usr = new HashMap<>();
        usr.put("Name", "Shan");
        usr.put("Age", "26");
        ///myRef.setValue(usr);
    }

    public void readOrders(View v){
        DatabaseReference myRef = fdb.getReference("Orders");
        ////tview.setText("Data = " + myRef.child("pass"));
        //myRef.child("order1").child("productId").setValue("p12");
        //myRef.child("order1").child("productName").setValue("Orange Juice");

        ////myRef.child("order1").setValue(pro);

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //String value = (String) dataSnapshot.getValue();
                ///Product p = dataSnapshot.getValue(Product.class);
                ///tview.setText("Data New = " + dataSnapshot.getValue());
                //tview.append(p.productName);

                ArrayList<String> listItems=new ArrayList<String>();
                ArrayAdapter<String> adapter;

                OrdersAdapter myAdapter;

                adapter=new ArrayAdapter<String>(MainActivity.this,
                        android.R.layout.simple_list_item_1,
                        listItems);



                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Product p2 = postSnapshot.getValue(Product.class);
                    listItems.add(p2.productName + ", " + p2.quantity);
                    ////listItems.add(p2);
                    ///tview.append(p2.productName + ", ");
                    //adapter.notifyDataSetChanged();
                }

                String[] strArray = new String[listItems.size()];
                strArray = listItems.toArray(strArray);
                myAdapter = new OrdersAdapter(MainActivity.this, strArray);

                //dataSnapshot.child("productName").getValue();
                ListView listView = (ListView) findViewById(R.id.list);
                listView.setAdapter(myAdapter);


                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {


                        //String selected = ((TextView) view).getText().toString();
                        String name = (String)parent.getAdapter().getItem(position).toString();


                        //View parentView = (View) parent.getParent();
                        ///String name = ((TextView) parentView.findViewById(R.id.secondLine)).getText() + "";
                        //String name = ((TextView)parent.getAdapter().getItem(position)).getText().toString();
                        ////arg0 is your ListView ... use arg1 which is your clicked row ... – Selvin Feb 15 '13 at 9:01

                        Toast.makeText(getApplicationContext(),
                                "Click " + name, Toast.LENGTH_LONG).show();
                        tview.setText("Clicked " + name + ". Modify/Delete?");
                    }
                });

            }

            @Override
            public void onCancelled(DatabaseError firebaseError) {

            }
        });

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and whenever data at this location is updated.
                //String value = dataSnapshot.getValue(String.class);
                ///Log.d("asd", "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                tview.setText("Error: "+ error.toException());
                Log.w("asd", "Failed to read value.", error.toException());
            }

        });

    }


    protected void queryOrders(View v){
        DatabaseReference myRef = fdb.getReference("Orders");

        //myRef.orderByChild("Orders").
        myRef.orderByChild("Orders").addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                tview.setText("Data = " + dataSnapshot.getKey());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {
                //Post changedPost = dataSnapshot.getValue(Post.class);
                ////tview.setText("Data = " + dataSnapshot.getKey());

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {}

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String prevChildKey) {}

            @Override
            public void onCancelled(DatabaseError databaseError) {}

        });

    }
}
