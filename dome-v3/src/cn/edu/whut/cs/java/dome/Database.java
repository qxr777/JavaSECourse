package cn.edu.whut.cs.java.dome;

import java.util.ArrayList;
/**
 * 提供一个多媒体数据库，可以将用户的CD和DVD信息存入，并可以列表输出所有实体
 * @author qixin
 *
 */
public class Database
{
    private ArrayList<Item> items;

    /**
     * 构造方法不需要参数
     */
    public Database()
    {
        items = new ArrayList<Item>();
    }

    /**
     * 向数据库中添加新的媒体信息
     * @param item  所有媒体父类的对象
     */
    public void addItem(Item item)
    {
    	items.add(item);
    }

    /**
     * 列表显示数据库中所有的媒体信息
     */
    public void list()
    {
    	for (Item item : items) {
//    		item.print();
    		System.out.println(item);
    		System.out.println();
    	}
    }
}
