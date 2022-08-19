//package com.spenndify.application.spendylast.wallet.selection;
//
//import org.jetbrains.annotations.NotNull;
//import org.springframework.web.bind.annotation.PutMapping;
//
//@org.springframework.stereotype.Service
//public class Service {
//    private double balance;
//    private double expense;
//    private BalanceService balanceService;
//    private BalancesRepo balancesRepo;
//    public void whetherMpesaOrBank(@NotNull SelectionRequest selectionRequest) throws Exception {
//        if(selectionRequest.getBankOrMpesa()==1)
//            isMpesa(selectionRequest);
//        else if(selectionRequest.getBankOrMpesa()==2)
//            isBank(selectionRequest);
//        else
//            throw new Exception("choice not available!");
//    }
//    public void isMpesa(@NotNull SelectionRequest selectionRequest) throws Exception{
//        if(selectionRequest.getCashOrWallet()==1)
//            setBalance_Expenses(selectionRequest);
//        else if(selectionRequest.getCashOrWallet()==2)
//            System.out.println("import data from sms");//import data
//        else
//            throw new Exception("choice not available");
//    }
//    public void isBank(@NotNull SelectionRequest selectionRequest) throws Exception{
//        if(selectionRequest.getCashOrWallet()==1)
//            setBalance_Expenses(selectionRequest);
//        else if(selectionRequest.getCashOrWallet()==2)
//            System.out.println("import records");
//        else
//            throw new Exception("");
//    }
//    public void setBalance_Expenses(SelectionRequest selectionRequest){
//        double currentBalance = selectionRequest.getBalance();
//        double newBalance = selectionRequest.getBalance() - selectionRequest.getExpense();
//        BalanceEn balanceEn = new BalanceEn(currentBalance,
//                selectionRequest.getExpense(),
//                newBalance);
//        balancesRepo.save(balanceEn);
//    }
//
//    public void subsequentExpense(BalanceRequest balanceRequest) {
//        BalanceEn priorBalance = balancesRepo.findBySpendUser(spendUser);
//        //update the database
//    }
//}
