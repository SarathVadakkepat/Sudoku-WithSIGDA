package com.sarath.sv17;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.util.Random;
import java.io.*;

public class GameEngine_alternate extends Activity{
   	
	 String x="";
	 String x1=""; 
    @Override
    public void onCreate(Bundle savedInstanceState) 
	    {
           super.onCreate(savedInstanceState);
           toughness();
        }
	    
    public void randomise()
	    {
	        ProgressDialog dialog = ProgressDialog.show(GameEngine_alternate.this, "", "Loading.", true);
	        dialog.show();
	        int n1=random_func(4);
		   	if(n1==1) random_level1();
		   	else if(n1==2) random_level2();
		   	else if(n1==3) random_level3();
		   	else if(n1==4) random_level4();
		   	   	
		  Intent intent =new Intent(GameEngine_alternate.this, Gameplay.class);
		  intent.putExtra("question", x);
		  startActivity(intent);
		  finish();
	    }
	    public void toughness()
	    {
	    	final Context mContext = this;
	    	final Dialog dialog = new Dialog(mContext);
	       	dialog.setContentView(R.layout.toughness);
	    	dialog.setTitle("Level");
	    	dialog.show();
	    	
	    	Button Easy=(Button) dialog.findViewById(R.id.buttoneasy);
	    	Button Medium=(Button) dialog.findViewById(R.id.buttonmedium);
	    	Button Hard=(Button) dialog.findViewById(R.id.buttonhard);
	    	
	    	Easy.setOnClickListener(new View.OnClickListener() 
    	    { 
	    	   @Override
               public void onClick(View view)
               {  
	    		   GetEasy();
                   dialog.dismiss();    
                   randomise();
                   }      
    	    });
	    	
	    	Medium.setOnClickListener(new View.OnClickListener() 
    	    {  
	    	   @Override
               public void onClick(View view)
               { 
	    		   GetMedium();
                   randomise();
                   dialog.dismiss();        
               }      
    	    });
	    	
	    	Hard.setOnClickListener(new View.OnClickListener() 
    	    { 
	    	   @Override
               public void onClick(View view)
               {  
	    		  GetHard();
                  randomise();
                  dialog.dismiss();        
                }      
    	    });
	    	return;
	    }
	    
	    public void GetEasy()
	    {
	    	int easy1=random_func(80);
	    	int counter=0;
	    	try {
	        	counter=0;
	    		String str="";
	    		InputStream is = this.getResources().openRawResource(R.drawable.data_easy);
	    		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
	    		while ((str = reader.readLine()) != null)
	    		{	
	    		   counter++;
	    		   if(counter==easy1)
	    			{
	    			   x=str;
	    				break;
	    			}
	    		}				
	         } 
	        catch (IOException e) {	}
	
	    return;
	    }
	     
	    public void GetMedium()
	    {
	    	int medium1=random_func(80);
	    	int counter=0;
	        
	        try {
	        	counter=0;
	    		String str="";
	    		InputStream is = this.getResources().openRawResource(R.drawable.data_medium);
	    		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
	    		while ((str = reader.readLine()) != null)
	    		{	
	    		   counter++;
	    		   if(counter==medium1)
	    			{
	    				x=str;
	    				break;
	    			}
	    		}				
	         } 
	        catch (IOException e) {	}
	
	    return;
	    }
	    
	    public void GetHard()
	    {
	    	int hard1=random_func(80);
	    	int counter=0;
	        
	        try {
	        	counter=0;
	    		String str="";
	    		InputStream is = this.getResources().openRawResource(R.drawable.data_hard);
	    		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
	    		while ((str = reader.readLine()) != null)
	    		{	
	    		   counter++;
	    		   if(counter==hard1)
	    			{
	    				x=str;
	    				break;
	    			}
	    		}				
	         } 
	        catch (IOException e) {	}
	
	    return;
	    }
	    
	    public void random_level1()
		{	
		    int n2=random_func(4);
		    if(n2==2) smart(8,1,0,8,1,0,1,0);
		   	else if(n2==3) smart(0,-1,8,8,1,0,2,0);
		   	else if(n2==4) smart(8,1,0,8,1,0,1,0);
		   	return;  
		}
		   	 
	    public void random_level2()
		{
			random_level1();
			int n2=random_func(3);
		    if(n2==1) smart(0,-1,8,8,1,0,1,0);
		    else if(n2==2) smart(8,1,0,0,-1,8,1,0);
		   	return; 
		}
		   		
		public void random_level3()
		{
		    random_level2();
			int n2=random_func(36)-1;
			int a1=(int)(n2/6);
			int a2=n2-(a1*6);
			int array[]={1,2,3,1,3,2,2,1,3,2,3,1,3,1,2,3,2,1};
			shift_h(array[a1*3],array[(a1*3)+1],array[(a1*3)+2]);
			shift_v(array[a2*3],array[(a2*3)+1],array[(a2*3)+2]);
			return; 
		}
				
		public void random_level4()
		{
			random_level3();
			random_level2();
			int n2=random_func(2);
			if(n2==2)
	   		 {
	   			 n2=random_func(2);
	   			 if(n2==2) supercharged();
	   		 }
			return;  
		 }
		   		
	    public int random_func(int n)
		{
			Random rand=new Random();
		    return (rand.nextInt(n)+1); 
		}   	
				
		public void smart(int a,int b,int c,int a1,int b1,int c1,int code,int e)
	    {  
	    	if(e==0) x="";
	    	for(int i=a;(-i*b)<=c;i=i-b)
	     	{  for(int j=a1;(-j*b1)<=c1;j=j-b1)
	  	    	{  if(code==1) x=x+x1.charAt((i*9)+j);
	  		     	if(code==2)	x=x+x1.charAt((j*9)+i);  
	  		     	    }    
	  		     	    	}
	  	if(e==0) x1=x;  
	  	 }
	  	 
	  	public void shift_v(int xx, int yy, int zz)
	    {
	    	 x="";
	  	     smart(((xx-1)*3),-1,((xx-1)*3)+2,0,-1,8,1,1);
	  	     smart(((yy-1)*3),-1,((yy-1)*3)+2,0,-1,8,1,1);
	         smart(((zz-1)*3),-1,((zz-1)*3)+2,0,-1,8,1,1);
	         x1=x; 
	     }
	     
	    public void shift_h(int xx, int yy, int zz)
	    {
	    	x="";
	  	    int num=0;
	  	    while(num<=8)
	  	    {	x=x+x1.substring((num*9)+((xx-1)*3),((num*9)+((xx-1)*3))+3)+x1.substring((num*9)+((yy-1)*3),((num*9)+((yy-1)*3))+3);
	  	     	x=x+x1.substring((num*9)+((zz-1)*3),((num*9)+((zz-1)*3))+3);
	  	     	num++;
	  		}
	       x1=x; 
	     }
	     
	    public void shift_vm(int xx,int yy,int zz)
	 	{  
	 	    x="";
		    x=x+x1.substring(0,27);
	        smart(xx+2,-1,xx+2,0,-1,8,1,1);
	        smart(yy+2,-1,yy+2,0,-1,8,1,1);
	        smart(zz+2,-1,zz+2,0,-1,8,1,1);
	        x=x+x1.substring(54,81);
	        x1=x;  
	    }
	          
	    public void shift_hm(int xx, int yy, int zz)
	    {
	      x="";
	  	  int num=0;
	      while(num<=8)
	  	  {
	  	    x=x+x1.substring((num*9),(num*9)+3);
	  	    x=x+x1.charAt((num*9)+xx+2)+x1.charAt((num*9)+yy+2)+x1.charAt((num*9)+zz+2);
	  	    x=x+x1.substring((num*9)+6,((num*9)+6)+3);
	  		num++; 
	  	  }
	    x1=x;   
	     }
	
	public void supercharged()
		{
			int n2=random_func(36)-1;
			int a1=(int)(n2/6);
			int a2=n2-(a1*6);
			int array[]={1,2,3,1,3,2,2,1,3,2,3,1,3,1,2,3,2,1};
			shift_hm(array[a1*3],array[(a1*3)+1],array[(a1*3)+2]);
			shift_vm(array[a2*3],array[(a2*3)+1],array[(a2*3)+2]);
			return; 
		}
	
	}

