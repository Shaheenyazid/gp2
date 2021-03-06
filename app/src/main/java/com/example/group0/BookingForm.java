package com.example.group0;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.provider.Telephony;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.text.DateFormat;
import java.util.Calendar;

public class BookingForm extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, AdapterView.OnItemSelectedListener {

     EditText CustFullName, CustPhoneNum, CustAddress;
     Button ConfBut, CustDate;
     Spinner CustClothe, CustSize, CustItem;
     private FirebaseDatabase firebaseDatabase;
     private DatabaseReference storageReference;
     String clothe, size, item, fullname, phoneNo, address, currentDateString;
     TextView Date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_form);

        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance().format(calendar.getTime());

        CustClothe = findViewById(R.id.etClotheName);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.clothes, android.R.layout.simple_spinner_item );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        CustClothe.setAdapter(adapter);
        CustClothe.setOnItemSelectedListener(this);
        

        CustSize = findViewById(R.id.etSizeName);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.sizes, android.R.layout.simple_spinner_item );
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        CustSize.setAdapter(adapter2);
        CustSize.setOnItemSelectedListener(this);

        CustItem = findViewById(R.id.etItem);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.items, android.R.layout.simple_spinner_item );
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        CustItem.setAdapter(adapter3);
        CustItem.setOnItemSelectedListener(this);

        CustFullName = findViewById(R.id.etFullName);
        CustPhoneNum = findViewById(R.id.etPhoneCust);
        CustAddress = findViewById(R.id.etAddressCust);
        CustDate = findViewById(R.id.etDate);
        ConfBut = findViewById(R.id.btnBooking);


        FirebaseDatabase.getInstance();

        CustDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new com.example.group0.DatePicker();
                datePicker.show(getSupportFragmentManager(),"date picker");
            }
        });





        ConfBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String fullname, phoneNo, address;
                firebaseDatabase = FirebaseDatabase.getInstance();
                phoneNo = CustPhoneNum.getEditableText().toString().trim();
                storageReference = firebaseDatabase.getReference("Booking").child(phoneNo);
                fullname = CustFullName.getEditableText().toString().trim();
                address = CustAddress.getEditableText().toString().trim();

                validate();
                BookingInfo bookingInfo = new BookingInfo(clothe, size, item, fullname, phoneNo, address, currentDateString);
                storageReference.setValue(bookingInfo);

            }
        });


    }

    private Boolean validate(){
        Boolean result = false;


        fullname = CustFullName.getText().toString();
        phoneNo = CustPhoneNum.getText().toString();
        address= CustAddress.getText().toString();




        if(fullname.isEmpty() || phoneNo.isEmpty()  || address.isEmpty()){
            Toast.makeText(this, "Please enter all the details", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(BookingForm.this, BookingForm.class));
        }else{
            result = true;
            startActivity(new Intent(BookingForm.this, SecondActivity.class));
        }

        return result;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        currentDateString = DateFormat.getDateInstance().format(c.getTime());
        Date = findViewById(R.id.tvDate3);
        Date.setText(currentDateString);




    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}