# ProjektVerwaltung

Dieses Projekt entstand im Rahmen der Veranstaltung Software-Engineering an der FH-SWF.

Die Anwendung läuft nach dem Start auf der URL http://localhost:8080.

Achtung, Passwörter werden aufgrund des Demo daseins dieser Software im Klartext gespeichert. Dies sollte vor 
einem Produktivbetrieb unbedingt angepasst werden.

Beispieldaten für eine MariaDB Datenbank werden in der data.sql mitgeliefert.

###Kurze Projektbeschreibung
Es existieren drei Benutzertypen Student, Dozent und Ansprechpartner.

Ein Student kann einen Projektantrag mit bis zu 2 weiteren Studenten ausfüllen, sowie
einen Ansprechpartner hinzufügen. Anschließend kann er die Freigabe des Projektes beantragen.

Ein Dozent kann Projektanträge die auf Freigabe warten annehmen, ablehnen oder eine Überarbeitung fordern.
In den letzten beiden Fällen kann er zudem einen Kommentar hinterlassen.

Ein Ansprechpartner erhält nur eine Übersicht über ihm zugewiesene Projekte.