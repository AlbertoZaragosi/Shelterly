/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package alberto.refugio;

/**
 *
 * @author neong
 */
public class User {
    String name;
    String email;
    boolean isRoot;
    //int age;
    String password;

    @Override
    public String toString() {
        return "User{" + "name=" + name + ", email=" + email + ", isRoot=" + isRoot + ", password=" + password + '}';
    }

    public User(String name, String email, boolean isRoot, String password) {
        this.name = name;
        this.email = email;
        this.isRoot = isRoot;
        //this.age = age;
        this.password = password;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
    
    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        //this.age = age;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isIsRoot() {
        return isRoot;
    }

    public void setIsRoot(boolean isRoot) {
        this.isRoot = isRoot;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
}
