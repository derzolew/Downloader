package com.maxvi.parsers;

import com.maxvi.models.DownloadObject;
import com.maxvi.models.DownloadObjectArray;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class XmlParser implements IParsable {

    private static final String ELEMENT_LINK = "link";
    private static final String ELEMENT_URL = "url";
    private static final String ELEMENT_NAME = "name";

    @Override
    public DownloadObjectArray parseFile(final String filePath) {

        final Document doc = getDocument(filePath);
        final NodeList nodeList;
        DownloadObjectArray downloadObjectArray = null;
        if (doc != null) {
            nodeList = doc.getElementsByTagName(ELEMENT_LINK);
            final int elementsNumber = nodeList.getLength();
            final DownloadObject[] downloadObjects = new DownloadObject[elementsNumber];

            for (int i = 0; i < elementsNumber; i++) {
                final Element element = (Element) nodeList.item(i);
                downloadObjects[i] = new DownloadObject();
                downloadObjects[i].setUrl(element.getElementsByTagName(ELEMENT_URL)
                        .item(0).getTextContent());
                downloadObjects[i].setName(element.getElementsByTagName(ELEMENT_NAME)
                        .item(0).getTextContent());
            }
            downloadObjectArray = new DownloadObjectArray();
            downloadObjectArray.setLinksArray(downloadObjects);
        }
        return downloadObjectArray;
    }

    private Document getDocument(final String docString) {
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setIgnoringComments(true);
        factory.setIgnoringElementContentWhitespace(true);
        try {
            final DocumentBuilder builder = factory.newDocumentBuilder();
            return builder.parse(docString);
        } catch (ParserConfigurationException | SAXException | IOException pE) {
            pE.printStackTrace();
        }
        return null;
    }
}
