# How to write a task

- Ziel im Auge behalten
- Was weiß der Student bereits / was weiß er noch nicht
- Sich bewusst machen, was man nicht erklären will
- Nichts als offensichtlich annehmen, Konzepte leicht erklären
- An PSE Namingconventions halten (s. unten)

## TODO Wie wir Dinge nennen und wie Prof. Becker es tut

| Bezeichnung                       | PSE Bezeichnung            |
| --------------------------------- | -------------------------- |
| Klassenattribut / -property / ... | Eigenschaft                |
| Funktion / Methode / ...          | Operation                  |
| Abfrage                           | Abfrage                    |
| Kommando                          | Kommando                   |
| Funktionsparameter / Argument     | Argument                   |
| Information Hiding                | Information Hiding         |
| (Null-)Pointer                    | (Null-)Referenz            |
| Scope                             | Sichtbarkeitsbereich       |
| Statische Variable / Funktion     | Klassenvariable/-operation |
| Klassenschnittstelle              | Schnittstelle              |
| Interface                         | Interface                  |

### Anmerkungen

- In der PSE wird strikt zwischen _Kommando_ und _Abfrage_ unterschieden (eine Operation ist _nie_ beides).
- In der PSE werden außerdem keine Arrays genutzt, sondern immer Collections.
- In der PSE werden statische Variablen / Funktionen vermieden.
