/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.lfsr;

/**
 *
 * @author massi
 */
class Lfsr {
    private short statoRegistro;  // Cambiato da int a short
    private short statoIniziale;  // Aggiunto stato iniziale

    private short i;  // Cambiato da int a short

    public Lfsr(int statoRegistro, short i) {
        this.statoRegistro = (short) statoRegistro;
        this.statoIniziale = this.statoRegistro;
        this.i = i;
    }

    public int passo() {
        int nuovoBit = Integer.bitCount(statoRegistro & i) % 2;
        statoRegistro = (short) ((statoRegistro >>> 1) | (nuovoBit << 15));
        System.out.println("Stato del registro: " + String.format("%16s", Integer.toBinaryString(statoRegistro & 0xFFFF)).replace(' ', '0'));
        return nuovoBit;
    }

    public int genera(int n) {
        int risultato = 0;
        for (int j = 0; j < n; j++) {
            risultato = (risultato << 1) | passo();
        }
        return risultato;
    }

    public int calcola_periodo() {
        int periodo = 0;

        do {
            passo();
            periodo++;
        } while (statoRegistro != statoIniziale && periodo < (1 << 15));

        return periodo;
    }

    public void reset() {
        statoRegistro = statoIniziale;
    }
}
