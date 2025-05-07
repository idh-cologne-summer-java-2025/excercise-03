Fortgeschrittene Programmierung (Java 2)

# Übung 3


Klonen Sie dieses Repository direkt in Eclipse und importieren Sie das Projekt. Legen Sie einen neuen Branch an, den Sie nach Ihrem GitHub-Benutzernamen benennen.

## Aufgabe 1

Im Projekt finden Sie eine Klasse `Document`, die eine Textdatei einlesen kann, und zwar über die (statische) Methode `Document.readFromFile()`. Sie finden außerdem eine Datei `data/dracula.txt`, wobei es sich um den Text von Bram Stokers' *Dracula* handelt.

Machen Sie zunächstmal die Klasse `Document` iterierbar, so dass sie über die Tokens im Dokument iteriert, die jeweils als Strings repräsentiert werden (d.h. die Klasse `Document` sollte das Interface `Iterable<String>` implementieren). Zur Erkennung einzelner Tokens können Sie auf die Klasse `StringTokenizer` zurückgreifen, die ebenfalls zum  `java.util` package gehört ([javadoc](https://docs.oracle.com/javase/8/docs/api/java/util/StringTokenizer.html)). 

Fügen Sie dann in der `main`-Methode eine Schleife ein, so dass die Tokens des Textes ausgegeben werden.

## Aufgabe 2

In der Sitzung haben wir angefangen, eine Implementierung einer verketteten Liste (*linked list*) zu erstellen, basierend auf der Klasse StringList aus der ersten Übung (und dem Wintersemester). In dieser Aufgabe führen wir das weiter. In der Klasse `idh.java.MyLinkedList` finden Sie eine teilweise Implementierung der Liste, so dass sie dem Interface `List<T>` entspricht. Einige der Methoden sind mit `// TODO: Implement` markiert -- implementieren Sie diese!

### Hinweise

1. Da alle Methoden vom Interface `java.util.List` vorgegeben werden, enthält die Datei nur sehr wenige Kommentare. Schauen Sie bei Unklarheiten, was die Methoden genau leisten sollen, in der Dokumentation des Interfaces nach.

2. Unten in der Klasse finden Sie einige als `private` deklarierte Methoden, die Sie beliebig nutzen können. Zögern Sie nicht, eigene zu ergänzen, wenn nötig.

----

Wenn Sie fertig sind, committen Sie alle Ihre Änderungen am Quellcode, und pushen Sie den neuen Branch auf das remote namens `origin` (= GitHub). 
