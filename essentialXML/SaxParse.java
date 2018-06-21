// Copyright 2012 Prof. Lixin Tao, Pace University, ltao@pace.edu
// This program parses an XML file specified on the command-line to see whether it is well-formed.
// If an XML Schema or DTD file is also specified on command line or in the XML file, 
// the XML file will be validated against the XML Schema or DTD file.
// This program uses a SAX parser. Another version of this program, "DomParse.java",
// do the same thing using a DOM parser.
// To compile this program, run "javac Saxparse.java"
// To run this program on command-line, run "java SaxParse [xmlFile].xml  [xsd-or-DTD-file] [-print]"
//   where the optional xsd-or-DTD-file is either an XSD (XML Schema) file with name extension ".xsd", or 
//   a DTD file with name extension ".dtd". Use command-line switch "-print" to print the XML file.
// To use this class in other projects, use the following pattern:
//   SaxParse  sax = new SaxParse();
//   sax.parse(xmlFileName, xsdFileNames, dtdFileName);  
//      xsdFileNames is an array of XSD file names; use null if no XSD file is used;
//      dtdFileName is null if DTD file is not used.

import java.io.*;
import java.util.*;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.ext.LexicalHandler;

import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;

public class SaxParse extends DefaultHandler
  implements LexicalHandler {
  StringBuffer textBuffer;
  static boolean validateDTD = false;     // validating parse with DTD
  static boolean validateXSD = false;     // validating parse with XML Schema
  static boolean internal = false;        // DTD file must be specified in the XML file
                          // internal = true iff DTD or Schema is specified in XML file
  static boolean isPrettyPrint = false;   // Whether pretty-printing the XML file as output
  static String xmlFileName = null;
  static String dtdFileName = null;
  static String[] xsdFileNames = null;
  static boolean isValidating = false;    // whether this parsing also validates against DTD or XSD
  
  static final String JAXP_SCHEMA_LANGUAGE =
        "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
  static final String W3C_XML_SCHEMA =
        "http://www.w3.org/2001/XMLSchema";
  static final String JAXP_SCHEMA_SOURCE =
        "http://java.sun.com/xml/jaxp/properties/schemaSource";
        
  static Vector<String> nsPrefixVector = new Vector<String>();
        
  public static void main(String args[]) {
  	String[] xsdFileNameBuffer = null;
  	if (args.length > 1)
  	  xsdFileNameBuffer = new String[args.length-1]; // support multiple XSD files
  	int xsd_i = 0;
    for (int i = 0; i < args.length; i++) {
    	String name = args[i]; 
    	if (name.toLowerCase().endsWith(".xml")) {
    		xmlFileName = name;
    		continue;
    	}
    	if (name.toLowerCase().endsWith(".xsd")) {
    		xsdFileNameBuffer[xsd_i++] = name;
    		continue;
    	}
    	if (name.toLowerCase().endsWith(".dtd")) {
    		dtdFileName = name;
    		continue;
    	}
    	if (name.toLowerCase().equals("-print"))
    	  isPrettyPrint = true;
    }
    if (xmlFileName == null) {
      System.err.println("Usage: java SaxParse XML-file [XSDs-or-DTD-file] [-print]");
      System.exit(1);
    }
    if (xsd_i > 0) {
    	xsdFileNames = new String[xsd_i];
      for (int i = 0; i < xsd_i; i++) xsdFileNames[i] = xsdFileNameBuffer[i];
    }
    new SaxParse().parse(xmlFileName, xsdFileNames, dtdFileName);
  }
  
  // Parse xmlFileName for well-formedness, or validate xmlFileName against xsdFileName
  // or dtdFileName,
  // or validate xmlFileName against XSDs or DTD specified in xmlFileName
  boolean parse(String xmlFileName, String xsdFileName, String dtdFileName) {
  	String[] xsdFileNames = {xsdFileName};
  	return parse(xmlFileName, xsdFileNames, dtdFileName);
  }
  	
  // Parse xmlFileName for well-formedness, or validate xmlFileName against all XSD files in xsdFileNames
  // or dtdFileName,
  // or validate xmlFileName against XSDs or DTDs specified in xmlFileName
  boolean parse(String xmlFileName, String[] xsdFileNames, String dtdFileName) {
    isValidating = false;
    boolean hasError = false; 
    SaxParse.xmlFileName = xmlFileName;
    SaxParse.dtdFileName = dtdFileName;
    if (dtdFileName != null) {
    	isValidating = true;
    	validateDTD = true;
    	internal = false;
    }
    File xmlFile = new File(xmlFileName);
    SaxParse.xsdFileNames = xsdFileNames;
    if ((xsdFileNames != null) && (xsdFileNames[0] != null) && xsdFileNames[0].toLowerCase().endsWith(".xsd")) {
    	isValidating = true;
    	validateXSD = true;
    	internal = false;
    }
    SAXParserFactory factory = SAXParserFactory.newInstance();
    if (isValidating = (isValidating || isValidating(xmlFile))) { 
      factory.setValidating(true);
      System.out.println("<!------- Validation is on ----------->");
    }
    else
      System.out.println("<!------- Validation is off ----------->");
    if (isPrettyPrint)
      System.out.println("<!------------- Output --------------->");
    factory.setNamespaceAware(true);
    try {
      // Set up output stream
      out = new OutputStreamWriter(System.out, "UTF8");

      // Parse the input
      SAXParser saxParser = factory.newSAXParser();
      
      if (validateXSD)
        saxParser.setProperty(JAXP_SCHEMA_LANGUAGE, W3C_XML_SCHEMA);
 
      if (validateXSD && !internal) // Schema file specified on command line
        saxParser.setProperty(JAXP_SCHEMA_SOURCE,	xsdFileNames);
      
      XMLReader xmlReader = saxParser.getXMLReader();
      xmlReader.setProperty("http://xml.org/sax/properties/lexical-handler", this); // this is also a lexical handler      
      if (dtdFileName != null) {
        byte[] b = TransformUtility.addDoctype(xmlFileName, dtdFileName).getBytes();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(b, 0, b.length);
        saxParser.parse(inputStream, this);
      }
      else 
        saxParser.parse(xmlFile, this);

    } catch (SAXParseException spe) {
      // Error generated by the parser
      hasError = true;
      System.out.println("\n** Parsing error"
        + ", line " + spe.getLineNumber()
        + ", uri " + spe.getSystemId());
      System.out.println("   " + spe.getMessage() );
      
      // Use the contained exception, if any
      Exception  x = spe;
      if (spe.getException() != null)
        x = spe.getException();
      x.printStackTrace();

    } catch (SAXException sxe) {
      // Error generated by this application
      // (or a parser-initialization error)
      hasError = true;
      Exception  x = sxe;
      if (sxe.getException() != null)
        x = sxe.getException();
      x.printStackTrace();

    } catch (ParserConfigurationException pce) {
      // Parser with specified options can't be built
      hasError = true;
      pce.printStackTrace();

    } catch (IOException ioe) {
      // I/O error
      hasError = true;
      ioe.printStackTrace();

    } catch (Throwable t) {
       hasError = true;
       t.printStackTrace();
    }
    if (isValidating) {
      if (!hasError) {
        System.out.println("\n---The document is well-formed and its validation has succeeded---\n");
        return true;
      }
      else {
        System.out.println("\n---Validation failed---\n");
        return false;
      }
    }
    if (!isValidating) {
      if (!hasError) {
        System.out.println("\n---The document is well-formed---\n");
        return true;
      }
      else {
        System.out.println("\n---The document is not well-formed---\n");
        return false;
      }
    }	
    return false;
  }
  
  // Does the XML file specify internally DTD or Schema file for validation?
  static boolean isValidating(File xmlFile) { 
  	boolean hasDOCTYPE = false; 
    internal = false;  // schema or DTD file may be specified in the XML file
  	// check whether the top 20 lines of the XML file have "chemaLocation=", or "<!DOCTYPE"
  	// and "SYSTEM" or "<!ELEMENT" ("<!DOCTYPE" could be used for defining entities only, then no validation)
  	try {
  		BufferedReader br = new BufferedReader(new FileReader(xmlFile));
  		String line = br.readLine().toLowerCase();
			for (int i = 0; (line != null) && (i < 20); i++) {
  			if (line.indexOf("chemalocation=") != -1)
  			  validateXSD = true;
  			if (line.indexOf("<!doctype") != -1)
  			  hasDOCTYPE = true;
  			if (hasDOCTYPE && (line.indexOf("<!element") != -1))
  			  validateDTD = true;
  			if (hasDOCTYPE && (line.indexOf("system") != -1))
  			  validateDTD = true;
  			if (validateXSD || validateDTD) {
  				internal = true;
  				return true;
  			}
  			line = br.readLine().toLowerCase();
  		}
  	}
  	catch (Exception e){} 
  	return false;
  }
    
 
  static private Writer  out;
  private String indentString = "    "; // Amount to indent
  private int indentLevel = -1;

  //===========================================================
  // SAX DocumentHandler methods
  //===========================================================

  public void setDocumentLocator(Locator l) {}

  public void startDocument() throws SAXException {
    if (isPrettyPrint) emit("<?xml version='1.0' encoding='UTF-8'?>");
  }

  public void endDocument() throws SAXException {
    try {
      nl();
      out.flush();
    } catch (IOException e) {
      throw new SAXException("I/O error", e);
    }
  }

  public void startElement(String namespaceURI,
                           String sName, // simple name
                           String qName, // qualified name
                           Attributes attrs)
  throws SAXException {
    echoText();
    String eName = qName; // element name
    //if ("".equals(eName)) eName = sName; // not namespaceAware
   	indentLevel++;
    nl(); 
    emit("<"+eName);
    Iterator j = nsPrefixVector.iterator();
    while (j.hasNext()) 
      emit("\n   " + j.next());  
    if (attrs != null) {
      for (int i = 0; i < attrs.getLength(); i++) {
        String aName = attrs.getQName(i); // Attr name 
        if ("".equals(aName)) aName = attrs.getLocalName(i);
        nl();
        emit("   ");
        emit(aName);
        emit("=\"");
        emit(attrs.getValue(i));
        emit("\"");
      }
    }
    if (attrs.getLength() + nsPrefixVector.size() > 0) nl();
    emit(">");    
    nsPrefixVector.clear();
  }
  
  public void endElement(String namespaceURI,
                         String sName, // simple name
                         String qName  // qualified name
                        )
  throws SAXException {
    String eName = qName; // element name
    //if ("".equals(eName)) eName = sName; // not namespaceAware
   	echoText();
    nl();
    emit("</"+eName+">");
    indentLevel--;
  }

  public void startPrefixMapping(String prefix, String uri) throws SAXException {
  	// emit("\n\t xmlns:" + prefix + "='" + uri + "'");
  	nsPrefixVector.add("xmlns:" + prefix + "='" + uri + "'");
  }
                          
  public void endPrefixMapping(String prefix) throws SAXException {
	  // emit("\n end ns prefix mapping for " + prefix);
  }
                      
  public void characters(char buf[], int offset, int len)
  throws SAXException {
    String s = new String(buf, offset, len);
    if (textBuffer == null) {
      textBuffer = new StringBuffer(s);
    } else {
      textBuffer.append(s);
    }
  }
  
  public void ignorableWhitespace(char buf[], int offset, int len)
  throws SAXException {
    // Ignore it
  }

  public void processingInstruction(String target, String data)
  throws SAXException {
    indentLevel++;
    nl();
    //emit("PROCESS: ");
    emit("<?"+target+" "+data+"?>");
    indentLevel--;
  }

  //===========================================================
  // SAX ErrorHandler methods
  //===========================================================

  // treat validation errors as fatal
  public void error(SAXParseException e) throws SAXParseException {
    throw e;
  }

  // dump warnings too
  public void warning(SAXParseException err) throws SAXParseException {
    System.out.println("** Warning"
          + ", line " + err.getLineNumber()
          + ", uri " + err.getSystemId());
    System.out.println("   " + err.getMessage());
  }

  //===========================================================
  // LexicalEventListener methods
  //===========================================================
    
  public void comment(char[] ch, int start, int length) throws SAXException {}

  public void startCDATA() throws SAXException {
    echoText(); // echo anything we've seen before now
    nl(); 
    //emit("START CDATA SECTION");
  }

  public void endCDATA() throws SAXException {
    echoText(); // echo the CDATA text
    nl(); 
    //emit("END CDATA SECTION");
  }

  public void startEntity(java.lang.String name) throws SAXException {
    //echoText(); // echo anything we've seen before now
    //nl(); 
    //emit("START ENTITY: "+name);
  }

  public void endEntity(java.lang.String name) throws SAXException {
    //echoText(); // echo the ENTITY text
    //nl(); 
    //emit("END ENTITY: "+name);
  }
  
  public void startDTD(String name, String publicId, String systemId)
  throws SAXException {
    //nl(); 
    //emit("START DTD: "+name
    //        +"\n           publicId=" + publicId
    //        +"\n           systemId=" + systemId);
  }
  
  public void endDTD() throws SAXException {
    //nl(); 
    //emit("END DTD");
  }

  //===========================================================
  // Utility Methods ...
  //===========================================================
  
  // Display text accumulated in the character buffer
  private void echoText() throws SAXException {
    if (textBuffer == null) return;   
   	nl(); 
    emit("    ");
    String s = "" + textBuffer;
    if (!s.trim().equals("")) emit(s);
      textBuffer = null;
  }

  // Wrap I/O exceptions in SAX exceptions, to
  // suit handler signature requirements
  private void emit(String s) throws SAXException {
  	if (!isPrettyPrint) return;
    try {
      out.write(s);
      out.flush();
    } catch (IOException e) {
      throw new SAXException("I/O error", e);
    }
  }

  // Start a new line
  // and indent the next line appropriately
  private void nl() throws SAXException {
  	if (!isPrettyPrint) return;
    String lineEnd = System.getProperty("line.separator");
    try {
      out.write(lineEnd);
      for (int i=0; i < indentLevel; i++) out.write(indentString);
    } catch (IOException e) {
      throw new SAXException("I/O error", e);
    }
  }
}
