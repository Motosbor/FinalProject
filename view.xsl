<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html"/>
    <xsl:template match="allUsers">
        <html>
            <head>
                <link rel="stylesheet" href="style.css"/>
            </head>
            <body>
                <h1>Users</h1>
                <table class="table">
                    <thead>
                        <tr>
                            <td>Имя</td>
                            <td>Фамилия</td>
                            <td>Логин</td>
                            <td>Пароль</td>
                        </tr>
                    </thead>
                    <tbody>
                        <xsl:for-each select="Users">
                            <tr>
                                <td>
                                    <xsl:value-of select="name"></xsl:value-of>
                                </td>
                                <td>
                                    <xsl:value-of select="lastName"></xsl:value-of>
                                </td>
                                <td>
                                    <xsl:value-of select="login"></xsl:value-of>
                                </td>
                                <td>
                                    <xsl:value-of select="password"></xsl:value-of>
                                </td>
                            </tr>
                        </xsl:for-each>
                    </tbody>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>