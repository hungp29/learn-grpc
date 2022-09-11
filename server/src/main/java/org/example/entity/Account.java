package org.example.entity;

public class Account {
    private int id;
    private String firstName;
    private String lastName;
    private int departmentId;
    private int numOfProduct;
    private long salary;

    public Account() {
    }

    public Account(int id, String lastName, String firstName, int departmentId, int numOfProduct, long salary) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.departmentId = departmentId;
        this.numOfProduct = numOfProduct;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public int getNumOfProduct() {
        return numOfProduct;
    }

    public void setNumOfProduct(int numOfProduct) {
        this.numOfProduct = numOfProduct;
    }

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }
}
