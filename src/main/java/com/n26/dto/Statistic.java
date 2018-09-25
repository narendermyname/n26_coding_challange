/**
 * 
 */
package com.n26.dto;

/**
 * @author natanwar
 *
 */
public class Statistic {
	private String sum;
	private String avg;
	private String max;
	private String min;
	private int count;

	public Statistic(String sum, String avg, String max, String min, int count) {
		this.sum = sum;
		this.avg = avg;
		this.max = max;
		this.min = min;
		this.count = count;
	}

	public String getSum() {
		return sum;
	}

	public void setSum(String sum) {
		this.sum = sum;
	}

	public String getAvg() {
		return avg;
	}

	public void setAvg(String avg) {
		this.avg = avg;
	}

	public String getMax() {
		return max;
	}

	public void setMax(String max) {
		this.max = max;
	}

	public String getMin() {
		return min;
	}

	public void setMin(String min) {
		this.min = min;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
