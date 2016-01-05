package com.sarath.sv17;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class Credit extends Activity{
    	
	  @Override
    public void onCreate(Bundle savedInstanceState) 
	  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.credit);
        Button sv1=(Button)findViewById(R.id.sv1);
        sv1.setOnClickListener(new View.OnClickListener()
           {   
        	  public void onClick(View view) 
               {	
        		  display(1);
                }  
        	});
        
        Button sv2=(Button)findViewById(R.id.sv2);
        sv2.setOnClickListener(new View.OnClickListener()
           {   
        	  public void onClick(View view) 
               {	
        		  display(2);
                }  
        	});
        Button vs1=(Button)findViewById(R.id.vs1);
       vs1.setOnClickListener(new View.OnClickListener()
           {   
        	  public void onClick(View view) 
               {	
        		  display(3);
                }  
        	});
        
        Button back=(Button)findViewById(R.id.b_back);
        back.setOnClickListener(new View.OnClickListener()
           {   
        	  public void onClick(View view) 
               {	
        		        finish();
                }  
        	});
       }
	  
	  public void display(int a)
	  {
		    final Context mContext = this;
	    	final Dialog dialog = new Dialog(mContext);
	       	dialog.setContentView(R.layout.sv1);
	       	RelativeLayout rl1=(RelativeLayout) dialog.findViewById(R.id.rlsv);
	    	if(a==1) rl1.setBackgroundResource(R.drawable.bg_sarath);
	    	if(a==2) rl1.setBackgroundResource(R.drawable.bg_sujith);
	    	if(a==3) rl1.setBackgroundResource(R.drawable.bg_vig);
	    	
	    	rl1.setOnClickListener(new View.OnClickListener()
	           {   
	        	  public void onClick(View view) 
	               {	
	        		        dialog.dismiss();
	                }  
	        	});
	    	dialog.show();
	    	return;
	  }
	  
	  
}