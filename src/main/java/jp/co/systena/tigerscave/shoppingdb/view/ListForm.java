package jp.co.systena.tigerscave.shoppingdb.view;

public class ListForm{
  private int  num,itemid;
  private String itemname;

  public void setNum(int num){
    this.num = num;
  }
  public int  getNum(){
    return this.num;
  }

  public void setItemid(int itemid){
    this.itemid = itemid;
  }
  public int  getItemid(){
    return this.itemid;
  }

  public void setItemname(String itemname){
    this.itemname = itemname;
  }
  public String  getItemname(){
    return this.itemname;
  }
}
