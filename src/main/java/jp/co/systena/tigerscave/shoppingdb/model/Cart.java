package jp.co.systena.tigerscave.shoppingdb.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
  public List<Order> orderList = new ArrayList<Order>();

  public List<Order> getOrderList() {
    return this.orderList;
  }

  public void add(Order order) {
    int orderid,num;
    boolean idflg = false;
    int index= 0;
    Order temporder;
    orderid = order.getItemid();

    for (Order o : orderList) {
      if (o.getItemid() == orderid) {
          idflg= true;
          break;
      }
      index=+1;
    }
    if (idflg) {
      temporder = orderList.get(index);
      num = temporder.getNum() + order.getNum();
      temporder.setNum(num);
      orderList.set(index, temporder);
    }
    else {
      orderList.add(order);
    }
  }

  public void cancel(int index) {
    // orderからはindexだけ取得できればよい
    orderList.remove(index);
  }
}
