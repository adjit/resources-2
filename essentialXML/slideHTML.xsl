<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet 
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
  version="1.0"
  >
  <xsl:output method="html"/> 

  <xsl:template match="/">
    <html><body>
       <xsl:apply-templates/>
    </body></html>
  </xsl:template>

  <xsl:template match="/slideshow">
    <h1 align="center"> <xsl:value-of select="@title"/> </h1>
    <h3 align="center"> <em><xsl:value-of select="@author"/></em> </h3>
    <h3 align="center"> <xsl:value-of select="@date"/> </h3>
    <hr/>
    <xsl:apply-templates select="slide"/>
  </xsl:template>

  <xsl:template match="slide">
    <xsl:apply-templates select="slide-title"/>
    <p align="center"> (<xsl:value-of select="@type"/>) </p>
    <ul>
    <xsl:apply-templates select="item"/>
    </ul>
    <hr/>
  </xsl:template>
  
  <xsl:template match="slide-title">
    <p align="center"><b> <xsl:value-of select="."/> </b></p>
  </xsl:template>
  
  <xsl:template match="item">
    <li> <xsl:apply-templates/> </li>
  </xsl:template>
  
  <xsl:template match="text()">
    <xsl:value-of select="."/>
  </xsl:template>
  
  <xsl:template match="em|b|br">
    <xsl:element name="{name()}">
       <xsl:apply-templates/>
    </xsl:element> 
  </xsl:template>
  
  <xsl:template match="diagram">
    <pre> <xsl:value-of select="."/> </pre>
  </xsl:template>
  
  <xsl:template match="list">
    <xsl:if test="@type='ordered'">
      <ol>
      <xsl:apply-templates/>
      </ol>
    </xsl:if>
    <xsl:if test="@type='unordered'">
      <ul>
      <xsl:apply-templates/>
      </ul>
    </xsl:if>
  </xsl:template>
  
  <xsl:template match="link">
    <xsl:variable name="empty_string"/>
    <xsl:if test="normalize-space(.) != $empty_string">
      <!--Target name specified.-->
      <xsl:call-template name="htmLink">
      	<xsl:with-param name="url" select="@target"/>
      	<xsl:with-param name="dest">
          <xsl:apply-templates/>  <!--Destination value = text of node-->
        </xsl:with-param>
      </xsl:call-template>
    </xsl:if>

    <xsl:if test="normalize-space(.) = $empty_string">
      <!--Target attribute not specified.-->
      <xsl:call-template name="htmLink">
        <xsl:with-param name="url" select="@target"/>
        <xsl:with-param name="dest" select="@target"/>
      </xsl:call-template>
    </xsl:if>
  </xsl:template>

  <!-- A named template that constructs an HTML link -->
  <xsl:template name="htmLink">
    <xsl:param name="url" select="UNDEFINED"/>
    <xsl:param name="dest" select="UNDEFINED"/> <!--default value-->
    <xsl:element name="a">
      <xsl:attribute name="href">
        <xsl:value-of select="$url"/> <!--link target-->
      </xsl:attribute>
      <xsl:value-of select="$dest"/>
    </xsl:element> 
  </xsl:template>
 
</xsl:stylesheet>


