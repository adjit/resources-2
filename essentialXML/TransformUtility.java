import java.io.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;

public class TransformUtility {
	// Convert DOM doc into a string
	public static String dom2string(Document doc) {
  	try {
  	  TransformerFactory tfactory = TransformerFactory.newInstance();
      Transformer transformer = tfactory.newTransformer();
      DOMSource src = new DOMSource(doc);
      StringWriter writer = new StringWriter();
      Result result = new StreamResult(writer);
      transformer.transform(src, result);
      return writer.toString();
    } catch(Exception e) {}
    return "";
  }
	
	// Insert/update in xmlFilename DOCTYPE for DTD file location dtdFilename
	public static String addDoctype(String xmlFilename, String dtdFilename) throws Exception {
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
				.newInstance();
		DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
		Document xmlDocument = builder.parse(new FileInputStream(xmlFilename));
		DOMSource source = new DOMSource(xmlDocument);		
		StringWriter writer = new StringWriter();
		Result result = new StreamResult(writer);
		TransformerFactory tfactory = TransformerFactory.newInstance();
		Transformer transformer = tfactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, dtdFilename);
		transformer.transform(source, result);
		return writer.getBuffer().toString();
	}
}