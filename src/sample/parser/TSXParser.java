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
import java.util.ArrayList;
import java.util.List;

public class TSXParser {

    public List<Tile> Parse(String pathFile, int firstgid)
    {
        List<Tile> tiles = new ArrayList<Tile>();
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            final DocumentBuilder builder = factory.newDocumentBuilder();
            final Document document = builder.parse(new File(pathFile));
            final Element eRoot = document.getDocumentElement();

            try {
                final Element eImage = (Element) eRoot.getElementsByTagName("image").item(0);

                int tilewidth = Integer.parseInt(eRoot.getAttribute("tilewidth"));
                int tileheight = Integer.parseInt(eRoot.getAttribute("tileheight"));
                int width = Integer.parseInt(eImage.getAttribute("width"));
                int height = Integer.parseInt(eImage.getAttribute("height"));
                String source = eImage.getAttribute("source");

                int columns = width / tilewidth;
                int rows = height / tileheight;

                for(int i = 0; i < rows; i++){
                    for(int j = 0; j < columns; j++){
                        tiles.add(new Tile(firstgid, source, j, i, width, height));
                        firstgid++;
                    }
                }

                final NodeList cTile = eRoot.getElementsByTagName("tile");
                for(int i = 0; i < cTile.getLength(); i++) {
                    Element eTile = (Element) cTile.item(i);

                    int id = Integer.parseInt(eTile.getAttribute("id"));
                    String type = eTile.getAttribute("type");

                    if(id >= 0 && id < tiles.size()){
                        tiles.get(id).setType(type);
                    }
                }

                return tiles;
            }
            catch (NumberFormatException nfe){
                System.out.println(nfe.getMessage());
                return null;
            }
        }
        catch (final ParserConfigurationException e){
            e.printStackTrace();
            tiles.clear();
        }
        catch (final SAXException e){
            e.printStackTrace();
        }
        catch (final IOException e){
            e.printStackTrace();
        }

        return null;
    }
}
