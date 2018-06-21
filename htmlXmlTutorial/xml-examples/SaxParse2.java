import java.io.*;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.*;

public class SaxParse2 {
  static final String JAXP_SCHEMA_LANGUAGE =
        "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
  static final String W3C_XML_SCHEMA =
        "http://www.w3.org/2001/XMLSchema";
        
  public static void main(String argv[]) throws Exception {
    if (argv.length < 1 || argv.length > 1) {
      System.err.println("Usage: java SaxParse1 XML-file");
      System.exit(1);
    }
    try {
      SAXParserFactory factory = SAXParserFactory.newInstance();
      factory.setValidating(true);
      factory.setNamespaceAware(true);
      SAXParser saxParser = factory.newSAXParser();
      saxParser.setProperty(JAXP_SCHEMA_LANGUAGE, W3C_XML_SCHEMA);
      saxParser.parse(new File(argv[0]), new Handler());
    } catch (SAXParseException spe) {
      System.out.println("\n** Parsing error"
        + ", line " + spe.getLineNumber()
        + ", uri " + spe.getSystemId());
      System.out.println("   " + spe.getMessage());
    }
    System.exit(0);
  }
}

class Handler extends DefaultHandler {
  private int indentLevel = -1;

  public void startDocument() throws SAXException {
    System.out.println("<?xml version='1.0' encoding='UTF-8'?>");
  }
  
  public void startElement(String namespaceURI,
                           String sName, // simple name
                           String qName, // qualified name
                           Attributes attrs)
  throws SAXException {
    //echoText();
    String eName = sName; // element name
    if ("".equals(eName)) eName = qName; // not namespaceAware
   	indentLevel++;
    nl(); 
    System.out.print("<"+eName);
    if (attrs != null) {
      for (int i = 0; i < attrs.getLength(); i++) {
        String aName = attrs.getLocalName(i); // Attr name 
        if ("".equals(aName)) aName = attrs.getQName(i);
        //System.out.println();
        System.out.print("  ");
        System.out.print(aName);
        System.out.print("=\"");
        System.out.print(attrs.getValue(i));
        System.out.print("\"");
      }
    }
    //if (attrs.getLength() > 0) System.out.println();
    System.out.print(">");
  }
  
  public void endElement(String namespaceURI,
                         String sName, // simple name
                         String qName  // qualified name
                        )
  throws SAXException
  {
    String eName = sName; // element name
    if ("".equals(eName)) eName = qName; // not namespaceAware
   	//echoText();
    //System.out.println();
    nl();
    System.out.print("</"+eName+">");
    indentLevel--;
  }
  
  public void characters(char buf[], int offset, int len)
  throws SAXException {
    String s = new String(buf, offset, len);
    if (s.trim().equals("")) return;
    indentLevel++;
    nl();
    System.out.print(s);
    indentLevel--;
  }
  
  // Start a new line
  // and indent the next line appropriately
  private void nl() {
    System.out.println();
    for (int i=0; i < indentLevel; i++) System.out.print("   ");
  }
  
    //===========================================================
  // SAX ErrorHandler methods
  //===========================================================

  // treat validation errors as fatal
  public void error(SAXParseException e)
  throws SAXParseException
  {
    throw e;
  }

  // dump warnings too
  public void warning(SAXParseException err)
  throws SAXParseException
  {
    System.out.println("** Warning"
          + ", line " + err.getLineNumber()
          + ", uri " + err.getSystemId());
    System.out.println("   " + err.getMessage());
  }
}
