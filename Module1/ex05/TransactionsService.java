package ex05;

import java.util.UUID;

public class TransactionsService {

    private static UsersList listOfUsers = new UsersArrayList();

    public void addUser(User user) {
        listOfUsers.userAdd(user);
    }

    public String getUserBalance(Integer id) {
        return String.format(listOfUsers.getUserId(id).getName() + " - " + listOfUsers.getUserId(id).getBalance());
    }

    public void makeTransaction(Integer idSend, Integer idRecip, Integer sum) {
        User send = listOfUsers.getUserId(idSend);

        User recip = listOfUsers.getUserId(idRecip);

        UUID id = UUID.randomUUID();

        if (send.getBalance() >= sum) {
            send.getTransactions().addTransaction(new Transaction(send, recip, -sum, id, MoneyOrder.CREDITS));
            recip.getTransactions().addTransaction(new Transaction(send, recip, sum, id, MoneyOrder.DEBITS));
        }
        else {
            throw new IllegalTransactionException("Not enough funds on the account");
        }
    }

    public Transaction[] getUserTransactions(Integer id) {
        User user = listOfUsers.getUserId(id);

        return user.getTransactions().toArray();
    }

    public void removeUserTransaction(Integer idUser, UUID idTrans) {
        User user = listOfUsers.getUserId(idUser);

        user.getTransactions().deleteTransaction(idTrans);
    }

    public Transaction[] checkTransactions() {
        TransactionsList uncheckedTransactions = new TransactionsLinkedList();

        Transaction[] temp;

        Transaction[] checkPair;

        Integer i = 0;

        Integer status;

        while (listOfUsers.getUserInd(i) != null) {
            temp = listOfUsers.getUserInd(i).getTransactions().toArray();
            for (int j = 0; j < temp.length; j++) {
                if (listOfUsers.getUserInd(i).equals(temp[j].getSender())) {
                    checkPair = temp[j].getRecipient().getTransactions().toArray();
                }
                else {
                    checkPair = temp[j].getSender().getTransactions().toArray();
                }
                status = 0;
                for (int k = 0; k < checkPair.length; k++) {
                    if (temp[j].getId() == checkPair[k].getId()) {
                        status = 1;
                    }
                }
                if (status == 0) {
                    uncheckedTransactions.addTransaction(temp[j]);
                }
            }
            i++;
        }

        return uncheckedTransactions.toArray();
    }
}
