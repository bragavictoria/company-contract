package br.com.voidstar.personCompany;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Company {
    private String name;
    private Set<Contract> contracts;

    public Company() {
        this.name = "";
        this.contracts = new HashSet<>();
    }

    public Company(String name) {
        this.name = name;
        this.contracts = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Contract> getContracts() {
        return contracts;
    }

    public int getNumberOfEmployees() {
        return contracts.size();
    }
    protected void hire(Contract contract) {
        contracts.add(contract);
        contract.getPerson().selfHire(this, contract.getSalary());
    }

    protected void dismiss(Contract contract) {
        contracts.remove(contract);
        contract.getPerson().selfDismiss(this);
    }

    public void hire(Person person, double salary) {
        if (person != null && salary > 0 && person.getCompany() != this) {
            if (person.getCompany() != null && person.getCompany() != this) {
                person.getCompany().dismiss(person);
            }
            Contract newContract = new Contract(this, person, salary);
            contracts.add(newContract);
            person.setSalary(salary);
            person.setCompany(this);
            person.setContract(newContract);
            }

            if (!contracts.contains(person)){
                //contracts.add(newContract());
                //person.setSalary();
                person.company = this;
            }
        }

//    private Contract newContract() {
//    }

    public void dismiss(Person person) {
        Contract contract = findContract(person);
        if (contract != null) {
            contracts.remove(contract);
            person.setSalary(0.0);
            person.company = null;
            person.selfDismiss(this);
        }
    }

    public Contract findContract(Person person) {
        if (person != null && person.getContract() != null && person.getCompany() == this){
            return person.getContract();
        }
        return null;
    }

    public boolean employed(Person person) {
        // it supposes uniqueness of persons (objects).
        return findContract(person) != null;
    }

    public double payroll() {
        return contracts.stream().mapToDouble(Contract::getSalary).sum();
    }
}
