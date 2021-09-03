package cn.edu.whut.cs.java.dome;

import java.util.Objects;

/**
 * 各类媒体的超类
 * @author qixin
 *
 */
public class Item extends Object{
    private String title;   //标题
    private int playingTime;  //总播放时长，单位秒
    private boolean gotIt;   // 是否拥有
    private String comment;   //备注

    /**
     * 初始化Item对象
     * @param theTitle  // 标题
     * @param time  // 总播放时长
     */
    public Item(String theTitle, int time)
    {
        title = theTitle;
        playingTime = time;
        gotIt = false;
        comment = "<no comment>";
    }

    public void setComment(String comment)
    {
        this.comment = comment;
    }

    public String getComment()
    {
        return comment;
    }

    public void setOwn(boolean ownIt)
    {
        gotIt = ownIt;
    }

    public boolean getOwn()
    {
        return gotIt;
    }

	public void print() {
		System.out.print(": " + title + " (" + playingTime + " mins)");
		if (gotIt) {
			System.out.println("*");
		} else {
			System.out.println();
		}
		System.out.println("    " + comment);
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return playingTime == item.playingTime &&
                gotIt == item.gotIt &&
                title.equals(item.title) &&
                Objects.equals(comment, item.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, playingTime, gotIt, comment);
    }
}
