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
    float[] prices = new float[] {0, 200, 300, 450, 325, 500};


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


        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.coast);             //saya tukar kat sini
        scaledbmp = Bitmap.createScaledBitmap(bmp, 1200, 518, false);

        ActivityCompat.requestPermissions(this,new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);

        createPDF();

    }

    private void createPDF() {
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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

                    myPaint.setStyle(Paint.Style.STROKE);
                    myPaint.setStrokeWidth(2);
                    canvas.drawRect(20, 780, pageWidth - 20, 860, myPaint);

                    myPaint.setTextAlign(Paint.Align.LEFT);
                    myPaint.setStyle(Paint.Style.FILL);
                    canvas.drawText("Si.No.", 40, 830, myPaint);
                    canvas.drawText("Item Description", 200, 830, myPaint);
                    canvas.drawText("Price", 700, 830, myPaint);
                    canvas.drawText("Qty", 900, 830, myPaint);
                    canvas.drawText("Total", 1050, 830, myPaint);

                    canvas.drawLine(180, 790, 180, 840, myPaint);
                    canvas.drawLine(680, 790, 680, 840, myPaint);
                    canvas.drawLine(880, 790, 880, 840, myPaint);
                    canvas.drawLine(1030, 790, 1030, 840, myPaint);

                    float total1 = 0, total2 = 0;
                    if (item1spinner.getSelectedItemPosition()!=0){
                        canvas.drawText("1", 40, 950, myPaint);
                        canvas.drawText(item1spinner.getSelectedItem().toString(), 200, 950, myPaint);
                        canvas.drawText(String.valueOf(prices[item1spinner.getSelectedItemPosition()]), 700, 950, myPaint);
                        canvas.drawText(qty1.getText().toString(), 900, 950, myPaint);
                        total1 = Float.parseFloat(qty1.getText().toString())*prices[item1spinner.getSelectedItemPosition()];
                        myPaint.setTextAlign(Paint.Align.LEFT);
                        canvas.drawText(String.valueOf(total1), pageWidth - 40, 950, myPaint);
                        myPaint.setTextAlign(Paint.Align.LEFT);
                    }

                    if (item2spinner.getSelectedItemPosition()!=0){
                        canvas.drawText("2", 40, 1050, myPaint);
                        canvas.drawText(item2spinner.getSelectedItem().toString(), 200, 1050, myPaint);
                        canvas.drawText(String.valueOf(prices[item2spinner.getSelectedItemPosition()]), 700, 1050, myPaint);
                        canvas.drawText(qty2.getText().toString(), 900, 1050, myPaint);
                        total2 = Float.parseFloat(qty2.getText().toString())*prices[item2spinner.getSelectedItemPosition()];
                        myPaint.setTextAlign(Paint.Align.LEFT);
                        canvas.drawText(String.valueOf(total2), pageWidth-40, 1050, myPaint);
                        myPaint.setTextAlign(Paint.Align.LEFT);
                    }

                    float subtotal = total1 + total2;
                    canvas.drawLine(680, 1200, pageWidth - 20, 1200, myPaint);
                    canvas.drawText("Sub Total", 700, 1250, myPaint);
                    canvas.drawText(";", 900, 1250, myPaint);
                    myPaint.setTextAlign(Paint.Align.LEFT);
                    canvas.drawText(String.valueOf(subtotal), pageWidth-40, 1250, myPaint);

                    myPaint.setTextAlign(Paint.Align.LEFT);
                    canvas.drawText("Tax (12%)", 700, 1300, myPaint);
                    canvas.drawText(";", 900, 1300, myPaint);
                    myPaint.setTextAlign(Paint.Align.RIGHT);
                    canvas.drawText(String.valueOf(subtotal*12/100), pageWidth-40, 1300, myPaint);
                    myPaint.setTextAlign(Paint.Align.LEFT);

                    myPaint.setColor(Color.rgb(247, 147, 30));
                    canvas.drawRect(680, 1350, pageWidth-20, 1450, myPaint);

                    myPaint.setColor(Color.BLACK);
                    myPaint.setTextSize(50f);
                    myPaint.setTextAlign(Paint.Align.LEFT);
                    canvas.drawText("Total", 700, 1415, myPaint);
                    myPaint.setTextAlign(Paint.Align.RIGHT);
                    canvas.drawText(String.valueOf(subtotal + (subtotal*12/100)), pageWidth-40, 1415, myPaint);

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