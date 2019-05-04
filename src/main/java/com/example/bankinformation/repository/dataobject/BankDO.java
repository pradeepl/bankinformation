package com.example.bankinformation.repository.dataobject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BANKS")
public class BankDO
{
  @Column(name = "ID")
  @Id
  private Long id;

  @Column(name="NAME")
  private String name;

  public BankDO()
  {

  }

  public BankDO(Long id, String name)
  {
    this.id=id;
    this.name=name;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


}
