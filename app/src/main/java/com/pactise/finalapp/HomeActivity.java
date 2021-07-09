package com.pactise.finalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

public class HomeActivity extends AppCompatActivity {
RadioGroup radioGroup;
String k;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        radioGroup=findViewById(R.id.radio);
       radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(RadioGroup group, int checkedId) {
               switch (checkedId){
                   case R.id.Rb1:
                       k="eg";
                       break;
                   case R.id.Rb2:
                       k="us";
                       break;
               }
           }
       });
    }



    public void openGeneralNews(View view) {
        openListActivity("general",k);
    }

    public void openHealthNews(View view) {
        openListActivity("health",k);
    }

    public void openScienceNews(View view) {
        openListActivity("science",k);
    }

    public void openSportsNews(View view) {
        openListActivity("sports",k);
    }

    public void openTechnologyNews(View view) {
        openListActivity("technology",k);
    }
    public void openEntertainmentNews(View view) {
        openListActivity("entertainment",k);
    }

    public void openBusinessNews(View view) {
        openListActivity("business",k);
    }

    public void openListActivity(String key1, String key2){
        Intent intent1= new Intent(this,MainActivity.class);
        intent1.putExtra(Constants.CATEGORY_KEY,key1);
        intent1.putExtra(Constants.COUNTRY_KEY,key2);
        startActivity(intent1);
    }

    @Override
    public void onBackPressed() {
        ExitDialog exit =new ExitDialog();
        exit.show(getSupportFragmentManager(),"");
        exit.setCancelable(false);
    }
}
