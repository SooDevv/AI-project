# Bag of Words Meets Bags of Popcorn
> IMDB Dataset(긍정과 부정 리뷰가 섞여있는 100,000만개의 데이터)을 사용하여 감정분석
## NLP
>인간이 발화하는 언어 현상을 기계적으로 분석해서 컴퓨터가 이해할 수 있는 형태로 만들고 그러한 형태를 다시 인간이 이해할 수 있는 언어로 표현하는 기술

## Data set
* IMDB Dataset(긍정과 부정 리뷰가 섞여있는 100,000만개의 데이터)

## What I Learn
* Word2Vec을 사용하여 모델을 학습시키는 방법과 감정분석에 단어 벡터를 사용하는 방법
* K-means 알고리즘을 사용해 군집
* 평가
  - **ROC 커브**(Receiver-Operating Characteristic curve)
    * TPR 과 FPR을 각각 X,Y축으로 놓은 그래프
    * ROC커브의 면적이 1에 가까울 수록 좋은 성능
  - TPR(민감도)
    * 1인 케이스에 대해 1로 예측한 비율
    * 암환자를 진찰해서 암이라고 진단함
  - FPR(특이도)
    * 0인 케이스에 대해 1로 잘못 예측한 비율
    * 암환자가 아닌데 암이라고 진단
* Google's Word2Vec
  - 단어의 의미와 관계를 이해하는 데 도움
  - 감정분석에 활용
* BOW
  - 하나의 토큰만 사용
  - 말뭉치에서 얼마나 많이 나타나는지 즉, 구조와 상관없이 단어의 출현횟수만 센다
  - 단점) 단어의 순서가 완전히 무시 --> n-gram 으로 보완(n개의 토큰을 사용)

## Part1

1. Data Load with Pandas
```
train = pd.read_csv('data/labeledTrainData.tsv', header=0, delimiter='\t', quoting=3)
```

2.

### Bag Of Word Model

1. John likes to watch movies. Mary likes movies too.
2. John also likes to watch football games.

위 두 문장을 토큰화하여 가방에 담고 <br>
[John, likes, to, watch, movies, Mary, too, also, football, games] <br>
각 문장과 가방을 mapping 하여 단어의 개수를 세어준다. <br>

1. [1, 2, 1, 1, 2, 1, 1, 0, 0, 0]
2. [1, 1, 1, 1, 0, 0, 0, 1, 1, 1]

**n-gram** <br>
bigram으로 하면 아래와 같은 가방이 나온다. <br>
[John likes, likes to, to watch, watch movies, Mary likes, likes movies, movies too] <br>
token 이 몇 개인지 세어서 벡터를 생성 --> CountVectorize

- scikit learn의 CountVectorizer
  * 정규표현식을 사용해 토큰을 추출
  * 모두 소문자로 알아서 변환
  * 의미없는 특성을 많이 생성하기 때문에 적어도 두 개의 문서에 나타난 토큰만 사용
  * min_df로 토큰이 나타날 최소 문서 개수 지정 가능

### 긍정/부정 예측
**RandomForest**
- 분류/회귀 등에 쓰이는 앙상블 학습 방법의 일종
- 다수의 결정 트리로부터 부류 또는 평균 예측치를 출력함으로써 동작
- 지도학
