package com.example.shan.jsoncommunication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
        //myRef.child("order1").child("quantity").setValue(10);

        ////myRef.child("order1").setValue(pro);

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //String value = (String) dataSnapshot.getValue();
                // do your stuff here with value
                ///Product p = dataSnapshot.getValue(Product.class);
                ///tview.setText("Data New = " + dataSnapshot.getValue());
                //tview.append(p.productName);

                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                // TODO: handle the post
                    Product p2 = postSnapshot.getValue(Product.class);
                    tview.append(p2.productName + ", ");
                }

                //dataSnapshot.child("productName").getValue();
            }

            @Override
            public void onCancelled(DatabaseError firebaseError) {

            }
        });


        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                //String value = dataSnapshot.getValue(String.class);
                //tview.setText("Data Change + " + value);
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
