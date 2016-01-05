package com.sarath.sv17;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.CheckBox;
import android.widget.Chronometer;
import android.widget.Toast;
import android.os.SystemClock;

public class Gameplay extends Activity {
    /** Called when the activity is first created. */
	
    Chronometer mChronometer;
	
	int sudoku_array_game[][]=new int[9][9];
	int check_array[][]=new int[9][9];
	Button numarray_button[]=new Button[10];
	int array_button_dialog[]=new int[10];
	TextView display_textview_array[]=new TextView[81];
	EditText display_EditText_array[]=new EditText[81];
	String x;
	View v2;
	int to_return=0, id1, toggle=0, flag_done=0;
	int cell_id;
	long elapsedMillis=0;
	  
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_play); 
         
        EditText num00=(EditText) findViewById(R.id.num00);
        cell_id=num00.getId();
        mChronometer = (Chronometer) findViewById(R.id.chronometer1);
        x=getIntent().getExtras().getString("question");
        start();
        
        CheckBox chkIos = (CheckBox) findViewById(R.id.checkBox1);
        chkIos.setOnClickListener(new OnClickListener() 
        { 	  @Override
    	  public void onClick(View v)
       	  {
            if (((CheckBox) v).isChecked())
             	toggle=1;
    		else
    			toggle=0;
       	  }
    	});
        
    }
     public int check_between()
     { 
    	 int flag=0;
    	 capturescreen();
    	  	for(int i=0;i<9;i++)
    		   for(int j=0;j<9;j++)
    			  if(check_array[i][j]==0&&sudoku_array_game[i][j]!=0)
    				if(!(val(sudoku_array_game[i][j],i,j)))	
    				   flag++;
    				   
     	 return flag;
     }
     
     public boolean val(int num,int r,int c)
 	{
 		int ctr=0;
 		for(int i=0;i<9;i++)
 		   if(sudoku_array_game[r][i]==num)
 		     ctr++;
 		if(ctr!=1)
 		   return false;
 		ctr=0;
 		
 		for(int i=0;i<9;i++)
 		  if(sudoku_array_game[i][c]==num)
 		     ctr++;
 		if(ctr!=1)
 		   return false;
 		ctr=0;
 		
 		r = (r / 3) * 3 ;
         c = ( c/ 3) * 3 ;
         for( int i = 0;i<3;i++ )
           for( int j = 0;j<3;j++ )
            if(sudoku_array_game[r+i][c+j]==num)
              ctr++;
         if(ctr!=1)
 		   return false;
 		   
         return true ;
 	}
     public void done()
 	{ 
    	 flag_done=0;
 		for(int i=0;i<9;i++)
 			for(int j=0;j<9;j++)
 				if(sudoku_array_game[i][j]==0)
 				{
 					flag_done=1;
 				    return;
 				}
 			
 		for(int i=0;i<9;i++)
 			for(int j=0;j<9;j++)
 				if(check_array[i][j]==0)
 				   if(!(val(sudoku_array_game[i][j],i,j)))	
 				     flag_done=2;
 					   
 	  return;
 	}
     
     public void popup_done()
     {
    	 stop_timer();
    	    final Context mContext = this;
	    	final Dialog dialog = new Dialog(mContext);
	       	dialog.setContentView(R.layout.highscore);
	       	dialog.setTitle("                Congratulations");
	    	dialog.show();
	    	RelativeLayout rl1=(RelativeLayout) dialog.findViewById(R.id.done);
	    	rl1.setOnClickListener(new View.OnClickListener()
	           {   
	        	  public void onClick(View view) 
	               {	
	        		        dialog.dismiss();
	        		        back_to_main();
	                }  
	        	});
	    	int min=(int)(elapsedMillis/60000);
	    	int sec=(int)((elapsedMillis/1000)-min*60);
	    	TextView pop_do=(TextView) dialog.findViewById(R.id.textView1);
	    	pop_do.setText("You have solved the puzzle in \n"+min+" Minute(s) "+sec+" Second(s).");
	 	   
	    	reset_timer();
	    	return;
	}
     
     public void back_to_main()
     {
    	 AlertDialog.Builder builder = new AlertDialog.Builder(this);
		  builder.setMessage("Next is what?")
	       .setCancelable(false)
	       .setPositiveButton("Main menu", new DialogInterface.OnClickListener() {
	           public void onClick(DialogInterface dialog, int id) {
	        	  finish();	
	           }
	       })
	       .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
	           public void onClick(DialogInterface dialog, int id) {
	                dialog.cancel();
	           }
	       })
	       .setNeutralButton("New game", new DialogInterface.OnClickListener() {
	           public void onClick(DialogInterface dialog, int id) {
	        	   Intent myIntent = new Intent(Gameplay.this, GameEngine.class);
	               startActivity(myIntent);  
	               finish();
	           }
	       });
		  AlertDialog alert = builder.create();
		  alert.show();
		  
		  return;
     }
    public void start()
    {
    	start_timer();
    	put_table();
        setup_display_textview();
        setup_edittext_listener();
        display();
        
        Button button1=(Button)findViewById(R.id.button_solve);
        button1.setOnClickListener(new OnClickListener()
		{
		   @Override
		   public void onClick(View v)
		   {
		     registerForContextMenu(v); 
	         openContextMenu(v);
	         unregisterForContextMenu(v);
		}});
    	
        Button button2=(Button)findViewById(R.id.reset);
		button2.setOnClickListener(new OnClickListener()
		{
		  @Override
		  public void onClick(View v)
		  {
			 capturescreen();
			 done();
			 popup_alert();
		     
	      }});    
		
		Button pause=(Button)findViewById(R.id.pause);
		pause.setOnClickListener(new OnClickListener()
		{
		   @Override
		   public void onClick(View v)
		   {
			  stop_timer();
		      pause_pop();
			}});
		
		Button bback=(Button)findViewById(R.id.b_back);
		bback.setOnClickListener(new OnClickListener()
		{
		   @Override
		   public void onClick(View v)
		   {
			    alerts(1);
			  }});
     }
    
    public void alerts(int code)
    {
    	final int cd=code;
    	String show="";
    	String positive="Yes";
    	String negative="No";
    	
    	if(code==1)
    		show="Back, are you sure?";
    	if(code==2)
    		show="Restart, are you sure?";
    	if(code==3)
    		show="New Game, are you sure?";
    	if(code==4)
    		show="Quit, are you sure?";
    	    	
    		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		  builder.setMessage(show)
	       .setCancelable(false)
	       .setPositiveButton(positive, new DialogInterface.OnClickListener() {
	           public void onClick(DialogInterface dialog, int id) 
	           
	           {
	        	  if(cd==1||cd==4) finish();	
	        	  if(cd==2) 
	        	  {
	        	     reset_timer();
   	        	     start();
	        	  }
	        	  if(cd==3)
	        	  {
	        		Intent myIntent = new Intent(Gameplay.this, GameEngine.class);
   	                startActivity(myIntent);  
   	                finish();
	        	  }
	        	  
	           }
	       })
	       .setNegativeButton(negative, new DialogInterface.OnClickListener() {
	           public void onClick(DialogInterface dialog, int id) {
	                dialog.cancel();
	           }
	       });
		  AlertDialog alert = builder.create();
		  alert.show();
		  return;
    }
    
    public void start_timer()
    {
    	int stoppedMilliseconds = 0;
        String chronoText = mChronometer.getText().toString();
        String array[] = chronoText.split(":");
        if (array.length == 2) 
        {
          stoppedMilliseconds = Integer.parseInt(array[0]) * 60 * 1000
              + Integer.parseInt(array[1]) * 1000;
        }
        else if (array.length == 3)
        {
          stoppedMilliseconds = Integer.parseInt(array[0]) * 60 * 60 * 1000 
              + Integer.parseInt(array[1]) * 60 * 1000
              + Integer.parseInt(array[2]) * 1000;
        }
        mChronometer.setBase(SystemClock.elapsedRealtime() - stoppedMilliseconds);
        mChronometer.start();
    }
    
    public void stop_timer()
    {
    	mChronometer.stop();
    	 elapsedMillis = SystemClock.elapsedRealtime()- mChronometer.getBase();
    }
    
    public void reset_timer()
    {
    	mChronometer.setBase(SystemClock.elapsedRealtime());
        mChronometer.stop();
    }
    
    @Override  
    public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo)
    {  
      super.onCreateContextMenu(menu, v, menuInfo);  
      menu.setHeaderTitle("Action");  
      menu.add(0, v.getId(), 0, "Restart Current Game");  
      menu.add(0, v.getId(), 0, "New Game"); 
      menu.add(0, v.getId(), 0, "Check");  
      menu.add(0, v.getId(), 0, "Quit");  
      menu.add(0, v.getId(), 0, "Cancel");  
   }  
    public void popup_alert()
    {
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
      if(flag_done==1||flag_done==2)
      {
    	if(flag_done==1)
    	{
		  builder.setMessage("The table is not complete.")
		  .setCancelable(false)
	       .setPositiveButton("Back to the game", new DialogInterface.OnClickListener() {
	           public void onClick(DialogInterface dialog, int id) {
	                dialog.cancel();
	           }
	       });
    	}
		if(flag_done==2)
		{
		  builder.setMessage("Sorry, the solution is incorrect.")
		  .setCancelable(false)
	       .setPositiveButton("Back to the game", new DialogInterface.OnClickListener() {
	           public void onClick(DialogInterface dialog, int id) {
	                dialog.cancel();
	           }
	       });
		}
		
	  AlertDialog alert = builder.create();
	  alert.show();
      }
      if(flag_done==0)
		{
			popup_done();
			return;
		}
    }
    
    @Override  
    public boolean onContextItemSelected(MenuItem item) 
    {  
    	
        if(item.getTitle()=="Restart Current Game")	alerts(2);
        else if(item.getTitle()=="New Game") alerts(3);
        else if(item.getTitle()=="Cancel") 	return false;      	      
        else if(item.getTitle()=="Quit") alerts(4);
   	         	         
        else if(item.getTitle()=="Check")
        {
        	AlertDialog.Builder builder = new AlertDialog.Builder(this);
        	int errors=check_between();
        	if(errors==0)
        	{       		
        	    builder.setMessage("So far so good.")
        	       .setCancelable(false)
        	       .setPositiveButton("Back to the game", new DialogInterface.OnClickListener() {
        	           public void onClick(DialogInterface dialog, int id) {
        	        	   dialog.cancel();
        	           }
        	       });
        	}
        	if(errors>0)
        	{
        		 	    builder.setMessage("You have made "+errors+" error(s).")
        	       .setCancelable(false)
        	       .setPositiveButton("Back to the game", new DialogInterface.OnClickListener() {
        	           public void onClick(DialogInterface dialog, int id) {
        	        	   dialog.cancel();
        	           }
        	 
        	       });
           	}
        	 AlertDialog alert = builder.create();
         	alert.show();
       } 
      
       else
       {
         return false;
        }  
      
    return true;  
    }  
    
    public void pause_pop()
    {
    	final Context mContext = this;
    	final Dialog dialog = new Dialog(mContext);
       	dialog.setContentView(R.layout.poppause);
    	dialog.setTitle("Paused");
    	dialog.show();
    	
    	Button back=(Button) dialog.findViewById(R.id.resume);
    	back.setOnClickListener(new View.OnClickListener()
    	{   @Override
            public void onClick(View view)
            { 	 
            	dialog.dismiss(); 
            	start_timer();
            }   
    	});
    	
    	return;
   }
    
    public void setup_edittext_listener()
    {
    	final AbsoluteLayout a1=(AbsoluteLayout) findViewById(R.id.abs2);
        for(int i=0;i<81;i++)
        { 
            v2 = a1.getChildAt(i);
            if(v2 instanceof EditText)
            {
                EditText a2=(EditText) v2;
                a2.setOnClickListener(new View.OnClickListener() 
                {
 		             @Override
 		             public void onClick(View view) {
 		            	int ids=view.getId();
 		            	if(toggle==1)
 		            	   check1(ids);
 		            	if(toggle==0)
 		                   	for(int i1=0;i1<10;i1++)
 		           		        array_button_dialog[i1]=0;
 		               numBoard(ids);   
 		        }  
 		    }); 
         }     
       }  
     }
    
    public void dialog_function_display()
    {
    	final AbsoluteLayout a3=(AbsoluteLayout) findViewById(R.id.abs2);
    	int diff=id1-cell_id;
        TextView temp=(TextView) a3.getChildAt(diff);
        temp.setTextColor(0xff993333);
        if(to_return!=0)
        temp.setText(String.valueOf(to_return)); 
        if(to_return==0)
        temp.setText(String.valueOf("")); 
        capturescreen();
        
        return;
    }
    
    public void check1(int ids1)
    {   
    	int i2=0;
        int xstart=0,ystart=0;
    	display();
    	int cellcode=ids1-cell_id;
    	int xx1=(int)(cellcode/9);
    	int xx2=cellcode-(9*xx1);
    	for(int i1=0;i1<10;i1++)
    		array_button_dialog[i1]=0;
    	
        i2=xx1;
        for(int j2=0;j2<9;j2++)
           array_button_dialog[sudoku_array_game[i2][j2]]=1;
        
         i2=xx2;
        for(int j2=0;j2<9;j2++)
         	array_button_dialog[sudoku_array_game[j2][i2]]=1;
        
        if(xx1<3) xstart=0;
        if(xx1>2&&xx1<6) xstart=3;
        if(xx1>5) xstart=6;
        if(xx2<3) ystart=0;
        if(xx2>2&&xx2<6) ystart=3;
        if(xx2>5) ystart=6;
        
        for(int k=xstart;k<xstart+3;k++)
         	for(int k1=ystart;k1<ystart+3;k1++)
        		array_button_dialog[sudoku_array_game[k][k1]]=1;
        	
       return; 
   }
    
    public void capturescreen()
    { 
    	setup_display_edittext();
    	int ctr=0;
    	for(int i=0;i<9;i++)
		    for(int j=0;j<9;j++)
			   sudoku_array_game[i][j]=returnstr(display_EditText_array[ctr++].getText().toString());
    
     return;
    }
    
    public int returnstr(String input1)
    {
    	if(input1.length()==0)
    		return 0;
    	else
    		return Integer.parseInt(input1);
    }
    
    public void numBoard(int id2)
    {
    	id1=id2;
    	final Context mContext = this;
    	final Dialog dialog = new Dialog(mContext);
      	dialog.setContentView(R.layout.keyboard);
    	dialog.setTitle("NumBoard");
    	dialog.show();
    	numarray_button[0]=(Button) dialog.findViewById(R.id.num0);
    	numarray_button[1]=(Button) dialog.findViewById(R.id.num1);
    	numarray_button[2]=(Button) dialog.findViewById(R.id.num2);
    	numarray_button[3]=(Button) dialog.findViewById(R.id.num3);
    	numarray_button[4]=(Button) dialog.findViewById(R.id.num4);
    	numarray_button[5]=(Button) dialog.findViewById(R.id.num5);
    	numarray_button[6]=(Button) dialog.findViewById(R.id.num6);
    	numarray_button[7]=(Button) dialog.findViewById(R.id.num7);
    	numarray_button[8]=(Button) dialog.findViewById(R.id.num8);
    	numarray_button[9]=(Button) dialog.findViewById(R.id.num9); 
    	
    	for(int h=1;h<10;h++)
    	   if(array_button_dialog[h]==1)
    			numarray_button[h].setEnabled(false);
    	
    	for(int h=1;h<10;h++)
    		if(array_button_dialog[h]==0)
    			numarray_button[h].setBackgroundResource(R.drawable.b_assist);
    	
    	numarray_button[1].setOnClickListener(new View.OnClickListener() 
    	    {  @Override
               public void onClick(View view)
               {
                   to_return=1;  
                   dialog_function_display();
                   dialog.dismiss();         
              }      
    	    });
    	
    	numarray_button[2].setOnClickListener(new View.OnClickListener() 
    	{    @Override
            public void onClick(View view)  
            {
            	to_return=2; 
            	dialog_function_display();
            	dialog.dismiss();       
            	} 
    	});
    	
    	numarray_button[3].setOnClickListener(new View.OnClickListener()
    	{   @Override
            public void onClick(View view) 
            {
            	to_return=3;    
            	dialog_function_display();
            	dialog.dismiss();            
            	}     
    	});
    	
    	numarray_button[4].setOnClickListener(new View.OnClickListener()
    	{   @Override
            public void onClick(View view)
            {  	to_return=4;   
            	dialog_function_display();
            	dialog.dismiss();     
            	}      
    	});
    	
    	numarray_button[5].setOnClickListener(new View.OnClickListener() 
    	{  @Override
            public void onClick(View view)
            {  	to_return=5;  
            	dialog_function_display();
            	dialog.dismiss(); 
            	}         
    	});
    	
    	numarray_button[6].setOnClickListener(new View.OnClickListener()
    	{   @Override
            public void onClick(View view) 
            {
            	to_return=6;   
            	dialog_function_display();
            	dialog.dismiss();       
            	}  
    	});
    	
    	numarray_button[7].setOnClickListener(new View.OnClickListener() 
    	{    @Override
            public void onClick(View view) 
            {  	to_return=7;  
            	dialog_function_display();
            	dialog.dismiss();        
            	}   	
    	});
    	
    	numarray_button[8].setOnClickListener(new View.OnClickListener() 
    	{    @Override
            public void onClick(View view) 
            { 	to_return=8;  
            	dialog_function_display();
            	dialog.dismiss();   
            	}   
    	});
    	
    	numarray_button[9].setOnClickListener(new View.OnClickListener()
    	{   @Override
            public void onClick(View view)
            { 	 to_return=9;
            	 dialog_function_display();
            	dialog.dismiss();    
            	}               
    	});
    	numarray_button[0].setBackgroundResource(R.drawable.b_eraser);
    	numarray_button[0].setOnClickListener(new View.OnClickListener()
    	{   @Override
            public void onClick(View view)
            { 	 to_return=0;
            	 dialog_function_display();
            	dialog.dismiss();   
            	}    
    	});
        
    	Button back=(Button) dialog.findViewById(R.id.numback);
    	back.setEnabled(false);
    	Button back1=(Button) dialog.findViewById(R.id.numback1);
    	back1.setBackgroundResource(R.drawable.b_x);
    	back1.setOnClickListener(new View.OnClickListener()
    	{   @Override
            public void onClick(View view)
            { 	 
            	dialog.dismiss();  
            	}   
    	});
    	
    	return;
   }
    
    public void setup_display_edittext()
    {       
  	   AbsoluteLayout llMain = (AbsoluteLayout)findViewById(R.id.abs2);
  	   for(int i=0;i<81;i++)
       {
          View v1 = llMain.getChildAt(i);
          if(v1 instanceof EditText)
             display_EditText_array[i]=(EditText) v1; 
         } 
  	   return;
    }
    
    public void put_table()
    {
    	for(int i=0;i<9;i++)
    		for(int j=0;j<9;j++)
    			check_array[i][j]=0;
    	
    	int ctr1=0;
    	for(int i=0;i<9;i++)
    	{
    		for(int j=0;j<9;j++)
    		{
    			sudoku_array_game[i][j]=Integer.parseInt(String.valueOf(x.charAt(ctr1++)));
    			if(sudoku_array_game[i][j]!=0)
    				check_array[i][j]=1;
    		}
    	}
    	return;
    }
    
    public void setup_display_textview()
    {   	
    	AbsoluteLayout llMain = (AbsoluteLayout)findViewById(R.id.abs2);
    	 for(int i=0;i<81;i++)
    	    {
    		    View v = llMain.getChildAt(i);
    		    if(v instanceof TextView)
    	        {
    	        	((TextView)v).setTextColor(0xffbe7b00);
    	           display_textview_array[i]=(TextView) v; 
    	        } 
    		}     
   }
     
    public void display()
    {
    	int ctr=0;   
        for(int i=0;i<9;i++)
        	for(int j=0;j<9;j++)
        	{
        		String string_dis=display_String(sudoku_array_game[i][j]);
        	    display_textview_array[ctr++].setText(string_dis);   
        	}
        return;
    }
    
    public String display_String(int num)
    {
    	String g="";
    	if(num==0)
    	return g;
    	else
    	return String.valueOf(num);
    }
 
}