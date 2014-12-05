package com.gtotek.wordsearch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.gtotek.adapter.WordAdapter;
import com.gtotek.dao.WordDAO;
import com.gtotek.entity.CategoryEntity;
import com.gtotek.util.SharedPreferencesUtil;
import com.gtotek.wordsearch.WordsearchGridView.OnWordSelectedListener;

public class MainActivity extends Activity implements OnWordSelectedListener {
	private static String[] mWordList = { "YES", "WONDROUS", "WONDERFUL",
		"WONDER", "WITH", "WILLING", "WHOLE", "WELL", "WEALTHY", "VOYAGE",
		"VIVACIOUS", "VITAL", "VISUALIZE", "VISION", "VIGOROUS", "VICTORY",
		"VIBRANT", "VENERATED", "VARY", "VALUED", "UPBEAT", "UNWAVERING",
		"UNUSUAL", "UNITY", "UNIQUE", "UNDERSTANDING", "TRUTH", "TRUSTFUL",
		"TRIUMPH", "TRANSFORM", "TRANQUIL", "TOGETHER", "TODAY", "TIED",
		"THRIVE", "THOROUGH", "THIS", "THERAPEUTIC", "THANKFUL", "TEAM",
		"TAKE", "SYNCHRONIZED", "SUSTAIN", "SURE", "SUPPORT", "SUNNY",
		"SUCCESS", "STYLE", "STUPENDOUS", "STUNNING", "STRONG", "STIRRING",
		"STIR", "STEADY", "STABLE", "SPONTANEOUS", "SPLENDID", "SPIRIT",
		"SPARKLING", "SOUL", "SOLUTION", "SMOOTH", "SMILE", "SMART",
		"SINCERE", "SIMPLE", "SILENCE", "SHOWN", "SHINE", "SHIFT",
		"SERENITY", "SENSATIONAL", "SENSATION", "SELFLESS", "SECURE",
		"SAFE", "ROUSING", "ROBUST", "RICH", "REWARDING", "REVOLUTIONIZE",
		"REVERED", "RESTORE", "RESPECT", "RESOURCES", "RESOUND",
		"RESOLUTION", "REPLENISHED", "RENOWNED", "RENEW", "REMARKABLE",
		"RELY", "RELAX", "REJUVENATE", "REJOICE", "REFRESH", "REFINEMENT",
		"RECOGNIZED", "REALIZE", "READY", "QUIET", "QUICK-MINDED", "QUICK",
		"QUEST", "PURPOSE", "PROUD", "PROTECT", "PROSPEROUS", "PROMINENT",
		"PROJECT", "PRODUCTIVE", "PRINCIPLE", "PRETTY", "PREPARED",
		"POWERFUL", "POSITIVE", "POPULAR", "POLISH", "POISE", "POETIC",
		"PLETHORA", "PLENTY", "PLENTEOUS", "PLEASURE", "PHENOMENON",
		"PERSISTENT", "PERFECT", "PERCEPTIVE", "PEACEFUL", "PEACE",
		"PASSIONATE", "PARTY", "PARADISE", "ORIGINAL", "OPTIMISTIC",
		"OPENHANDED", "OPEN", "NURTURE", "NOVEL", "NOURISH", "NATURE",
		"MOVING", "MOTIVATE", "MOMENT", "MODIFY", "MISSION", "MIRACLE",
		"METAMORPHOSIS", "MEND", "MEDITATE", "MEANINGFUL", "MASTER",
		"MARVELOUS", "MAKE", "MAJESTIC", "MAINTAIN", "MAGNANIMOUS",
		"LUMINOUS", "LUCRATIVE", "LUCIDITY", "LOYAL", "LOVELINESS", "LOVE",
		"LIVELY", "LEGENDARY", "LEARN", "LEADER", "LAUGH", "KNOW", "KISS",
		"KINDHEARTED", "KIND", "KEEN", "JUBILATION", "JOVIAL", "JOINED",
		"JAZZED", "INVENTIVE", "INTUITIVE", "INTELLIGENT", "INTELLECTUAL",
		"INSTINCT", "INSTANTANEOUS", "INSPIRE", "INNOVATE", "INNATE",
		"INGENIOUS", "INDEPENDENT", "INCREASE", "INCOMPARABLE",
		"IMPECCABLE", "IMMENSE", "IMMACULATE", "IMAGINATIVE", "IDEAL",
		"HONORED", "HONEST", "HOLY", "HIGHEST", "HERE", "HELPFUL",
		"HEAVENLY", "HEART", "HEALTHY", "HEALED", "HARMONY", "HAPPY",
		"HANDSOME", "GUTSY", "GROW", "GRIN", "GREGARIOUS", "GREEN",
		"GRATITUDE", "GRACIOUS", "GRACEFUL", "GRACE", "GORGEOUS", "GOOD",
		"GLOW", "GLAD", "GIVE", "GIFTED", "GENUINE", "GENIUS", "GENEROUS",
		"GATHER", "FUNNY", "FULL", "FRIENDLY", "FREEDOM", "FORTUNATE",
		"FOLLOW", "FLOURISH", "FLEXIBLE", "FEAT", "FASCINATING", "FAMOUS",
		"FAMILY", "FAITH", "EXULTANT", "EXTRAORDINARY", "EXQUISITE",
		"EXPRESSIVE", "EXPRESS", "EXPLORE", "EXPAND", "EXHILARATING",
		"EXCITED", "EVERYONE", "ESTEEM", "ESTABLISHED", "ESSENCE",
		"EQUITABLE", "ENTIRELY", "ENTHUSE", "ENTERTAINING", "ENORMOUSLY",
		"ENJOY", "ENGAGING", "ENERGY", "ENERGETIC", "ENDORSE", "ENCOURAGE",
		"ENCOMPASSING", "EMPATHETIC", "EMBRACE", "ELOQUENT", "ELEGANCE",
		"ELECTRIFYING", "EFFORTLESS", "EFFICIENT", "EFFERVESCENT",
		"EFFECTIVE", "ECSTASY", "EASY", "EARNEST", "EAGER", "EACH",
		"DOUBT", "DONATE", "DIVINE", "DISTINGUISHED", "DISCOVER",
		"DISCIPLINED", "DIRECT", "DETERMINED", "DESERVING", "DELIGHT",
		"DEDICATED", "DAZZLING", "CUTE", "CURE", "CULTIVATE", "CUDDLE",
		"CREATE", "COURTEOUS", "COURAGEOUS", "COUPLED", "CORE", "COPIOUS",
		"CONVICTION", "CONTENT", "CONSTANT", "CONSCIOUS", "CONNECT",
		"CONGENIAL", "CONFIDENT", "COMRADESHIP", "COMPLETE",
		"COMPASSIONATE", "COMPANIONSHIP", "COMMEND", "COMFORTABLE",
		"CLOSENESS", "CLEVER", "CLEAN", "CLASSY", "CLARITY", "CHOOSE",
		"CHERISH", "CHEERFUL", "CHARMING", "CHARITABLE", "CHARISMATIC",
		"CHARACTER", "CHANGE", "CERTAIN", "CELEBRATE", "CARING",
		"CAPTIVATING", "CALM", "BURGEON", "BUNCH", "BUBBLY", "BRILLIANT",
		"BRIGHT", "BRAVE", "BOUNTY", "BOLD", "BLOOM", "BLISS", "BLESSED",
		"BIGHEARTED", "BENEVOLENT", "BENEFIT", "BELIEVE", "BEAUTIFUL",
		"BEAMING", "BASIC", "AUTHENTIC", "ATTRACTIVE", "ATTENTIVE",
		"ASTUTE", "ASTOUNDING", "ASTONISH", "ASSERTIVE", "ARTISTIC",
		"ARTICULATE", "APTITUDE", "APPROVE", "APPRECIATION", "ANSWER",
		"ANIMATED", "AMUSING", "AMITY", "AMAZE", "ALTER", "ALLOW",
		"ALLIANCE", "ALIVE", "AIRY", "AGREE", "AFFLUENT", "AFFIRMATIVE",
		"AFFIRM", "ADVENTURE", "ADORED", "ADOPT", "ADMIRE", "ADJUST",
		"ACUMEN", "ACTIVE", "ACHIEVEMENT", "ACCOMPLISHED", "ACCLAIMED",
		"ACCEPT", "ABUNDANT", "ABSOLUTELY" };
	
	private static final int BACK_TO_HOME = 2;
	private static final int GAME_OVER = 1;
	private static final int GAME_COMPLETE = 3;
	
	private static final String KEY_SCORE = "score";
	
	private static int scoreMax = 2000;
	
	private int myScore = 0;
	
	private int highScores = 0;
	
	private Context mContext = this;

	private WordDAO mWordDAO;

	private WordsearchGridView mWordsearchGridView;
	private GridView mGrvWord;
	private WordAdapter mWordAdapter;
	
	private ProgressBar prgbTime;
	private TextView tvScore;

	private int mRows = 8;
	private int mColumns = 8;
	
	private int timeMax = 900000;
	
	private int timeCount = 0;
	
	private String TAG = "MainActivity";

	private final Direction[] mDirections = Direction.values();
	private boolean[][] mLock;
	private int[] mRandomIndexes;

	private char[][] mBoard;
	private Set<Word> mSolution = new HashSet<Word>();
	private Set<Word> mFoundWords = new HashSet<Word>();
	
	private boolean mActive = false;
	private Handler mHander;
	
	private boolean isBack = false;
	private boolean isComplete = false;

	private SharedPreferencesUtil sharedPreferencesUtil;

	private TextView tvHighScore;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		sharedPreferencesUtil = new SharedPreferencesUtil(mContext, KEY_SCORE);
		
		highScores = sharedPreferencesUtil.getInt(KEY_SCORE, 0);
		
		initHanlder();
		initUI();
		startTimeCount();
	}

	private void initUI() {
		Bundle bundle = getIntent().getExtras();
		CategoryEntity categoryEntity = (CategoryEntity)bundle
				.getSerializable(Define.KEY_CATEGORY);
		mWordDAO = new WordDAO(mContext);

		mWordList = mWordDAO.getRndWordEntity(categoryEntity.getId());

		prgbTime = (ProgressBar)this.findViewById(R.id.prgbTime);
		tvScore = (TextView)this.findViewById(R.id.tvScore);
		tvHighScore = (TextView)this.findViewById(R.id.tvHighScore);
		
		tvHighScore.setText(""+highScores);
		
		tvScore.setText(""+myScore);
		prgbTime.setProgress(100);
		
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

		if (mFoundWords.size() == mSolution.size()) {
			onPuzzleComplete();
		}
	}

	private void initBoard() {
		// TODO Auto-generated method stub
		mWordsearchGridView.setBoard(mBoard);

		List<Word> sortedWords = new ArrayList<Word>(mSolution);
		Collections.sort(sortedWords);
		mWordAdapter = new WordAdapter(mContext, sortedWords);
		mWordAdapter.setWordsFound(mFoundWords);

		mGrvWord = (GridView) findViewById(R.id.grd_word_list);
		mGrvWord.setAdapter(mWordAdapter);
	 
	}

	private void onPuzzleComplete() {
		mHander.sendEmptyMessage(GAME_COMPLETE);
	}
	
	private final Runnable mRunnable = new Runnable() {
		public void run() {
			if (mActive) {
				if (timeCount < timeMax) {
					timeCount = timeCount + 1000;

					int percent =100 - ((timeCount * 100) / timeMax);
					
					if (percent % 2 == 0) {
						prgbTime.setProgress(percent);

					}
					mHander.postDelayed(mRunnable, 1000);
				}else{
					mHander.sendEmptyMessage(GAME_OVER);
				}
			}else{
				if(!isBack){
					mHander.sendEmptyMessage(1);
				}
			}
		}
	};

	private void startTimeCount() {
		mActive = true;
		if(mHander == null){
			initHanlder();
		}
		mHander.post(mRunnable);
	}
	
	private void initHanlder(){
		mHander = new Handler(){
			@Override
			public void handleMessage(Message msg) {
				int whatup = msg.what;
				
				switch (whatup) {
					case GAME_OVER:
						showDialog("Game Over", "Time to play this screen has expired!\nDo you want to play again?", "Back to Home", "Re-Play",new OnClickListener() {
							@Override
							public void onClick(View v) {
								onBackPressed();
							}
						},new OnClickListener() {
							
							@Override
							public void onClick(View v) {
								dismissDialog();
								
								myScore = 0;
								timeCount = 0;
								prgbTime.setProgress(100);
								tvScore.setText(""+myScore);
								resetNewGame();
								startTimeCount();
							}
						});
						break;
					case GAME_COMPLETE:
						mActive = false;
						highScores = sharedPreferencesUtil.getInt(KEY_SCORE, 0);
						int countComple = (scoreMax *prgbTime.getProgress())/100;
						
						myScore = myScore + countComple;
						
						if(myScore > highScores){
							sharedPreferencesUtil.put(KEY_SCORE, myScore);
							
							tvHighScore.setText(""+myScore);
						}
						
						tvScore.setText(""+myScore);
						
						prgbTime.setProgress(100);
						timeCount = 0;
						startTimeCount();
						
						resetNewGame();
						
						
						Toast.makeText(mContext, "Puzzle complete, well done!",
								Toast.LENGTH_SHORT).show();
						break;
					default:
						break;
				}
				
				Log.d(TAG, "handleMessage Stop::::" + whatup);
				
				super.handleMessage(msg);
			}
		};
	}

	private Dialog dialog;
	private void showDialog(String strTitle,String strContent,String strLeft,String strRight,OnClickListener onClickListenerLeft,OnClickListener onClickListenerRight){
		dialog = new Dialog(mContext);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.dialog_layout);
		
		dialog.setCanceledOnTouchOutside(false);
		
		dialog.setOnCancelListener(new OnCancelListener() {
			@Override
			public void onCancel(DialogInterface arg0) {
				dialog.dismiss();
			}
		});
		
		TextView tvTitle = (TextView)dialog.findViewById(R.id.tvTitle);
		TextView tvContent = (TextView)dialog.findViewById(R.id.tvContent);
		TextView tvLeft = (TextView)dialog.findViewById(R.id.tvLeft);
		TextView tvRight = (TextView)dialog.findViewById(R.id.tvRight);
		
		tvTitle.setText(strTitle);
		tvContent.setText(strContent);
		tvLeft.setText(strLeft);
		tvRight.setText(strRight);
		
		tvLeft.setOnClickListener(onClickListenerLeft);
		
		tvRight.setOnClickListener(onClickListenerRight);
		
		dialog.show();
	}
	
	private void resetNewGame(){
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
	}
	
	private void dismissDialog(){
		if(dialog != null && dialog.isShowing()){
			dialog.dismiss();
		}
	}
	
	@Override
	public void onBackPressed() {
		isBack = true;
		mActive = false;
		showDialog("Game Note", "Do you want to cancel Game to go back to it?", "Yes", "No",new OnClickListener() {
			@Override
			public void onClick(View v) {
				MainActivity.this.finish();
			}
		},new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
				startTimeCount();
				
//				timeCount = 0;
//				prgbTime.setProgress(100);
//				resetNewGame();
//				startTimeCount();
			}
		});
//		super.onBackPressed();
		
	}
}
