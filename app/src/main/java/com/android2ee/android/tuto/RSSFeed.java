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
import java.util.List;
import java.util.Vector;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

/**
 * @author (Julien PAPUT sous la direction du Dr. Mathias Séguy)
 * @goals
 * This class aims to:
 * <ul><li>Manage the RSS feed</li></ul>
 */
public class RSSFeed {
	
	/******************************************************************************************/
	/** Attributes **************************************************************************/
	/******************************************************************************************/
	
	//the Title of the news
	private String _title = null;
	
	//the publication date of the news
    private String _pubdate = null;
    
    //The number of news
    private int _itemcount = 0;
    
    //the list of news
    private final List<Data> _itemlist;
    
    RSSFeed()
    {
        _itemlist = new Vector(0); 
    }
    
    //to add an item (news) to the list
    int addItem(Data item)
    {
        _itemlist.add(item);
        _itemcount++;
        return _itemcount;
    }
    
    
    /******************************************************************************************/
	/** Getters & Setters **************************************************************************/
	/******************************************************************************************/
   
    //get the item
    Data getItem(int location)
    {
        return _itemlist.get(location);
    }
    
    //Get all items
    List getAllItems()
    {
        return _itemlist;
    }
    
    //get the number of item
    int getItemCount()
    {
        return _itemcount;
    }
    
    //set a title
    void setTitle(String title)
    {
        _title = title;
    }
    
    //set a publication date
    void setPubDate(String pubdate)
    {
        _pubdate = pubdate;
    }
    
    //get the title
    String getTitle()
    {
        return _title;
    }
    
    //get the publication date
    String getPubDate()
    {
        return _pubdate;
    }
}
