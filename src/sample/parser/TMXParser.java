package sample.parser;

import java.io.File;
import java.io.IOException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class TMXParser {

    public TMXParser(String directory, String file) {
        this.Parse(directory, file);
    }

    public boolean Parse(String directory, String file) {
        System.out.println(file);

        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            final DocumentBuilder builder = factory.newDocumentBuilder();
            final Document document = builder.parse(new File(directory + file));
            final Element eRoot = document.getDocumentElement();

            try {
                int width = Integer.parseInt(eRoot.getAttribute("width"));
                int height = Integer.parseInt(eRoot.getAttribute("height"));
                int tilewidth = Integer.parseInt(eRoot.getAttribute("tilewidth"));
                int tileheight = Integer.parseInt(eRoot.getAttribute("height"));

                final NodeList childs = eRoot.getElementsByTagName("tileset");

                for(int i = 0; i < childs.getLength(); i++) {
                    Element eNode = (Element)childs.item(i);
                    TSXParser tsx = new TSXParser();
                    tsx.Parse(directory + eNode.getAttribute("source"));
                }

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
