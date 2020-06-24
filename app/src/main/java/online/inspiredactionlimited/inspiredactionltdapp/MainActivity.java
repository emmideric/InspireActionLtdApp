package online.inspiredactionlimited.inspiredactionltdapp;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;

import com.example.inspireapp.R;

public class MainActivity extends AppCompatActivity {

    ViewFlipper v_flipper;
    Button btn_airtel, btn_mtn;
    String airtel_no = "tel:0700949090";
    String mtn_no = "tel:0774949090";
    String SMS_no = "0700949090";

    final int SEND_SMS_PERMISSION_REQUEST_CODE = 1;
    EditText message;
    Button btn_SMS;
    Button btn_whatsapp;
    Button btn_appiontment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int images[] = {R.drawable.slide_1, R.drawable.slide_2, R.drawable.slide_3};

        v_flipper = findViewById(R.id.v_flipper);

        //for loop
        /*for (int i = 0; i < images.length; i++){
            flipperImages(images[i]);
        }*/
        //using foreach
        for (int image: images){
            flipperImages(image);
        }

        btn_airtel = findViewById(R.id.btn_airtel);
        //configure airtel button to call on click
        btn_airtel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String phone = "tel:0700949090";
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(phone));
                startActivity(intent);
            }
        });

        btn_mtn = findViewById(R.id.btn_mtn);
        //configure mtn button to call on click
        btn_mtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String phone = "tel:0700949090";
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(phone));
                startActivity(intent);
            }
        });

        //sending message via sms messenger
        btn_SMS = findViewById(R.id.btn_SMS);
        btn_SMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = "tel:0700949090";

                Intent send = new Intent(Intent.ACTION_VIEW);
                send.putExtra("address", phone);
                send.putExtra("sms_body", "");
                send.setType("vnd.android-dir/mms-sms");
                startActivity(send);
            }
        });

        //sending via whatsapp
        btn_whatsapp = findViewById(R.id.btn_whatsapp);

        btn_whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get the edited value
                //String send_message = message.getText().toString();

                //check whether whatsapp is installed;
                boolean installed = appInstalledOrNot("com.whatsapp");

                if (installed){
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://api.whatsapp.com/send?phone="+"256"+SMS_no));
                    startActivity(intent);
                }else{
                    Toast.makeText(MainActivity.this, "Whatsapp app not installed on your device", Toast.LENGTH_SHORT);
                }
            }
        });

        //Appointment button clicked
        btn_appiontment = findViewById(R.id.btn_appointment);

        btn_appiontment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAppointmentActivity();
            }
        });


    }

    public void flipperImages(int image){
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(image);

        v_flipper.addView(imageView);
        v_flipper.setFlipInterval(4000); //4 sec
        v_flipper.setAutoStart(true);

        //animation
        v_flipper.setInAnimation(this, android.R.anim.slide_in_left);
        v_flipper.setOutAnimation(this, android.R.anim.slide_out_right);
    }

    public boolean appInstalledOrNot(String url){
        PackageManager packagemanager = getPackageManager();
        boolean app_installed;
        try {
            packagemanager.getPackageInfo(url, PackageManager.GET_ACTIVITIES);
            app_installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            app_installed = false;
        }
        return  app_installed;
    }

    public  void openAppointmentActivity(){
        Intent intent = new Intent(this, AppointmentActivity.class);
        startActivity(intent);
    }
}
