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

/**
 * @author (Julien PAPUT sous la direction du Dr. Mathias Séguy)
 * @goals
 * This class aims to:
 * <ul><li>transform the feed into model object</li></ul>
 */
public class Data {
	/******************************************************************************************/
	/** Attributes **************************************************************************/
	/******************************************************************************************/
	
	//the title of the news
	private String _title = null;
	//the description of the news
    private String _description = null;
    //the link of the news
    private String _link = null;
    //the categry of the news
    private String _category = null;
    //The plublication Date of the news
    private String _pubdate = null;
  
    Data()
    {
    }
    
    //set the title
    void setTitle(String title)
    {
        _title = title;
    }
    
    //set the description
    void setDescription(String description)
    {
        _description = description;
    }
    
    //set the link
    void setLink(String link)
    {
        _link = link;
    }
    
    //set the category
    void setCategory(String category)
    {
        _category = category;
    }
    
    //set the publication date
    void setPubDate(String pubdate)
    {
        _pubdate = pubdate;
    }
    
    //get the article title
    String getTitle()
    {
        return _title;
    }
    
    //get the article description
    String getDescription()
    {
        return _description;
    }
    
    //get the article link
    String getLink()
    {
        return _link;
    }
    
    //get the article category
    String getCategory()
    {
        return _category;
    }
    
    //get the publication date
    String getPubDate()
    {
        return _pubdate;
    }
    
    //if the string is over 25 caractère make "..."
    @Override
	public String toString()
    {
        // limit how much text you display
        if (_title.length() > 25)
        {
            return _title.substring(0, 25) + "...";
        }
        return _title;
    }
}
