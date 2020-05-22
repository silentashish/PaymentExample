package com.example.imepaycheck;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.swifttechnology.imepaysdk.ENVIRONMENT;
import com.swifttechnology.imepaysdk.IMEPayment;
import com.swifttechnology.imepaysdk.IMEPaymentCallback;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = findViewById(R.id.bus_ko_ticket);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                Toast.makeText(getApplicationContext(),"click",Toast.LENGTH_SHORT).show();
                IMEPayment imePayment = new IMEPayment(getApplicationContext(), ENVIRONMENT.TEST);
                imePayment.performPayment("BUSTICKET",
                        "Bus Ko Ticket",
                        "https://dev.buskoticket.com/api/v1/paywithImePay",
                        "100",
                        generateString(),
                        "BUSKOTICKET",
                        "Buskoticket",
                        "Buskoticket",
                        new IMEPaymentCallback() {
                            @Override
                            public void onSuccess(int responseCode, String responseDescription, String transactionId, String msisdn, String amount, String refId) {
                                // Response Code 100 : Transaction successful.
                                // Response Code 101 : Transaction failed.
                                // responseDescription : Message sent from server, contains transaction success message/ failure message with reason
                                // transactionId : Unique ID generated from IME pay system
                                // msisdn : Customer Mobile Number
                                // amount : Amount paid by customer
                                // refId : Reference Value
                                Toast.makeText(getApplicationContext(),"Payment Success", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        final Button buttonNxt = findViewById(R.id.heroku);
        buttonNxt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                Toast.makeText(getApplicationContext(),"click next",Toast.LENGTH_SHORT).show();
                IMEPayment imePayment = new IMEPayment(getApplicationContext(), ENVIRONMENT.TEST);
                imePayment.performPayment("BUSTICKET",
                        "Bus Ko Ticket",
                        "https://intense-caverns-78068.herokuapp.com/data",
                        "100",
                        generateString(),
                        "BUSKOTICKET",
                        "Buskoticket",
                        "Buskoticket",
                        new IMEPaymentCallback() {
                            @Override
                            public void onSuccess(int responseCode, String responseDescription, String transactionId, String msisdn, String amount, String refId) {
                                // Response Code 100 : Transaction successful.
                                // Response Code 101 : Transaction failed.
                                // responseDescription : Message sent from server, contains transaction success message/ failure message with reason
                                // transactionId : Unique ID generated from IME pay system
                                // msisdn : Customer Mobile Number
                                // amount : Amount paid by customer
                                // refId : Reference Value
                                Toast.makeText(getApplicationContext(),"Payment Success", Toast.LENGTH_SHORT).show();
                            }
                        });
            }

        });
    }

    public static String generateString() {
        String uuid = UUID.randomUUID().toString();
        return uuid;
    }
}
