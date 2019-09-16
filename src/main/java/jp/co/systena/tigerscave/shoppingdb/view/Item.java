package jp.co.systena.tigerscave.shoppingdb.view;

public class Item {
  private int  itemid,price;
  private String name;

  public void setName(String name){
    this.name = name;
  }

  public String getName(){
    return this.name;
  }

  public void setItemid(int itemid){
    this.itemid = itemid;
  }

  public int  getItemid(){
    return this.itemid;
  }

  public void setPrice(int price){
    this.price = price;
  }

  public int getPrice(){
    return this.price;
  }
}
