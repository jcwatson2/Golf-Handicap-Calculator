//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           (Creates a HandicapEntry data type)
// Files:           (Main.java, IndexRecords.java)
// Course:          (CS400, fall, 2019)
//
// Author:          (James Watson)
// Email:           (Jcwatson2@wisc.edu)
// Lecturer's Name: (Deppler)
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// 
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   __x_ Write-up states that pair programming is allowed for this assignment.
//   __x_ We have both read and understand the course Pair Programming Policy.
//   __x_ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully 
// acknowledge and credit those sources of help here.  Instructors and TAs do 
// not need to be credited here, but tutors, friends, relatives, room mates, 
// strangers, and others do.  If you received no outside help from either type
//  of source, then please explicitly indicate NONE.
//
// Persons:         (N/A)
// Online Sources:  (N/A)
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

package application;

public class HandicapEntry {
	private int score;
	private double courseRating;
	private int slopeRating;
	
	/**
	 * Constructor for the HandicapEntry class
	 * @param score
	 * @param courseRating
	 * @param slopeRating
	 */
	public HandicapEntry(int score, double courseRating, int slopeRating) {
		this.score = score;
		this.courseRating = courseRating;
		this.slopeRating = slopeRating;
	}
	
	/**
	 * Getter method of integer Score
	 * @return score
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * Sets the score variable
	 * @param score to be set
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * Getter method of the CourseRating double
	 * @return course rating
	 */
	public double getCourseRating() {
		return courseRating;
	}
	
	/**
	 * Sets the course rating variable
	 * @param courseRating
	 */
	public void setCourseRating(int courseRating) {
		this.courseRating = courseRating;
	}

	/**
	 * Getter method of the Slope rating integer
	 * @return
	 */
	public int getSlopeRating() {
		return slopeRating;
	}

	/**
	 * Sets the Slope Rating integer
	 * @param slopeRating
	 */
	public void setSlopeRating(int slopeRating) {
		this.slopeRating = slopeRating;
	}
}
