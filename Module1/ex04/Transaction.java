package ex04;

import java.util.UUID;

public class Transaction {

    private UUID id;

    private User recipient;

    private User sender;

    private MoneyOrder transfer;

    private Integer sum;

    public Transaction(User sender, User recipient, Integer sum, UUID id, MoneyOrder transfer) {
        this.sender = sender;

        this.recipient = recipient;

        if (transfer == MoneyOrder.DEBITS && sum > 0) {
            this.sum = sum;
            recipient.setBalance(sum);
        }
        else if (transfer == MoneyOrder.CREDITS && sum < 0) {
            this.sum = sum;
            sender.setBalance(sum);
        }
        else {
            this.sum = 0;
        }

        this.transfer = transfer;

        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public User getRecipient() {
        return recipient;
    }

    public User getSender() {
        return sender;
    }

    public Integer getSum() {
        return sum;
    }

    public MoneyOrder getTransfer() {
        return transfer;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", recipient=" + recipient +
                ", sender=" + sender +
                ", transfer=" + transfer +
                ", sum=" + sum +
                '}';
    }
}
