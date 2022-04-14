package com.example.hired;
/**
 * Models a question consisting of a question text and a correct answer
 *
 */

public class Question {
	
	private String question;
	//private String answer;
	
	/**
	 * Constructs a default question with an empty question and an empty answer
	 */
	public Question()  {
		question = "";
		//answer = "";
		
	}
	
	/**
	 * Constructs a question with a given question text and a given correct answer
	 * @param questionText the question text
	 * @param questionAnswer the correct answer
	 */
	public Question(String questionText, String questionAnswer)  {
		question = questionText;
		//answer = questionAnswer;
	}
	
	/**
	 * Constructs a question with a given question text with a null answer.
	 * @param questionText the question text
	 */
	public Question(String questionText)  {
		question = questionText;
		//answer = "Answers not added yet.";
	}

	
	/**
	 * Gets the question text
	 * @return the question text
	 */
	public String getQuestion()  {

		return question;
	}
	


	/**
	 * Sets the correct question to this question if not added to the construction.
	 * @param questionText the correct answer
	 */
	public void setQuestion(String questionText){

		question = questionText;
	}
	
	/**
	 * Displays this question to the console
	 */
	public void display()  {

		System.out.println(question);
	}

}
