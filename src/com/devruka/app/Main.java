package com.devruka.app;

import com.devruka.entities.Contract;
import com.devruka.services.ContractService;
import com.devruka.services.PaypalService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        int contractNumber;
        LocalDate contractDate;
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        double contractTotalValue;
        int monthsQuantity;
        Contract contract;


        Scanner in = new Scanner(System.in);

        System.out.println("BEM VINDO AO PROGRAMA DE PARCELAMENTO DE CONTRATO");
        System.out.println("-------------------------------------------------");
        System.out.print("Digite o número do contrato: ");
        contractNumber = in.nextInt();
        System.out.print("Digite a data do contrato: ");
        contractDate = LocalDate.parse(in.next(),fmt );
        System.out.print("Digite o valor total do contrato: ");
        contractTotalValue = in.nextDouble();

        contract = new Contract(contractNumber, contractDate, contractTotalValue);
        ContractService contractService = new ContractService(new PaypalService());

        System.out.println("Informe o número de prestações que deseja parcelar no contrato: ");
        monthsQuantity = in.nextInt();

        contractService.processContract(contract, monthsQuantity);

        System.out.println("Parcelas: ");
        contract.printInstallments();
    }
}