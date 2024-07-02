package com.MyBank;
import java.util.Date;

public class Transaction {
	private double amount;
	private Date timestampDate;
	private String memoString;
	private Account inAccount;
	
	/**
	 * @param amount - the amount transacted (in rands)
	 * @param inAccount - account transaction belongs to
	 */
	public Transaction(double amount, Account inAccount)
	{
		this.amount = amount;
		this.inAccount = inAccount;
		this.timestampDate = new Date();
		this.memoString = "";
	}
	
	/**
	 * @param amount - the amount transacted (in rands)
	 * @param memoString - memo (reference) for transaction
	 * @param inAccount - account transaction belongs to
	 */
	public Transaction(double amount, String memoString, Account inAccount) {
		this(amount, inAccount);
		this.memoString = memoString;
	}
	
	/**
	 * @return the amount
	 */
	public double getAmount() {
		return this.amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}

	/**
	 * @return the timestampDate
	 */
	public Date getTimestampDate() {
		return timestampDate;
	}

	/**
	 * @param timestampDate the timestampDate to set
	 */
	public void setTimestampDate(Date timestampDate) {
		this.timestampDate = timestampDate;
	}

	/**
	 * @return the memoString
	 */
	public String getMemoString() {
		return memoString;
	}

	/**
	 * @param memoString the memoString to set
	 */
	public void setMemoString(String memoString) {
		this.memoString = memoString;
	}

	/**
	 * @return the inAccount
	 */
	public Account getInAccount() {
		return inAccount;
	}

	/**
	 * @param inAccount the inAccount to set
	 */
	public void setInAccount(Account inAccount) {
		this.inAccount = inAccount;
	}

	/**
	 * String summarizing the transaction
	 * @return summary string
	 */
	public String getSummaryLine() {
		if(this.amount >= 0) {
			return String.format("%s : R%.02f : %s", this.timestampDate.toString(), this.amount, this.memoString);
		}else {
			return String.format("%s : R(%.02f) : %s", this.timestampDate.toString(), -this.amount, this.memoString);
		}
	}
}
