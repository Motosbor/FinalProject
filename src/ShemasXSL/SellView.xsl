<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html"/>
    <xsl:template match="xmlCollector">
        <html>
            <head>
                <link rel="stylesheet" href="src/styles/TableStyle.css"/>
            </head>
            <body>
                <h1>Users</h1>
                <table class="table">
                    <tbody>
                        <xsl:for-each select="objectList">
                            <tr>
                                <td>
                                    <xsl:value-of select="sellerId"></xsl:value-of>
                                </td>
                                <td>
                                    <xsl:value-of select="seller"></xsl:value-of>
                                </td>
                                <td>
                                    <xsl:value-of select="customer"></xsl:value-of>
                                </td>
                                <td>
                                    <xsl:value-of select="product"></xsl:value-of>
                                </td>
                                <td>
                                    <xsl:value-of select="count"></xsl:value-of>
                                </td>
                                <td>
                                    <xsl:value-of select="totalPrice"></xsl:value-of>
                                </td>
                                <td>
                                    <xsl:value-of select="dateOfSell"></xsl:value-of>
                                </td>
                            </tr>
                        </xsl:for-each>
                    </tbody>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>