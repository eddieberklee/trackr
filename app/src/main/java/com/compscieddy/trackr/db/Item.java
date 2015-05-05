package com.compscieddy.trackr.db;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

/**
 * Created by elee on 4/28/15.
 */
@Table(name = "Items")
public class Item extends Model {

  @Column(name = "Name")
  public String name;

  @Column(name = "Category")
  public Category category;

  public Item() {
    super();
  }

  public Item(String name, Category category) {
    super();
    this.name = name;
    this.category = category;
  }

  public static Item getRandom() {
    return new Select().from(Item.class).orderBy("RANDOM()").executeSingle();
  }
  public static Item getRandom(Category category) {
    return new Select().from(Item.class).where("Category = ?", category.getId()).orderBy("RANDOM()").executeSingle();
  }

}
