package ma.formations.graphql.presentation;

import lombok.AllArgsConstructor;
import ma.formations.graphql.dtos.transaction.AddWireTransferRequest;
import ma.formations.graphql.dtos.transaction.AddWireTransferResponse;
import ma.formations.graphql.dtos.transaction.GetTransactionListRequest;
import ma.formations.graphql.dtos.transaction.TransactionDto;
import ma.formations.graphql.service.ITransactionService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
public class TransactionGraphqlController {

    private ITransactionService transactionService;

    @MutationMapping
    public AddWireTransferResponse addWireTransfer(@Argument("dto") AddWireTransferRequest dto) {
        return transactionService.wiredTransfer(dto);
    }

    @QueryMapping
    public List<TransactionDto> getTransactions(@Argument GetTransactionListRequest dto) {
        return transactionService.getTransactions(dto);
    }
}