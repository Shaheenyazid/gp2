package com.example.group0;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Invoice extends AppCompatActivity {

    Button createButton;
    Spinner item1spinner, item2spinner;
    EditText customerName, phoneNo, qty1, qty2;
    Bitmap bmp, scaledbmp;
    int pageWidth = 1200;
    Date dateObj;
    DateFormat dateFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);

        createButton = findViewById(R.id.create_button);
        item1spinner = findViewById(R.id.item1spinner);
        item2spinner = findViewById(R.id.item2spinner);
        customerName = findViewById(R.id.editTextCustomerName);
        phoneNo = findViewById(R.id.editTextPhoneNo);
        qty1 = findViewById(R.id.editTextQty1);
        qty2 = findViewById(R.id.editTextQty2);
        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_background);
        scaledbmp = Bitmap.createScaledBitmap(bmp, 1200, 518, false);

        ActivityCompat.requestPermissions(this,new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);

        createPDF();

    }

    private void createPDF() {
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dateObj = new Date();

                if (customerName.getText().toString().length() == 0 ||
                        phoneNo.getText().toString().length() == 0 || qty1.getText().toString().length() == 0 || qty2.getText().toString().length() == 0) {
                    Toast.makeText(Invoice.this, "Some files are empty", Toast.LENGTH_LONG).show();
                } else {

                    PdfDocument myPdfDocument = new PdfDocument();
                    Paint myPaint = new Paint();
                    Paint titlepaint = new Paint();

                    PdfDocument.PageInfo myPageInfo1 = new PdfDocument.PageInfo.Builder(1200, 2010, 1).create();
                    PdfDocument.Page myPage1 = myPdfDocument.startPage(myPageInfo1);
                    Canvas canvas = myPage1.getCanvas();

                    canvas.drawBitmap(scaledbmp, 0, 0, myPaint);
                    titlepaint.setTextAlign(Paint.Align.CENTER);
                    titlepaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
                    titlepaint.setTextSize(70f);
                    canvas.drawText("Arca Creation", pageWidth / 2, 270, titlepaint);

                    titlepaint.setTextAlign(Paint.Align.CENTER);
                    titlepaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.ITALIC));
                    titlepaint.setTextSize(70f);
                    canvas.drawText("Invoice", pageWidth / 2, 500, titlepaint);

                    myPaint.setTextAlign(Paint.Align.LEFT);
                    myPaint.setTextSize(35f);
                    myPaint.setColor(Color.BLACK);
                    canvas.drawText("Customer Name:" + customerName.getText(), 20, 590, myPaint);
                    canvas.drawText("Contact No:" + phoneNo.getText(), 20, 640, myPaint);

                    myPaint.setTextAlign(Paint.Align.RIGHT);
                    canvas.drawText("Invoice No:" + "6969", -20, 590, myPaint); //ADD Invoice Number

                    dateFormat = new SimpleDateFormat("dd/mm/yy");
                    canvas.drawText("Date:" + dateFormat.format(dateObj), pageWidth - 20, 640, myPaint);

                    dateFormat = new SimpleDateFormat("HH:mm:ss");
                    canvas.drawText("Time" + dateFormat.format(dateObj), -20, 690, myPaint);

                    myPdfDocument.finishPage(myPage1);
                    File file = new File(Environment.getExternalStorageDirectory(), "/Hello.pdf");

                    try {
                        myPdfDocument.writeTo(new FileOutputStream(file));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    myPdfDocument.close();
                }
            }
        });
    }
}