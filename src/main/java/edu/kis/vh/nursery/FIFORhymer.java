package edu.kis.vh.nursery;

/**
 * Klasa FIFORhymer implementuje mechanizm wyliczanki w kolejności FIFO
 * (First-In-First-Out – pierwszy wchodzi, pierwszy wychodzi).
 *
 * Rozszerza klasę DefaultCountingOutRhymer i modyfikuje sposób zwracania elementów,
 * tak aby zachowywały się jak kolejka, a nie stos.
 */
public class FIFORhymer extends DefaultCountingOutRhymer {

    /**
     * Tymczasowy obiekt pomocniczy używany do odwrócenia kolejności elementów.
     */
    private final DefaultCountingOutRhymer temp = new DefaultCountingOutRhymer();

    /**
     * Zwraca element zgodnie z zasadą FIFO.
     *
     * Metoda przenosi wszystkie elementy do struktury tymczasowej,
     * aby odwrócić ich kolejność, następnie pobiera pierwszy dodany element,
     * a pozostałe elementy przywraca do oryginalnej struktury.
     *
     * @return element, który został dodany jako pierwszy
     */
    @Override
    public int countOut() {
        // Przeniesienie wszystkich elementów do struktury tymczasowej
        while (!callCheck())
            temp.countIn(super.countOut());

        // Pobranie najstarszego elementu (FIFO)
        int ret = temp.countOut();

        // Przywrócenie pozostałych elementów
        while (!temp.callCheck())
            countIn(temp.countOut());

        return ret;
    }
}