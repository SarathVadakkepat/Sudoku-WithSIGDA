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

public class GameEngine extends Activity{
   	
	 String x; 
     int i,j;
     int array[][]=new int[9][9];
     int ctr=0;
     
	    @Override
    public void onCreate(Bundle savedInstanceState) 
	    {
           super.onCreate(savedInstanceState);
           toughness();
        }
	    
    public void randomise()
	    {
	        ProgressDialog dialog = ProgressDialog.show(GameEngine.this, "", "Loading.", true);
	        dialog.show();
	    	int n1=random_func(4);
		   	if(n1==1)
		   	  random_level1();
		   	else if(n1==2)
		   	{    
		   	    random_level1();
		   	    random_level2();
		   	}
		   	else if(n1==3)
		   	{  
		        random_level1();
		   	    random_level2();
		   	    random_level3();
		   	}
		   	else if(n1==4)
		   	{    
		   	    random_level1();
		   	    random_level2();
		   	    random_level3();
		   	    random_level4();		 	
		   	}
		   	get_string();
		   	
		  Intent intent =new Intent(GameEngine.this, Gameplay.class);
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
		    if(n2==1) north();
		    else if(n2==2) south();
		    else if(n2==3) east();
		    else if(n2==4)	west();
		   		
		    return;
		}
		
		public void random_level2()
		{
			 int n2=random_func(3);
		   	 if(n2==1) mirror_vertical();
		   	 else if(n2==2) mirror_horizontal();
		   	 else if(n2==3) {}
		   	 
		     return;
		}
		public void random_level3()
		{   
			int n2=random_func(36);
			switch(n2)
			{
				case 1: break;
				case 2: shift_v(1,3,2);
				        break;
				case 3: shift_v(2,1,3);
				        break;
				case 4: shift_v(2,3,1);
				        break;
				case 5: shift_v(3,1,2);
				        break;
				case 6: shift_v(2,3,1);
				        break;
				case 7: shift_h(1,3,2);
	                  	break;
				case 8: shift_h(1,3,2);
	                    shift_v(1,3,2);
	                  	break;
				case 9: shift_h(1,3,2);
	                    shift_v(2,1,3);
	                  	break;
			   case 10: shift_h(1,3,2);
	                    shift_v(2,3,1);
	                  	break;
			   case 11: shift_h(1,3,2);
	                  	shift_v(3,1,2);
	                  	break;
			   case 12: shift_h(1,3,2);
	                   	shift_v(3,2,1);
	                  	break;
			   case 13: shift_h(2,1,3);
	                  	break;
			   case 14: shift_h(2,1,3);
	                  	shift_v(1,3,2);
				        break;
			   case 15: shift_h(2,1,3);
	                  	shift_v(2,1,3);
				        break;
			   case 16: shift_h(2,1,3);
	                  	shift_v(2,3,1);
				        break;
			   case 17: shift_h(2,1,3);
	                  	shift_v(3,1,2);
				        break;
			   case 18: shift_h(2,1,3);
	                  	shift_v(3,2,1);
				        break;
			   case 19: shift_h(2,3,1);
	                  	break;
			   case 20: shift_h(2,3,1);
	                  	shift_v(1,3,2);
	                  	break;
			   case 21: shift_h(2,3,1);
	                  	shift_v(2,1,3);
	                  	break;
			   case 22: shift_h(2,3,1);
	                  	shift_v(2,3,1);
	                  	break;
			   case 23: shift_h(2,3,1);
	                  	shift_v(3,1,2);
	                  	break;
			   case 24: shift_h(2,3,1);
	                  	shift_v(3,2,1);
	                    break;
	           case 25: shift_h(3,1,2);
	                  	break;
			   case 26: shift_h(3,1,2);
	                  	shift_v(1,3,2);
	                  	break;
			   case 27: shift_h(3,1,2);
	                  	shift_v(2,1,3);
	                  	break;
			   case 28: shift_h(3,1,2);
	                  	shift_v(2,3,1);
	                  	break;
			   case 29: shift_h(3,1,2);
	                  	shift_v(3,1,2);
	                  	break;
			   case 30: shift_h(3,1,2);
	                  	shift_v(3,2,1);
	                  	break;
	           case 31: shift_h(3,2,1);
	                  	break;
			   case 32: shift_h(3,2,1);
	                  	shift_v(1,3,2);
	                  	break;
			   case 33: shift_h(3,2,1);
	                  	shift_v(2,1,3); 
	                  	break;
			   case 34: shift_h(3,2,1);
	                  	shift_v(2,3,1);
	                  	break;
			   case 35: shift_h(3,2,1);
	                  	shift_v(3,1,2);
	                  	break;
			   case 36: shift_h(3,2,1);
	                  	shift_v(3,2,1);
	                  	break;
			}
			return;
		}
		
		public void shift_h(int a, int b, int c)
		{
			east();
			shift_v(a,b,c);
	        west();
	        return;
		}
		
		public void shift_hm(int a, int b, int c)
		{
			east();
			shift_vm(a,b,c);
	        west();
	        return;
		}
			
		public void random_level4()
		{
			int n2=random_func(3);
		   		 if(n2==1) mirror_vertical();
		   		 else if(n2==2) mirror_horizontal();
		   		 else if(n2==3)
		   		 {
		   			 n2=random_func(2);
		   			 if(n2==2) supercharged();
		   		 }
		   		
		   	return;
		}
		
		public void supercharged()
		{
			int n2=random_func(36);
			switch(n2)
			{
				case 1: break;
				case 2: shift_vm(1,3,2);
				        break;
				case 3: shift_vm(2,1,3);
				        break;
				case 4: shift_vm(2,3,1);
				        break;
				case 5: shift_vm(3,1,2);
				        break;
				case 6: shift_vm(2,3,1);
				        break;
				case 7: shift_hm(1,3,2);
	                  	break;
				case 8: shift_hm(1,3,2);
	                    shift_vm(1,3,2);
	                  	break;
				case 9: shift_hm(1,3,2);
	                    shift_vm(2,1,3);
	                  	break;
			   case 10: shift_hm(1,3,2);
	                    shift_vm(2,3,1);
	                  	break;
			   case 11: shift_hm(1,3,2);
	                  	shift_vm(3,1,2);
	                  	break;
			   case 12: shift_hm(1,3,2);
	                   	shift_vm(3,2,1);
	                  	break;
			   case 13: shift_hm(2,1,3);
	                  	break;
			   case 14: shift_hm(2,1,3);
	                  	shift_vm(1,3,2);
				        break;
			   case 15: shift_hm(2,1,3);
	                  	shift_vm(2,1,3);
				        break;
			   case 16: shift_hm(2,1,3);
	                  	shift_vm(2,3,1);
				        break;
			   case 17: shift_hm(2,1,3);
	                  	shift_vm(3,1,2);
				        break;
			   case 18: shift_hm(2,1,3);
	                  	shift_vm(3,2,1);
				        break;
			   case 19: shift_hm(2,3,1);
	                  	break;
			   case 20: shift_hm(2,3,1);
	                  	shift_vm(1,3,2);
	                  	break;
			   case 21: shift_hm(2,3,1);
	                  	shift_vm(2,1,3);
	                  	break;
			   case 22: shift_hm(2,3,1);
	                  	shift_vm(2,3,1);
	                  	break;
			   case 23: shift_hm(2,3,1);
	                  	shift_vm(3,1,2);
	                  	break;
			   case 24: shift_hm(2,3,1);
	                  	shift_vm(3,2,1);
	                    break;
	           case 25: shift_hm(3,1,2);
	                  	break;
			   case 26: shift_hm(3,1,2);
	                  	shift_vm(1,3,2);
	                  	break;
			   case 27: shift_hm(3,1,2);
	                  	shift_vm(2,1,3);
	                  	break;
			   case 28: shift_hm(3,1,2);
	                  	shift_vm(2,3,1);
	                  	break;
			   case 29: shift_hm(3,1,2);
	                  	shift_vm(3,1,2);
	                  	break;
			   case 30: shift_hm(3,1,2);
	                  	shift_vm(3,2,1);
	                  	break;
	           case 31: shift_hm(3,2,1);
	                  	break;
			   case 32: shift_hm(3,2,1);
	                  	shift_vm(1,3,2);
	                  	break;
			   case 33: shift_hm(3,2,1);
	                  	shift_vm(2,1,3); 
	                  	break;
			   case 34: shift_hm(3,2,1);
	                  	shift_vm(2,3,1);
	                  	break;
			   case 35: shift_hm(3,2,1);
	                  	shift_vm(3,1,2);
	                  	break;
			   case 36: shift_hm(3,2,1);
	                  	shift_vm(3,2,1);
	                  	break;
			}
			return;
		}
		public int random_func(int n)
		{
			Random rand=new Random();
			int num=rand.nextInt(n);
			return num+1;
		}
		
		public void north()
		{   ctr=0;
			for(i=0;i<9;i++)
			   for(j=0;j<9;j++)
					array[i][j]=Integer.parseInt(String.valueOf(x.charAt(ctr++)));
		    get_string(); 
		    return;
		}
		    
	     public void south()
		{   east();
		    east();
		    get_string();
		    return;	
		}
				    
		public void west()
		{	ctr=0;
			for(i=0;i<9;i++)
			    for(j=8;j>=0;j--)
			       array[j][i]=Integer.parseInt(String.valueOf(x.charAt(ctr++))); 
			get_string();
			return; 
	    }
			
			public void east()
		{	 ctr=0;
			for(i=8;i>=0;i--)
			  for(j=0;j<9;j++)
			    array[j][i]=Integer.parseInt(String.valueOf(x.charAt(ctr++))); 
		    get_string(); 
		    return;  
		 }	
		    	    
		public void get_string()
		{   x="";
			for(i=0;i<9;i++)
			   for(j=0;j<9;j++)
			      x=x+String.valueOf(array[i][j]);
			      
			 return;
		}
		
		public void mirror_vertical()
		{	ctr=0;
			for(i=0;i<9;i++)
				for(j=8;j>=0;j--)
					array[i][j]=Integer.parseInt(String.valueOf(x.charAt(ctr++)));
					
		    get_string();
		    return;
		}
					
	    public void mirror_horizontal()
		{	ctr=0;
			for(i=8;i>=0;i--)
			   for(j=0;j<9;j++)
				    array[i][j]=Integer.parseInt(String.valueOf(x.charAt(ctr++)));
			
		    get_string();
		    return; 
		 }	
					
		public void shift_v(int a,int b,int c)
	     {
	      	String str[]=new String[3];
	     	str[0]=x.substring(0,27);
	     	str[1]=x.substring(27,54);
	     	str[2]=x.substring(54,81);
	     	for(i=0;i<3;i++)
	     	{
	     		for(int j=0;j<9;j++)
	     		   array[i][j]=str[a-1].charAt(i*9+j)-48;
	     		for(int j=0;j<9;j++)
	     		   array[i+3][j]=str[b-1].charAt(i*9+j)-48;
	     		for(int j=0;j<9;j++)
	     		   array[i+6][j]=str[c-1].charAt(i*9+j)-48;
	     	}
	     	get_string();
	     	return;
	     }
		
		public void shift_vm(int a,int b,int c)
	     {
	      	String str[]=new String[3];
	     	str[0]=x.substring(27,36);
	     	str[1]=x.substring(36,45);
	     	str[2]=x.substring(45,54);
	     
	     		for(int j=0;j<9;j++)
	     		   array[3][j]=str[a-1].charAt(j)-48;
	     		for(int j=0;j<9;j++)
	     		   array[4][j]=str[b-1].charAt(j)-48;
	     		for(int j=0;j<9;j++)
	     		   array[5][j]=str[c-1].charAt(j)-48;
	     	
	     	get_string();
	     	return;
	     }
	     
		/* 
		 * 
		 * 
		 *  GAME ENGINE STYLE
		 *  
		 *  
		 *  public static void smart(int a,int b,int c,int a1,int b1,int c1,int code,int e)
            {
  	          if(e==0)
  	          x="";
  	          for(int i=a;(-i*b)<=c;i=i-b)
  	          {  for(int j=a1;(-j*b1)<=c1;j=j-b1)
  		         {   if(code==1)
  		   	           array1[ptr][ptr1++]=array[i][j];
  		             else 
  			           array1[ptr][ptr1++]=array[j][i];
  		          }
  	          }
             }
             
             public static void set()
	         {   ctr=0;
		         for(i=0;i<9;i++)
		            for(j=0;j<9;j++)
				      array[i][j]=Integer.parseInt(String.valueOf(x.charAt(ctr++)));
	    
	             return;	}

	         }
	         
	         smart(0,-1,8,8,1,0,2,0);   East
	         smart(8,1,0,0,-1,8,2,0);   West
	         smart(8,1,0,8,1,0,1,0); South
	         smart(0,-1,8,8,1,0,1,0); Mirror vertical
	         smart(8,1,0,0,-1,8,1,0); Mirror Horizontal
		 */
}