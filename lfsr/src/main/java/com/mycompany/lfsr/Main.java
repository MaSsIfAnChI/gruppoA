/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lfsr;

import java.util.Scanner;

/**
 *
 * @author massi
 */
public class Main {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);  // crea un nuovo scanner per leggere l'input


        System.out.println("Inserisci il testo da crittografare:");  // Chiede all'utente di inserire il testo da crittografare
        String input = scanner.nextLine();  // Legge il testo da crittografare.


        System.out.println("Inserisci la chiave di crittografia:");  // Chiede all'utente di inserire la chiave
        String chiaveStringa = scanner.nextLine();  // Legge la chiave
        int chiave = chiaveStringa.hashCode();  // Converte la chiave in un intero utilizzando il metodo hashCode()


        int i = 0b10101010101010101010101010101010;  // Definisce la i, 0b=binario, determina i bit nel registro che vengono utilizzati per calcolare il nuovo bit da inserire nel registro, un bit viene utilizzato se è impostato a 1 quindi ogni bit in posizione dispari nel registro viene utilizzato per calcolare il nuovo bit, Questo nuovo bit viene inserito all’inizio del registro, mentre tutti gli altri bit vengono spostati di una posizione a destra.


        Lfsr Lfsr = new Lfsr(chiave, i);  // Crea un nuovo oggetto lfsr con la chiave e la posizione


        // Crittografia
        byte[] criptato = input.getBytes();  // Converte la stringa di input in un array di byte
        for (int j = 0; j < criptato.length; j++) {  // Per ogni byte-->
            criptato[j] ^= Lfsr.genera(8);  // -->esegue l'operazione XOR con un byte generato dal registro a scorrimento
        }


        System.out.println("Testo criptato: " + new String(criptato));


        // Decrittografia
        Lfsr = new Lfsr(chiave, i);  // Crea un nuovo lfsr con la stessa chiave e la stessa i per resettare lo stato del registro
        byte[] decriptato = criptato.clone();  // Crea una copia dell'array criptato
        for (int j = 0; j < decriptato.length; j++) {  // Per ogni byte-->
            decriptato[j] ^= Lfsr.genera(8);  // -->esegue l'operazione XOR con un byte generato dal registro a scorrimento.
        }


        System.out.println("Testo decriptato: " + new String(decriptato));


        // Dopo la crittografia o la decriptazione
        int periodo = Lfsr.calcola_periodo();
        System.out.println("Periodo del registro a scorrimento: " + periodo);


    }
}


