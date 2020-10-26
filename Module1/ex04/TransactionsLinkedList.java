package ex04;

import java.util.UUID;

public class TransactionsLinkedList implements TransactionsList {

    private int size;

    private Node first;

    private Node last;

    public TransactionsLinkedList() {
        this.size = 0;
        this.first = null;
        this.last = null;
    }

    public void addTransaction(Transaction transaction) {
        if (size == 0) {
            this.first = new Node(null, transaction, null);
            this.last = this.first;
        }
        else {
            this.last.next = new Node(this.last, transaction, null);
            this.last = this.last.next;
        }
        size++;
    }

    public void deleteTransaction(UUID id) {
        Node temp = this.first;

        Node prev;

        Node next;

        for (int i = 0; i < size; i++) {
            if (temp.transaction.getId() == id) {
                break ;
            }
            temp = temp.next;
        }

        if (temp == null) {
            throw new TransactionNotFoundException("There is no transaction with this ID");
        }

        prev = temp.prev;

        next = temp.next;

        if (prev != null) {
            prev.next = temp.next;
        }

        if (next != null) {
            next.prev = temp.prev;
        }

        temp.next = null;

        temp.prev = null;

        size--;
    }

    public Transaction[] toArray() {
        Transaction[] transactionsArr = new Transaction[size];

        Node temp = this.first;

        for (int i = 0; i < size; i++) {
            transactionsArr[i] = temp.transaction;
            temp = temp.next;
        }

        return transactionsArr;
    }

    private static class Node {
        Transaction transaction;
        Node next;
        Node prev;

        public Node(Node prev, Transaction transaction, Node next) {
            this.prev = prev;
            this.next = next;
            this.transaction = transaction;
        }
    }
}
