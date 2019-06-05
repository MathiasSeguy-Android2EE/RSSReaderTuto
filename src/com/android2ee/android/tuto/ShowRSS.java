/**
* <ul>
* Android Tutorial, An <strong>Android2EE</strong>'s project.</br>
* Produced by <strong>Dr. Mathias SEGUY</strong> with the smart contribution of <strong>Julien PAPUT</strong>.</br>
* Delivered by <strong>http://android2ee.com/</strong></br>
* Belongs to <strong>Mathias Seguy</strong></br>
* ****************************************************************************************************************</br>
* This code is free for any usage but can't be distribute.</br>
* The distribution is reserved to the site <strong>http://android2ee.com</strong>.</br>
* The intelectual property belongs to <strong>Mathias Seguy</strong>.</br>
* <em>http://mathias-seguy.developpez.com/</em></br>
* </br>
* For any information (Advice, Expertise, J2EE or Android Training, Rates, Business):</br>
* <em>mathias.seguy.it@gmail.com</em></br>
* *****************************************************************************************************************</br>
* Ce code est libre de toute utilisation mais n'est pas distribuable.</br>
* Sa distribution est reservée au site <strong>http://android2ee.com</strong>.</br>
* Sa propriété intellectuelle appartient à la société <strong>ST Informatique Services</strong>.</br>
* <em>http://mathias-seguy.developpez.com/</em></br>
* </br>
* Pour tous renseignements (Conseil, Expertise, Formations J2EE ou Android, Prestations, Forfaits):</br>
* <em>mathias.seguy.it@gmail.com</em></br>
* *****************************************************************************************************************</br>
* Les meilleurs des Ingénieurs Java-J2EE nous rejoignent pour participer avec nous à la grande aventure</br>
* de l’informatique des prochaines décennies.</br>
* N'hésitez pas à nous contacter.</br>
* *****************************************************************************************************************</br>
* com.android2ee.android.tuto</br>
* 25 mars 2011</br>
*/
package com.android2ee.android.tuto.rssreader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

/**
 * @author (Julien PAPUT sous la direction du Dr. Mathias Séguy)
 * @goals
 * This class aims to:
 * <ul><li>show the selected RSS</li></ul>
 */
public class ShowRSS extends Activity {
	
	 @Override
	public void onCreate(Bundle icicle) 
	    {
	        super.onCreate(icicle);
	        setContentView(R.layout.showrss);
	        
	        String myFeed = null;
	        
	        //get the intent that launch this
	        Intent myIntent = getIntent();
	        WebView browser=(WebView)findViewById(R.id.htmlView);
	        
	        //If the intent is not null, use its data
	        if (myIntent != null)
	        {
	        	//retrieve the data send with the intent
	        	Bundle b = myIntent.getBundleExtra("android.intent.extra.INTENT");
	        	//Make the content of the String
	        	myFeed = b.getString("title") + "\n\n" + b.getString("author") +  b.getString("pubdate") + "\n\n" + b.getString("description").replace('\n',' ') + "\n\nMore information:\n" + b.getString("link");
	        	browser.loadUrl(b.getString("link"));
	        }
	        //else put "information not found" in the string
	        else
	        {
	        	myFeed = "Information Not Found.";
	        
	        }
	        
	        TextView db= (TextView) findViewById(R.id.storybox);
	        //Set the text of the textview
	        db.setText(myFeed);
	        
	       
	        
	        Button backbutton = (Button) findViewById(R.id.back);
	        
	        //Set an onClickListener on the Button
	        backbutton.setOnClickListener(new Button.OnClickListener() 
	        {
	            @Override
				public void onClick(View v) 
	            {
	            	//if the user push the button, back to the RSS list
	            	finish();
	            }
	        });        
	    }
	 
}
