package sample.parser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class TSXParser {

    public TSXParser()
    {

    }

    public TSXParser(String pathFile)
    {
        this.Parse(pathFile);
    }

    public boolean Parse(String pathFile)
    {
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            final DocumentBuilder builder = factory.newDocumentBuilder();
            final Document document = builder.parse(new File(pathFile));
            final Element eRoot = document.getDocumentElement();

            try {

            }
            catch (NumberFormatException nfe){
                System.out.println(nfe.getMessage());
                return false;
            }
        }
        catch (final ParserConfigurationException e){
            e.printStackTrace();
            return false;
        }
        catch (final SAXException e){
            e.printStackTrace();
            return false;
        }
        catch (final IOException e){
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
