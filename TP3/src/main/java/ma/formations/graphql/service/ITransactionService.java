package ma.formations.graphql.service;

import ma.formations.graphql.dtos.transaction.AddWireTransferRequest;
import ma.formations.graphql.dtos.transaction.AddWireTransferResponse;
import ma.formations.graphql.dtos.transaction.GetTransactionListRequest;
import ma.formations.graphql.dtos.transaction.TransactionDto;

import java.util.List;

public interface ITransactionService {
    AddWireTransferResponse wiredTransfer(AddWireTransferRequest dto);
    List<TransactionDto> getTransactions(GetTransactionListRequest dto);
}