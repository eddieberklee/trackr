package com.compscieddy.trackr.db;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.List;

/**
 * Created by elee on 4/28/15.
 */
@Table(name = "Categories")
public class Category extends Model {

  // setting specific indexes by doing (name="Name", index=true) instead
  @Column(name = "Name")
  public String name;

  // method optional; doesn't affect foreign key creation
  public List<Item> items() {
    return getMany(Item.class, "Category");
  }

}
