package com.devruka.services;

import com.devruka.entities.Contract;
import com.devruka.entities.Installment;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ContractService {

    private OnlinePaymentService paymentService;

    public ContractService(OnlinePaymentService paymentService){
        this.paymentService = paymentService;
    }
    public void processContract(Contract contract, int months){

        double basicQuota = contract.getTotalValue()/months;
        double installmentValue;

        // installment process
        for (int i=1; i<=months; i++){
            //define a data da parcela
            LocalDate dueDate = contract.getDate().plusMonths(i);
            //processa o valor para a parcela
            //calcula o juros
            double interest = paymentService.interest(basicQuota, i);
            //calcula a taxa
            double fee = paymentService.paymentFee(basicQuota + interest);

            installmentValue = basicQuota + interest + fee;

            contract.getInstallments().add(new Installment(dueDate, installmentValue));

        }
    }
}
