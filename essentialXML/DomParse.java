// Copyright 2012 Prof. Lixin Tao, Pace University, ltao@pace.edu
// This program parses an XML file specified on the command-line to see whether it is well-formed.
// If an XML Schema or DTD file is also specified on command line or in the XML file, 
// the XML file will be validated against the XML Schema or DTD file.
// This program uses a DOM parser. Another version of this program, "Saxparse.java",
// do the same thing using a SAX parser.
// To compile this program, run "javac DomParse.java"
// To run this program on command-line, run "java DomParse [xmlFile].xml  [xsd-or-DTD-file] [-print]"
//   where the optional xsd-or-DTD-file is either an XSD (XML Schema) file with name extension ".xsd", or 
//   a DTD file with name extension ".dtd". Use command-line switch "-print" to print the XML file.
// To use this class in other projects, use the following pattern:
//   DomParse  dom = new DomParse();
//   dom.parse(xmlFileName, xsdFileNames, dtdFileName);  
//      xsdFileNames is an array of XSD file names; use null if no XSD file is used;
//      dtdFileName is null if DTD file is not used.

import javax.xml.parsers.DocumentBuilder; 
import javax.xml.parsers.DocumentBuilderFactory;  
import javax.xml.parsers.ParserConfigurationException; 
 
import org.xml.sax.SAXException;  
import org.xml.sax.SAXParseException;  
import org.xml.sax.ErrorHandler;

import java.io.*;
import org.w3c.dom.Document;

public class DomParse {
  StringBuffer textBuffer;
  static boolean validateDTD = false;     // validating parse with DTD
  static boolean validateXSD = false;     // validating parse with XML Schema
  static boolean internal = false;        // DTD file must be specified in the XML file
                          // internal = true iff DTD or Schema is specified in XML file
  static boolean isPrettyPrint = false;   // Whether pretty-printing the XML file as output
  static Document document;               // Output DOM tree
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
    	if (name.toLowerCase().equals("-print")) {
    	  isPrettyPrint = true;
    	}
    }
    if (xmlFileName == null) {
      System.err.println("Usage: java DomParse XML-file [XSDs-or-DTD-file] [-print]");
      System.exit(1);
    }
    DomParse dp = new DomParse();
    if (xsd_i > 0) {
    	xsdFileNames = new String[xsd_i];
      for (int i = 0; i < xsd_i; i++) xsdFileNames[i] = xsdFileNameBuffer[i];
    }
    dp.parse(xmlFileName, xsdFileNames, dtdFileName);
    if (isPrettyPrint) 
      System.out.println(TransformUtility.dom2string(document));
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
    DomParse.xmlFileName = xmlFileName;
    DomParse.dtdFileName = dtdFileName;
    if (dtdFileName != null) {
    	isValidating = true;
    	validateDTD = true;
    	internal = false;
    }
    File xmlFile = new File(xmlFileName);
    DomParse.xsdFileNames = xsdFileNames;
    if ((xsdFileNames != null) && (xsdFileNames[0] != null) && xsdFileNames[0].toLowerCase().endsWith(".xsd")) {
    	isValidating = true;
    	validateXSD = true;
    	internal = false;
    }
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
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
      if (validateXSD)
        factory.setAttribute(JAXP_SCHEMA_LANGUAGE, W3C_XML_SCHEMA);
      if (validateXSD && !internal) // Schema file specified on command line
        factory.setAttribute(JAXP_SCHEMA_SOURCE, xsdFileNames);
      DocumentBuilder builder = factory.newDocumentBuilder();
      builder.setErrorHandler(new MyErrorHandler());
      if (dtdFileName != null) {
        byte[] b = TransformUtility.addDoctype(xmlFileName, dtdFileName).getBytes();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(b, 0, b.length);
        document = builder.parse(inputStream);
      }
      else 
        document = builder.parse(xmlFile);
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
  
  public Document getDom() {
  	return document;
  }
}

class MyErrorHandler implements ErrorHandler {
  public void fatalError(SAXParseException e) throws SAXParseException {
    throw e;
  }  
  
  // treat validation errors as fatal
  public void error(SAXParseException e) throws SAXParseException {
    throw e;
  }

  // dump warnings
  public void warning(SAXParseException err) throws SAXParseException {
    System.out.println("** Warning"
          + ", line " + err.getLineNumber()
          + ", uri " + err.getSystemId());
    System.out.println("   " + err.getMessage());
  }
}