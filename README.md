# pio-tdd

**Repozytorium zawiera kod z projektu prezentującego testy jednostkowe i technikę TDD.**

Celem jest zaimplementowanie klasy `Cipher` odpowiedzialnej za szyfrowanie tekstu metodą Cezara.
Przyjmujemy stały alfabet 26 znaków A-Z (bez spacji). 
Kod jest tworzony zgodnie z techniką **TDD** (ang. _Test Driven Development_), czyli napisanie
jakiegokolwiek kawałka kodu (produkcyjnego) musi wynikać z tego, że istnieje test, kóry nie przechodzi pozytywnie.
Innymi słowy, stosujemy następujący proces:

1. Piszemy test sprawdzający coś, co chcielibyśmy zaimplementować.
2. Piszemy najprostszy kod, który spowoduje pozytywne przejście tego testu.

Pierwsza faza jest nazywana **fazą czerwoną**, dlatego że po uruchomieniu testów wynik w konsoli jest "czerwony".
Analogicznie, druga faza jest **fazą zieloną**.

W fazie zielonej powinno się od czasu do czasu przeprowadzić refaktoryzację, czyli poprawić kod, np.: uporządkować go,
ładniej ponazywać zmienne, zaimplementować trochę inaczej itp.

Pamiętajcie, że dzięki temu podejściu zawsze mamy komplet testów i 100% pokrycie kodu. To sprawia, że "grzebanie"
w kodzie jest bezpieczne, ponieważ w przypadku wprowadzenia niechcący jakiegokolwiek błędu od razu się o nim dowiadujemy.

Kolejne etapy pracy nad kodem zostały umieszczone w osobnych gałęziach:

- [etap-1](https://github.com/oleklamza/pio-tdd/tree/etap-1): Przygotowanie projektu: katalog źródłowy _test_,
  skonfigurowanie JUnit 5, utworzenie klasy `Cipher`.
- [etap-2](https://github.com/oleklamza/pio-tdd/tree/etap-2): Podstawowe testy szyfrowania
  (metoda `encrypt(String): String`); najprostsza implementacja.
- [etap-3](https://github.com/oleklamza/pio-tdd/tree/etap-3): Dodanie możliwości zmiany klucza szyfrującego:
  metoda `setKey(int)`.
- [etap-4](https://github.com/oleklamza/pio-tdd/tree/etap-4): Optymalizacja czasu przetwarzania: wyeliminowanie
  kłopotliwej konkatenacji w pętli.
- [etap-5](https://github.com/oleklamza/pio-tdd/tree/etap-5): Obsługa sytuacji typu `null` zamiast tesktu,
  nieprawiłowe znaki, puste łańcuchy.
