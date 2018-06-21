                            Example Projects for Essential XML

                                        Lixin Tao
                                     January 22, 2008

Test the Sample XML Projects:

a) Run "java SaxParse threadSlides.xml slideshow.xsd" for validating 
   "threadSlides.xml" against Schema file "slideshow.xsd". Here we specify the 
   Schema file on command line.

b) Run "java SaxParse threadSlidesSchema.xml" for validating 
   "threadSlidesSchema.xml" against Schema file "slideshow.xsd". Here we specify the 
   Schema file within the XML file.

c) Run "java SaxParse NsThreadSlidesSchemaLocal.xml" for validating 
   "NsThreadSlidesSchemaLocal.xml" against local Schema file "NsSlideshow.xsd". 
   Both the Schema definition file and the XML instance file use namespace 
   "http://dps.csis.pace.edu". Here we specify the Schema file within the XML file.

d) Run "java SaxParse NsThreadSlidesSchemaUrl.xml" for validating 
   "NsThreadSlidesSchemaUrl.xml" against Schema file "NsSlideshow.xsd" at URL
   http://csis.pace.edu/~lixin/. 
   Both the Schema definition file and the XML instance file use namespace 
   "http://dps.csis.pace.edu". Here we specify the Schema file within the XML file.
   This validation will work only if you have Internet connection.

e) Run "java SaxParse threadSlidesDTD.xml" for validating "threadSlidesDTD.xml"
   against DTD file "slideshow.dtd". Here we specify the DTD file within
   the XML file. 

f) Run "java SaxParse threadSlides.xml slideshow.dtd" for validating "threadSlides.xml"
   against DTD file "slideshow.dtd". Here we specify the DTD file on the command line. 
   
g) Run "java DomParse threadSlidesSchema.xml" for validating 
   "threadSlidesSchema.xml"
   against Schema file "slideshow.xsd", and presenting a DOM tree of the XML file.
   Here we specify the Schema file within the XML file.

h) Run "java Transform slideHTML.xsl threadSlides.xml" to transform the XML file
   into an HTML file.

i) Open file "threadSlidesXSLT.xml" in Internet Explorer (normally by just clicking
   the file in Windows Explorer) to see the transformed HTML file. Here IE follows 
   the instructions in "slideHTML.xsl", which is specified in the XML file, to 
   transform the XML file into an HTML file on-the-fly. XSLT has been built in IE.
   
j) Run "java SaxParse demoID.xml demoID.dtd" for validating 
   "demoID.xml.xml" against DTD file "demoID.dtd" to learn how ID and IRREFS work. 

k) Run "java SaxParse demoID.xml demoID.xsd" for validating 
   "demoID.xml.xml" against XSD file "demoID.xsd" to learn how ID and IRREFS work. 
   
You can relace "SaxParse" with "DomParse" and the results are the same.

-------------------------------------

Folder "essentialXML" contains the following files:

1) File "lixinXML.jar", which is my XML utilities bundled as a jar file. We are 
   not using it in the following instructions. To use my XML utilities from any
   working directory, this file has been copied to 
   "C:\Program Files\Java\jdk1.6.0_05\jre\lib\endorsed".

2) File "slideshow.dtd", which defines in DTD a simple XML dialect for slide show.

3) File "slideshow.xsd", which is the XML Schema version of "slideshow.dtd".

4) File "NsSlideshow.xsd", which is "slideshow.xsd" but the definitions are in a namespace.

5) File "threadSlides.xml", an XML instance file for "slideshow.dtd" or 
   "slideshow.xsd"

6) File "threadSlidesDTD.xml", the same as "threadSlides.xml" except that it 
   specifies internally to use the DTD file "slideshow.dtd" as its grammar 
   definition.

7) File "threadSlidesSchema.xml", the same as "threadSlides.xml" except that it 
   specifies internally the Schema file "slideshow.xsd" as its grammar definition.

8) File "NsThreadSlidesSchemaLocal.xml", the same as "threadSlides.xml" except that it 
   uses namespace and specifies internally the Schema file "NsSlideshow.xsd" as its 
   grammar definition.

9) File "NsThreadSlidesSchemaUrl.xml", the same as "threadSlides.xml" except that it 
   uses namespace and specifies internally URL 
   "http://csis.pace.edu/~lixin/NsSlideshow.xsd" as its grammar definition.

10) File "slideHTML.xsl", the XSL stylesheet for transforming slide show XML files
    into HTML files.

11) File "threadSlidesXSLT.xml", the same as "threadSlides.xml" except the 
    specification of the XSL file "slideHTML.xsl" as its transformation stylesheet.

12) Source code "SaxParse.java" for your reference. 
    You don't need to fully understand it.

13) Source code "DomParse.java" for your reference. 
    You don't need to fully understand it.

14) Source code "Transform.java" for your reference. 
    You don't need to fully understand it.