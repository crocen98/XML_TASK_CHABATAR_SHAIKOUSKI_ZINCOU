package com.javatr.entity.tariff;

import java.io.Serializable;
import java.util.Objects;

public class Tariff implements Serializable {

  private String id;
  private String name;
  private String operatorName;
  private int payroll;
  private int smsPrice;
  private Billing billing;

  public Tariff(){

  }

  public Tariff(String id, String name, String operatorName,  Billing billing, int payroll, int smsPrice){
    this.name = name;
    this.id = id;
    this.operatorName = operatorName;
    this.payroll = payroll;
    this.smsPrice = smsPrice;
    this.billing = billing;
  }

  public void setSmsPrice(int smsPrice){
    this.smsPrice = smsPrice;
  }

  public void setBilling(Billing billing) {
    this.billing = billing;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setOperatorName(String operatorName) {
    this.operatorName = operatorName;
  }

  public void setPayroll(int payroll) {
    this.payroll = payroll;
  }

  public int getPayroll() {
    return payroll;
  }

  public int getSmsPrice() {
    return smsPrice;
  }

  public Billing getBilling() {
    return billing;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getOperatorName() {
    return operatorName;
  }

  @Override
  public int hashCode() {
    return Objects.hash(name,id,billing,payroll,operatorName,smsPrice);
  }


  @Override
  public boolean equals(Object obj) {
    if(obj == null) {
      return false;
    }
    if (this.getClass() != obj.getClass()){
      return false;
    }
    Tariff tariff = (Tariff) obj;
    if(!this.id.equals(tariff.getId())){
      return false;
    }
    if (!this.name.equals(tariff.getName())){
      return false;
    }
    if(this.billing != tariff.getBilling()){
      return false;
    }
    if(!this.operatorName.equals(tariff.getOperatorName())){
      return false;
    }
    if(this.smsPrice != tariff.getSmsPrice()){
      return false;
    }
    return this.payroll == tariff.getPayroll();
  }

  @Override
  public String toString() {
    return "Tariff{" +
        "name='" + name + '\'' +
        ", id=" + id +
        ", operatorName='" + operatorName + '\'' +
        ", payroll= " + payroll +
        ", billing=" + billing +
        ", sms price= " + smsPrice +
        '}';
  }
}
