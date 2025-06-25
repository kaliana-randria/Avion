#!/bin/bash

# Définition des variables
APP_NAME="GestionAvion2"
SRC_DIR="src"
WEB_DIR="src/webapp"
BUILD_DIR="build"
LIB_DIR="lib"
TOMCAT_WEBAPPS="/opt/homebrew/Cellar/tomcat/11.0.6/libexec/webapps"
SERVLET_API_JAR="$LIB_DIR/servlet-api.jar"

# Nettoyage et création du répertoire temporaire
rm -rf $BUILD_DIR
mkdir -p $BUILD_DIR/WEB-INF/{classes,lib}

# Compilation des fichiers Java avec le JAR des Servlets
find $SRC_DIR -name "*.java" > sources.txt
javac -cp $SERVLET_API_JAR -d $BUILD_DIR/WEB-INF/classes @sources.txt

# Copie du driver PostgreSQL vers WEB-INF/lib
cp $LIB_DIR/postgresql-42.7.4.jar $BUILD_DIR/WEB-INF/lib/

# Copier les fichiers web (web.xml, JSP, etc.)
cp -r $WEB_DIR/* $BUILD_DIR/



# Générer le fichier .war dans le dossier build
cd $BUILD_DIR || exit
jar -cvf $APP_NAME.war *
cd ..

# Déploiement dans Tomcat
cp -f $BUILD_DIR/$APP_NAME.war $TOMCAT_WEBAPPS/

echo ""
echo "Déploiement terminé. Redémarrez Tomcat si nécessaire."
echo ""