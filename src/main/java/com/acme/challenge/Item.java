package com.acme.challenge;

public class Item {

	private Integer begin;
	private Integer end;
	private String date;
	private String sheet;
	private Integer max1;
	private Integer max2;
	private Integer max3;
	private Integer min;
	private Double average;

	public Item(Integer begin, Integer end, String date, String sheet,
			Integer max1, Integer max2, Integer max3, Integer min,
			Double average) {
		super();
		this.begin = begin;
		this.end = end;
		this.date = date;
		this.sheet = sheet;
		this.max1 = max1;
		this.max2 = max2;
		this.max3 = max3;
		this.min = min;
		this.average = average;
	}

	@Override
	public String toString() {
		return "Item [begin=" + begin + ", end=" + end + ", date=" + date
				+ ", sheet=" + sheet + ", max1=" + max1 + ", max2=" + max2
				+ ", max3=" + max3 + ", min=" + min + ", average=" + average
				+ "]";
	}

	public String getMax1() {
		return max1.toString();
	}

	public void setMax1(Integer max1) {
		this.max1 = max1;
	}

	public String getMax2() {
		return max2.toString();
	}

	public void setMax2(Integer max2) {
		this.max2 = max2;
	}

	public String getMax3() {
		return max3.toString();
	}

	public void setMax3(Integer max3) {
		this.max3 = max3;
	}

	public String getMin() {
		return min.toString();
	}

	public void setMin(Integer min) {
		this.min = min;
	}

	public String getAverage() {
		return average.toString();
	}

	public void setAverage(Double average) {
		this.average = average;
	}

	public String getBegin() {
		return begin.toString();
	}

	public void setBegin(int begin) {
		this.begin = begin;
	}

	public String getEnd() {
		return end.toString();
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getSheet() {
		return sheet;
	}

	public void setSheet(String sheet) {
		this.sheet = sheet;
	}

}
