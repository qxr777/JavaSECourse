package cn.edu.whut.cs.java.dome;


import java.util.Objects;

/**
 * 代表CD媒体
 * @author qixin
 *
 */
public class CD extends Item
{
    private String artist;
    private int numberOfTracks;

    /**
     * CD类的构造方法
     * @param theTitle CD的标题
     * @param theArtist  艺术家
     * @param tracks  音轨数量
     * @param time    总播放时长
     */
    public CD(String theTitle, String theArtist, int tracks, int time)
    {
    	super(theTitle, time);
    	artist = theArtist;
        numberOfTracks = tracks;
    }

    /**
     * 在控制台输出CD对象的属性值
     */
    @Override
	public void print() {
		System.out.print("CD");
		super.print();
		System.out.println("    " + artist);
		System.out.println("    tracks: " + numberOfTracks);
	}

	@Override
	public String toString() {
		return "CD{" +
				"artist='" + artist + '\'' +
				", numberOfTracks=" + numberOfTracks +
				'}';
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), artist, numberOfTracks);
	}

	//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((artist == null) ? 0 : artist.hashCode());
//		result = prime * result + numberOfTracks;
//		return result;
//	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CD other = (CD) obj;
		if (artist == null) {
			if (other.artist != null)
				return false;
		} else if (!artist.equals(other.artist))
			return false;
		if (numberOfTracks != other.numberOfTracks)
			return false;
		return true;
	}
	
	

}
