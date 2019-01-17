package com.javatr.entity.tariff;

import java.util.Objects;

public class Tariff {

  private String id;
  private boolean preciousness;
  private String name;
  private String operatorName;
  private int payroll;
  private Billing billing;

  public void setBilling(Billing billing) {
    this.billing = billing;
  }

  public void setPreciousness(boolean preciousness) {
    this.preciousness = preciousness;
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

  public boolean getPreciousness(){
    return preciousness;
  }

  @Override
  public int hashCode() {
    return Objects.hash(name,preciousness,id,billing,payroll,operatorName);
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
    if(!this.preciousness == tariff.getPreciousness()){
      return false;
    }
    return this.payroll == tariff.getPayroll();
  }

  @Override
  public String toString() {
    return "Tariff{" +
        "name='" + name + '\'' +
        ", preciousness=" + preciousness +
        ", id=" + id +
        ", operatorName='" + operatorName + '\'' +
        ", payroll=" + payroll +
        ", billing=" + billing +
        '}';
  }
}
