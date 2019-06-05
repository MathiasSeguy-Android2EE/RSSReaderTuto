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
* Sa propriété intellectuelle appartient à <strong>Mathias Séguy</strong>.</br>
* <em>http://mathias-seguy.developpez.com/</em></br>
* </br>
* Pour tous renseignements (Conseil, Expertise, Formations J2EE ou Android, Prestations, Forfaits):</br>
* <em>mathias.seguy.it@gmail.com</em></br>
* *****************************************************************************************************************</br>
* Merci à vous d'avoir confiance en Android2EE les Ebooks de programmation Android.
* N'hésitez pas à nous suivre sur twitter: http://fr.twitter.com/#!/android2ee
* N'hésitez pas à suivre le blog Android2ee sur Developpez.com : http://blog.developpez.com/android2ee-mathias-seguy/
* *****************************************************************************************************************</br>
* com.android2ee.android.tuto</br>
* 25 mars 2011</br>
*/
package com.android2ee.android.tuto.rssreader;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.util.Log;

/**
 * @author (Julien PAPUT sous la direction du Dr. Mathias Séguy)
 * @goals This class aims to:
 *        <ul>
 *        <li>Parse the XML file</li>
 *        </ul>
 */
public class RSSParser extends DefaultHandler {

	/******************************************************************************************/
	/** Attributes **************************************************************************/
	/******************************************************************************************/
	// the RSS feed
	RSSFeed _feed;
	// a news
	Data _item;
	// the last node name
	String _lastElementName = "";
	boolean bFoundChannel = false;

	// constante
	final int RSS_TITLE = 1;
	final int RSS_LINK = 2;
	final int RSS_DESCRIPTION = 3;
	final int RSS_CATEGORY = 4;
	final int RSS_PUBDATE = 5;

	int depth = 0;
	int currentstate = 0;

	/*
	 * Constructor
	 */
	RSSParser() {
	}

	/*
	 * getFeed - this returns our feed when all of the parsing is complete
	 */
	RSSFeed getFeed() {
		return _feed;
	}

	@Override
	public void startDocument() throws SAXException {
		// initialize our RSSFeed object - this will hold our parsed contents
		_feed = new RSSFeed();
		// initialize the RSSItem object - you will use this as a crutch to grab
		// the info from the channel
		// because the channel and items have very similar entries..
		_item = new Data();

	}

	@Override
	public void startElement(String namespaceURI, String localName, String qName, Attributes atts)
			throws SAXException {
		// next depth step into the xml file
		depth++;

		// if the node name is channel
		if (localName.equals("channel")) {
			currentstate = 0;
			return;
		}

		// if the node name is image
		if (localName.equals("image")) {
			// record our feed data - you temporarily stored it in the item :)
			_feed.setTitle(_item.getTitle());
			_feed.setPubDate(_item.getPubDate());
		}

		// if the node name is item
		if (localName.equals("item")) {
			// create a new item
			_item = new Data();
			return;
		}
		if (localName.equals("title")) {
			currentstate = RSS_TITLE;
			return;
		}

		// if the node name is description
		if (localName.equals("description")) {
			currentstate = RSS_DESCRIPTION;
			return;
		}

		// if the node name is link
		if (localName.equals("link")) {
			currentstate = RSS_LINK;
			return;
		}

		// if the node name is category
		if (localName.equals("category")) {
			currentstate = RSS_CATEGORY;
			return;
		}

		// if the node name is pubdate
		if (localName.equals("pubDate")) {
			currentstate = RSS_PUBDATE;
			return;
		}
		// if you don't explicitly handle the element, make sure you don't wind
		// up erroneously storing a newline or other bogus data into one of our
		// existing elements
		currentstate = 0;
	}

	@Override
	public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
		depth--;
		if (localName.equals("item")) {
			// add our item to the list!
			_feed.addItem(_item);
			return;
		}
	}

	@Override
	public void characters(char ch[], int start, int length) {
		String theString = new String(ch, start, length);
		Log.i("RSSReader", "characters[" + theString + "]");

		switch (currentstate) {
		case RSS_TITLE:
			_item.setTitle(theString);
			currentstate = 0;
			break;
		case RSS_LINK:
			_item.setLink(theString);
			currentstate = 0;
			break;
		case RSS_DESCRIPTION:
			_item.setDescription(theString);
			currentstate = 0;
			break;
		case RSS_CATEGORY:
			_item.setCategory(theString);
			currentstate = 0;
			break;
		case RSS_PUBDATE:
			_item.setPubDate(theString);
			currentstate = 0;
			break;
		default:
			return;
		}

	}
}
