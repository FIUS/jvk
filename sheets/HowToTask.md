# How to write a task

- Ziel im Auge behalten
- Was weiß der Student bereits / was weiß er noch nicht
- Sich bewusst machen, was man nicht erklären will
- Nichts als offensichtlich annehmen, Konzepte leicht erklären
- An PSE Namingconventions halten (s. unten)

## Technical
The exercise are writen in LaTeX. The files are in `/sheets/<sheet number>`. Every exercise gets its own file with the sheme `exercise-<Nr>.tex`. The Number must be consecutive.
Every file hast to contain the `\exercise{}` commdan at the beginning, which also sets the title for the exercise.

Also look if the settings (e.g. Year) needs to be changed in the vorkurs.cls

## TODO Wie wir Dinge nennen und wie Prof. Becker es tut

| Bezeichnung                       | PSE Bezeichnung              |
| --------------------------------- | ---------------------------- |
| Klassenattribut / -property / ... | Attribut / Eigenschaft       |
| Funktion / Methode / ...          | Operation                    |
| Abfrage                           | Abfrage                      |
| Kommando                          | Kommando                     |
| Information Hiding                | Information Hiding           |
| (Null-)Pointer                    | (Null-)Referenz              |
| Scope                             | Sichtbarkeitsbereich         |
| Statische Variable / Funktion     | Klassenvariable/-operation   |
| Klassenschnittstelle              | Klassenschnittstelle         |
| Interface                         | Interface                    |
| Funktionsparameter / Argument     | Parameter / Argument (s. 1.) |

### Anmerkungen

1. Je nach Kontext "Parameter" oder "Argument", s. Bsp. von Sandro:
   ```java
   public void foo (final String bar) {...} // bar is param of foo.
   ...
   final String quaz = "Lorem ipsum";
   foo(quaz); // quaz is the argument for foo.
   ```
1. In der PSE wird strikt zwischen _Kommando_ und _Abfrage_ unterschieden (eine Operation ist _nie_ beides).
1. In der PSE werden außerdem keine Arrays genutzt, sondern immer Collections.
1. In der PSE werden statische Variablen / Funktionen vermieden.
