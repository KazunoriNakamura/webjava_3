package jp.co.systena.tigerscave.shoppingdb.view;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class ListService {

  @Autowired
  private  JdbcTemplate jdbcTemplate;

  public List<Item> getItemList() {

    List<Item> list = jdbcTemplate.query("SELECT * FROM items ORDER BY item_id", new BeanPropertyRowMapper<Item>(Item.class));

    return list;
  }

}
