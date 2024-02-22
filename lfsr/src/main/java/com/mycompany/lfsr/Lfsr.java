/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.lfsr;

/**
 *
 * @author massi
 */


public class Lfsr {


    private int statoRegistro;  // Variabile per memorizzare lo stato del registro
    private int i;  // Variabile per memorizzare la posizione


    public Lfsr(int statoRegistro, int i) {
        this.statoRegistro = statoRegistro;  // Inizializza lo stato del registro
        this.i = i;  // Inizializza la posizione
    }


    public int passo() {  // Metodo per calcolare il prossimo bit
        int nuovoBit = Integer.bitCount(statoRegistro & i) % 2;  // Calcola il bit come il conteggio dei bit a 1 nello stato del registro con la posizione memorizzata, modulo 2
        statoRegistro = (statoRegistro >>> 1) | (nuovoBit << 31);  // Aggiorna lo stato del registro spostandolo di un bit a destra e inserendo il nuovo bit a sinistra
        
        // Stampa il valore del registro in formato binario
        System.out.println("Stato del registro: " + Integer.toBinaryString(statoRegistro));
        
        return nuovoBit;
    }


    public int genera(int n) {  // Metodo per generare un intero di n bit.
        int risultato = 0;  // Inizializza il risultato a 0.
        for (int j = 0; j < n; j++) {  // Per ogni bit da generare-->
            risultato = (risultato << 1) | passo();  // -->sposta il risultato di un bit a sinistra e inserisci il prossimo bit
        }
        return risultato; 
    }
    
    public int calcola_periodo() {
    int statoIniziale = statoRegistro;  // Salva lo stato iniziale del registro
    int periodo = 0;  // Inizializza la lunghezza del periodo a 0


    do {
        passo();  // Esegui un passo nel registro
        periodo++;
    } while (statoRegistro != statoIniziale && periodo < (1 << 16));  // Continua finché non torni allo stato iniziale o raggiungi 2^16 passi


    return periodo;
}


}
