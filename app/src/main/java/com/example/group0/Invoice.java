package com.example.group0;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Invoice extends AppCompatActivity {

    private TextView Name, PhoneNo, Address, Date, Clothe, Size, Item, total;
    private Button BtnDone;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;
    String strTotalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);

        Name = findViewById(R.id.tv_name1);
        PhoneNo = findViewById(R.id.tv_Phone);
        Address = findViewById(R.id.tv_Address1);
        Date = findViewById(R.id.tv_Date);
        Clothe = findViewById(R.id.tv_nameClothes);
        Size = findViewById(R.id.tv_sizeClothes);
        Item = findViewById(R.id.tv_ItemClothes);
        total = findViewById(R.id.tv_total);
        BtnDone = findViewById(R.id.btn_Done);

        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("Booking").child(firebaseAuth.getUid());

        BtnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Invoice.this, SecondActivity.class));

            }
        });

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                BookingInfo bookingInfo = dataSnapshot.getValue(BookingInfo.class);
                Name.setText(bookingInfo.getFullname());
                PhoneNo.setText(bookingInfo.getphoneNo());
                Address.setText(bookingInfo.getAddress());
                Date.setText(bookingInfo.getDate());
                Clothe.setText(bookingInfo.getClothe());
                Size.setText(bookingInfo.getSize());
                Item.setText(bookingInfo.getItem());
                String item = bookingInfo.getItem();



                int numItem = Integer.parseInt(item);
                double totalPrice = numItem*150;
               // strTotalPrice = String.valueOf(totalPrice);
                strTotalPrice = String.format("%.2f", totalPrice);

                total.setText("RM" + strTotalPrice);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Invoice.this, error.getCode(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}