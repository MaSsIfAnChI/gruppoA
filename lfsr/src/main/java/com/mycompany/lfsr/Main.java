/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lfsr;

/**
 *
 * @author massi
 */
import java.util.Base64;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Inserisci il testo da crittografare:");
        String input = scanner.nextLine();

        System.out.println("Inserisci la chiave di crittografia:");
        String chiaveStringa = scanner.nextLine();
        byte[] chiaveBytes = chiaveStringa.getBytes();
        String chiaveBase64 = Base64.getEncoder().encodeToString(chiaveBytes);

        short i = (short) 0b1010101010101010;  // Cambiato da int a short

        Lfsr lfsr = new Lfsr(chiaveBase64.hashCode(), i);

        byte[] criptato = input.getBytes();
        for (int j = 0; j < criptato.length; j++) {
            criptato[j] ^= lfsr.genera(8);
        }

        System.out.println("Testo criptato: " + new String(criptato));

        lfsr.reset(); // Aggiunto metodo reset per riportare il registro allo stato iniziale

        byte[] decriptato = criptato.clone();
        for (int j = 0; j < decriptato.length; j++) {
            decriptato[j] ^= lfsr.genera(8);
        }

        System.out.println("Testo decriptato: " + new String(decriptato));

        int periodo = lfsr.calcola_periodo();
        System.out.println("Periodo del registro a scorrimento: " + periodo);
    }
}
