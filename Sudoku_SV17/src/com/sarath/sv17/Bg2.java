package com.sarath.sv17;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Bg2 extends Activity{
   	
	    @Override
    public void onCreate(Bundle savedInstanceState)
	 {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bgmenu);
        Button solver=null;
        Button new_game=null;
        Button exit=null;
        Button credits=null;
                
        solver=(Button)findViewById(R.id.button2);
        solver.setOnClickListener(new View.OnClickListener()
           { 
        	 public void onClick(View view) 
              {    
        		 Intent myIntent = new Intent(Bg2.this, Sudoku_solver.class);
                   startActivity(myIntent);   
              }     
           });
        
        new_game=(Button)findViewById(R.id.button1);
        new_game.setOnClickListener(new View.OnClickListener()
           {   
        	  public void onClick(View view) 
               {	
        		  Intent myIntent = new Intent(Bg2.this, GameEngine.class);
                startActivity(myIntent);  
               }  
        	});
        
       exit=(Button)findViewById(R.id.button4);
       exit.setOnClickListener(new View.OnClickListener()
          { 
    	     public void onClick(View view)
            { 
    	    	 System.exit(0); 
    	    	 } 
    	   });
       
       credits=(Button)findViewById(R.id.button3);
       credits.setOnClickListener(new View.OnClickListener()
           {  
    	     public void onClick(View view) 
               { 
    	    	 Intent myIntent = new Intent(Bg2.this, Credit.class);
                startActivity(myIntent);
                }  
    	     });
        }
}