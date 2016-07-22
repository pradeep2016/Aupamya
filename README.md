# Aupamya
package EpubPackage;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
//import java.util.*;
import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.Resource;
import nl.siegmann.epublib.domain.TOCReference;
import nl.siegmann.epublib.domain.TableOfContents;
import nl.siegmann.epublib.epub.EpubReader;
import com.opensymphony.xwork2.ActionSupport;
public class Demo extends ActionSupport
{	
	private String str;
	public String getStr() {
		return str;
	}

	public void setStr(String str)
	{
		this.str = str;
	}
	public String execute() throws Exception 
	  {		  
	    //str="hello";
	    //str=GetSpineReader("C:\\Final_project\\epubBook\\kathryn-seifert-soul-life.epub");		
		//String htmlString="";		
		try
		{		   
		EpubReader epubReader = new EpubReader();   
		Book book = epubReader.readEpub(new FileInputStream("C:\\Final_project\\epubBook\\soul-life.epub"));  
		//Book book = epubReader.readEpub(new FileInputStream(epubfilepath));  
		System.out.println(book.getTitle());			
		String line=null;
		System.out.println("TOC: "+book.getTableOfContents());
	    int spineSize = book.getSpine().size();
	    System.out.println("spine size: "+spineSize);
		for(int i=0;i<spineSize;i++)
		{		
		InputStream s=book.getSpine().getSpineReferences().get(i).getResource().getInputStream();
		BufferedReader reader=new BufferedReader(new InputStreamReader(s));
		StringBuilder sb=new StringBuilder();	
		while((line=reader.readLine())!=null)
		{
		  sb.append(line+"\n");	
		}	
		//htmlString=sb.toString().replaceAll("\\<.*?>", "");	
		str=sb.toString();
		s.close();
		//System.out.println(htmlString);
		}	
		}
		catch(IOException e)
		{
		 e.printStackTrace();
		}
	   return "success";
	  }
	
//***********************************************************************
// to get content of epub reader
//-----------------------------------------------------------------------
/*	
public String GetSpineReader(String epubfilepath)
 {
	String htmlString="";		
	try
	{		   
	EpubReader epubReader = new EpubReader();   
	//Book book = epubReader.readEpub(new FileInputStream("C:\\Final_project\\epubBook\\gunroom.epub"));  
	Book book = epubReader.readEpub(new FileInputStream(epubfilepath));  
	System.out.println(book.getTitle());			
	String line=null;
	System.out.println("TOC: "+book.getTableOfContents());
    int spineSize = book.getSpine().size();
    System.out.println("spine size: "+spineSize);
	for(int i=0;i<spineSize;i++)
	{		
	InputStream s=book.getSpine().getSpineReferences().get(i).getResource().getInputStream();
	BufferedReader reader=new BufferedReader(new InputStreamReader(s));
	StringBuilder sb=new StringBuilder();	
	while((line=reader.readLine())!=null)
	{
	  sb.append(line+"\n");	
	}	
	//htmlString=sb.toString().replaceAll("\\<.*?>", "");	
	htmlString=sb.toString();
	s.close();
	//System.out.println(htmlString);
	}	
	}
	catch(IOException e)
	{
	 e.printStackTrace();
	}
	return htmlString;
}
*/
}
