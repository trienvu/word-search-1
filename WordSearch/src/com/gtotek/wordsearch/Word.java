package com.gtotek.wordsearch;

import android.os.Parcel;
import android.os.Parcelable;
 

public class Word implements Parcelable, Comparable<Word> {
	public static final Parcelable.Creator<Word> CREATOR = new Parcelable.Creator<Word>() {
		public Word  createFromParcel(Parcel in) {
			return new Word(in);
		}

		public Word[] newArray(int size) {
			return new Word[size];
		}
	};

	private String mWord;
	private int mRow, mCol;
	private Direction mDirection;

	public Word(Parcel in) {
		mWord = in.readString();
		mRow = in.readInt();
		mCol = in.readInt();
		mDirection = Direction.valueOf(in.readString());
	}

	public int compareTo(Word another) {
		return mWord.compareTo(another.getWord());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.os.Parcelable#describeContents()
	 */
	public int describeContents() {
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.os.Parcelable#writeToParcel(android.os.Parcel, int)
	 */
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(mWord);
		dest.writeInt(mRow);
		dest.writeInt(mCol);
		dest.writeString(mDirection.name());
	}

	public Word(String word, int row, int col, Direction direction) {
		super();
		mWord = word;
		mRow = row;
		mCol = col;
		mDirection = direction;
	}

	/**
	 * @return the word
	 */
	public String getWord() {
		return mWord;
	}

	/**
	 * @return the row
	 */
	public int getRow() {
		return mRow;
	}

	/**
	 * @return the col
	 */
	public int getCol() {
		return mCol;
	}

	/**
	 * @return the direction
	 */
	public Direction getDirection() {
		return mDirection;
	}

	// public int getFirstPosition() {
	// return (mRow * mColumns) + mCol;
	// }

	@Override
	public String toString() {
		return "Word [mWord=" + mWord + ", mRow=" + mRow + ", mCol=" + mCol
				+ ", mDirection=" + mDirection + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + mCol;
		result = prime * result
				+ ((mDirection == null) ? 0 : mDirection.hashCode());
		result = prime * result + mRow;
		result = prime * result + ((mWord == null) ? 0 : mWord.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Word other = (Word) obj;
		if (mCol != other.mCol)
			return false;
		if (mDirection != other.mDirection)
			return false;
		if (mRow != other.mRow)
			return false;
		if (mWord == null) {
			if (other.mWord != null)
				return false;
		} else if (!mWord.equals(other.mWord))
			return false;
		return true;
	}
}
