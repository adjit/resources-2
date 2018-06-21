import java.io.*;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.*;

public class SaxDtdValidator {
  static final String JAXP_SCHEMA_LANGUAGE =
        "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
  static final String W3C_XML_SCHEMA =
        "http://www.w3.org/2001/XMLSchema";
        
  public static void main(String argv[]) throws Exception {
    if (argv.length != 1) {
      System.err.println("Usage: java SaxXsdValidator XML-file-with-XSD-specified");
      System.exit(1);
    }
    try {
      SAXParserFactory factory = SAXParserFactory.newInstance();
      factory.setValidating(true);
      factory.setNamespaceAware(true);
      SAXParser saxParser = factory.newSAXParser();
      saxParser.parse(new File(argv[0]), new Handler());
    } catch (SAXParseException e) {
      System.out.println("\n** Parsing error"
        + ", line " + e.getLineNumber()
        + ", uri " + e.getSystemId());
      System.out.println("   " + e.getMessage());
      System.exit(0);
    }
    System.out.println("Validation successful");
    System.exit(0);
  }
}

class Handler extends DefaultHandler {
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
