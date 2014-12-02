package com.gtotek.wordsearch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.GridView;
import android.widget.Toast;

import com.gtotek.wordsearch.WordsearchGridView.OnWordSelectedListener;

public class MainActivity extends Activity implements OnWordSelectedListener {
	private Context mContext = this;

	private WordsearchGridView mWordsearchGridView;
	private GridView mGrvWord;
	private WordListAdapter mWordAdapter;

	private int mRows = 8;
	private int mColumns = 8;

	private static String[] mWordList = { "YES", "WONDROUS", "WONDERFUL", "WONDER", "WITH", "WILLING", "WHOLE",
		"WELL", "WEALTHY", "VOYAGE", "VIVACIOUS", "VITAL", "VISUALIZE", "VISION", "VIGOROUS", "VICTORY", "VIBRANT",
		"VENERATED", "VARY", "VALUED", "UPBEAT", "UNWAVERING", "UNUSUAL", "UNITY", "UNIQUE", "UNDERSTANDING", "TRUTH", "TRUSTFUL",
		"TRIUMPH", "TRANSFORM", "TRANQUIL", "TOGETHER", "TODAY", "TIED", "THRIVE", "THOROUGH", "THIS", "THERAPEUTIC",
		"THANKFUL", "TEAM", "TAKE", "SYNCHRONIZED", "SUSTAIN", "SURE", "SUPPORT", "SUNNY", "SUCCESS", "STYLE", "STUPENDOUS",
		"STUNNING", "STRONG", "STIRRING", "STIR", "STEADY", "STABLE", "SPONTANEOUS", "SPLENDID", "SPIRIT", "SPARKLING",
		"SOUL", "SOLUTION", "SMOOTH", "SMILE", "SMART", "SINCERE", "SIMPLE", "SILENCE", "SHOWN", "SHINE", "SHIFT", "SERENITY",
		"SENSATIONAL", "SENSATION", "SELFLESS", "SECURE", "SAFE", "ROUSING", "ROBUST", "RICH", "REWARDING", "REVOLUTIONIZE",
		"REVERED", "RESTORE", "RESPECT", "RESOURCES", "RESOUND", "RESOLUTION", "REPLENISHED", "RENOWNED", "RENEW",
		"REMARKABLE", "RELY", "RELAX", "REJUVENATE", "REJOICE", "REFRESH", "REFINEMENT", "RECOGNIZED", "REALIZE", "READY", "QUIET",
		"QUICK-MINDED", "QUICK", "QUEST", "PURPOSE", "PROUD", "PROTECT", "PROSPEROUS", "PROMINENT", "PROJECT", "PRODUCTIVE",
		"PRINCIPLE", "PRETTY", "PREPARED", "POWERFUL", "POSITIVE", "POPULAR", "POLISH", "POISE", "POETIC", "PLETHORA", "PLENTY",
		"PLENTEOUS", "PLEASURE", "PHENOMENON", "PERSISTENT", "PERFECT", "PERCEPTIVE", "PEACEFUL", "PEACE", "PASSIONATE", "PARTY",
		"PARADISE", "ORIGINAL", "OPTIMISTIC", "OPENHANDED", "OPEN", "NURTURE", "NOVEL", "NOURISH", "NATURE", "MOVING",
		"MOTIVATE", "MOMENT", "MODIFY", "MISSION", "MIRACLE", "METAMORPHOSIS", "MEND", "MEDITATE", "MEANINGFUL",
		"MASTER", "MARVELOUS", "MAKE", "MAJESTIC", "MAINTAIN", "MAGNANIMOUS", "LUMINOUS", "LUCRATIVE", "LUCIDITY", "LOYAL",
		"LOVELINESS", "LOVE", "LIVELY", "LEGENDARY", "LEARN", "LEADER", "LAUGH", "KNOW", "KISS", "KINDHEARTED",
		"KIND", "KEEN", "JUBILATION", "JOVIAL", "JOINED", "JAZZED", "INVENTIVE", "INTUITIVE", "INTELLIGENT", "INTELLECTUAL",
		"INSTINCT", "INSTANTANEOUS", "INSPIRE", "INNOVATE", "INNATE", "INGENIOUS", "INDEPENDENT", "INCREASE", "INCOMPARABLE",
		"IMPECCABLE", "IMMENSE", "IMMACULATE", "IMAGINATIVE", "IDEAL", "HONORED", "HONEST", "HOLY", "HIGHEST", "HERE",
		"HELPFUL", "HEAVENLY", "HEART", "HEALTHY", "HEALED", "HARMONY", "HAPPY", "HANDSOME", "GUTSY", "GROW", "GRIN",
		"GREGARIOUS", "GREEN", "GRATITUDE", "GRACIOUS", "GRACEFUL", "GRACE", "GORGEOUS", "GOOD", "GLOW", "GLAD", "GIVE", "GIFTED",
		"GENUINE", "GENIUS", "GENEROUS", "GATHER", "FUNNY", "FULL", "FRIENDLY", "FREEDOM", "FORTUNATE", "FOLLOW", "FLOURISH",
		"FLEXIBLE", "FEAT", "FASCINATING", "FAMOUS", "FAMILY", "FAITH", "EXULTANT", "EXTRAORDINARY", "EXQUISITE", "EXPRESSIVE",
		"EXPRESS", "EXPLORE", "EXPAND", "EXHILARATING", "EXCITED", "EVERYONE", "ESTEEM", "ESTABLISHED", "ESSENCE",
		"EQUITABLE", "ENTIRELY", "ENTHUSE", "ENTERTAINING", "ENORMOUSLY", "ENJOY", "ENGAGING", "ENERGY", "ENERGETIC", "ENDORSE",
		"ENCOURAGE", "ENCOMPASSING", "EMPATHETIC", "EMBRACE", "ELOQUENT", "ELEGANCE", "ELECTRIFYING", "EFFORTLESS", "EFFICIENT",
		"EFFERVESCENT", "EFFECTIVE", "ECSTASY", "EASY", "EARNEST", "EAGER", "EACH", "DOUBT", "DONATE", "DIVINE", "DISTINGUISHED",
		"DISCOVER", "DISCIPLINED", "DIRECT", "DETERMINED", "DESERVING", "DELIGHT", "DEDICATED", "DAZZLING", "CUTE",
		"CURE", "CULTIVATE", "CUDDLE", "CREATE", "COURTEOUS", "COURAGEOUS", "COUPLED", "CORE", "COPIOUS", "CONVICTION", "CONTENT",
		"CONSTANT", "CONSCIOUS", "CONNECT", "CONGENIAL", "CONFIDENT", "COMRADESHIP", "COMPLETE", "COMPASSIONATE",
		"COMPANIONSHIP", "COMMEND", "COMFORTABLE", "CLOSENESS", "CLEVER", "CLEAN", "CLASSY", "CLARITY", "CHOOSE", "CHERISH",
		"CHEERFUL", "CHARMING", "CHARITABLE", "CHARISMATIC", "CHARACTER", "CHANGE", "CERTAIN", "CELEBRATE", "CARING", "CAPTIVATING",
		"CALM", "BURGEON", "BUNCH", "BUBBLY", "BRILLIANT", "BRIGHT", "BRAVE", "BOUNTY", "BOLD", "BLOOM", "BLISS", "BLESSED",
		"BIGHEARTED", "BENEVOLENT", "BENEFIT", "BELIEVE", "BEAUTIFUL", "BEAMING", "BASIC", "AUTHENTIC", "ATTRACTIVE", "ATTENTIVE",
		"ASTUTE", "ASTOUNDING", "ASTONISH", "ASSERTIVE", "ARTISTIC", "ARTICULATE", "APTITUDE", "APPROVE", "APPRECIATION", "ANSWER",
		"ANIMATED", "AMUSING", "AMITY", "AMAZE", "ALTER", "ALLOW", "ALLIANCE", "ALIVE", "AIRY", "AGREE", "AFFLUENT", "AFFIRMATIVE",
		"AFFIRM", "ADVENTURE", "ADORED", "ADOPT", "ADMIRE", "ADJUST", "ACUMEN", "ACTIVE", "ACHIEVEMENT", "ACCOMPLISHED", "ACCLAIMED",
		"ACCEPT", "ABUNDANT", "ABSOLUTELY"
};

	private final Direction[] mDirections = Direction.values();
	private boolean[][] mLock;
	private int[] mRandomIndexes;

	private char[][] mBoard;
	private Set<Word> mSolution = new HashSet<Word>();
	private Set<Word> mFoundWords = new HashSet<Word>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initUI();
	}

	private void initUI() {
		mWordsearchGridView = (WordsearchGridView) this
				.findViewById(R.id.grd_wordsearch);
		mWordsearchGridView.setOnWordSelectedListener(this);

		mColumns = mWordsearchGridView.getNumColumns();
		mRows = mWordsearchGridView.getNumRows();
		mBoard = new char[mRows][mColumns];
		mLock = new boolean[mRows][mColumns];

		resetBoard();
		generateBoard();

		initBoard();

		AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
		anim.setDuration(500);
		mWordsearchGridView.startAnimation(anim);
	}

	private void resetBoard() {
		mFoundWords.clear();
		mSolution.clear();
		mWordsearchGridView.reset();

		for (int i = 0; i < mLock.length; i++) {
			for (int j = 0; j < mLock[i].length; j++) {
				mLock[i][j] = false;
			}
		}

		for (int i = 0; i < mBoard.length; i++) {
			for (int j = 0; j < mBoard[i].length; j++) {
				mBoard[i][j] = 'Z';
			}
		}

		Random rand = new Random(System.currentTimeMillis());
		for (int i = mWordList.length - 1; i >= 1; i--) {
			int randIndex = rand.nextInt(i);
			String word = mWordList[i];
			mWordList[i] = mWordList[randIndex];
			mWordList[randIndex] = word;
		}

	}

	private void generateBoard() {
		mRandomIndexes = new int[mRows * mColumns];

		Random rand = new Random(System.currentTimeMillis());
		for (int i = 0; i < mRandomIndexes.length; i++) {
			mRandomIndexes[i] = i;
		}

		for (int i = mRandomIndexes.length - 1; i >= 1; i--) {
			int randIndex = rand.nextInt(i);
			int realIndex = mRandomIndexes[i];
			mRandomIndexes[i] = mRandomIndexes[randIndex];
			mRandomIndexes[randIndex] = realIndex;
		}

		for (int i = mWordList.length - 1; i >= 1; i--) {
			int randIndex = rand.nextInt(i);
			String word = mWordList[i];
			mWordList[i] = mWordList[randIndex];
			mWordList[randIndex] = word;
		}

		for (String word : mWordList) {
			addWord(word);
		}

	}

	private Word addWord(String word) {
		if (word.length() > mColumns && word.length() > mRows) {
			return null;
		}

		Random rand = new Random();
		for (int i = mDirections.length - 1; i >= 1; i--) {
			int randIndex = rand.nextInt(i);
			Direction direction = mDirections[i];
			mDirections[i] = mDirections[randIndex];
			mDirections[randIndex] = direction;
		}

		Direction bestDirection = null;
		int bestRow = -1;
		int bestCol = -1;
		int bestScore = -1;
		for (int index : mRandomIndexes) {
			int row = index / mColumns;
			int col = index % mColumns;
			for (Direction direction : mDirections) {
				int score = canFit(word, direction, row, col);
				if (score > bestScore) {
					bestRow = row;
					bestCol = col;
					bestDirection = direction;
					bestScore = score;
				}
			}
		}
		if (bestScore >= 0) {
			Word result = new Word(word, bestRow, bestCol, bestDirection);
			placeWord(result);
			return result;
		}

		return null;
	}

	/**
	 * Places the provided word in the puzzle at the given location. This
	 * assumes that tests have already been performed to check if the word will
	 * fit. Returns a representation of the word in the puzzle at the
	 * appropriate location.
	 */
	private void placeWord(Word word) {
		int curRow = word.getRow();
		int curCol = word.getCol();
		final String wordStr = word.getWord();
		final Direction direction = word.getDirection();
		for (int i = 0; i < wordStr.length(); i++) {
			char c = wordStr.charAt(i);

			mBoard[curRow][curCol] = c;
			mLock[curRow][curCol] = true;

			if (direction.isUp()) {
				curRow -= 1;
			} else if (direction.isDown()) {
				curRow += 1;
			}

			if (direction.isLeft()) {
				curCol -= 1;
			} else if (direction.isRight()) {
				curCol += 1;
			}
		}

		mSolution.add(word);
	}

	/**
	 * Determines if a given word can be placed at a given location in a
	 * particular direction. This returns a count of the number of characters
	 * shared with other words on the board. This is used for difficulty
	 * weighting
	 */
	private int canFit(String word, Direction direction, int row, int col) {
		if (getAvailableSpace(direction, row, col) < word.length()) {
			return -1;
		}

		int score = 0;
		int curRow = row;
		int curCol = col;
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);

			if (mLock[curRow][curCol] && mBoard[curRow][curCol] != c) {
				return -1;
			} else if (mLock[curRow][curCol]) {
				score++;
			}

			if (direction.isUp()) {
				curRow -= 1;
			} else if (direction.isDown()) {
				curRow += 1;
			}

			if (direction.isLeft()) {
				curCol -= 1;
			} else if (direction.isRight()) {
				curCol += 1;
			}

		}

		return score;
	}

	/**
	 * @return The maximum possible length of a word placed at the given
	 *         location in a given direction.
	 */
	private int getAvailableSpace(Direction direction, int row, int col) {
		switch (direction) {
		case DOWN:
			return mRows - row;
		case DOWN_LEFT:
			return Math.min(mRows - row, col);
		case DOWN_RIGHT:
			return Math.min(mRows - row, mColumns - col);
		case LEFT:
			return col;
		case RIGHT:
			return mColumns - col;
		case UP:
			return row;
		case UP_LEFT:
			return Math.min(row, col);
		case UP_RIGHT:
			return Math.min(row, mColumns - col);

		}

		return 0;
	}

	@Override
	public void onWordSelected(List<Integer> positions) {
		// TODO Auto-generated method stub
		int firstPos = positions.get(0);
		int lastPos = positions.get(positions.size() - 1);
		StringBuilder forwardWord = new StringBuilder();
		StringBuilder reverseWord = new StringBuilder();
		for (Integer position : positions) {
			int row = position / mColumns;
			int col = position % mColumns;
			char c = mBoard[row][col];
			forwardWord.append(c);
			reverseWord.insert(0, c);
		}

		for (Word word : mSolution) {
			int wordStart = (word.getRow() * mColumns) + word.getCol();
			if (wordStart != firstPos && wordStart != lastPos) {
				continue;
			}

			Word found = (word.getWord().equals(forwardWord.toString())) ? word
					: null;
			if (found == null) {
				found = (word.getWord().equals(reverseWord.toString())) ? word
						: null;
			}

			if (found != null) {
				mFoundWords.add(found);
				mWordsearchGridView.clearHint();
				mWordsearchGridView.wordFound(found);
				mWordAdapter.setWordFound(found);
				break;
			}
		}
		
		
		Toast.makeText(mContext,mFoundWords.size() +"  -  "+ mSolution.size(), 2000).show();

		if (mFoundWords.size() == mSolution.size()) {
			onPuzzleComplete();
		}
	}
	
	private void initBoard() {
		// TODO Auto-generated method stub
		mWordsearchGridView.setBoard(mBoard);

		List<Word> sortedWords = new ArrayList<Word>(mSolution);
		Collections.sort(sortedWords);
		mWordAdapter = new WordListAdapter(mContext, sortedWords);
		mWordAdapter.setWordsFound(mFoundWords);

		mGrvWord = (GridView) findViewById(R.id.grd_word_list);
		mGrvWord.setAdapter(mWordAdapter);
		mGrvWord.setEnabled(false);
		mGrvWord.setFocusable(false);
	}

	private void onPuzzleComplete() { 
		AlphaAnimation fadeOut = new AlphaAnimation(1.0f, 0.0f);
		fadeOut.setFillAfter(true);
		fadeOut.setDuration(500);
		fadeOut.setAnimationListener(new AnimationListener() {

			public void onAnimationStart(Animation animation) {
			}

			public void onAnimationRepeat(Animation animation) {
			}

			public void onAnimationEnd(Animation animation) {
				resetBoard();
				generateBoard();
				initBoard();
				AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
				anim.setDuration(500);
				mWordsearchGridView.startAnimation(anim);
			}

			
		});
		mWordsearchGridView.startAnimation(fadeOut);

		fadeOut = new AlphaAnimation(1.0f, 0.0f);
		fadeOut.setFillAfter(true);
		fadeOut.setDuration(500);
		fadeOut.setAnimationListener(new AnimationListener() {

			public void onAnimationStart(Animation animation) {
			}

			public void onAnimationRepeat(Animation animation) {
			}

			public void onAnimationEnd(Animation animation) {
				mGrvWord.startLayoutAnimation();
			}
		}); 

		Toast.makeText(mContext, "Puzzle complete, well done!", Toast.LENGTH_SHORT).show();
	}
}
