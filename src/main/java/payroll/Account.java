package payroll;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
class Account {

    private @Id @GeneratedValue Long id;
    private String name;
    private Double balance;


    Account() {}

    Account(String name, Double balance) {
        this.name = name;
        this.balance = balance;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Double getBalance() {
        return balance;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Account))
            return false;
        Account account = (Account) o;
        return Objects.equals(this.id, account.id) && Objects.equals(this.name, account.name)
                && Objects.equals(this.balance, account.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.balance);
    }

    @Override
    public String toString() {
        return "Account:{" + "id=" + this.id + ", name='" + this.name + '\'' + ", balance='" + this.balance + '\'' + '}';
    }
}