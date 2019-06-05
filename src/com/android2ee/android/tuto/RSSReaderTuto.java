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

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

/**
 * @author (Julien PAPUT sous la direction du Dr. Mathias Séguy)
 * @goals
 * This class aims to:
 * <ul><li>Display the RSS feeds</li></ul>
 */
public class RSSReaderTuto extends Activity implements OnItemClickListener{
	/******************************************************************************************/
	/** Attributes **************************************************************************/
	/******************************************************************************************/
	
	//the URL of the RSS feed
	public final String RSSURL = "http://blog.developpez.com/xmlsrv/rss2.php?blog=389";
	
	//THe RSS feed
	private RSSFeed feed = null;
	
	//the list of the news
	private List<Data> myItems;

	//the hashmpa that links Title and content of Rss post
	ArrayList<HashMap<String, String>> listItem = new ArrayList<HashMap<String, String>>();
	
	/** Called when the activity is first created. */
    @Override
	public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.main);
        
        // get the feed
        feed = getFeed(RSSURL);

        // display all the RSS
        displayRSS();
        
    }

    //Parse and Get the RSS Feed
    private RSSFeed getFeed(String urlToRssFeed)
    {
    	try
    	{
    	   // setup the url
    	   URL url = new URL(urlToRssFeed);

           // create the parser factory
           SAXParserFactory factory = SAXParserFactory.newInstance();
           // create a parser. The parser will parse the XML file of the RSS feed
           SAXParser parser = factory.newSAXParser();

           // create the reader (scanner)
           XMLReader xmlreader = parser.getXMLReader();
           // instantiate our handler
           RSSParser theRssHandler = new RSSParser();
           // assign our handler
           xmlreader.setContentHandler(theRssHandler);
           // get our data via the url class
           InputSource is = new InputSource(url.openStream());
           // perform the synchronous parse           
           xmlreader.parse(is);
           // get the results - should be a fully populated RSSFeed instance, or null on error
           //les handlers ca marche pas comme ca bordel!
           return theRssHandler.getFeed();
    	}
    	catch (Exception ee)
    	{
    		return null;
    	}
    }
    
    //display the RSS
    private void displayRSS()
    {
  
        ListView itemlist = (ListView) findViewById(R.id.itemlist);

        HashMap<String, String> map;

        //Get all items into an aray
        myItems = feed.getAllItems();
        
        //For each item in array, put it in the hashmap ...
        for(Data rss : myItems){
        	map = new HashMap<String, String>();
        	map.put("titre", rss.getTitle());
        	map.put("description", rss.getDescription());
        	//and add the hashmap to the list
        	listItem.add(map);
        }
        
        //create the adapter, which take a context, the list and a layout at parameters 
        SimpleAdapter mSchedule = new SimpleAdapter (this.getBaseContext(), listItem, R.layout.item,
                new String[] {"titre", "description"}, new int[] {R.id.titre, R.id.description});

        //set an adapter to the listeview
        itemlist.setAdapter(mSchedule);
        
        //set an itemclickListener to the listView
        itemlist.setOnItemClickListener(this);
        
        itemlist.setSelection(0);
        
    }
    
    //When user clic on an Item
     @Override
	public void onItemClick(AdapterView parent, View v, int position, long id)
     {
    	
    	 //Instanciation of an intent
    	 Intent myIntent = new Intent().setClass(this,ShowRSS.class);
         
    	 //my a bundle with the feed information
    	 Bundle b = new Bundle();
    	 b.putString("title", feed.getItem(position).getTitle());
    	 b.putString("description", feed.getItem(position).getDescription());
    	 b.putString("link", feed.getItem(position).getLink());
    	 b.putString("pubdate", feed.getItem(position).getPubDate());
    	 
    	 //put the bundle into the intent
    	 myIntent.putExtra("android.intent.extra.INTENT", b);
    	
    	 //start the activity
    	 startActivity(myIntent);
    	 
     }
}