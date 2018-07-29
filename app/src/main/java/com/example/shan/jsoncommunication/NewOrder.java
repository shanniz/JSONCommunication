package com.example.shan.jsoncommunication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;


//This class handles making new order
public class NewOrder extends AppCompatActivity {

    private FirebaseDatabase fdb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_order);

        fdb = FirebaseDatabase.getInstance();
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

}
