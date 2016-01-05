package com.sarath.sv17;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.content.Context;
import android.content.DialogInterface;
import android.app.Dialog;

public class Sudoku_solver extends Activity{
    
	int sudoku_array[][]=new int[9][9];
	TextView display_textview_array[]=new TextView[81];
	EditText display_EditText_array[]=new EditText[81];
	EditText input_string; 
	 
	int cell_id;
	View v2;
	int to_return=0, id1;
	Button numarray_button[]=new Button[10];
	int array_button_dialog[]=new int[10];

	   
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {   super.onCreate(savedInstanceState);
        setContentView(R.layout.sudoku_solver);
        setup_display_textview();
        EditText num00=(EditText) findViewById(R.id.num00);
        cell_id=num00.getId();
        start();
     }
     
    public void setup_display_textview()
    {  AbsoluteLayout llMain = (AbsoluteLayout)findViewById(R.id.abs2);
    	 for(int i=0;i<81;i++)
    	    {   View v = llMain.getChildAt(i);
    	        if(v instanceof TextView)
    	        { 	((TextView)v).setTextColor(0xffbe7b00);
    	           display_textview_array[i]=(TextView) v;  }}  
    	return;
    }
    
    public void setup_display_edittext()
      {  AbsoluteLayout llMain = (AbsoluteLayout)findViewById(R.id.abs2);
    	 for(int i=0;i<81;i++)
         {  View v1 = llMain.getChildAt(i);
            if(v1 instanceof EditText)
               display_EditText_array[i]=(EditText) v1;    }   
    	 return;
      }
    public void capturescreen()
    { 	setup_display_edittext();
    	int ctr=0;
    	for(int i=0;i<9;i++)
		    for(int j=0;j<9;j++)
			   sudoku_array[i][j]=returnstr(display_EditText_array[ctr++].getText().toString());
       
    	return;
    }
    public void check1(int ids1)
    {   int i2=0;
        int xstart=0,ystart=0;
    	int cellcode=ids1-cell_id;
    	int xx1=(int)(cellcode/9);
    	int xx2=cellcode-(9*xx1);
    	for(int i1=0;i1<10;i1++)
    		array_button_dialog[i1]=0;
    	 
        i2=xx1;
        for(int j=0;j<9;j++)
          	array_button_dialog[sudoku_array[i2][j]]=1;
        
        i2=xx2;
        for(int j=0;j<9;j++)
          	array_button_dialog[sudoku_array[j][i2]]=1;
        
        if(xx1<3) xstart=0;
        if(xx1>2&&xx1<6) xstart=3;
        if(xx1>5) xstart=6;
        if(xx2<3) ystart=0;
        if(xx2>2&&xx2<6) ystart=3;
        if(xx2>5) ystart=6;
        
        for(int i=xstart;i<xstart+3;i++)
           	for(int j=ystart;j<ystart+3;j++)
        	    array_button_dialog[sudoku_array[i][j]]=1;
        
        return;
     }
    
    public void setup_edittext_listener()
    {  final AbsoluteLayout a1=(AbsoluteLayout) findViewById(R.id.abs2);
        for(int i=0;i<81;i++)
        {   v2 = a1.getChildAt(i);
            if(v2 instanceof EditText)
            {   EditText a2=(EditText) v2;
                a2.setOnClickListener(new View.OnClickListener() {
 		             @Override
 		             public void onClick(View view) {
 		            	int ids=view.getId();
 		            	check1(ids);
 		            	numBoard(ids);  }     });   }     } 
         return;
      }
    
    public void display()
    { 	int ctr=0;   
        for(int i=0;i<9;i++)
        	for(int j=0;j<9;j++)
        	   display_textview_array[ctr++].setText(String.valueOf(sudoku_array[i][j]));  
        
        return;
       }
    
    public void start()
    { 	for(int i=0;i<9;i++)
           for(int j=0;j<9;j++)
    	   	   sudoku_array[i][j]=0;
    	   
        setup_edittext_listener();
    	Button button1=(Button)findViewById(R.id.button_solve);
		button1.setOnClickListener(new OnClickListener()
		{	@Override
		   public void onClick(View v)
			{
		        start_solve();	
		}});
    	
        Button button2=(Button)findViewById(R.id.reset);
		button2.setOnClickListener(new OnClickListener()
		{	@Override
			public void onClick(View v)
			{
			reset(); }});  
		Button quit=(Button) findViewById(R.id.bback1);
        quit.setOnClickListener(new OnClickListener()
      		{   @Override
      			public void onClick(View v) {
      			quit_ask();
      			    			
                 }}); 
        return;
		}
    public void quit_ask()
    {
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
		  builder.setMessage("Back, are you sure?")
	       .setCancelable(false)
	       .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
	           public void onClick(DialogInterface dialog, int id) {
	        	  finish();	
	           }
	       })
	       .setNegativeButton("No", new DialogInterface.OnClickListener() {
	           public void onClick(DialogInterface dialog, int id) {
	                dialog.cancel();
	           }
	       });
		  
		  AlertDialog alert = builder.create();
		  alert.show();
		  return;
    }
    
    public void dialog_function_display()
    {	final AbsoluteLayout a3=(AbsoluteLayout) findViewById(R.id.abs2);
    	int diff=id1-cell_id;
      	TextView temp=(TextView) a3.getChildAt(diff);
      	if(to_return!=0) temp.setText(String.valueOf(to_return)); 
      	if(to_return==0) temp.setText(String.valueOf("")); 
      	capturescreen();
      	return;
    }
     
    public void numBoard(int id2)
    {  	id1=id2;
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
               {   to_return=1;  
                   dialog_function_display();
                   dialog.dismiss();         }        });
    	  
    	numarray_button[2].setOnClickListener(new View.OnClickListener() 
    	{   @Override
            public void onClick(View view) 
            { 	to_return=2; 
            	dialog_function_display();
            	dialog.dismiss();         }    	});
    	
    	numarray_button[3].setOnClickListener(new View.OnClickListener()
    	{   @Override
            public void onClick(View view) 
            {  	to_return=3;    
            	dialog_function_display();
            	dialog.dismiss();      }    });
    	
    	numarray_button[4].setOnClickListener(new View.OnClickListener()
    	{   @Override
            public void onClick(View view)
            {  	to_return=4;   
            	dialog_function_display();
            	dialog.dismiss();  }     });
    	    	
    	numarray_button[5].setOnClickListener(new View.OnClickListener() 
    	{   @Override
            public void onClick(View view)
            { 	to_return=5;  
            	dialog_function_display();
            	dialog.dismiss(); }       	});
    	    	
    	numarray_button[6].setOnClickListener(new View.OnClickListener()
    	{   @Override
            public void onClick(View view) 
            {  	to_return=6;   
            	dialog_function_display();
            	dialog.dismiss();  	}    	});
    	
    	numarray_button[7].setOnClickListener(new View.OnClickListener() 
    	{  @Override
            public void onClick(View view) 
            {  	to_return=7;  
            	dialog_function_display();
            	dialog.dismiss();    }   	});
    	  	
    	numarray_button[8].setOnClickListener(new View.OnClickListener() 
    	{   @Override
            public void onClick(View view) 
            { 	to_return=8;  
            	dialog_function_display();
            	dialog.dismiss();           }   });
    	 
    	numarray_button[9].setOnClickListener(new View.OnClickListener()
    	{   @Override
            public void onClick(View view)
            { 	to_return=9;
            	dialog_function_display();
            	dialog.dismiss();         	}            });
    	    
    	numarray_button[0].setBackgroundResource(R.drawable.b_eraser);
    	numarray_button[0].setOnClickListener(new View.OnClickListener()
    	{   @Override
            public void onClick(View view)
            { 	 to_return=0;
            	 dialog_function_display();
            	 dialog.dismiss();        	}            });
    	
    	Button back1=(Button) dialog.findViewById(R.id.numback1);
    	back1.setBackgroundResource(R.drawable.b_x);
    	back1.setOnClickListener(new View.OnClickListener()
    	{   @Override
            public void onClick(View view)
            { 	 
            	dialog.dismiss();    }     });
    	
    	Button back=(Button) dialog.findViewById(R.id.numback);
    	back.setEnabled(false);
    	
      	return ;
   }
    
    public void reset()
    {  	setContentView(R.layout.sudoku_solver);
    	setup_display_textview();
    	start();
    	return;
    }
    
    public void start_solve()
    { 	run_solve();
        final  ProgressDialog dialog = ProgressDialog.show(Sudoku_solver.this, "", "Solving.", true);
        new Handler().postDelayed(new Runnable() {
        @Override
        public void run() {
        	dialog.dismiss();
        	display();
           }
    }, 2000);
		
		return;
    }
    
    /*SUDOKU SOLVER*/
    
    public void run_solve()
    {  try
       {   start_finding( 0, 0 ) ;     }
       catch( Exception e )   {   }
    }
    
    public  void next_cell_find( int r, int c ) throws Exception
    {  if( c < 8 )  start_finding( r, c + 1 ) ;
       else         start_finding( r + 1, 0 ) ;
    }
    
    public void start_finding( int r, int c ) throws Exception
    {   if( r > 8 ){
              throw new Exception( "Solution found" ) ;     }
         if(sudoku_array[r][c] != 0 )
          next_cell_find( r, c ) ;
       else
       {  for( int num = 1; num < 10; num++ )
          {  if( checkRow(r,num) && checkCol(c,num) && checkBox(r,c,num) )
             { sudoku_array[r][c] = num ;
               next_cell_find( r, c ) ;      }    }
        sudoku_array[r][c] = 0 ;    }
    }
    
    protected boolean checkBox( int row, int col, int num )
    {  row = (row / 3) * 3 ;
       col = (col / 3) * 3 ;
      for( int r = 0; r < 3; r++ )
          for( int c = 0; c < 3; c++ )
          if(sudoku_array[row+r][col+c] == num )
             return false ;

       return true ;
    }
        
    protected boolean checkRow( int row, int num )
    {  for( int col = 0; col < 9; col++ )
          if( sudoku_array[row][col] == num )
             return false ;

       return true ;
    }

    protected boolean checkCol( int col, int num )
    {  for( int row = 0; row < 9; row++ )
          if( sudoku_array[row][col] == num )
             return false;
 
       return true ;
    }
    
    public int returnstr(String input1)
    {	if(input1.length()==0) return 0;
    	else return Integer.parseInt(input1);
    }
}