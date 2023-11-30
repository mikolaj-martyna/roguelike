# Roguelike

## Fabuła gry

W świecie, w którym cywilizacja trzyma się nieba, latające miasta i wyspy połączone skomplikowanymi sieciami mostów
służą jako ostatnie bastiony ludzkości. Jako odważny kapitan sterowca, twoja podróż rozpoczyna się w tętniącym życiem
latającym mieście Aeolus. Twój dziadek, znany wynalazca, pozostawił po sobie tajemniczy dziennik wypełniony tajemniczymi
szkicami i wskazówkami na temat dawno zaginionego artefaktu znanego jako "Wieczne Dynamo". Urządzenie to podobno posiada
moc utrzymywania lewitacji całych miast w nieskończoność.

Twoim zadaniem jest przemierzanie miast i wysp w poszukiwaniu tego bezcennego artefaktu, walcząc z nieuczciwymi piratami
powietrznymi, zdradzieckimi automatami i nawigując po niebezpiecznych sieciach mostów. Po drodze odkryjesz spisek, który
grozi pogrążeniem świata w chaosie. Twoje wybory i działania zadecydują o losie miast i zamieszkujących je ludzi.

## Główne mechaniki gry:

1. **Miasta generowane proceduralnie:** Każda rozgrywka generuje unikalny zestaw miast i wysp, zapewniając, że żadne
   dwie przygody nie będą takie same. Układ, architektura i mieszkańcy różnią się, oferując świeże wrażenia przy każdej
   próbie.
2. **Trwała śmierć i progresja:** Kiedy twoja postać umiera, traci wszystkie postępy, przedmioty i ulepszenia. Jednak w
   miarę eksploracji gromadzone są wiedza i plany, które pozwolą odblokować nowe części statku i ulepszenia w kolejnych
   rozgrywkach.
3. **Personalizacja sterowca:** Zbieraj zasoby i odzyskuj części, aby ulepszać i dostosowywać swój sterowiec. Modyfikuj
   broń, silniki i pancerz swojego statku, dostosowując się do wyzwań stawianych przez każde miasto. Twoje wybory
   wpływają na przetrwanie w tym steampunkowym świecie.
4. **Dynamiczna nawigacja po mostach:** Mosty łączące miasta są głównym elementem rozgrywki. Sieci te pełne są
   niebezpieczeństw, zagadek i wrogów. Wybory, których dokonujesz podczas poruszania się po nich, mogą wpłynąć na twoje
   postępy. Użyj strategicznego planowania i sprytu, aby pokonać zdradzieckie ścieżki.

## Pola

1. ` ` - pustka, po której nie można się poruszać
2. `@` - gracz
3. `#` - most
4. `.` - teren wyspy
5. `|`, `-` - granice wyspy
6. `o` - przeciwnik
7. `u` - skrzynia

## Przedmioty

[TODO]

## Walka

1. Atak - Określa obrażenia, jakie istota może zadać przeciwnikowi. Im wyższa wartość, tym potężniejsze ataki.
2. Obrona - Mierzy zdolność istoty do obrony się przed atakami przeciwnika. Wysoka wartość OBR zmniejsza otrzymywane obrażenia.
3. Zręczność - Decyduje o szybkości i precyzji ruchów w walce. Wyższa zręczność może wpłynąć na unikanie ataków przeciwnika.
4. Życie - Oznacza ogólną wytrzymałość istoty. Kiedy PŻ spadnie do zera, istota ginie.
5. Specjalne efekty przedmiotów - niektóre przedmioty posiadają specjalne efekty i statusy, które wpływają na statystyki bohatera.

### Statystyki

[TODO]

### Umiejętności

[TODO]

### Losowość

Każda umiejętność ma przedział wartości, które może przyjąć. Przy jej wywołaniu losowana jest liczba z tego zakresu, a w przypadku niektórych umiejętności, na przykład uniku, losowana jest wartość z przedziału 0-100 i porównywana jest do szansy na unik (np. 30%, 0-30).
