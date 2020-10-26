package ex05;

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

    public void ifRemove() {
        if (this.transfer.equals(MoneyOrder.DEBITS)) {
            System.out.println("Перевод From " +
                                this.sender.getName() +
                                "(id = " +
                                this.sender.getId() +
                                ")" +
                                this.sum +
                                " удалён");
        }
        else if (this.transfer.equals(MoneyOrder.CREDITS)) {
            System.out.println("Перевод To " +
                                this.recipient.getName() +
                                "(id = " +
                                this.recipient.getId() +
                                ")" +
                                this.sum +
                                " удалён");
        }
    }

    public void ifUnconfirmed() {
        if (this.transfer.equals(MoneyOrder.DEBITS)) {
            System.out.println(this.recipient.getName() + "(id = " + this.recipient.getId() + ") имеет неподтверждённый перевод id = " +
                    this.id + " от " + this.sender.getName() + "(id = " + this.sender.getId() + ") на сумму " + sum);
        }
        else if (this.transfer.equals(MoneyOrder.CREDITS)) {
            System.out.println(this.sender.getName() + "(id = " + this.sender.getId() + ") имеет неподтверждённый перевод id = " +
                    this.id + " для " + this.recipient.getName() + "(id = " + this.recipient.getId() + ") на сумму " + sum);
        }
    }

    @Override
    public String toString() {
        if (this.transfer.equals(MoneyOrder.DEBITS)) {
            return "From " +
                    this.sender.getName() +
                    "(id = " +
                    this.sender.getId() +
                    ") " +
                    this.sum +
                    " with id = " +
                    this.id;
        }
        else if (this.transfer.equals(MoneyOrder.CREDITS)) {
            return "To " +
                    this.recipient.getName() +
                    "(id = " +
                    this.recipient.getId() +
                    ") " +
                    this.sum +
                    " with id = " +
                    this.id;
        }
        return "";
    }
}
