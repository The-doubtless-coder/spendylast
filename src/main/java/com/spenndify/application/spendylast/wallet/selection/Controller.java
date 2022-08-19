//package com.spenndify.application.spendylast.wallet.selection;
//
//import lombok.AllArgsConstructor;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.validation.constraints.NotNull;
//
//@RestController
//@AllArgsConstructor
//@RequestMapping("/business")
//public class Controller {
//    private final Service service;
//    private final BalanceService balanceService;
//
//    @PostMapping("/accountType")
//    public void settingAccounts(@NotNull SelectionRequest selectionRequest) throws Exception {
//        service.whetherMpesaOrBank(selectionRequest);
//    }
//    @PostMapping("/balanceExpense")
//    public void inputBalance_Expenses(@NotNull BalanceRequest balanceRequest){
//        balanceService.setBalancesAndExpenses();
//
//    }
//    @PutMapping("/subsequentExpenses")
//    public void addSubsequentExpenses(BalanceRequest balanceRequest){
//        service.subsequentExpense(balanceRequest){
//
//        }
//    }
//}
