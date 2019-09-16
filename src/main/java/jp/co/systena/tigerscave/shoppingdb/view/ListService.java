package jp.co.systena.tigerscave.shoppingdb.view;

import java.util.ArrayList;
import java.util.List;

public class ListService {

  public List<Item> getItemList() {
    List<Item> itemList = new ArrayList<Item>();

    Item item1 = new Item();
    item1.setName("虎の穴入門");
    item1.setItemid(12345);
    item1.setPrice(1000);
    itemList.add(item1);

    Item item2 = new Item();
    item2.setName("虎の穴応用");
    item2.setItemid(12346);
    item2.setPrice(5000);
    itemList.add(item2);

    return itemList;
  }

}
