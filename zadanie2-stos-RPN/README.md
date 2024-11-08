Termin oddania:     16.11.2024 10:30
Punkty:             10
1. Zadanie 1: Stos (4 pkt)
(a) Zaimplementuj klasę Stack, która realizuje ideę stosu dla napisów, z następującymi metodami publicznymi:
- push - wkłada jeden element na stos.
- pop - zdejmuje jeden element ze stosu i zwraca wartość tego elementu. Co ma się dziać, gdy metoda pop próbuje zdjąć element z pustego stosu?
- peek - zwraca wartość elementu na szczycie stosu, ale go nie zdejmuje. Jak rozwiązać problem pustego stosu podobnie jak w przypadku metody pop?

Podstawową strukturą danych w implementacji stosu powinna być tablica. Stos nie powinien posiadać ograniczeń rozmiaru.

Przykład:
Stack stack = new Stack();
stack.push("A"); - dodaje element "A" na stos
stack.push("B"); - dodaje element "B" na stos
stack.pop(); - usuwa i zwraca element "B"
stack.peek(); - zwraca element "A" bez zdejmowania go ze stosu

(b) Przygotuj testy jednostkowe dla klasy Stack, zanim przystąpisz do implementacji tej klasy.

2. Zadanie 2: RPN (6 pkt)
(a) Zaimplementuj klasę, która oblicza wyrażenia arytmetyczne zapisane w Odwrotnej Notacji Polskiej (RPN). Założenia:
- Wyrażenia są ciągami znaków.
- Program umożliwia wyliczanie wyrażeń złożonych z liczb całkowitych oraz operacji binarnych, takich jak +, -, *, /.
- Do implementacji wykorzystaj klasę Stack z Zadania 1.

Przykład:
Wyrażenie: "3 4 + 2 *"
Operacje:
push(3), push(4), pop(4), pop(3), push(3+4), push(2), pop(2), pop(7), push(7*2)
Wynik: 14

(b) Przygotuj testy jednostkowe dla implementacji RPN.