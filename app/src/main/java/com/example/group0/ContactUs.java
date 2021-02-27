package com.example.group0;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class ContactUs extends AppCompatActivity {

    ImageButton androidImageButton;

    Button buttonCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        TextView email = (TextView) findViewById(R.id.tv_Arcacom);
        email.setText(Html.fromHtml("<a href=\"mailto:arcacreation08@gmail.com\">arcacreation08@gmail.com</a>"));
        email.setMovementMethod(LinkMovementMethod.getInstance());



        buttonCall = findViewById(R.id.btn_call);
        buttonCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:+60 13-317-4100"));
                startActivity(intent);
            }
        });

        findViewById(R.id.btn_facebook) .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked_btn("https://www.facebook.com/arcacreation12/?ref=page_internal");
            }
        });

        findViewById(R.id.btn_insta) .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked_btn("https://www.instagram.com/arca_creation/?hl=en");
            }
        });

        findViewById(R.id.btn_browser) .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked_btn("https://www.arcacreation.com");
            }
        });
    }
    public void clicked_btn(String url)
    {
        Intent intent=new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }


}
