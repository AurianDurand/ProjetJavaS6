package sample.parser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class TMXParser {

    public Layers Parse(String directory, String file) {

        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            final DocumentBuilder builder = factory.newDocumentBuilder();
            final Document document = builder.parse(new File(directory + file));
            final Element eRoot = document.getDocumentElement();

            try {
                int width = Integer.parseInt(eRoot.getAttribute("width"));
                int height = Integer.parseInt(eRoot.getAttribute("height"));
                int tileheight = Integer.parseInt(eRoot.getAttribute("tileheight"));
                int tilewidth = Integer.parseInt(eRoot.getAttribute("tilewidth"));

                Layers layers = new Layers(width, height, tilewidth, tileheight);

                final NodeList cTileset = eRoot.getElementsByTagName("tileset");
                List<Tile> allTiles = new ArrayList<Tile>();

                for(int i = 0; i < cTileset.getLength(); i++) {
                    Element eTileset = (Element)cTileset.item(i);
                    String source = eTileset.getAttribute("source");
                    int firstgid = Integer.parseInt(eTileset.getAttribute("firstgid"));

                    TSXParser tsx = new TSXParser();
                    allTiles.addAll(tsx.Parse(directory + source, firstgid));
                }

                final NodeList cLayer = eRoot.getElementsByTagName("layer");
                for(int i = 0; i < cLayer.getLength(); i++) {
                    Element eLayer = (Element)cLayer.item(i);
                    Element eData = (Element)eLayer.getElementsByTagName("data").item(0);

                    String rawData = eData.getTextContent().replaceAll("\\s+","");
                    String[] randeredData = rawData.split(",");

                    Tile[][] layer = new Tile[height][width];
                    int posX = 0;
                    int posY = 0;
                    for(String gid : randeredData){
                        boolean isTileFound = false;
                        for(Tile tile : allTiles){
                            if(tile.getGid() == Integer.parseInt(gid)){
                                isTileFound = true;
                                layer[posY][posX] = tile;
                                break;
                            }
                        }
                        if(!isTileFound) layer[posY][posX] = null;
                        posX++;
                        if(posX >= width){
                            posX = 0;
                            posY++;
                        }
                    }

                    layers.add(layer);
                }

                return layers;
            }
            catch (NumberFormatException nfe){
                System.out.println(nfe.getMessage());
                return null;
            }
        }
        catch (final ParserConfigurationException e){
            e.printStackTrace();
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
