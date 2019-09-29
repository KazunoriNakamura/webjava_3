package jp.co.systena.tigerscave.shoppingdb.view;

public class Item {
  private int  item_id,price;
  private String item_name;

  public void setItem_name(String item_name){
    this.item_name = item_name;
  }

  public String getItem_Name(){
    return this.item_name;
  }

  public void setItem_id(int item_id){
    this.item_id = item_id;
  }

  public int  getItemid(){
    return this.item_id;
  }

  public void setPrice(int price){
    this.price = price;
  }

  public int getPrice(){
    return this.price;
  }
}
