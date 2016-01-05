package com.sarath.sv17;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Sudoku_SV17Activity extends Activity {
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main); 
        
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                final Intent mainIntent = new Intent(Sudoku_SV17Activity.this, Bg2.class);
                Sudoku_SV17Activity.this.startActivity(mainIntent);
                Sudoku_SV17Activity.this.finish();
               }
        }, 5000);
    }
}