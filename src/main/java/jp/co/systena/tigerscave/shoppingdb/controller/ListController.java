package jp.co.systena.tigerscave.shoppingdb.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import jp.co.systena.tigerscave.shoppingdb.model.Cart;
import jp.co.systena.tigerscave.shoppingdb.model.Order;
import jp.co.systena.tigerscave.shoppingdb.view.Item;
import jp.co.systena.tigerscave.shoppingdb.view.ListForm;
import jp.co.systena.tigerscave.shoppingdb.view.ListService;

@Controller // Viewあり。Viewを返却するアノテーション
public class ListController {
  @Autowired
  HttpSession session;

  @Autowired
  ListService listservice;

  @Autowired
  private  JdbcTemplate jdbcTemplate;

  @RequestMapping(value = "/Show", method = RequestMethod.GET) // URLとのマッピング
  public ModelAndView  show(ModelAndView  mav) {

    List<Item> itemList = listservice.getItemList();

    Map<Integer, Item> itemListMap = new HashMap<Integer, Item>();
    for (Item item : itemList) {
      itemListMap.put(item.getItemid(), item);
    }
    mav.addObject("itemList", itemListMap);

    Cart cart = (Cart) session.getAttribute("cart");
    if (cart == null) {
      cart = new Cart();
      session.setAttribute("cart", cart);
    }

    mav.addObject("orderList", cart.getOrderList());

    // 合計金額計算
    int totalPrice = 0;
    for (Order order : cart.getOrderList()) {
      if (itemListMap.containsKey(order.getItemid())) {
        totalPrice += order.getNum() * itemListMap.get(order.getItemid()).getPrice();
      }
    }
    mav.addObject("totalPrice", totalPrice);

    mav.setViewName("ListView");

    return mav;
  }

  @RequestMapping(value = {"/Show"}, method = {RequestMethod.POST})
  public ModelAndView  order(ModelAndView mav,
          BindingResult bindingResult,
          @Valid ListForm form) {

    Cart cart = (Cart) session.getAttribute("cart");
    if (cart == null) {
      cart = new Cart();
      session.setAttribute("cart", cart);
    }

    if (bindingResult.getAllErrors().size() > 0) {
        mav.addObject("form", form);

        ListService service = new ListService();
        List<Item> itemList = service.getItemList();

        // 商品一覧をMapに入れ直し
        Map<Integer, Item> itemListMap = new HashMap<Integer, Item>();
        for (Item item : itemList) {
          itemListMap.put(item.getItemid(), item);
        }

        mav.addObject("itemList", itemListMap);

        // Viewのテンプレート名を設定
        mav.setViewName("ListView");

        return mav;

    }

    Order order = new Order();
    order.setItemid(form.getItemid());
    order.setNum(form.getNum());
    cart.add(order);

    // セッションへ保存
    session.setAttribute("cart", cart);

    // 購入履歴DBを更新
    jdbcTemplate.update(
        "UPDATE history SET item_num = item_num +? WHERE item_id = ?",
        form.getNum(),
        form.getItemid());

    return new ModelAndView("redirect:/Show");

  }

  @RequestMapping(value = {"/Del"}, method = {RequestMethod.POST})
  public ModelAndView  cancel(ModelAndView mav,
          BindingResult bindingResult,
          @Valid ListForm form) {

    Cart cart = (Cart) session.getAttribute("cart");
    if (cart == null) {
      cart = new Cart();
      session.setAttribute("cart", cart);
    }

    Order order = new Order();
    order.setItemid(form.getItemid());
    order.setNum(form.getNum());
    cart.cancel(0);

    // セッションへ保存
    session.setAttribute("cart", cart);

    return new ModelAndView("redirect:/Show");
  }
}

