# Aupamya
package epubPackage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import javax.annotation.Resources;
import org.kxml2.kdom.Document;
import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.Resource;
import nl.siegmann.epublib.domain.TOCReference;
import nl.siegmann.epublib.domain.TableOfContents;
import nl.siegmann.epublib.epub.EpubReader;
import nl.siegmann.epublib.service.MediatypeService;
class ZipFiles
{
   public void DownloadResource(String directory) {
    
    	try
    	{       
    	EpubReader epubReader = new EpubReader();   
    	Book book = epubReader.readEpub(new FileInputStream("C:\\Final_project\\epubBook\\soul-life.epub"));  
    	nl.siegmann.epublib.domain.Resources rst = book.getResources();
        Collection<Resource> clrst = ((nl.siegmann.epublib.domain.Resources) rst).getAll();
        Iterator<Resource> itr = clrst.iterator();
        System.out.println("Downlod path"+ directory);
        while (itr.hasNext()) {
            Resource rs = itr.next();
            if ((rs.getMediaType() == MediatypeService.JPG) || (rs.getMediaType() == MediatypeService.PNG) || (rs.getMediaType() == MediatypeService.GIF)
                    || (rs.getMediaType() == MediatypeService.CSS)) {
                File oppath1 = new File(directory + File.separator + rs.getHref());
                oppath1.getParentFile().mkdirs();

                System.out.println("Resource Name - "+ rs.getHref());
                oppath1.createNewFile();
                System.out.println("Oppath - "+ oppath1.getAbsolutePath());

                System.out.println("File Checking - "+ "Exists - " + oppath1.exists() + " & Write - " + oppath1.canWrite());
                FileOutputStream fos1 = new FileOutputStream(oppath1);
                fos1.write(rs.getData());
                fos1.close();

            } 

        }
    	}
     catch (IOException e) {
    	 System.out.println("error"+ e.getMessage());
    }
   }
}
